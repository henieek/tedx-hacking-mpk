package tedx.mpk;

import java.util.List;

public class Line {

    private int number;
    private List<Station> stations;

    public Line() {

    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public List<Station> getStations() {
        return stations;
    }

    public void setStations(List<Station> stations) {
        this.stations = stations;
    }
}
