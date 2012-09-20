package tedx.mpk;

import java.util.ArrayList;
import java.util.List;

public class PlacesManager {

    private static List<Place> PLACES;
    private static final String baseUrl = "http://student.agh.edu.pl/~marta/hackaton/";
    static {
        PLACES = new ArrayList<Place>();

        Place miejsce = new Place();
        miejsce.setName("Kościół Mariacki");
        miejsce.setImageUrl(baseUrl + "1.jpg");
        miejsce.setLatitude(50.06179);
        miejsce.setLongitude(19.9371);
        PLACES.add(miejsce);
        miejsce = new Place();
        miejsce.setName("MOCAK");
        miejsce.setImageUrl(baseUrl + "2.jpg");
        miejsce.setLatitude(50.047408);
        miejsce.setLongitude(19.960862);
        PLACES.add(miejsce);
        miejsce = new Place();
        miejsce.setName("Manggha");
        miejsce.setImageUrl(baseUrl + "3.jpg");
        miejsce.setLatitude(50.05096);
        miejsce.setLongitude(19.93164);
        PLACES.add(miejsce);
        miejsce = new Place();
        miejsce.setName("Wawel");
        miejsce.setImageUrl(baseUrl + "4.jpg");
        miejsce.setLatitude(50.05424);
        miejsce.setLongitude(19.93551);
        PLACES.add(miejsce);
    }

    public List<Place> getAllPlaces() {
        return PLACES;
    }
}
