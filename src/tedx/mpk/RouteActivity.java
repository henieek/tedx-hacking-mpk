package tedx.mpk;

import android.os.Bundle;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;

import java.util.List;

public class RouteActivity extends MapActivity {

    private MapView mapView;
    private MapController mapController;
    private List<Overlay> overlays;

    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_map);
        mapView = (MapView) findViewById(R.id.mapview);
        mapController = mapView.getController();
        overlays = mapView.getOverlays();
    }

    @Override
    protected boolean isRouteDisplayed() {
        return true;
    }
}
