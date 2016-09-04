package kr.hs.dimigo.ver2.hello.world;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.opengl.GLSurfaceView;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Handler;
import android.provider.Settings;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import cn.refactor.lib.colordialog.PromptDialog;
import com.mousebird.maply.*;
import com.squareup.picasso.Picasso;
import kr.hs.dimigo.ver2.hello.world.joystick.HYBluetoothSocketListener;
import kr.hs.dimigo.ver2.hello.world.joystick.IJoysticDataReceiveListener;
import kr.hs.dimigo.ver2.hello.world.parsing.CountryService;
import kr.hs.dimigo.ver2.hello.world.parsing.Result;
import kr.hs.dimigo.ver2.hello.world.parsing.RootClass;
import kr.hs.dimigo.ver2.hello.world.particle.HWParticleRenderer;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import org.apache.commons.codec.net.URLCodec;
import org.apache.commons.io.input.NullInputStream;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;
import java.net.Authenticator;
import java.net.PasswordAuthentication;
import java.util.UUID;

public class HWMainActivity extends AppCompatActivity implements IJoysticDataReceiveListener {

    private static final int BLUETOOTH_RESULT = 1000;
    private static final UUID KNOWN_BT_UUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");

    public static final String API = "AIzaSyA-4eTj0NN2OIcjoFhoNNfg8cVSIsxSMdc";
    public static final String URL = "https://maps.googleapis.com/maps/api/geocode/";
    private static String htmlPageUrl = "https://namu.wiki/w/";
    private String htmlContentInStringFormat = "";
    private String mCountryName = "";
    private byte[] encodedText;

    private HWGlobeFragment mGlobeFragment;
    private GlobeController mGlobeControl;
    private double[] mCurrentPositionAxis = new double[3];

    private GLSurfaceView mSurfaceView;
    private RelativeLayout mConnectJoystickButton;
    private TextView mJoyStickText;
    private ImageView mTargetView;
    private TextView mTIPText;

    private BroadcastReceiver bluetoothReceiver;
    private IntentFilter filter;
    private boolean isBluetoothConnected = false;
    private String mBluetoothDeviceMacAddress = "";

