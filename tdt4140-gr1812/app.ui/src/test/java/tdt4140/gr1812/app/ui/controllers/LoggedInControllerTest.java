package tdt4140.gr1812.app.ui.controllers;

import static org.junit.Assert.*;

import org.junit.Test;

import tdt4140.gr1812.app.ui.FxApp;

public class LoggedInControllerTest {
	
	LoggedInController controller = new LoggedInController();

	@Test
	public void testSetApplication() {
		FxApp app = new FxApp();
		controller.setApplication(app);
		assertEquals(app, controller.getApplication());
	}
	
	@Test
	public void testSetCoach() {
		boolean coach = true;
		controller.setCoach(coach);
		assertEquals(coach, controller.getCoach());
	}
	
	@Test
	public void testSetCurrentUser() {
		String currentUser = "12121212";
		controller.setCurrentUser(currentUser);
		assertEquals(currentUser, controller.getCurrentUser());
	}
	
	@Test
	public void testLoggUt() {
		FxApp app = new FxApp();
		controller.setApplication(app);
		controller.loggUt();
		assertNull(app.getCurrentUser());
	}
	
	@Test
	public void testRegistrerOkt() {
		
	}
	
	@Test
	public void testSetWorkoutsInTable() {
		
	}

}
