package tdt4140.gr1812.app.core;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import tdt4140.gr1812.app.core.dataClasses.Athlete;
import tdt4140.gr1812.app.core.dataClasses.Sport;

public class AthleteTest {

	private Athlete athlete;
	
	@Before
	public void setUp() throws Exception {
		 this.athlete = new Athlete("95902393", "Maren" , "Barth");
	}
	
	@Test
	public void testGetPhoneNumber() {
		assertEquals("95902393", athlete.getPhoneNumber());
	}
		
	
	@Test
	public void testGetFirstName() {
		assertEquals("Maren", athlete.getFirstName());
	}
	
	@Test
	public void testGetLastName() {
		assertEquals("Barth", athlete.getLastName());
	}
	
	@Test
	public void testGetFullName() {
		assertEquals("Maren Barth", athlete.getFullName());
	}
	
	
	@Test
	public void testSetAge() {
		athlete.setAge(25);
		assertEquals(25, athlete.getAge());
	}
	
	@Test
	public void testSetAgeThrowsIllegalArgumentException() {
		try {
	        athlete.setAge(201);
	        System.out.println("Should have thrown IllegalArgumentException.");
	        assert false;
	    } catch (IllegalArgumentException e) {
	        assert true;
	    }
		try {
	        athlete.setAge(-1);
	        System.out.println("Should have thrown IllegalArgumentException.");
	        assert false;
	    } catch (IllegalArgumentException e) {
	        assert true;
	    }
	}
	
	@Test
	public void testSetWeight() {
		athlete.setWeight(60);
		assertTrue(60 == athlete.getWeight());
	}
	
	@Test
	public void testSetGender() {
		athlete.setGender(true);
		assertTrue(athlete.getGender());
		athlete.setGender(false);
		assertFalse(athlete.getGender());
	}
	
	@Test
	public void testSetMaxPulse() {
		athlete.setMaxPulse(230);
		assertTrue(230 == athlete.getMaxPulse());
	}
	
	@Test 
	public void testSetSport() {
		
		Sport basket = new Sport("Basket");
		Sport volley = new Sport("Volley");
		athlete.addSport(basket);
		athlete.addSport(volley);
		assertEquals(new ArrayList(Arrays.asList(basket, volley)),athlete.getSports());
	}
	
	
	@After
	public void tearDown() throws Exception {
	}
	
}
