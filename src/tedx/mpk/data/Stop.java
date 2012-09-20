package tedx.mpk.data;

import java.util.ArrayList;
import java.util.List;

import tedx.mpk.Station;

class Stop extends Station {
	
	public Stop(String name,int lat,int lon)
	{
		super();
		setName(name);
		setLongitude(lon);
		setLatitude(lat);
	}
	
	List<Stop> edges = new ArrayList<Stop>();
	
	Point getPoint()
	{
		return new Point(getLatitude(),getLongitude());
	}
}
