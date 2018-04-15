package tdt4140.gr1812.app.core;

import static org.junit.Assert.*;

import org.junit.Test;

import tdt4140.gr1812.app.core.dataClasses.Sport;

public class SportTest {

	@Test
	public void testToString() {
		Sport s = new Sport("langrenn");
		assertEquals("langrenn", s.toString());
	}

}
