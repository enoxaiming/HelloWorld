package kr.hs.dimigo.ver2.hello.world.joystick;

import android.util.Log;

/**
 * Created by dsa28s on 2016. 9. 3..
 */

public class HYMessagePoster implements Runnable {

    private String message;

    public HYMessagePoster(String m) {
        message = m;
    }

    @Override
    public void run() {
        Log.e("Data", message);
    }
}
