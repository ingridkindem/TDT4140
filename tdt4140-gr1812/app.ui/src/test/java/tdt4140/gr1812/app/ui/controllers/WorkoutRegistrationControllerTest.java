package tdt4140.gr1812.app.ui.controllers;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import javafx.embed.swing.JFXPanel;
import javafx.scene.control.CheckBox;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import tdt4140.gr1812.app.ui.FxApp;

public class WorkoutRegistrationControllerTest {
	
	private WorkoutRegistrationController controller;

	@Before
	public void setUp() { //hva blir feil her?
		controller = new WorkoutRegistrationController();
		final JFXPanel fxPanel = new JFXPanel();
		controller.lengdePaaOkt = new TextField();
		controller.extraField = new TextField();
		controller.maal = new TextField();
		controller.basket = new RadioMenuItem();
		controller.fotball = new RadioMenuItem();
		controller.langrenn = new RadioMenuItem();
		controller.feedback = new Text();
		controller.privatOkt = new CheckBox();
	}
	
	@Test
	public void testHandleRegistrer() {
		controller.basket.setSelected(true);
		controller.fotball.setSelected(false);
		controller.langrenn.setSelected(false);
		controller.lengdePaaOkt.setText("30");
		controller.puls.setText("-50,60,70");
		controller.extraField.setText("-50,60,70");
		controller.maal.setText("mål");
		assertFalse(controller.handleRegistrer());

		controller.basket.setSelected(false);
		controller.fotball.setSelected(true);
		controller.langrenn.setSelected(false);
		controller.lengdePaaOkt.setText("-10");
		controller.puls.setText("50,60,70");
		controller.extraField.setText("50,60,70");
		controller.maal.setText("mål");
		assertFalse(controller.handleRegistrer());
		
		controller.basket.setSelected(false);
		controller.fotball.setSelected(false);
		controller.langrenn.setSelected(true);
		controller.lengdePaaOkt.setText("10");
		controller.puls.setText("-50,60,70");
		controller.extraField.setText("-50,60,70");
		controller.maal.setText("mål");
		assertFalse(controller.handleRegistrer());
	}

	@Test
	public void testSetField() {
		controller.setField();
	}
	
	@Test
	public void testInitialize() { //hvordan teste
		
	}
	@Test
	public void testUpdate() {
		controller.update();
	}
	
	@Test
	public void testSetCurrentUser() {
		String currentUser = "11111111";
		controller.setCurrentUser(currentUser);
		assertEquals(currentUser, controller.getCurrentUser());
	}

	
	@Test
	public void testHandleKryssUt() { 
		
	}
	
	@Test
	public void testSetApplication() {
		FxApp app = new FxApp();
		controller.setApplication(app);
		assertEquals(app, controller.app);
	}

}

