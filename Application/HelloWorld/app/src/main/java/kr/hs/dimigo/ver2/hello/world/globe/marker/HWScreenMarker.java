package kr.hs.dimigo.ver2.hello.world.globe.marker;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import com.mousebird.maply.MarkerInfo;
import com.mousebird.maply.Point2d;
import com.mousebird.maply.ScreenMarker;
import kr.hs.dimigo.ver2.hello.world.R;

/**
 * Created by dsa28s on 2016. 9. 3..
 */

public class HWScreenMarker {

    private MarkerInfo mMarkerInfo;
    private Bitmap mMarkerIcon;
    private Point2d mMarkerPoint;

    public HWScreenMarker(Activity a) {
        mMarkerInfo = new MarkerInfo();
        mMarkerIcon = BitmapFactory.decodeResource(a.getResources(), R.drawable.hw_marker);
        mMarkerPoint = new Point2d(70, 70);
    }

    public MarkerInfo getMarkerInformation() {
        return mMarkerInfo;
    }

    public ScreenMarker getSouthKoreaMaker() {
        ScreenMarker mMaker = new ScreenMarker();
        mMaker.loc = Point2d.FromDegrees(128.123, 36.386);
        mMaker.image = mMarkerIcon;
        mMaker.size = mMarkerPoint;
        return mMaker;
    }

    public ScreenMarker getNigeriaMaker() {
        ScreenMarker mMaker = new ScreenMarker();
        mMaker.loc = Point2d.FromDegrees(7.607, 9.405);
        mMaker.image = mMarkerIcon;
        mMaker.size = mMarkerPoint;
        return mMaker;
    }

    public ScreenMarker getNigerMaker() {
        ScreenMarker mMaker = new ScreenMarker();
        mMaker.loc = Point2d.FromDegrees(9.514, 17.435);
        mMaker.image = mMarkerIcon;
        mMaker.size = mMarkerPoint;
        return mMaker;
    }

    public ScreenMarker getNicaraguaMaker() {
        ScreenMarker mMaker = new ScreenMarker();
        mMaker.loc = Point2d.FromDegrees(-85.040, 12.793);
        mMaker.image = mMarkerIcon;
        mMaker.size = mMarkerPoint;
        return mMaker;
    }
}
