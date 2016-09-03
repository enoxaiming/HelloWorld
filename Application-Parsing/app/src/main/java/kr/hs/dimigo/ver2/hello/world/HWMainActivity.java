package kr.hs.dimigo.ver2.hello.world;

import android.provider.DocumentsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HWMainActivity extends AppCompatActivity {

    static final String API = "AIzaSyA-4eTj0NN2OIcjoFhoNNfg8cVSIsxSMdc";
    static final String URL = "https://maps.googleapis.com/maps/api/geocode/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hw_activity_main);
        Button button = (Button)findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getCountry();
            }
        });
    }

    private void getCountry() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        double x = 40.714224;
        double y = -73.961452;
        //String ct = "json?latlng"+String.valueOf(x)+","+String.valueOf(y)+"&key="+API;
        
        CountryService countryService = retrofit.create(CountryService.class);

        Call<RootClass> call = countryService.getCountry(String.valueOf(x)+","+String.valueOf(y),API);

        call.enqueue(new Callback<RootClass>() {
            @Override
            public void onResponse(Call<RootClass> call, Response<RootClass> response) {
                RootClass rootClass = response.body();
                System.out.println(rootClass.getResults().getAddressComponents().getLongName());
            }

            @Override
            public void onFailure(Call<RootClass> call, Throwable t) {

            }
        });



    }
}
