package kr.hs.dimigo.ver2.hello.world.joystick;

import android.bluetooth.BluetoothSocket;

/**
 * Created by dsa28s on 2016. 9. 4..
 */

public interface IJoysticDataReceiveListener {
    void onReceivedDataFromJoystic(boolean isC, int x1, int y1, boolean b1, int x2, int y2, boolean b2, String uid);
}
