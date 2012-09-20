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
        miejsce.setLatitude(50061800);
        miejsce.setLongitude(19938877);

        PLACES.add(miejsce);
        miejsce = new Place();
        miejsce.setName("MOCAK");
        miejsce.setImageUrl(baseUrl + "2.jpg");
        miejsce.setLatitude(50047368);
        miejsce.setLongitude(19961386);
        PLACES.add(miejsce);
        miejsce = new Place();
        miejsce.setName("Manggha");
        miejsce.setImageUrl(baseUrl + "3.jpg");
        miejsce.setLatitude(50047516);
        miejsce.setLongitude(19931774);
        PLACES.add(miejsce);
        miejsce = new Place();
        miejsce.setName("Wawel");
        miejsce.setImageUrl(baseUrl + "4.jpg");
        miejsce.setLatitude(50052477);
        miejsce.setLongitude(19939327);
        PLACES.add(miejsce);
    }

    public List<Place> getAllPlaces() {
        return PLACES;
    }
}
