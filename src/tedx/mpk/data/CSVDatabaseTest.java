package tedx.mpk.data;

import org.junit.Test;

import tedx.mpk.Station;
import static org.junit.Assert.*;

public class CSVDatabaseTest {
	
	@Test
	public void testFindNearestStop() throws Exception {
		Point point = new Point(50074200,19910400);
		CSVDatabase database = new CSVDatabase();
		
		Station find = database.findNearest(point);
		assertEquals("Uniwersytet Pedagogiczny", find.getName());
	}
	
	@Test
	public void testConvertToLongFromString() throws Exception {
		String toTest = "12.50005";
		assertEquals(12500050, CSVDatabase.convertCoordFromString(toTest));
	}
}
