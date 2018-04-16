package tdt4140.gr1812.app.core;

import static org.junit.Assert.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.junit.Test;

import tdt4140.gr1812.app.core.models.loggedIn.LoggedInModel;

public class LoggedInModelTest {
	
	LoggedInModel model = new LoggedInModel();
	
	@Test
	public void testGetName() { 
		assertEquals(LoggedInModel.getName(""), "failure");
	}
	
	@Test
	public void testGetWorkoutsForAthlete() { 
		LoggedInModel.getWorkoutsForAthlete("12345678", false);
		assertTrue(LoggedInModel.getWorkoutsForAthlete("", false).isEmpty());
	}
	
	@Test
	public void testGetPulseZones() {
		assertTrue(LoggedInModel.getPulseZones("", false).isEmpty());
	}
	
	@Test
	public void testStringToDate() {
		String date = LoggedInModel.stringToDate("10 Apr 2018 22:00:00 GMT");
		assertEquals(date, "2018-04-10");
		try {
			String d = LoggedInModel.stringToDate("");
			assertNull(d);
		} catch (Exception e) {	
			fail();
		}
	}
	
	@Test
	public void testGetStringAsList() {
		List<Integer> list = LoggedInModel.getStringAsList("50, 60, 70");
		assertEquals(list, Arrays.asList(50,60,70));
		try {
			LoggedInModel.getStringAsList("-1");
			fail();
		} catch (IllegalArgumentException e) {	
			
		}  
	}
	
	@Test
	public void testHoursToMinutes() {
		assertEquals(LoggedInModel.hoursToMinutes("2:00"), 120);
		assertEquals(LoggedInModel.hoursToMinutes("0:10"), 10);
	}
	
	@Test
	public void testGetMonth() {
		assertEquals(LoggedInModel.getMonth("Jan"), 0);
		assertEquals(LoggedInModel.getMonth("Feb"), 1);
		assertEquals(LoggedInModel.getMonth("Mar"), 2);
		assertEquals(LoggedInModel.getMonth("Apr"), 3);
		assertEquals(LoggedInModel.getMonth("May"), 4);
		assertEquals(LoggedInModel.getMonth("Jun"), 5);
		assertEquals(LoggedInModel.getMonth("Jul"), 6);
		assertEquals(LoggedInModel.getMonth("Aug"), 7);
		assertEquals(LoggedInModel.getMonth("Sep"), 8);
		assertEquals(LoggedInModel.getMonth("Oct"), 9);
		assertEquals(LoggedInModel.getMonth("Nov"), 10);
		assertEquals(LoggedInModel.getMonth("Des"), 11);
	}
	
	@Test
	public void testDeleteUser() {
		assertFalse(LoggedInModel.deleteUser(""));
	}

}
