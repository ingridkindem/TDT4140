package tdt4140.gr1812.app.core;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.junit.Test;

import tdt4140.gr1812.app.core.models.loggedIn.LoggedInModel;

public class LoggedInModelTest {

	@Test
	public void testGetName() { 
		
	}
	
	@Test
	public void testGetWorkoutsForAthlete() { 
		
	}
	
	/*
	@Test
	public void testStringToDate() {
		Date date = LoggedInModel.stringToDate("2017:12:24");
		assertEquals(date, new Date(2017, 12, 24));
		try {
			LoggedInModel.stringToDate("");
			fail();
		} catch (Exception e) {	
		}
	}
	*/
	
	@Test
	public void testcheckPulse() {
		List<Integer> list = LoggedInModel.checkPulse("50,60,70");
		assertEquals(list, Arrays.asList(50,60,70));
		try {
			LoggedInModel.checkPulse("-1");
			fail();
		} catch (IllegalArgumentException e) {	
		}
	}

}
