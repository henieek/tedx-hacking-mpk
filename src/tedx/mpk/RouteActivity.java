package tedx.mpk;

import android.os.Bundle;
import com.google.android.maps.MapActivity;

public class RouteActivity extends MapActivity {

    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);

    }

    @Override
    protected boolean isRouteDisplayed() {
        return true;
    }
}
