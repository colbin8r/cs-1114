package colbin8r;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class BarChartGPAsTest {
	
	private BarChartGPAs charts;

	@Before
	public void setUp() throws Exception {
		this.charts = new BarChartGPAs();
	}

	@After
	public void tearDown() throws Exception {
		this.charts = null;
	}
	
	@Test
	public void getBarlength() throws Exception {
		
		assertEquals(40, this.charts.getBarLength(2.0));
		assertEquals(60, this.charts.getBarLength(3.0));
		assertEquals(80, this.charts.getBarLength(4.0));
		
		this.charts.setMaxColLength(60);
		
		assertEquals(30, this.charts.getBarLength(2.0));
		assertEquals(45, this.charts.getBarLength(3.0));
		
		this.charts.setMaxRangeValue(6.0);
		
		assertEquals(20, this.charts.getBarLength(2.0));
		assertEquals(30, this.charts.getBarLength(3.0));
	}

}
