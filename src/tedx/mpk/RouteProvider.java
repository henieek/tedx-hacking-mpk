package tedx.mpk;

import com.google.android.maps.GeoPoint;

public class RouteProvider {

    public Route getRouteFromMyLocation(GeoPoint usersLocation, Place place) {
        Route route = new Route();
        Line four = new Line();

        Station station = new Station();

        station.setLatitude(50065608);
        station.setLongitude(19946880);
        station.setName("Dworzec glowny");

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
    }

}
