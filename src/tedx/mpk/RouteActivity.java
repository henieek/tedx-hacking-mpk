
package tedx.mpk;

import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import com.google.android.maps.*;

import java.util.List;

public class RouteActivity extends MapActivity implements LocationListener {

    public static final String EXTRA_PLACE = "extra_place";

    private MapView mapView;
    private MapController mapController;
    private List<Overlay> overlays;

    private RouteProvider routeProvider = new RouteProvider();
    private OverlaysFromRouteGenerator overlaysFromRouteGenerator = new OverlaysFromRouteGenerator();

    private LocationManager locationManager;

    private Place destination;

    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        String provider = locationManager.getBestProvider(new Criteria(), true);
        locationManager.requestLocationUpdates(provider, 400, 1, this);
        setContentView(R.layout.activity_map);

        mapView = (MapView) findViewById(R.id.mapview);
        mapView.setClickable(true);
        mapController = mapView.getController();
        overlays = mapView.getOverlays();
        destination = (Place) getIntent().getSerializableExtra(EXTRA_PLACE);
    }

    @Override
    protected boolean isRouteDisplayed() {
        return true;
    }


    public void onLocationChanged(Location location) {

        GeoPoint users = new GeoPoint((int) (location.getLatitude()*1e6), (int) (location.getLongitude()*1e6));
        overlays.addAll(overlaysFromRouteGenerator.getOverlaysForRoute(
                routeProvider.getRouteFromMyLocation(users, destination),
                getResources().getDrawable(R.drawable.point_green)));

        mapController.animateTo(users);
        mapController.setZoom(15);
    }


    public void onStatusChanged(String s, int i, Bundle bundle) {
        //To change body of implemented methods use File | Settings | File Templates.
    }


    public void onProviderEnabled(String s) {
        //To change body of implemented methods use File | Settings | File Templates.
    }


    public void onProviderDisabled(String s) {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}

