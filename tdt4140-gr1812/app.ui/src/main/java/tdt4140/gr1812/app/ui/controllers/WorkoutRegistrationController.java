package tdt4140.gr1812.app.ui.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.TextField;

public class WorkoutRegistrationController {
	
	//WorkoutRegistrationModel model;
	
	@FXML
	TextField lengdePåØkt;
	@FXML
	TextField puls;
	@FXML
	TextField mål;
	@FXML
	Hyperlink registrer;
	@FXML
	Hyperlink kryssUt;
	@FXML
	RadioMenuItem basket;
	@FXML
	RadioMenuItem langrenn;
	@FXML
	RadioMenuItem fotball;
	@FXML
	CheckBox privatØkt;
	
	@FXML
	public void update() {
		
	}
	
	@FXML
	public void handleRegistrer() {
		boolean b = basket.isSelected();
		boolean f = fotball.isSelected();
		boolean l = langrenn.isSelected();
		boolean c = privatØkt.isPressed();
		String lengde = lengdePåØkt.getText();
		String p = puls.getText();
		String m = mål.getText();
	}
	
	@FXML
	public void handleKryssUt() {
		
	}

}
