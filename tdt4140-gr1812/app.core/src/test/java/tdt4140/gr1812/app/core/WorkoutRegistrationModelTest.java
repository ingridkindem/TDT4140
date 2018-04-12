package tdt4140.gr1812.app.core;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

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
		List<Integer> liste = model.checkPulse("50,60,70");
		List<Integer> fasitListe = Arrays.asList(50,60,70);
		assertEquals(liste, fasitListe);
	}
	
	@Test
	public void testCheckThrows() {
		try {
			model.checkThrows("-17");
			System.out.println("Should have thrown IllegalArgumentException");
			assert false;
		} catch (IllegalArgumentException e) {
			assert true;
		}
		int test_throw = model.checkThrows("50");
		int fasit_throw = 50;
		assertEquals(test_throw, fasit_throw);
	}
	
	@Test
	public void testCheckGameTime() {
		try {
			model.checkGameTime("-78");
			System.out.println("Should have thrown IllegalArgumentException");
			assert false;
		} catch (IllegalArgumentException e) {
			assert true;
		}
		int test_time = model.checkGameTime("50");
		int fasit_time = 50;
		assertEquals(test_time,fasit_time);
	}
	
	@Test
	public void testCheckDuration() {
		try {
			model.checkDuration("0");
			System.out.println("Should have thrown IllegalArgumentException.");
			assert false;
		} catch (IllegalArgumentException e) {
			try {
				model.checkDuration("30");
				assert true;
			} catch (Exception e2) {
				assert false;
			}
		}
	}
	
	@Test
	public void testCheckDistance() {
		try {
			model.checkDistance("-20");
			System.out.println("Should have thrown IllegalArgumentException.");
			assert false;
		} catch (IllegalArgumentException e) {
			try {
				model.checkDistance("30");
				assert true;
			} catch (Exception e2) {
				assert false;
			}
		}
	}
	
	@Test
	public void testValueForExtraField() {
		assertEquals(model.valueForExtraField("Basket"), "Antall kast i kurven");
		assertEquals(model.valueForExtraField("Fotball"), "Antall spilte minutter");
		assertEquals(model.valueForExtraField("Langrenn"), "Antall kilometer");
		assertEquals(model.valueForExtraField("hei"), null);
	}

	@Test
	public void testCheckSport() {
		try { 
			model.checkSport(null);
			System.out.println("Should have thrown IllegalArgumentException.");
			assert false;
		} catch (IllegalArgumentException e) {
			try {
				model.checkSport(new Sport(""));
				assert true;
			} catch (Exception e2) {
				assert false;
			}
		}
		assertEquals(model.getText(), "Sport field is empty");
	}
	
	@Test
	public void testWorkoutRegistrationModelInit() {  //failer fortsatt, problem med server?
		boolean t = model.WorkoutRegistrationModelInit("46643025", "111,12", "10", "123", new Sport("basket"), "Hei", true);
		assertTrue(t); 
		boolean t2 = model.WorkoutRegistrationModelInit("2020", "110,130", "70", "122", new Sport("basket"), "Hei", false);
		assertTrue(t2);
		boolean f = model.WorkoutRegistrationModelInit("20", "20,20", "70","-10", new Sport("fotball"), "Hei", true);
		assertFalse(f);
	}
	
}
