package kr.hs.dimigo.ver2.hello.world;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.opengl.GLSurfaceView;
import android.os.Build;
import android.os.Handler;
import android.provider.Settings;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import cn.refactor.lib.colordialog.PromptDialog;
import com.mousebird.maply.*;
import kr.hs.dimigo.ver2.hello.world.joystick.HYBluetoothSocketListener;
import kr.hs.dimigo.ver2.hello.world.joystick.IJoysticDataReceiveListener;
import kr.hs.dimigo.ver2.hello.world.particle.HWParticleRenderer;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.UUID;

public class HWMainActivity extends AppCompatActivity implements IJoysticDataReceiveListener {

    private static final int BLUETOOTH_RESULT = 1000;
    private static final UUID KNOWN_BT_UUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");

    //public static final String API = "AIzaSyA-4eTj0NN2OIcjoFhoNNfg8cVSIsxSMdc";
    //public static final String URL = "https://maps.googleapis.com/maps/api/geocode/";

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
    public void onReceivedDataFromJoystic(boolean isC, int x1, int y1, boolean b1, int x2, int y2, boolean b2, int uid) {
        if(isC) {
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
                    mAxisX = mDefaultSpeed * x1;
                } else {
                    mAxisX = mDefaultSpeed * x1;
                }

                if(y1 < 0) {
                    mAxisY = Math.abs(mDefaultSpeed) * y1;
                } else {
                    mAxisY = Math.abs(mDefaultSpeed) * y1;
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
}
