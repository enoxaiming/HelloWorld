package kr.hs.dimigo.ver2.hello.world;

import android.app.Application;
import android.content.Context;

/**
 * Created by dsa28s on 2016. 9. 3..
 */

public class HWApplication extends Application {

    private static Context mContext;

    @Override
    public void onCreate() {
        mContext = getApplicationContext();

        super.onCreate();
    }

    public static Context getContext() {
        return mContext;
    }
}
