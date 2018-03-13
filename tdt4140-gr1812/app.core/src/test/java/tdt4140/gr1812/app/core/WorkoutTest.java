package tdt4140.gr1812.app.core;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import tdt4140.gr1812.app.core.dataClasses.Workout;

public class WorkoutTest {
	
	private Workout workout;
	
	@Before
	public void setUp() throws Exception {
		this.workout=new Workout(140, true);
		
	}
	
	@Test
	public void testSetPulse() {
		List<Integer> pulses=new ArrayList<Integer>();
		pulses.add(100);
		pulses.add(120);
		pulses.add(200);
		workout.setPulses(100,120,200);
		assertEquals(pulses,workout.getPulses());
	}
	
	@Test
	public void testSetPulsesThrowsIllegalArgumentException() {
		try {
			workout.setPulses(251);
			System.out.println("Should have thrown IllegalArgumentException.");
			assert false;
		} catch (IllegalArgumentException e) {
			assert true;
		}
		try {
			workout.setPulses(0);
			System.out.println("Should have thrown IllegalArgumentException.");
			assert false;
		} catch (IllegalArgumentException e) {
			assert true;
		}
	}
	
	@Test
	public void testSetDistance() {
		workout.setDistance(10);
		assertEquals(10, workout.getDistance());
	}
	
	@Test
	public void testSetCalories() {
		workout.setCalories(200);
		assertEquals(200, workout.getCalories());
	}
	
	@Test
	public void testSetDate() {
		workout.setDate("11/11/11");
		assertEquals("11/11/11", workout.getDate());
	}
	
	@Test
	public void testSetComment() {
		workout.setComment("Dette var en fin økt!");
		assertEquals("Dette var en fin økt!", workout.getComment());
	}
	
	@Test
	public void testGetDuration() {
		//String testDur="2:20";
		assertEquals("2:20", workout.getDuration());
	}
	
	/*@Test
	public void testGetSports() {
		assertEquals
	}*/
	
	@Test
	public void testGetPrivacy() {
		assertEquals(true, workout.getPrivacy());
	}
	
	@After
	public void tearDown() throws Exception {
		
	}
	
	
	
	

}
