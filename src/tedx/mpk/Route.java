package tedx.mpk;

import java.util.ArrayList;
import java.util.List;

public class Route {

    private List<Line> listOfLines = new ArrayList<Line>();

    public List<Line> getListOfLines() {
        return listOfLines;
    }

    public void setListOfLines(List<Line> listOfLines) {
        this.listOfLines = listOfLines;
    }
}
