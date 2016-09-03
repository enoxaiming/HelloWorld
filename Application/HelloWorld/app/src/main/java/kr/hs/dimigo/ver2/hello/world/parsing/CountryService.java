package kr.hs.dimigo.ver2.hello.world.parsing;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

import static android.R.style.Widget;

/**
 * Created by JunHyeok on 2016. 9. 3..
 */

public interface CountryService {
    @GET("json")
    Call<RootClass> getCountry(@Query("latlng")String x,
                               @Query("key") String key);
}

