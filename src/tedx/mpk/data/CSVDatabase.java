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
        boolean found = finder.find((Stop) a, null);
        System.err.println("addLine !found? " + found);
        Stop current = (Stop) b;
        List<Stop> l = new ArrayList<Stop>();
        System.err.println("addLine !src " + a.getName());
        System.err.println("addLine !dest " + b.getName());
        while (current != null) {
            l.add(current);
            System.err.println("addLinereal" + current.getName());
            current = finder.parents.get(current);
        }
        System.err.println("addLine !nothing found");

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

        boolean find(Stop s, Stop _) {
        	Queue<Stop> l = new LinkedList<Stop>();
        	l.add(s);
        	s = null;
        	_ = null;
        	while(l.size() != 0) {
        		Stop curr  = l.poll();
        		if(curr == target) return true;
        		visited.add(curr);
        		System.err.println("Now " + curr + " edges " + curr.edges.size());
        		for(Stop n: curr.edges) {
        			if(!visited.contains(n)) {
        				System.err.println("addLine dodaje! " + n.getName());
        				parents.put(n, curr);
        				visited.add(n);
        				l.add(n);
        			} else {
        				System.err.println("addLine dupa " + n.getName());
        			}
        		}
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
            String b = line.get(1);
            if (stopMap.containsKey(a) && stopMap.containsKey(b)) {
            	//System.err.println("EDGE " + stopMap.get(a).getName() + " " + 
            	//		stopMap.get(b).getName());
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
