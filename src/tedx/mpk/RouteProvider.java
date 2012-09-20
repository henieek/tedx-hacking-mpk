package tedx.mpk;

import android.content.Context;
import android.widget.Toast;
import com.google.android.maps.GeoPoint;
import tedx.mpk.data.CSVDatabase;
import tedx.mpk.data.Point;

import java.io.IOException;

public class RouteProvider {

    private Context context;
    private CSVDatabase csvDatabase;

    public RouteProvider(Context context) {
        this.context = context;
        csvDatabase = new CSVDatabase(context);
    }

    public Route getRouteFromMyLocation(GeoPoint usersLocation, Place place) {
        Point originPoint = new Point(usersLocation.getLatitudeE6(), usersLocation.getLongitudeE6());
        Point destinationPoint = new Point(place.getLatitude(), place.getLongitude());


        Route route = new Route();
        Line four = new Line();

        Station station = null;
        try {
            station = csvDatabase.findNearest(originPoint);
            Station stationDestination = csvDatabase.findNearest(destinationPoint);

            Toast.makeText(context, station.getName(), Toast.LENGTH_LONG).show();

            route.getListOfLines().addAll(csvDatabase.findPath(station, stationDestination));

            return route;
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            return null;
        }

    }

}
