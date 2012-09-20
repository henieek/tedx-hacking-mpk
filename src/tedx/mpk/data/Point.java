package tedx.mpk.data;

import static java.lang.Math.pow;

public class Point {
	
	public Point(long x, long y) {
		this.x = x;
		this.y = y;
	}
	
	public long x;
	public long y;
	
	public double distance(Point b)
	{
		return Math.sqrt(pow((x - b.x),2) + pow((y - b.y),2));
	}
}
