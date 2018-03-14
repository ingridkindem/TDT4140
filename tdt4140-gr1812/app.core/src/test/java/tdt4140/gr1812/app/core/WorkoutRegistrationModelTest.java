package tdt4140.gr1812.app.core;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Test;

import tdt4140.gr1812.app.core.dataClasses.Sport;
import tdt4140.gr1812.app.core.models.workoutRegistration.WorkoutRegistrationModel;

public class WorkoutRegistrationModelTest {

	private WorkoutRegistrationModel model = new WorkoutRegistrationModel();
	
	@Test
	public void testCheckPulse() {
		try {
			model.checkPulse("12,-3");
			System.out.println("Should have thrown IllegalArgumentException.");
			assert false;
		} catch (IllegalArgumentException e) {
			assert true;
		}
	}
	
	public void testCheckDuration() {
		try {
			model.checkDuration(0);
			System.out.println("Should have thrown IllegalArgumentException.");
			assert false;
		} catch (IllegalArgumentException e) {
			assert true;
		}
	}
	
	public void testCheckSport() {
		try {
			model.checkSport(null);
			System.out.println("Should have thrown IllegalArgumentException.");
			assert false;
		} catch (IllegalArgumentException e) {
			assert true;
		}
	}
	
	public void testWorkoutRegistrationModelInit() {
		boolean t = model.WorkoutRegistrationModelInit(20, "70,90,100", new Sport("fotball"), "Hei", true);
		assertTrue(t);
		boolean f = model.WorkoutRegistrationModelInit(-20, "70,90,100", new Sport("fotball"), "Hei", true);
		assertFalse(f);
	}
	
}
