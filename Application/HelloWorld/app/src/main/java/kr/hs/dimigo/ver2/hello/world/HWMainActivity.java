package kr.hs.dimigo.ver2.hello.world;

import android.content.Intent;
import android.opengl.GLSurfaceView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import kr.hs.dimigo.ver2.hello.world.particle.HWParticleRenderer;

public class HWMainActivity extends AppCompatActivity {

    private GLSurfaceView mSurfaceView;
    private RelativeLayout mCommitContainer;
    private ImageView mTargetView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hw_activity_main);

        mSurfaceView = (GLSurfaceView)findViewById(R.id.hw_activity_main_GLSURFACEVIEW);
        mCommitContainer = (RelativeLayout)findViewById(R.id.hw_activity_main_CONTAINER);
        mTargetView = (ImageView)findViewById(R.id.hw_activity_main_TARGET_IMAGEVIEW);

        mSurfaceView.setEGLContextClientVersion(2);

        HWParticleRenderer mParticleRenderer = new HWParticleRenderer(mSurfaceView);
        mSurfaceView.setRenderer(mParticleRenderer);
        mSurfaceView.setRenderMode(GLSurfaceView.RENDERMODE_CONTINUOUSLY);

        HWGlobeFragment mGlobeFragment = new HWGlobeFragment();
        FragmentTransaction mTransaction = getSupportFragmentManager().beginTransaction();
        mTransaction.replace(R.id.hw_activity_main_CONTAINER, mGlobeFragment, "globe");
        mTransaction.setCustomAnimations(R.anim.hw_slidedown, 0, R.anim.hw_slidedown, 0);
        mTransaction.commit();

        ViewCompat.setElevation(mTargetView, 9999);
    }
}
