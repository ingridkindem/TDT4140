package tdt4140.gr1812.app.core;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Test;

import tdt4140.gr1812.app.core.dataClasses.Athlete;
import tdt4140.gr1812.app.core.dataClasses.Sport;

public class AthleteTest {
	
	Athlete a = new Athlete("12341234", "Marte", "Bolstad");

	@Test
	public void testConstructor() {
		assertEquals("12341234", a.getPhoneNumber());
		assertEquals("Marte", a.getFirstName());
		assertEquals("Bolstad", a.getLastName());
		assertEquals("Marte Bolstad", a.getFullName());
	}
	
	@Test
	public void testSetAge() {
		a.setAge(23);
		assertEquals(23, a.getAge());
	}
	
	@Test
	public void testSetWeight() {
		a.setWeight(200);
		assertEquals(200, a.getWeight(), 0.0001);
	}
	
	@Test 
	public void testSetMaxPulse() {
		a.setMaxPulse(200);
		assertEquals(200, a.getMaxPulse());
	}
	
	@Test
	public void testSetGender() {
		a.setGender(true);
		assertTrue(a.getGender());
	}
	
	@Test
	public void testAddSport() {
		Sport s = new Sport("langrenn");
		a.addSport(s);
		assertEquals(Arrays.asList(s), a.getSports());
	}

}
