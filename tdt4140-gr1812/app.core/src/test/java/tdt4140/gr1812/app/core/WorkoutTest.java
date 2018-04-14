package tdt4140.gr1812.app.core;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import tdt4140.gr1812.app.core.dataClasses.Sport;
import tdt4140.gr1812.app.core.dataClasses.Workout;

public class WorkoutTest {
	
	private Workout workout;
	
	private Sport sport;
	
	@Before
	public void setUp() throws Exception {
		sport = new Sport("Fotball");
		this.workout=new Workout(sport, true);
		
	}
	
	@Test
	public void testSetPulses() {
		workout.setPulses(Arrays.asList(251,53));
		assertEquals(Arrays.asList(251,53), workout.getPulses());
		assertEquals(251, workout.getMaxpulse());
	}
	
	@Test
	public void testSetDuration() {
		try {
			workout.setDuration(-3);
			fail();
		} catch (IllegalArgumentException e) {
			
		}
		workout.setDuration(140);
		assertEquals("2:20",workout.getDuration());
	}
	
	@Test
	public void testSetDate() {
		workout.setDate(new Date(2018,5,12));
		assertEquals(new Date(2018,5,12), workout.getDate());
	}
	
	@Test
	public void testSetGoal() {
		workout.setGoal("Dette var en fin ookt!");
		assertEquals("Dette var en fin ookt!", workout.getGoal());
	}
	
	@Test
	public void testGetSport() {
		assertEquals(this.sport, workout.getSport());
	}
	
	@Test
	public void testGetPrivacy() {
		assertEquals(true, workout.getPrivacy());
	}
	
	

}
