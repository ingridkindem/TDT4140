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
		assertTrue(LoggedInModel.getWorkoutsForAthlete("").isEmpty());
	}
	
	@Test
	public void testGetPulseZones() {
		assertTrue(LoggedInModel.getPulseZones("").isEmpty());
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

}
