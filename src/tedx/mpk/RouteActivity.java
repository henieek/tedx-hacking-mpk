package tedx.mpk;

import android.os.Bundle;
import com.google.android.maps.*;

import java.util.List;

public class RouteActivity extends MapActivity {

    private MapView mapView;
    private MapController mapController;
    private List<Overlay> overlays;

    private RouteProvider routeProvider = new RouteProvider();
    private OverlaysFromRouteGenerator overlaysFromRouteGenerator = new OverlaysFromRouteGenerator();


    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_map);

        mapView = (MapView) findViewById(R.id.mapview);
        mapController = mapView.getController();
        overlays = mapView.getOverlays();

        Place wawelCastle = new Place();
        wawelCastle.setName("Wawel");
        wawelCastle.setImageUrl("http://c.wrzuta.pl/wi4867/2b7f86d4001f73af4fd1e625/wawel_i_klasztory_w_krakowie");

        overlays.clear();
        GeoPoint users = new GeoPoint(50065608, 19946880);
        overlays.addAll(overlaysFromRouteGenerator.getOverlaysForRoute(
                routeProvider.getRouteFromMyLocation(users, wawelCastle),
                getResources().getDrawable(R.drawable.point_green)));

        mapView.setClickable(true);


        mapController.animateTo(users);
        mapController.setZoom(15);
    }

    @Override
    protected boolean isRouteDisplayed() {
        return true;
    }
}
