package tedx.mpk;

import java.util.ArrayList;
import java.util.List;

public class PlacesManager {

    private static List<Place> PLACES;

    static {
        PLACES = new ArrayList<Place>();

        Place miejsce = new Place();
        miejsce.setName("Wawel");
        miejsce.setImageUrl("http://c.wrzuta.pl/wi4867/2b7f86d4001f73af4fd1e625/wawel_i_klasztory_w_krakowie");
        PLACES.add(miejsce);
        miejsce = new Place();
        miejsce.setName("Sukiennice");
        miejsce.setImageUrl("http://www.infotuba.pl/upload/article/3927.jpg");
        PLACES.add(miejsce);
        miejsce = new Place();
        miejsce.setName("Plac Centralny");
        miejsce.setImageUrl("http://1.bp.blogspot.com/-dAj23WW33p0/TmtVftV6uJI/AAAAAAAAAD4/N7aKBPU9c7I/s1600/Plac_centralny.jpg");
        PLACES.add(miejsce);
        miejsce = new Place();
        miejsce.setName("Muzeum Naradowe");
        miejsce.setImageUrl("http://studente.pl/_cache/_thumbs/CF/cf03b8cc6ce874fd5fcf67ababf73970.jpg");
        PLACES.add(miejsce);
        miejsce = new Place();
        miejsce.setName("Galeria Krakowska");
        miejsce.setImageUrl("http://plfoto.com/zdjecia_new/1065187.jpg");
        PLACES.add(miejsce);
    }



    public List<Place> getAllPlaces() {
        return PLACES;
    }
}
