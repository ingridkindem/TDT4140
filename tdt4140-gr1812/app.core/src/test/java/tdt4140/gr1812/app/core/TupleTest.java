package tdt4140.gr1812.app.core;

import static org.junit.Assert.*;

import org.junit.Test;

import tdt4140.gr1812.app.core.dataClasses.Tuple;

public class TupleTest {

	@Test
	public void testTuple() {
		Tuple t = new Tuple(3,4);
		assertEquals(t.x, 3);
	}

}
