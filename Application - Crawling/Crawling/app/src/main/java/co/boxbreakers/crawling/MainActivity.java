package co.boxbreakers.crawling;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.squareup.picasso.Picasso;

import org.apache.commons.codec.net.URLCodec;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.UnsupportedCharsetException;
import java.util.Iterator;


public class MainActivity extends AppCompatActivity {

    private static String htmlPageUrl = "https://namu.wiki/w/";
    private static String ImageUrl = "http://www.geognos.com/api/en/countries/flag/GR.png";
    private TextView textviewHtmlDocument;
    private String htmlContentInStringFormat = "";
    private EditText editText;
    ImageView imageView;



    @Override
    protected void onCreate(Bundle savedInstanceState)   {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);





        textviewHtmlDocument = (TextView)findViewById(R.id.textView);
        textviewHtmlDocument.setMovementMethod(new ScrollingMovementMethod());
        editText = (EditText)findViewById(R.id.editText);
        imageView = (ImageView)findViewById(R.id.imageView2);


        Button htmlTitleButton = (Button)findViewById(R.id.button);
        htmlTitleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                URLCodec urlCodec = new URLCodec("UTF-8");
                byte[] encodedText = urlCodec.encode(editText.getText().toString().getBytes());
                htmlPageUrl = htmlPageUrl+new String(encodedText);
                Log.e("HTML",htmlPageUrl);
                Picasso.with(getApplicationContext()).load(ImageUrl).into(imageView);
                JsoupAsyncTask jsoupAsyncTask = new JsoupAsyncTask();
                jsoupAsyncTask.execute();

            }
        });
    }




    private class JsoupAsyncTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... params) {
            try {
                Document doc = Jsoup.connect(htmlPageUrl).get();
                Elements links = doc.select(".wiki-table tbody tr td");

                /*for(Element link : links) {
                    Iterator<Element> iterator = link.getAllElements().iterator();
                    //Log.e("HTML",links.html());
                    while(iterator.hasNext()){
                        Log.e("Logggg",iterator.next().text());
                    }
                }*/
                int i = 0;
                int j,k,l,m;
                String s[] = new String[links.size()+1];

                for (Element link : links) {
                    s[i++] = link.text().trim();
                }

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

                for(l = 0 ; l <= i ; l++) {
                    if(s[l].equals("화폐단위")) {
                        break;
                    }
                }


                htmlContentInStringFormat += (s[k+1] + " " + s[j+1] + " " + s[l+1]);

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
            textviewHtmlDocument.setText(htmlContentInStringFormat);
        }
    }



}

