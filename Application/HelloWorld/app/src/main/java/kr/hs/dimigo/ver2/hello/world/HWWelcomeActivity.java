package kr.hs.dimigo.ver2.hello.world;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ConfigurationInfo;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;
import com.yalantis.starwars.TilesFrameLayout;
import com.yalantis.starwars.interfaces.TilesFrameLayoutListener;
import kr.hs.dimigo.ver2.hello.world.particle.HWParticleRenderer;

/**
 * Created by dsa28s on 2016. 9. 3..
 */

public class HWWelcomeActivity extends AppCompatActivity {

    //private TilesFrameLayout mTileLayout;
    private GLSurfaceView mSurfaceView;
    private FrameLayout mFrameLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hw_welcome_activity);

        //mTileLayout = (TilesFrameLayout)findViewById(R.id.hw_welcome_activity_TFL);
        mSurfaceView = (GLSurfaceView)findViewById(R.id.hw_welcome_activity_GLSURFACEVIEW);
        mFrameLayout = (FrameLayout)findViewById(R.id.hw_welcome_activity_FRAMELAYOUT);

        if(checkSupportedOpenGLSupported()) {
            mSurfaceView.setEGLContextClientVersion(2);

            HWParticleRenderer mParticleRenderer = new HWParticleRenderer(mSurfaceView);
            mSurfaceView.setRenderer(mParticleRenderer);
            mSurfaceView.setRenderMode(GLSurfaceView.RENDERMODE_CONTINUOUSLY);
        } else {
            //Not Supported
        }

        HWWelcomeFragment mWelcomeFragment = new HWWelcomeFragment();
        FragmentTransaction mTransaction = getSupportFragmentManager().beginTransaction();
        mTransaction.replace(R.id.hw_welcome_activity_FRAMELAYOUT, mWelcomeFragment, "welcome");
        mTransaction.setCustomAnimations(R.anim.hw_slidedown, 0, R.anim.hw_slidedown, 0);
        mTransaction.commit();
    }

    private boolean checkSupportedOpenGLSupported() {
        ActivityManager mManager = (ActivityManager)getSystemService(Context.ACTIVITY_SERVICE);
        ConfigurationInfo mInfo = mManager.getDeviceConfigurationInfo();

        return mInfo.reqGlEsVersion >= 0x2000;
    }

    /*public TilesFrameLayout getTileLayout() {
        return mTileLayout;
    }*/
}
