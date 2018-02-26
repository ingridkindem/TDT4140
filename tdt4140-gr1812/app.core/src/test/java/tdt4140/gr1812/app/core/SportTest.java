package tdt4140.gr1812.app.core;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import tdt4140.gr1812.app.core.dataClasses.Sport;

public class SportTest {
	
	@Test
	public void returnMySport() {
		
		Sport tester = new Sport("Basket"); 
		assertEquals("Basket", tester.getSport());
	}

}
