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
        Point point = new Point(usersLocation.getLatitudeE6(), usersLocation.getLongitudeE6());


        Route route = new Route();
        Line four = new Line();

        Station station = null;
        try {
            station = csvDatabase.findNearest(point);

            Toast.makeText(context, station.getName(), Toast.LENGTH_LONG).show();

            four.addStation(station);
            station = new Station();
            station.setLatitude(50060043);
            station.setLongitude(19945335);
            station.setName("Poczta glowna");
            four.addStation(station);

            station = new Station();
            station.setLatitude(50054091);
            station.setLongitude(19936152);
            station.setName("Wawel");
            four.addStation(station);

            route.getListOfLines().add(four);

            return route;
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            return null;
        }

    }

}
