package kr.hs.dimigo.ver2.hello.world.joystick;

import android.bluetooth.BluetoothSocket;
import android.os.Handler;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.io.InterruptedIOException;

/**
 * Created by dsa28s on 2016. 9. 3..
 */

public class HYBluetoothSocketListener implements Runnable {

    private BluetoothSocket socket;
    private Handler handler;

    private IJoysticDataReceiveListener mListener;

    public HYBluetoothSocketListener(BluetoothSocket socket,
                                   Handler handler) {
        this.socket = socket;
        this.handler = handler;

        try {
            socket.connect();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setOnJoysticDataReceiveListener(IJoysticDataReceiveListener l) {
        mListener = l;
    }

    public void run() {
        int bufferSize = 2048;
        byte[] buffer = new byte[bufferSize];
        try {
            String b = new String();
            int read = -1;
            final byte[] bytes = new byte[2048];
            while(true) {
                read = socket.getInputStream().read(bytes);

                for(int i = 0; i < read ; i++) {
                    if((char)bytes[i] == ';') {
                        //Log.w("BT_TAG", "found ';'");
                        //x1:0,
                        if(mListener != null) {
                            String s[] = b.split(",");
                            String s2[] = new String[10];
                            for(int j = 0; j < 7; j++) {
                                s2[j] = s[j].split(":")[1];
                            }

                            mListener.onReceivedDataFromJoystic(true, Integer.parseInt(s2[0]), Integer.parseInt(s2[1]), stringToBoolean(s2[2]), Integer.parseInt(s2[3]), Integer.parseInt(s2[4]), stringToBoolean(s2[5]), Integer.parseInt(s2[6]));
                        }

                        //Log.w("BT_TAG", b);
                        b = new String();
                        //str += "\n";
                        continue;
                    }
                    b += (char)bytes[i];
                }
            }
        } catch (IOException e) {
            mListener.onReceivedDataFromJoystic(false, 0, 0, false, 0, 0, false, 0);
            //Log.d("BLUETOOTH_COMMS", e.getMessage());
        }
    }

    private boolean stringToBoolean(String s) {
        if(s.equals("0")) {
            return false;
        } else {
            return true;
        }
    }
}