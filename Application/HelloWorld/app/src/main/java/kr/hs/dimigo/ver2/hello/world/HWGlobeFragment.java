package kr.hs.dimigo.ver2.hello.world;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import com.mousebird.maply.*;
import kr.hs.dimigo.ver2.hello.world.globe.marker.HWScreenMarker;

import java.io.File;
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
        globeControl.animatePositionGeo(2.237851619720459, 0.6353013515472412, 0.5716807365417481, 1.0);
        globeControl.gestureDelegate = new GlobeController.GestureDelegate() {
            @Override
            public void userDidSelect(GlobeController globeControl, SelectedObject[] selObjs, Point2d loc, Point2d screenLoc) {
                HWMainActivity mActivity = (HWMainActivity)getActivity();
                mActivity.getCounteryFromServer(loc.toDegrees().getX(), loc.toDegrees().getY());
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

                Log.e("Axis", globeControl.getPositionGeo().toString());
                //Log.e("Height", globeControl.getGlobeView().getHeight() + "");

                if(globeControl.getGlobeView().getHeight() > 0.80) {
                    globeControl.getGlobeView().setHeight(0.80);
                    globeControl.animatePositionGeo(mCurrentPositionAxis[0], mCurrentPositionAxis[1], mCurrentPositionAxis[2], 1.0);
                }
            }
        };

        HWMainActivity mActivity = (HWMainActivity)getActivity();
        mActivity.setGlobeControl(globeControl);
    }

    private void insertMarker() {
        List<ScreenMarker> mMarkers = new ArrayList<>();

        HWScreenMarker mMarker = new HWScreenMarker(getActivity());

        mMarkers.add(mMarker.getSouthKoreaMaker());
        mMarkers.add(mMarker.getNicaraguaMaker());
        mMarkers.add(mMarker.getNigeriaMaker());
        mMarkers.add(mMarker.getNigerMaker());
        mMarkers.add(mMarker.getAfghanistyanMaker());
        mMarkers.add(mMarker.getAlbaniaMaker());
        mMarkers.add(mMarker.getAlgeriaMaker());
        mMarkers.add(mMarker.getAmericanSamoaMaker());
        mMarkers.add(mMarker.getAndorraMaker());
        mMarkers.add(mMarker.getAngolaMaker());
        mMarkers.add(mMarker.getAnguillaMaker());
        mMarkers.add(mMarker.getArgentinaMaker());
        mMarkers.add(mMarker.getArmeniaMaker());
        mMarkers.add(mMarker.getArubaMaker());
        mMarkers.add(mMarker.getAustraliaMaker());
        mMarkers.add(mMarker.getAustriaMaker());
        mMarkers.add(mMarker.getAzerbaijanMaker());
        mMarkers.add(mMarker.getTheBahamasMaker());
        mMarkers.add(mMarker.getBahrainMaker());
        mMarkers.add(mMarker.getBangladeshMaker());
        mMarkers.add(mMarker.getBarbadosMaker());
        mMarkers.add(mMarker.getBelarusMaker());
        mMarkers.add(mMarker.getBelgiumMaker());
        mMarkers.add(mMarker.getBelizeMaker());
        mMarkers.add(mMarker.getBeninMaker());
        mMarkers.add(mMarker.getBermudaMaker());
        mMarkers.add(mMarker.getBhutanMaker());
        mMarkers.add(mMarker.getBoliviaMaker());
        mMarkers.add(mMarker.getBonaireMaker());
        mMarkers.add(mMarker.getBosniaAndHerzegovinaMaker());
        mMarkers.add(mMarker.getBotswanaMaker());
        mMarkers.add(mMarker.getBouvetIslandMaker());
        mMarkers.add(mMarker.getBrazilMaker());
        mMarkers.add(mMarker.getBritishIndianOceanTerritoryMaker());
        mMarkers.add(mMarker.getBritishVirginIslandsMaker());
        mMarkers.add(mMarker.getBruneiMaker());
        mMarkers.add(mMarker.getBulgariaMaker());
        mMarkers.add(mMarker.getBurkinaFasoMaker());
        mMarkers.add(mMarker.getBurundiMaker());
        mMarkers.add(mMarker.getCambodiaMaker());
        mMarkers.add(mMarker.getCameroonMaker());
        mMarkers.add(mMarker.getCanadaMaker());
        mMarkers.add(mMarker.getCapeVerdeMaker());
        mMarkers.add(mMarker.getChinaMaker());
        mMarkers.add(mMarker.getCongoMaker());
        mMarkers.add(mMarker.getDCongoMaker());
        mMarkers.add(mMarker.getCostaricaMaker());
        mMarkers.add(mMarker.getComorosMaker());
        mMarkers.add(mMarker.getCroatiaMaker());
        mMarkers.add(mMarker.getCubaMaker());
        mMarkers.add(mMarker.getCuracaoMaker());
        mMarkers.add(mMarker.getCyprusMaker());
        mMarkers.add(mMarker.getCzechRepublicMaker());
        mMarkers.add(mMarker.getDenmarkMaker());
        mMarkers.add(mMarker.getDjiboutiMaker());
        mMarkers.add(mMarker.getDominicaMaker());
        mMarkers.add(mMarker.getDominicanRepublicMaker());
        mMarkers.add(mMarker.getEcuadorMaker());
        mMarkers.add(mMarker.getElSalvadorMaker());
        mMarkers.add(mMarker.getEgyptMaker());
        mMarkers.add(mMarker.getGuineaMaker());
        mMarkers.add(mMarker.getEstoniaMaker());
        mMarkers.add(mMarker.getEritreaMaker());
        mMarkers.add(mMarker.getFijiMaker());
        mMarkers.add(mMarker.getFinlandMaker());
        mMarkers.add(mMarker.getFranceMaker());
        mMarkers.add(mMarker.getFrenchGuianaMaker());
        mMarkers.add(mMarker.getPolynesiaMaker());
        mMarkers.add(mMarker.getGabonMaker());
        mMarkers.add(mMarker.getGambiaMaker());
        mMarkers.add(mMarker.getGeorgiaMaker());
        mMarkers.add(mMarker.getGermanyMaker());
        mMarkers.add(mMarker.getGhanaMaker());
        mMarkers.add(mMarker.getGibraltarMaker());
        mMarkers.add(mMarker.getGreeceMaker());
        mMarkers.add(mMarker.getGrenadaMaker());
        mMarkers.add(mMarker.getGuadeloupeMaker());
        mMarkers.add(mMarker.getGuamMaker());
        mMarkers.add(mMarker.getGuatemalaMaker());
        mMarkers.add(mMarker.getGuernseyMaker());
        mMarkers.add(mMarker.getGuyanaMaker());
        mMarkers.add(mMarker.getHaitiMaker());
        mMarkers.add(mMarker.getHondurasMaker());
        mMarkers.add(mMarker.getHungaryMaker());
        mMarkers.add(mMarker.getIcelandMaker());
        mMarkers.add(mMarker.getIndiaMaker());
        mMarkers.add(mMarker.getIndonesiaMaker());
        mMarkers.add(mMarker.getIranMaker());
        mMarkers.add(mMarker.getIraqMaker());
        mMarkers.add(mMarker.getIsraelMaker());
        mMarkers.add(mMarker.getItaryMaker());
        mMarkers.add(mMarker.getJamaicaMaker());
        mMarkers.add(mMarker.getJapanMaker());
        mMarkers.add(mMarker.getJerseyMaker());
        mMarkers.add(mMarker.getJordanMaker());
        mMarkers.add(mMarker.getKuwaitMaker());
        mMarkers.add(mMarker.getKyrgyzstanMaker());
        mMarkers.add(mMarker.getLatviaMaker());
        mMarkers.add(mMarker.getLebanonMaker());
        mMarkers.add(mMarker.getEthiopiaMaker());

        //ComponentObject mObject = mapControl.addScreenMarkers(mMarkers, mMarkerInfo, MaplyBaseController.ThreadMode.ThreadAny);
        //mapControl.addScreenMarkers(mMarkers, mMarkerInfo, MaplyBaseController.ThreadMode.ThreadAny);
        globeControl.addScreenMarkers(mMarkers, mMarker.getMarkerInformation(), MaplyBaseController.ThreadMode.ThreadAny);
    }
}
