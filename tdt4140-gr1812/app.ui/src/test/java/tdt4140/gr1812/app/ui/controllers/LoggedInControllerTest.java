package tdt4140.gr1812.app.ui.controllers;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import javafx.embed.swing.JFXPanel;
import javafx.scene.Parent;
import javafx.scene.SubScene;
import javafx.scene.chart.StackedBarChart;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.text.Text;
import tdt4140.gr1812.app.ui.FxApp;

public class LoggedInControllerTest {
	
	LoggedInController controller;
	
	@Before
	public void setUp() {
		final JFXPanel fxPanel = new JFXPanel();
		controller = new LoggedInController();
		controller.name = new Text();
		controller.workoutsTable = new TableView();
		controller.goal = new TableColumn();
		controller.date = new TableColumn();
		controller.duration = new TableColumn();
		controller.maxpulse = new TableColumn();
		controller.sport = new TableColumn();
		controller.chart = new StackedBarChart(controller.xdays, controller.ytime);
		controller.chartScene = new SubScene(controller.chart,50,50);
	}

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
	public void testSetWorkoutsInTable() {
		controller.setWorkoutsInTable();
		assertEquals(controller.workoutsTable.getItems(), controller.getObservableWorkouts());
	}
	
	@Test
	public void testSetChart() {
		controller.setChart();
		assertEquals(controller.xdays.getLabel(), "Dager");
		assertEquals(controller.ytime.getLabel(), "Tid");
		assertEquals(controller.chartScene.getRoot(), controller.chart);
	}
	
	@Test
	public void testUpdate() {
		controller.setCoach(false);
		controller.setCurrentUser("testuser");
		controller.update();
		assertTrue(controller.getAtLoggedInView());
	}
	
	@Test
	public void getDay() {
		assertEquals("Fredag", controller.getDay(5));
		assertEquals("Onsdag", controller.getDay(3));
		assertEquals("Sondag", controller.getDay(0));
		assertEquals("Mandag", controller.getDay(1));
	}
	
	@Test
	public void testGetDate() {
		assertEquals(controller.getDate(0), "2018-04-13");
	}

}