    private BluetoothSocket mSocket = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hw_activity_main);

        mSurfaceView = (GLSurfaceView) findViewById(R.id.hw_activity_main_GLSURFACEVIEW);
        mConnectJoystickButton = (RelativeLayout) findViewById(R.id.hw_activity_main_CONNECT_JOYSTICK_CONTAINER);
        mTargetView = (ImageView) findViewById(R.id.hw_activity_main_TARGET_IMAGEVIEW);
        mJoyStickText = (TextView) findViewById(R.id.hw_activity_main_CONNECT_JOYSTIC_TEXT);
        mTIPText = (TextView) findViewById(R.id.hw_activity_main_TIP_TEXT);

        if (Build.VERSION.SDK_INT >= 21) {
            mConnectJoystickButton.setBackgroundResource(R.drawable.hw_ripple_square);
        }

        mConnectJoystickButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isBluetoothConnected) {
                    if (checkEnabledBluetooth() == 1) { //OFF
                        Intent mIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                        startActivityForResult(mIntent, BLUETOOTH_RESULT);
                    } else { //ON
                        Toast.makeText(HWMainActivity.this, getResources().getString(R.string.hw_bluetooth_goto_settings), Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(Settings.ACTION_BLUETOOTH_SETTINGS));
                    }
                }

            }
        });

        mSurfaceView.setEGLContextClientVersion(2);

        HWParticleRenderer mParticleRenderer = new HWParticleRenderer(mSurfaceView);
        mSurfaceView.setRenderer(mParticleRenderer);
        mSurfaceView.setRenderMode(GLSurfaceView.RENDERMODE_CONTINUOUSLY);

        mGlobeFragment = new HWGlobeFragment();
        FragmentTransaction mTransaction = getSupportFragmentManager().beginTransaction();
        mTransaction.replace(R.id.hw_activity_main_CONTAINER, mGlobeFragment, "globe");
        mTransaction.setCustomAnimations(R.anim.hw_slidedown, 0, R.anim.hw_slidedown, 0);
        mTransaction.commit();

        //mGlobeControl = mGlobeFragment.getGlobeControl();
    }

    public void setGlobeControl(GlobeController gc) {
        mGlobeControl = gc;

        //Log.e("TEST", (mGlobeControl == null) + "");
    }

    @Override
    protected void onResume() {
        super.onResume();

        bluetoothReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                String action = intent.getAction();
                BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);

                if (BluetoothDevice.ACTION_ACL_CONNECTED.equals(action)) {
                    mBluetoothDeviceMacAddress = device.getAddress();
                    isBluetoothConnected = true;
                    mJoyStickText.setText(getResources().getString(R.string.hw_bluetooth_joystick_connected));
                    if (Build.VERSION.SDK_INT >= 21) {
                        mConnectJoystickButton.setBackground(null);
                    }
                    mTargetView.setVisibility(View.VISIBLE);
                    mTIPText.setText(getResources().getString(R.string.hw_main_tip_ii_text));
                    readJoystickData();
                    //Log.e("TEST", device.getName().toString() +" Device Is Connected!");
                } else if (BluetoothDevice.ACTION_ACL_DISCONNECTED.equals(action)) {
                    isBluetoothConnected = false;
                    mJoyStickText.setText(getResources().getString(R.string.hw_connect_joystic));
                    if (Build.VERSION.SDK_INT >= 21) {
                        mConnectJoystickButton.setBackgroundResource(R.drawable.hw_ripple_square);
                    }
                    mTargetView.setVisibility(View.GONE);
                    mTIPText.setText(getResources().getString(R.string.hw_main_tip_i_text));
                    //Log.e("TEST", device.getName().toString() +" Device Is DISConnected!");
                } else if (BluetoothAdapter.ACTION_STATE_CHANGED.equals(action)) {
                    if (intent.getIntExtra(BluetoothAdapter.EXTRA_STATE, -1)
                            == BluetoothAdapter.STATE_OFF) {
                        isBluetoothConnected = false;
                        mJoyStickText.setText(getResources().getString(R.string.hw_connect_joystic));
                        if (Build.VERSION.SDK_INT >= 21) {
                            mConnectJoystickButton.setBackgroundResource(R.drawable.hw_ripple_square);
                        }
                        mTargetView.setVisibility(View.GONE);
                        mTIPText.setText(getResources().getString(R.string.hw_main_tip_i_text));
                    }
                }
            }
        };

        filter = new IntentFilter(BluetoothDevice.ACTION_ACL_CONNECTED);
        filter.addAction(BluetoothAdapter.ACTION_STATE_CHANGED);
        registerReceiver(bluetoothReceiver, filter);
    }

    private void readJoystickData() {
        BluetoothDevice mRemoteDevice = BluetoothAdapter.getDefaultAdapter().getRemoteDevice(mBluetoothDeviceMacAddress);
        Handler mHandler = new Handler();

        try {
            HYBluetoothSocketListener mSocketListener = new HYBluetoothSocketListener(mRemoteDevice.createRfcommSocketToServiceRecord(KNOWN_BT_UUID), mHandler);
            mSocketListener.setOnJoysticDataReceiveListener(this);
            Thread mThread  = new Thread(mSocketListener);
            mThread.start();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(bluetoothReceiver);
    }

    @Override
    protected void onPause() {
        super.onPause();
        //
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case BLUETOOTH_RESULT:
                if (resultCode == RESULT_OK) {
                    Toast.makeText(HWMainActivity.this, getResources().getString(R.string.hw_bluetooth_goto_settings), Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(Settings.ACTION_BLUETOOTH_SETTINGS));
                } else {

                }
                break;
        }
    }

    private int checkEnabledBluetooth() {
        BluetoothAdapter mAdapter = BluetoothAdapter.getDefaultAdapter();
        if (mAdapter == null) {
            new PromptDialog(this).setDialogType(PromptDialog.DIALOG_TYPE_WRONG)
                    .setTitleText(getResources().getString(R.string.hw_hardware_not_supported_TITLE)).setContentText(getResources().getString(R.string.hw_bluetooth_not_supported_SUMMARY))
                    .setPositiveListener(getResources().getString(R.string.hw_ok), new PromptDialog.OnPositiveListener() {
                        @Override
                        public void onClick(PromptDialog dialog) {
                            dialog.dismiss();
                        }
                    }).show();
            return 0;
        } else {
            if (!mAdapter.isEnabled()) {
                return 1;
            } else {
                return 2;
            }
        }
    }

    //1 : 좌표, 2 : 확대 축소, 버튼은 클릭으로
    @Override
    public void onReceivedDataFromJoystic(boolean isC, int x1, int y1, boolean b1, int x2, int y2, boolean b2, String uid) {
        if(isC) {
            Log.e("RFID", uid + "");

            try {
                mCurrentPositionAxis[0] = mGlobeControl.getPositionGeo().getX();
                mCurrentPositionAxis[1] = mGlobeControl.getPositionGeo().getY();
                mCurrentPositionAxis[2] = mGlobeControl.getPositionGeo().getZ();

                float mAxisX = 0.0001f, mAxisY = 0.0001f;
                float mDefaultSpeed = -0.005f;

                if(mGlobeControl.getGlobeView().getHeight() > 0.6) {
                    mDefaultSpeed = -0.005f;
                } else if(mGlobeControl.getGlobeView().getHeight() > 0.3) {
                    mDefaultSpeed = -0.003f;
                } else if(mGlobeControl.getGlobeView().getHeight() > 0.2) {
                    mDefaultSpeed = -0.002f;
                } else if(mGlobeControl.getGlobeView().getHeight() > 0.1) {
                    mDefaultSpeed = -0.001f;
                } else if(mGlobeControl.getGlobeView().getHeight() > 0) {
                    mDefaultSpeed = -0.0005f;
                }

                if(x1 < 0) {
                    mAxisX = Math.abs(mDefaultSpeed) * x1;
                } else {
                    mAxisX = Math.abs(mDefaultSpeed) * x1;
                }

                if(y1 < 0) {
                    mAxisY = mDefaultSpeed * y1;
                } else {
                    mAxisY = mDefaultSpeed * y1;
                }

                mGlobeControl.animatePositionGeo(mCurrentPositionAxis[0] - mAxisX, mCurrentPositionAxis[1] - mAxisY, mCurrentPositionAxis[2], 1);

                if(y2 < 0) {
                    zoomGlobe();
                } else if(y2 > 0){
                    pinchGlobe();
                }
                //mGlobeControl.setPositionGeo(mCurrentPositionAxis[0] - Math.min);

                if(b1 || b2) {
                    DisplayMetrics mMetrics = new DisplayMetrics();
                    getWindowManager().getDefaultDisplay().getMetrics(mMetrics);
                    mGlobeControl.processTap(new Point2d(mMetrics.widthPixels / 2, mMetrics.heightPixels / 2));
                }

                if(uid.equals("17918322203")) { //미국
                    mGlobeControl.animatePositionGeo(-1.696556806564331,0.6122363209724426,0.39117915970186656, 0.5);
                } else if(uid.equals("1177913199")) { //한국
                    mGlobeControl.animatePositionGeo(2.237851619720459, 0.6353013515472412, 0.5716807365417481, 0.5);
                } else if(uid.equals("2114912899")) { //이탈리아
                    mGlobeControl.animatePositionGeo(0.2218082845211029,0.7623533010482788,0.21061856718455968, 0.5);
                }
            } catch(Exception e) {

            }
        } else {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    isBluetoothConnected = false;
                    mJoyStickText.setText(getResources().getString(R.string.hw_connect_joystic));
                    if (Build.VERSION.SDK_INT >= 21) {
                        mConnectJoystickButton.setBackgroundResource(R.drawable.hw_ripple_square);
                    }
                    mTargetView.setVisibility(View.GONE);
                    mTIPText.setText(getResources().getString(R.string.hw_main_tip_i_text));
                }
            });

        }
    }

    private void zoomGlobe() {
        CoordSystemDisplayAdapter coordAdapter = mGlobeControl.getGlobeView().getCoordAdapter();
        Point2d frameSize = mGlobeControl.getViewSize();

        DisplayMetrics mMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(mMetrics);
        Point2d touch = new Point2d(mMetrics.widthPixels / 2, mMetrics.heightPixels / 2);
        Matrix4d mapTransform = mGlobeControl.getGlobeView().calcModelViewMatrix();
        Point3d pt = mGlobeControl.getGlobeView().pointOnSphereFromScreen(touch, mapTransform, frameSize, false);
        if (pt == null) {
            return;
        }

        Point3d localPt = coordAdapter.displayToLocal(pt);
        if (localPt == null) {
            return;
        }

        Point3d geoCoord = coordAdapter.getCoordSystem().localToGeographic(localPt);

        if (pt != null || geoCoord != null)
        {
            // Zoom in where they tapped
            double height = mGlobeControl.getGlobeView().getHeight();
            double newHeight = height/2.0;
            newHeight = Math.min(newHeight, 1000);
            newHeight = Math.max(newHeight, 0);

            // Note: This isn't right.  Need the
            Quaternion newQuat = mGlobeControl.getGlobeView().makeRotationToGeoCoord(geoCoord.getX(), geoCoord.getY(), mGlobeControl.getGlobeView().northUp);

            // Now kick off the animation
            mGlobeControl.getGlobeView().setAnimationDelegate(new GlobeAnimateRotation(mGlobeControl.getGlobeView(), mGlobeControl.renderWrapper.maplyRender, newQuat, newHeight, 0.5));
        }
    }

    private void pinchGlobe() {
        CoordSystemDisplayAdapter coordAdapter = mGlobeControl.getGlobeView().getCoordAdapter();
        Point2d frameSize = mGlobeControl.getViewSize();

        DisplayMetrics mMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(mMetrics);
        Point2d touch = new Point2d(mMetrics.widthPixels / 2, mMetrics.heightPixels / 2);
        Matrix4d mapTransform = mGlobeControl.getGlobeView().calcModelViewMatrix();
        Point3d pt = mGlobeControl.getGlobeView().pointOnSphereFromScreen(touch, mapTransform, frameSize, false);
        if (pt == null) {
            return;
        }

        Point3d localPt = coordAdapter.displayToLocal(pt);
        if (localPt == null) {
            return;
        }

        Point3d geoCoord = coordAdapter.getCoordSystem().localToGeographic(localPt);

        if (pt != null || geoCoord != null)
        {
            // Zoom in where they tapped
            double height = mGlobeControl.getGlobeView().getHeight();
            double newHeight = height*2.0;
            newHeight = Math.min(newHeight, 1000);
            newHeight = Math.max(newHeight, 0);

            // Note: This isn't right.  Need the
            Quaternion newQuat = mGlobeControl.getGlobeView().makeRotationToGeoCoord(geoCoord.getX(), geoCoord.getY(), mGlobeControl.getGlobeView().northUp);

            // Now kick off the animation
            mGlobeControl.getGlobeView().setAnimationDelegate(new GlobeAnimateRotation(mGlobeControl.getGlobeView(), mGlobeControl.renderWrapper.maplyRender, newQuat, newHeight, 0.5));
        }
    }

    public String getCounteryFromServer(double x, double y) {
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(new Interceptor() {
            @Override
            public okhttp3.Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                HttpUrl url = request.url().newBuilder().addQueryParameter("language","ko").build();
                request = request.newBuilder().url(url).build();
                return chain.proceed(request);
            }
        }).build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();

        CountryService countryService = retrofit.create(CountryService.class);

        Call<RootClass> call = countryService.getCountry(String.valueOf(y)+","+String.valueOf(x),API);

        call.enqueue(new Callback<RootClass>() {
            @Override
            public void onResponse(Call<RootClass> call, Response<RootClass> response) {
                //Log.e("Success", String.valueOf(response.isSuccessful()));
                try {
                    RootClass rootClass = response.body();
                    mCountryName = rootClass.getResults().getAddressComponents().getLongName();
                    String mShortCountryName = rootClass.getResults().getAddressComponents().getShortName();
                    Log.e("SHORT", mShortCountryName);
                    parsingCountryInformationFromServer(mCountryName);
                } catch(ArrayIndexOutOfBoundsException e) {

                }
            }

            @Override
            public void onFailure(Call<RootClass> call, Throwable t) {
                Log.e("TEST", t.getMessage());
            }
        });

        return mCountryName;
    }

    private void parsingCountryInformationFromServer(String name) {
        URLCodec urlCodec = new URLCodec("UTF-8");
        encodedText = urlCodec.encode(name.getBytes());
        htmlPageUrl = htmlPageUrl+new String(encodedText);
        //Picasso.with(getApplicationContext()).load(ImageUrl).into(imageView);
        JsoupAsyncTask jsoupAsyncTask = new JsoupAsyncTask();
        jsoupAsyncTask.execute();
    }

    private class JsoupAsyncTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... params) {
            try {
                Authenticator.setDefault(new Authenticator() {
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication("dimigo_ver02", "1234".toCharArray());
                    }
                });

                Document doc = Jsoup.connect(htmlPageUrl).get();
                Elements links = doc.select(".wiki-table tbody tr td");

                int i = 0;
                int j = 0,k = 0,l = 0,m = 0;
                String s[] = new String[links.size()+1];

                for (Element link : links) {
                    s[i++] = link.text().trim();
                    if(link.attr("style").contains("background-color:")) {
                        // s[i++] = link.text().trim();
                        htmlContentInStringFormat += "<b><big>" + s[i - 1] + "</big></b><br>";
                        //Log.e("TEST", s[i - 1]);
                    } else {
                        htmlContentInStringFormat += s[i - 1] + "<br>";
                    }
                }

                /*try {
                    for(k=0; k<=i; k++) {
                        if(s[k].equals("수도")) {
                            break;
                        }
                    }

                    for(j = 0 ; j <= i ; j++) {
                        if(s[j].equals("인구")) {
                            break;
                        }
                    }
                } catch(NullPointerException e) {

                }

                try {
                    for(l = 0 ; l <= i ; l++) {
                        if(s[l].equals("화폐단위")) {
                            break;
                        }
                    }

                    htmlContentInStringFormat += (s[k+1] + "\n\n" + s[j+1] + "\n\n" + s[l+1]);
                } catch(NullPointerException e) {
                    try {
                        htmlContentInStringFormat += (s[k+1] + "\n\n" + s[j+1]);
                    } catch(ArrayIndexOutOfBoundsException ew) {

                    }
                }*/




                /*
                for(Element link : links){
                    s[i++] = link.text().trim();
                    htmlContentInStringFormat += link.text().trim()+"\n";
                }

                int arraynum = 0;
                String[] item = new String[links.size()];

                for(Element link : links) {
                    item[arraynum++] = link.text().trim();
                }

                for(int i = 0; i<links.size() ; i++) {
                    if(item[i].equals("322,667,000명 (3위)")) {
                        Log.e("i",String.valueOf(i));
                    }
                }

                */
                //htmlContentInStringFormat += ( item[17] +"  " +  item[61]) ;


            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            new PromptDialog(HWMainActivity.this).setDialogType(PromptDialog.DIALOG_TYPE_INFO)
                    .setTitleText("나라 정보").setContentText(Html.fromHtml(htmlContentInStringFormat))
                    .setPositiveListener("이 나라에 대해 더 알고싶어요!", new PromptDialog.OnPositiveListener() {
                        @Override
                        public void onClick(PromptDialog dialog) {
                            Intent i = new Intent(Intent.ACTION_VIEW);
                            Uri u = Uri.parse("https://ko.wikipedia.org/wiki/" + new String(encodedText));
                            i.setData(u);
                            startActivity(i);
                        }
                    }).show();

            htmlPageUrl = "https://namu.wiki/w/";
            htmlContentInStringFormat = "";
        }
    }
}
