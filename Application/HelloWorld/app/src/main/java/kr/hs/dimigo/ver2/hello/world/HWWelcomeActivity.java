package kr.hs.dimigo.ver2.hello.world;

import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.ConfigurationInfo;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import kr.hs.dimigo.ver2.hello.world.particle.HWParticleRenderer;

/**
 * Created by dsa28s on 2016. 9. 3..
 */

public class HWWelcomeActivity extends AppCompatActivity {

    private GLSurfaceView mSurfaceView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hw_welcome_activity);

        mSurfaceView = (GLSurfaceView)findViewById(R.id.hw_welcome_activity_GLSURFACEVIEW);

        if(checkSupportedOpenGLSupported()) {
            mSurfaceView.setEGLContextClientVersion(2);

            HWParticleRenderer mParticleRenderer = new HWParticleRenderer(mSurfaceView);
            mSurfaceView.setRenderer(mParticleRenderer);
            mSurfaceView.setRenderMode(GLSurfaceView.RENDERMODE_CONTINUOUSLY);
        }
    }

    private boolean checkSupportedOpenGLSupported() {
        ActivityManager mManager = (ActivityManager)getSystemService(Context.ACTIVITY_SERVICE);
        ConfigurationInfo mInfo = mManager.getDeviceConfigurationInfo();

        return mInfo.reqGlEsVersion >= 0x2000;
    }
}
