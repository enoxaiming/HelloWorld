package kr.hs.dimigo.ver2.hello.world;

import android.content.Intent;
import android.opengl.GLSurfaceView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import kr.hs.dimigo.ver2.hello.world.particle.HWParticleRenderer;

public class HWMainActivity extends AppCompatActivity {

    private GLSurfaceView mSurfaceView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hw_activity_main);

        startActivity(new Intent(this, HWWelcomeActivity.class));

        mSurfaceView = (GLSurfaceView)findViewById(R.id.hw_activity_main_GLSURFACEVIEW);

        mSurfaceView.setEGLContextClientVersion(2);

        HWParticleRenderer mParticleRenderer = new HWParticleRenderer(mSurfaceView);
        mSurfaceView.setRenderer(mParticleRenderer);
        mSurfaceView.setRenderMode(GLSurfaceView.RENDERMODE_CONTINUOUSLY);
    }
}
