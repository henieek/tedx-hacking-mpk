package tedx.mpk.data;

import android.content.Context;
import tedx.mpk.Line;
import tedx.mpk.Station;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class CSVDatabase {

    private List<Stop> stopList = new ArrayList<Stop>();
    private Map<String, Stop> stopMap = new HashMap<String, Stop>();
    private Context context;

    public CSVDatabase(Context context) {
        this.context = context;
    }

    public Station findNearest(Point point) throws IOException {
        if (stopList.size() == 0)
            loadStops();
        double min = Double.MAX_VALUE;
        Station current = null;
        for (Stop stop : stopList)
            if (min > point.distance(stop.getPoint())) {
                min = point.distance(stop.getPoint());
                current = stop;
            }
        return current;
    }

    public List<Line> findPath(Station a, Station b) throws IOException {
        PathFinder finder = new PathFinder();
        finder.target = (Stop) b;
        finder.find((Stop) a, null);
        Stop current = (Stop) b;
        List<Stop> l = new ArrayList<Stop>();
        while (current != null) {
            l.add(current);
            current = finder.parents.get(current);
        }

        List<Line> lines = new ArrayList<Line>();
        Line line = new Line();
        line.setNumber(69);
        line.getStations().addAll((List<Station>) ((List) l));
        lines.add(line);

        return lines;
    }

    class PathFinder {
        Stop target;
        Set<Stop> visited = new HashSet<Stop>();
        Map<Stop, Stop> parents = new HashMap<Stop, Stop>();

        boolean find(Stop s, Stop parent) {
            visited.add(s);
            parents.put(s, parent);
            for (Stop n : s.edges) {
                if (visited.contains(n)) continue;
                if (n == target) {
                    return true;
                }
                if (find(n, s)) return true;
            }
            return false;
        }
    }

    public Station getStation(String a) {
        return stopMap.get(a);
    }

    private void loadStops() throws IOException {
        CSVLoader loader = new CSVLoader("stops.csv");
        List<String> line;
        while ((line = loader.nextRow()) != null) {
            if (line.size() < 6) continue;
            Stop stop;
            stopList.add(stop = new Stop(line.get(1),
                    convertCoordFromString(line.get(4)),
                    convertCoordFromString(line.get(5))));
            stopMap.put(stop.getName(), stop);
        }
        loadEdges();
    }


    private void loadEdges() throws IOException {
        CSVLoader loader = new CSVLoader("edges.csv");
        List<String> line;
        while ((line = loader.nextRow()) != null) {
            String a = line.get(0);
            String b = line.get(0);
            if (stopMap.containsKey(a)) {
                stopMap.get(a).edges.add(stopMap.get(b));
            }
        }
    }

    static int convertCoordFromString(String t) {
        return (int) (Double.valueOf(t) * 1e6);
    }

    class CSVLoader {
        private BufferedReader reader;

        public CSVLoader(String filename) throws IOException {
            reader = new BufferedReader(new InputStreamReader(context.getAssets().open(filename)));

        }

        public List<String> nextRow() throws IOException {
            String line = reader.readLine();
            if (line != null)
                return Arrays.asList(line.split(","));
            return null;
        }
    }
}
