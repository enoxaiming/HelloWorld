package kr.hs.dimigo.ver2.hello.world;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import com.mousebird.maply.*;
import kr.hs.dimigo.ver2.hello.world.globe.marker.HWScreenMarker;

import java.io.File;
import java.io.InterruptedIOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dsa28s on 2016. 9. 2..
 */

public class HWGlobeFragment extends GlobeMapFragment {
    private double mCurrentPositionAxis[] = new double[3];

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle inState) {
        super.onCreateView(inflater, container, inState);

        return baseControl.getContentView();
    }

    @Override
    protected MapDisplayType chooseDisplayType() {
        return MapDisplayType.Globe;
    }

    @Override
    protected void controlHasStarted() {
        insertMarker();

        String cacheDirName = "dimigo_globe_fragment_cache";
        File cacheDir = new File(getActivity().getCacheDir(), cacheDirName);
        cacheDir.mkdir();

        RemoteTileSource mRemoteTileSource = new RemoteTileSource(new RemoteTileInfo("https://maps.wikimedia.org/osm-intl/", "png", 2, 15));
        mRemoteTileSource.setCacheDir(cacheDir);

        SphericalMercatorCoordSystem mSystem = new SphericalMercatorCoordSystem();
        QuadImageTileLayer mBaseLayer = new QuadImageTileLayer(globeControl, mSystem, mRemoteTileSource);
        mBaseLayer.setImageDepth(1);
        mBaseLayer.setSingleLevelLoading(false);
        mBaseLayer.setUseTargetZoomLevel(false);
        mBaseLayer.setCoverPoles(true);
        mBaseLayer.setHandleEdges(true);

        globeControl.addLayer(mBaseLayer);
        globeControl.handleStartMoving(true);
        globeControl.animatePositionGeo(2.2265329360961914, 0.6531323790550232, 0.37025268615338325, 1.0);
        globeControl.gestureDelegate = new GlobeController.GestureDelegate() {
            @Override
            public void userDidSelect(GlobeController globeControl, SelectedObject[] selObjs, Point2d loc, Point2d screenLoc) {

            }

            @Override
            public void userDidTap(GlobeController globeControl, Point2d loc, Point2d screenLoc) {

            }

            @Override
            public void userDidLongPress(GlobeController globeControl, SelectedObject[] selObjs, Point2d loc, Point2d screenLoc) {

            }

            @Override
            public void globeDidStartMoving(GlobeController globeControl, boolean userMotion) {

            }

            @Override
            public void globeDidStopMoving(GlobeController globeControl, Point3d[] corners, boolean userMotion) {

            }

            @Override
            public void globeDidMove(GlobeController globeControl, Point3d[] corners, boolean userMotion) {
                mCurrentPositionAxis[0] = globeControl.getPositionGeo().getX();
                mCurrentPositionAxis[1] = globeControl.getPositionGeo().getY();
                mCurrentPositionAxis[2] = globeControl.getPositionGeo().getZ();

                if(globeControl.getGlobeView().getHeight() > 0.80) {
                    globeControl.getGlobeView().setHeight(0.80);
                    globeControl.animatePositionGeo(mCurrentPositionAxis[0], mCurrentPositionAxis[1], mCurrentPositionAxis[2], 1.0);
                }
            }
        };
    }

    private void insertMarker() {
        List<ScreenMarker> mMarkers = new ArrayList<>();

        HWScreenMarker mMarker = new HWScreenMarker(getActivity());

        mMarkers.add(mMarker.getSouthKoreaMaker());
        mMarkers.add(mMarker.getNicaraguaMaker());
        mMarkers.add(mMarker.getNigeriaMaker());
        mMarkers.add(mMarker.getNigerMaker());

        //ComponentObject mObject = mapControl.addScreenMarkers(mMarkers, mMarkerInfo, MaplyBaseController.ThreadMode.ThreadAny);
        //mapControl.addScreenMarkers(mMarkers, mMarkerInfo, MaplyBaseController.ThreadMode.ThreadAny);
        globeControl.addScreenMarkers(mMarkers, mMarker.getMarkerInformation(), MaplyBaseController.ThreadMode.ThreadAny);
    }
}
