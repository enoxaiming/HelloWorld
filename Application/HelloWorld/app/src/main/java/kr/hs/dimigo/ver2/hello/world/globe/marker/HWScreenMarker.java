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

    public ScreenMarker getAfghanistyanMaker() {
        ScreenMarker mMaker = new ScreenMarker();
        mMaker.loc = Point2d.FromDegrees(66.516, 34.159);
        mMaker.image = mMarkerIcon;
        mMaker.size = mMarkerPoint;
        return mMaker;
    }

    public ScreenMarker getAlbaniaMaker() {
        ScreenMarker mMaker = new ScreenMarker();
        mMaker.loc = Point2d.FromDegrees(20.127, 40.647);
        mMaker.image = mMarkerIcon;
        mMaker.size = mMarkerPoint;
        return mMaker;
    }

    public ScreenMarker getAlgeriaMaker() {
        ScreenMarker mMaker = new ScreenMarker();
        mMaker.loc = Point2d.FromDegrees(3.379, 27.897);
        mMaker.image = mMarkerIcon;
        mMaker.size = mMarkerPoint;
        return mMaker;
    }

    public ScreenMarker getAmericanSamoaMaker() {
        ScreenMarker mMaker = new ScreenMarker();
        mMaker.loc = Point2d.FromDegrees(-170.7789, -14.3332);
        mMaker.image = mMarkerIcon;
        mMaker.size = mMarkerPoint;
        return mMaker;
    }

    public ScreenMarker getAndorraMaker() {
        ScreenMarker mMaker = new ScreenMarker();
        mMaker.loc = Point2d.FromDegrees(1.576, 42.547);
        mMaker.image = mMarkerIcon;
        mMaker.size = mMarkerPoint;
        return mMaker;
    }

    public ScreenMarker getAngolaMaker() {
        ScreenMarker mMaker = new ScreenMarker();
        mMaker.loc = Point2d.FromDegrees(17.996, -12.181);
        mMaker.image = mMarkerIcon;
        mMaker.size = mMarkerPoint;
        return mMaker;
    }

    public ScreenMarker getAnguillaMaker() {
        ScreenMarker mMaker = new ScreenMarker();
        mMaker.loc = Point2d.FromDegrees(-63.0500, 18.2167);
        mMaker.image = mMarkerIcon;
        mMaker.size = mMarkerPoint;
        return mMaker;
    }

    public ScreenMarker getArgentinaMaker() {
        ScreenMarker mMaker = new ScreenMarker();
        mMaker.loc = Point2d.FromDegrees(-64.168, -33.528);
        mMaker.image = mMarkerIcon;
        mMaker.size = mMarkerPoint;
        return mMaker;
    }

    public ScreenMarker getArmeniaMaker() {
        ScreenMarker mMaker = new ScreenMarker();
        mMaker.loc = Point2d.FromDegrees(44.806, 40.475);
        mMaker.image = mMarkerIcon;
        mMaker.size = mMarkerPoint;
        return mMaker;
    }

    public ScreenMarker getArubaMaker() {
        ScreenMarker mMaker = new ScreenMarker();
        mMaker.loc = Point2d.FromDegrees(-69.9667, 12.5000);
        mMaker.image = mMarkerIcon;
        mMaker.size = mMarkerPoint;
        return mMaker;
    }

    public ScreenMarker getAustraliaMaker() {
        ScreenMarker mMaker = new ScreenMarker();
        mMaker.loc = Point2d.FromDegrees(134.050, -23.992);
        mMaker.image = mMarkerIcon;
        mMaker.size = mMarkerPoint;
        return mMaker;
    }

    public ScreenMarker getAustriaMaker() {
        ScreenMarker mMaker = new ScreenMarker();
        mMaker.loc = Point2d.FromDegrees(14.144, 47.523);
        mMaker.image = mMarkerIcon;
        mMaker.size = mMarkerPoint;
        return mMaker;
    }

    public ScreenMarker getAzerbaijanMaker() {
        ScreenMarker mMaker = new ScreenMarker();
        mMaker.loc = Point2d.FromDegrees(47.211, 40.402);
        mMaker.image = mMarkerIcon;
        mMaker.size = mMarkerPoint;
        return mMaker;
    }

    public ScreenMarker getTheBahamasMaker() {
        ScreenMarker mMaker = new ScreenMarker();
        mMaker.loc = Point2d.FromDegrees(-78.019, 24.670);
        mMaker.image = mMarkerIcon;
        mMaker.size = mMarkerPoint;
        return mMaker;
    }

    public ScreenMarker getBahrainMaker() {
        ScreenMarker mMaker = new ScreenMarker();
        mMaker.loc = Point2d.FromDegrees(50.552, 26.020);
        mMaker.image = mMarkerIcon;
        mMaker.size = mMarkerPoint;
        return mMaker;
    }

    public ScreenMarker getBangladeshMaker() {
        ScreenMarker mMaker = new ScreenMarker();
        mMaker.loc = Point2d.FromDegrees(90.033, 24.046);
        mMaker.image = mMarkerIcon;
        mMaker.size = mMarkerPoint;
        return mMaker;
    }

    public ScreenMarker getBarbadosMaker() {
        ScreenMarker mMaker = new ScreenMarker();
        mMaker.loc = Point2d.FromDegrees(-59.556, 13.144);
        mMaker.image = mMarkerIcon;
        mMaker.size = mMarkerPoint;
        return mMaker;
    }

    public ScreenMarker getBelarusMaker() {
        ScreenMarker mMaker = new ScreenMarker();
        mMaker.loc = Point2d.FromDegrees(28.437, 53.832);
        mMaker.image = mMarkerIcon;
        mMaker.size = mMarkerPoint;
        return mMaker;
    }

    public ScreenMarker getBelgiumMaker() {
        ScreenMarker mMaker = new ScreenMarker();
        mMaker.loc = Point2d.FromDegrees(4.779, 50.778);
        mMaker.image = mMarkerIcon;
        mMaker.size = mMarkerPoint;
        return mMaker;
    }

    public ScreenMarker getBelizeMaker() {
        ScreenMarker mMaker = new ScreenMarker();
        mMaker.loc = Point2d.FromDegrees(-88.714, 17.207);
        mMaker.image = mMarkerIcon;
        mMaker.size = mMarkerPoint;
        return mMaker;
    }

    public ScreenMarker getBeninMaker() {
        ScreenMarker mMaker = new ScreenMarker();
        mMaker.loc = Point2d.FromDegrees(2.362, 10.331);
        mMaker.image = mMarkerIcon;
        mMaker.size = mMarkerPoint;
        return mMaker;
    }

    public ScreenMarker getBermudaMaker() {
        ScreenMarker mMaker = new ScreenMarker();
        mMaker.loc = Point2d.FromDegrees(-64.7400, 32.3302);
        mMaker.image = mMarkerIcon;
        mMaker.size = mMarkerPoint;
        return mMaker;
    }

    public ScreenMarker getBhutanMaker() {
        ScreenMarker mMaker = new ScreenMarker();
        mMaker.loc = Point2d.FromDegrees(89.926, 27.455);
        mMaker.image = mMarkerIcon;
        mMaker.size = mMarkerPoint;
        return mMaker;
    }

    public ScreenMarker getBoliviaMaker() {
        ScreenMarker mMaker = new ScreenMarker();
        mMaker.loc = Point2d.FromDegrees(-64.662, -16.641);
        mMaker.image = mMarkerIcon;
        mMaker.size = mMarkerPoint;
        return mMaker;
    }

    public ScreenMarker getBonaireMaker() {
        ScreenMarker mMaker = new ScreenMarker();
        mMaker.loc = Point2d.FromDegrees(-122.1554, 37.6936);
        mMaker.image = mMarkerIcon;
        mMaker.size = mMarkerPoint;
        return mMaker;
    }

    public ScreenMarker getBosniaAndHerzegovinaMaker() {
        ScreenMarker mMaker = new ScreenMarker();
        mMaker.loc = Point2d.FromDegrees(17.833, 44.250);
        mMaker.image = mMarkerIcon;
        mMaker.size = mMarkerPoint;
        return mMaker;
    }

    public ScreenMarker getBotswanaMaker() {
        ScreenMarker mMaker = new ScreenMarker();
        mMaker.loc = Point2d.FromDegrees(24.191, -22.108);
        mMaker.image = mMarkerIcon;
        mMaker.size = mMarkerPoint;
        return mMaker;
    }

    public ScreenMarker getBouvetIslandMaker() {
        ScreenMarker mMaker = new ScreenMarker();
        mMaker.loc = Point2d.FromDegrees(3.4000, -54.4333);
        mMaker.image = mMarkerIcon;
        mMaker.size = mMarkerPoint;
        return mMaker;
    }

    public ScreenMarker getBrazilMaker() {
        ScreenMarker mMaker = new ScreenMarker();
        mMaker.loc = Point2d.FromDegrees(-49.542, -11.929);
        mMaker.image = mMarkerIcon;
        mMaker.size = mMarkerPoint;
        return mMaker;
    }

    public ScreenMarker getBritishIndianOceanTerritoryMaker() {
        ScreenMarker mMaker = new ScreenMarker();
        mMaker.loc = Point2d.FromDegrees(72.0000, -6.0000);
        mMaker.image = mMarkerIcon;
        mMaker.size = mMarkerPoint;
        return mMaker;
    }

    public ScreenMarker getBritishVirginIslandsMaker() {
        ScreenMarker mMaker = new ScreenMarker();
        mMaker.loc = Point2d.FromDegrees(-64.5000, 18.5000);
        mMaker.image = mMarkerIcon;
        mMaker.size = mMarkerPoint;
        return mMaker;
    }

    public ScreenMarker getBruneiMaker() {
        ScreenMarker mMaker = new ScreenMarker();
        mMaker.loc = Point2d.FromDegrees(114.579, 4.480);
        mMaker.image = mMarkerIcon;
        mMaker.size = mMarkerPoint;
        return mMaker;
    }

    public ScreenMarker getBulgariaMaker() {
        ScreenMarker mMaker = new ScreenMarker();
        mMaker.loc = Point2d.FromDegrees(25.177, 42.502);
        mMaker.image = mMarkerIcon;
        mMaker.size = mMarkerPoint;
        return mMaker;
    }

    public ScreenMarker getBurkinaFasoMaker() {
        ScreenMarker mMaker = new ScreenMarker();
        mMaker.loc = Point2d.FromDegrees(-1.667, 12.500);
        mMaker.image = mMarkerIcon;
        mMaker.size = mMarkerPoint;
        return mMaker;
    }

    public ScreenMarker getBurundiMaker() {
        ScreenMarker mMaker = new ScreenMarker();
        mMaker.loc = Point2d.FromDegrees(3.3822, 29.3644);
        mMaker.image = mMarkerIcon;
        mMaker.size = mMarkerPoint;
        return mMaker;
    }

    public ScreenMarker getCambodiaMaker() {
        ScreenMarker mMaker = new ScreenMarker();
        mMaker.loc = Point2d.FromDegrees(104.914, 11.575);
        mMaker.image = mMarkerIcon;
        mMaker.size = mMarkerPoint;
        return mMaker;
    }

    public ScreenMarker getCameroonMaker() {
        ScreenMarker mMaker = new ScreenMarker();
        mMaker.loc = Point2d.FromDegrees(11.5167, 3.8667);
        mMaker.image = mMarkerIcon;
        mMaker.size = mMarkerPoint;
        return mMaker;
    }

    public ScreenMarker getCanadaMaker() {
        ScreenMarker mMaker = new ScreenMarker();
        mMaker.loc = Point2d.FromDegrees(-75.7839, 45.2094);
        mMaker.image = mMarkerIcon;
        mMaker.size = mMarkerPoint;
        return mMaker;
    }

    public ScreenMarker getCapeVerdeMaker() {
        ScreenMarker mMaker = new ScreenMarker();
        mMaker.loc = Point2d.FromDegrees(-23.515, 14.944);
        mMaker.image = mMarkerIcon;
        mMaker.size = mMarkerPoint;
        return mMaker;
    }

    public ScreenMarker getChinaMaker() {
        ScreenMarker mMaker = new ScreenMarker();
        mMaker.loc = Point2d.FromDegrees(116.3972, 39.9075);
        mMaker.image = mMarkerIcon;
        mMaker.size = mMarkerPoint;
        return mMaker;
    }

    public ScreenMarker getCongoMaker() {
        ScreenMarker mMaker = new ScreenMarker();
        mMaker.loc = Point2d.FromDegrees(15.425, -3.845);
        mMaker.image = mMarkerIcon;
        mMaker.size = mMarkerPoint;
        return mMaker;
    }

    public ScreenMarker getDCongoMaker() {
        ScreenMarker mMaker = new ScreenMarker();
        mMaker.loc = Point2d.FromDegrees(15.3136, -4.3276);
        mMaker.image = mMarkerIcon;
        mMaker.size = mMarkerPoint;
        return mMaker;
    }

    public ScreenMarker getCostaricaMaker() {
        ScreenMarker mMaker = new ScreenMarker();
        mMaker.loc = Point2d.FromDegrees(-84.232, 9.777);
        mMaker.image = mMarkerIcon;
        mMaker.size = mMarkerPoint;
        return mMaker;
    }

    public ScreenMarker getComorosMaker() {
        ScreenMarker mMaker = new ScreenMarker();
        mMaker.loc = Point2d.FromDegrees(43.2551, -11.7022);
        mMaker.image = mMarkerIcon;
        mMaker.size = mMarkerPoint;
        return mMaker;
    }

    public ScreenMarker getCroatiaMaker() {
        ScreenMarker mMaker = new ScreenMarker();
        mMaker.loc = Point2d.FromDegrees(15.5, 45.16666666);
        mMaker.image = mMarkerIcon;
        mMaker.size = mMarkerPoint;
        return mMaker;
    }

    public ScreenMarker getCubaMaker() {
        ScreenMarker mMaker = new ScreenMarker();
        mMaker.loc = Point2d.FromDegrees(-80, 21.5);
        mMaker.image = mMarkerIcon;
        mMaker.size = mMarkerPoint;
        return mMaker;
    }

    public ScreenMarker getCuracaoMaker() {
        ScreenMarker mMaker = new ScreenMarker();
        mMaker.loc = Point2d.FromDegrees(-68.933333, 12.116667);
        mMaker.image = mMarkerIcon;
        mMaker.size = mMarkerPoint;
        return mMaker;
    }

    public ScreenMarker getCyprusMaker() {
        ScreenMarker mMaker = new ScreenMarker();
        mMaker.loc = Point2d.FromDegrees(33, 35);
        mMaker.image = mMarkerIcon;
        mMaker.size = mMarkerPoint;
        return mMaker;
    }

    public ScreenMarker getCzechRepublicMaker() {
        ScreenMarker mMaker = new ScreenMarker();
        mMaker.loc = Point2d.FromDegrees(15.5, 49.75);
        mMaker.image = mMarkerIcon;
        mMaker.size = mMarkerPoint;
        return mMaker;
    }

    public ScreenMarker getDenmarkMaker() {
        ScreenMarker mMaker = new ScreenMarker();
        mMaker.loc = Point2d.FromDegrees(10, 56);
        mMaker.image = mMarkerIcon;
        mMaker.size = mMarkerPoint;
        return mMaker;
    }

    public ScreenMarker getDjiboutiMaker() {
        ScreenMarker mMaker = new ScreenMarker();
        mMaker.loc = Point2d.FromDegrees(43, 11.5);
        mMaker.image = mMarkerIcon;
        mMaker.size = mMarkerPoint;
        return mMaker;
    }

    public ScreenMarker getDominicaMaker() {
        ScreenMarker mMaker = new ScreenMarker();
        mMaker.loc = Point2d.FromDegrees(-61.33333333, 15.41666666);
        mMaker.image = mMarkerIcon;
        mMaker.size = mMarkerPoint;
        return mMaker;
    }

    public ScreenMarker getDominicanRepublicMaker() {
        ScreenMarker mMaker = new ScreenMarker();
        mMaker.loc = Point2d.FromDegrees(-70.66666666, 19);
        mMaker.image = mMarkerIcon;
        mMaker.size = mMarkerPoint;
        return mMaker;
    }

    public ScreenMarker getEcuadorMaker() {
        ScreenMarker mMaker = new ScreenMarker();
        mMaker.loc = Point2d.FromDegrees(-77.5, -2);
        mMaker.image = mMarkerIcon;
        mMaker.size = mMarkerPoint;
        return mMaker;
    }

    public ScreenMarker getElSalvadorMaker() {
        ScreenMarker mMaker = new ScreenMarker();
        mMaker.loc = Point2d.FromDegrees(-88.91666666, 13.83333333);
        mMaker.image = mMarkerIcon;
        mMaker.size = mMarkerPoint;
        return mMaker;
    }

    public ScreenMarker getEgyptMaker() {
        ScreenMarker mMaker = new ScreenMarker();
        mMaker.loc = Point2d.FromDegrees(30, 27);
        mMaker.image = mMarkerIcon;
        mMaker.size = mMarkerPoint;
        return mMaker;
    }

    public ScreenMarker getGuineaMaker() {
        ScreenMarker mMaker = new ScreenMarker();
        mMaker.loc = Point2d.FromDegrees(10, 2);
        mMaker.image = mMarkerIcon;
        mMaker.size = mMarkerPoint;
        return mMaker;
    }

    public ScreenMarker getEstoniaMaker() {
        ScreenMarker mMaker = new ScreenMarker();
        mMaker.loc = Point2d.FromDegrees(26, 59);
        mMaker.image = mMarkerIcon;
        mMaker.size = mMarkerPoint;
        return mMaker;
    }

    public ScreenMarker getEritreaMaker() {
        ScreenMarker mMaker = new ScreenMarker();
        mMaker.loc = Point2d.FromDegrees(39, 15);
        mMaker.image = mMarkerIcon;
        mMaker.size = mMarkerPoint;
        return mMaker;
    }

    public ScreenMarker getFijiMaker() {
        ScreenMarker mMaker = new ScreenMarker();
        mMaker.loc = Point2d.FromDegrees(175,-18 );
        mMaker.image = mMarkerIcon;
        mMaker.size = mMarkerPoint;
        return mMaker;
    }

    public ScreenMarker getFinlandMaker() {
        ScreenMarker mMaker = new ScreenMarker();
        mMaker.loc = Point2d.FromDegrees(26, 64);
        mMaker.image = mMarkerIcon;
        mMaker.size = mMarkerPoint;
        return mMaker;
    }

    public ScreenMarker getFranceMaker() {
        ScreenMarker mMaker = new ScreenMarker();
        mMaker.loc = Point2d.FromDegrees(2, 46);
        mMaker.image = mMarkerIcon;
        mMaker.size = mMarkerPoint;
        return mMaker;
    }

    public ScreenMarker getFrenchGuianaMaker() {
        ScreenMarker mMaker = new ScreenMarker();
        mMaker.loc = Point2d.FromDegrees(-53, 4);
        mMaker.image = mMarkerIcon;
        mMaker.size = mMarkerPoint;
        return mMaker;
    }

    public ScreenMarker getPolynesiaMaker() {
        ScreenMarker mMaker = new ScreenMarker();
        mMaker.loc = Point2d.FromDegrees(-140, -15);
        mMaker.image = mMarkerIcon;
        mMaker.size = mMarkerPoint;
        return mMaker;
    }

    public ScreenMarker getGabonMaker() {
        ScreenMarker mMaker = new ScreenMarker();
        mMaker.loc = Point2d.FromDegrees(11.75, -1);
        mMaker.image = mMarkerIcon;
        mMaker.size = mMarkerPoint;
        return mMaker;
    }

    public ScreenMarker getGambiaMaker() {
        ScreenMarker mMaker = new ScreenMarker();
        mMaker.loc = Point2d.FromDegrees(-16.56666666, 13.46666666);
        mMaker.image = mMarkerIcon;
        mMaker.size = mMarkerPoint;
        return mMaker;
    }

    public ScreenMarker getGeorgiaMaker() {
        ScreenMarker mMaker = new ScreenMarker();
        mMaker.loc = Point2d.FromDegrees(43.5, 42);
        mMaker.image = mMarkerIcon;
        mMaker.size = mMarkerPoint;
        return mMaker;
    }

    public ScreenMarker getGermanyMaker() {
        ScreenMarker mMaker = new ScreenMarker();
        mMaker.loc = Point2d.FromDegrees(9, 51);
        mMaker.image = mMarkerIcon;
        mMaker.size = mMarkerPoint;
        return mMaker;
    }

    public ScreenMarker getGhanaMaker() {
        ScreenMarker mMaker = new ScreenMarker();
        mMaker.loc = Point2d.FromDegrees(-2, 8);
        mMaker.image = mMarkerIcon;
        mMaker.size = mMarkerPoint;
        return mMaker;
    }

    public ScreenMarker getGibraltarMaker() {
        ScreenMarker mMaker = new ScreenMarker();
        mMaker.loc = Point2d.FromDegrees(-5.35, 36.13333333);
        mMaker.image = mMarkerIcon;
        mMaker.size = mMarkerPoint;
        return mMaker;
    }

    public ScreenMarker getGreeceMaker() {
        ScreenMarker mMaker = new ScreenMarker();
        mMaker.loc = Point2d.FromDegrees(22,39);
        mMaker.image = mMarkerIcon;
        mMaker.size = mMarkerPoint;
        return mMaker;
    }

    public ScreenMarker getGrenadaMaker() {
        ScreenMarker mMaker = new ScreenMarker();
        mMaker.loc = Point2d.FromDegrees(-61.66666666, 12.1166666);
        mMaker.image = mMarkerIcon;
        mMaker.size = mMarkerPoint;
        return mMaker;
    }

    public ScreenMarker getGuadeloupeMaker() {
        ScreenMarker mMaker = new ScreenMarker();
        mMaker.loc = Point2d.FromDegrees(-61.583333, 16.25);
        mMaker.image = mMarkerIcon;
        mMaker.size = mMarkerPoint;
        return mMaker;
    }

    public ScreenMarker getGuamMaker() {
        ScreenMarker mMaker = new ScreenMarker();
        mMaker.loc = Point2d.FromDegrees(144.78333333, 13.46666666);
        mMaker.image = mMarkerIcon;
        mMaker.size = mMarkerPoint;
        return mMaker;
    }

    public ScreenMarker getGuatemalaMaker() {
        ScreenMarker mMaker = new ScreenMarker();
        mMaker.loc = Point2d.FromDegrees(-90.25, 15.5);
        mMaker.image = mMarkerIcon;
        mMaker.size = mMarkerPoint;
        return mMaker;
    }

    public ScreenMarker getGuernseyMaker() {
        ScreenMarker mMaker = new ScreenMarker();
        mMaker.loc = Point2d.FromDegrees(-2.58333333, 49.46666666);
        mMaker.image = mMarkerIcon;
        mMaker.size = mMarkerPoint;
        return mMaker;
    }

    public ScreenMarker getGuyanaMaker() {
        ScreenMarker mMaker = new ScreenMarker();
        mMaker.loc = Point2d.FromDegrees(-59, 5);
        mMaker.image = mMarkerIcon;
        mMaker.size = mMarkerPoint;
        return mMaker;
    }

    public ScreenMarker getHaitiMaker() {
        ScreenMarker mMaker = new ScreenMarker();
        mMaker.loc = Point2d.FromDegrees(-72.41666666, 19);
        mMaker.image = mMarkerIcon;
        mMaker.size = mMarkerPoint;
        return mMaker;
    }

    public ScreenMarker getHondurasMaker() {
        ScreenMarker mMaker = new ScreenMarker();
        mMaker.loc = Point2d.FromDegrees(-86.5, 15);
        mMaker.image = mMarkerIcon;
        mMaker.size = mMarkerPoint;
        return mMaker;
    }

    public ScreenMarker getHungaryMaker() {
        ScreenMarker mMaker = new ScreenMarker();
        mMaker.loc = Point2d.FromDegrees(20, 47);
        mMaker.image = mMarkerIcon;
        mMaker.size = mMarkerPoint;
        return mMaker;
    }

    public ScreenMarker getIcelandMaker() {
        ScreenMarker mMaker = new ScreenMarker();
        mMaker.loc = Point2d.FromDegrees(-18, 65);
        mMaker.image = mMarkerIcon;
        mMaker.size = mMarkerPoint;
        return mMaker;
    }

    public ScreenMarker getIndiaMaker() {
        ScreenMarker mMaker = new ScreenMarker();
        mMaker.loc = Point2d.FromDegrees(77, 20);
        mMaker.image = mMarkerIcon;
        mMaker.size = mMarkerPoint;
        return mMaker;
    }
    public ScreenMarker getIndonesiaMaker() {
        ScreenMarker mMaker = new ScreenMarker();
        mMaker.loc = Point2d.FromDegrees(120, -5);
        mMaker.image = mMarkerIcon;
        mMaker.size = mMarkerPoint;
        return mMaker;
    }
    public ScreenMarker getIranMaker() {
        ScreenMarker mMaker = new ScreenMarker();
        mMaker.loc = Point2d.FromDegrees(53, 32);
        mMaker.image = mMarkerIcon;
        mMaker.size = mMarkerPoint;
        return mMaker;
    }
    public ScreenMarker getIraqMaker() {
        ScreenMarker mMaker = new ScreenMarker();
        mMaker.loc = Point2d.FromDegrees(44, 33);
        mMaker.image = mMarkerIcon;
        mMaker.size = mMarkerPoint;
        return mMaker;
    }
    public ScreenMarker getIsraelMaker() {
        ScreenMarker mMaker = new ScreenMarker();
        mMaker.loc = Point2d.FromDegrees(34.75, 31.5);
        mMaker.image = mMarkerIcon;
        mMaker.size = mMarkerPoint;
        return mMaker;
    }
    public ScreenMarker getItaryMaker() {
        ScreenMarker mMaker = new ScreenMarker();
        mMaker.loc = Point2d.FromDegrees(12.83333333, 42.83333333);
        mMaker.image = mMarkerIcon;
        mMaker.size = mMarkerPoint;
        return mMaker;
    }
    public ScreenMarker getJamaicaMaker() {
        ScreenMarker mMaker = new ScreenMarker();
        mMaker.loc = Point2d.FromDegrees(-77.5, 18.25);
        mMaker.image = mMarkerIcon;
        mMaker.size = mMarkerPoint;
        return mMaker;
    }
    public ScreenMarker getJapanMaker() {
        ScreenMarker mMaker = new ScreenMarker();
        mMaker.loc = Point2d.FromDegrees(138, 36);
        mMaker.image = mMarkerIcon;
        mMaker.size = mMarkerPoint;
        return mMaker;
    }
    public ScreenMarker getJerseyMaker() {
        ScreenMarker mMaker = new ScreenMarker();
        mMaker.loc = Point2d.FromDegrees(-2.16666666, 49.25);
        mMaker.image = mMarkerIcon;
        mMaker.size = mMarkerPoint;
        return mMaker;
    }
    public ScreenMarker getJordanMaker() {
        ScreenMarker mMaker = new ScreenMarker();
        mMaker.loc = Point2d.FromDegrees(36, 31);
        mMaker.image = mMarkerIcon;
        mMaker.size = mMarkerPoint;
        return mMaker;
    }
    public ScreenMarker getKuwaitMaker() {
        ScreenMarker mMaker = new ScreenMarker();
        mMaker.loc = Point2d.FromDegrees(45.75, 29.5);
        mMaker.image = mMarkerIcon;
        mMaker.size = mMarkerPoint;
        return mMaker;
    }
    public ScreenMarker getKyrgyzstanMaker() {
        ScreenMarker mMaker = new ScreenMarker();
        mMaker.loc = Point2d.FromDegrees(75, 41);
        mMaker.image = mMarkerIcon;
        mMaker.size = mMarkerPoint;
        return mMaker;
    }
    public ScreenMarker getLatviaMaker() {
        ScreenMarker mMaker = new ScreenMarker();
        mMaker.loc = Point2d.FromDegrees(25, 75);
        mMaker.image = mMarkerIcon;
        mMaker.size = mMarkerPoint;
        return mMaker;
    }
    public ScreenMarker getLebanonMaker() {
        ScreenMarker mMaker = new ScreenMarker();
        mMaker.loc = Point2d.FromDegrees(35.83333333, 33.83333333);
        mMaker.image = mMarkerIcon;
        mMaker.size = mMarkerPoint;
        return mMaker;
    }
    public ScreenMarker getEthiopiaMaker() {
        ScreenMarker mMaker = new ScreenMarker();
        mMaker.loc = Point2d.FromDegrees(38, 8);
        mMaker.image = mMarkerIcon;
        mMaker.size = mMarkerPoint;
        return mMaker;
    }


}
