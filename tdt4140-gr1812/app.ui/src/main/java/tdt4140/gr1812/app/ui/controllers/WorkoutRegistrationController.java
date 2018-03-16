package tdt4140.gr1812.app.ui.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import tdt4140.gr1812.app.core.dataClasses.Sport;
import tdt4140.gr1812.app.core.models.workoutRegistration.WorkoutRegistrationModel;
import tdt4140.gr1812.app.ui.FxApp;

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
	Text feedback;
	
	WorkoutRegistrationModel model = new WorkoutRegistrationModel();
	FxApp app;
	
	
	@FXML
	public void update() {
		feedback.setText(model.getText());
	}
	
	@FXML
	public boolean handleRegistrer() {
		boolean b = basket.isSelected();
		boolean f = fotball.isSelected();
		boolean l = langrenn.isSelected();
		boolean c = privatØkt.isPressed();
		String lengde = lengdePåØkt.getText();
		String p = puls.getText();
		String m = mål.getText();
		Sport s = null;
		if (b) {
			s = new Sport("basket");
		}
		if (f) {
			s = new Sport("fotball");
		}
		if (l) {
			s = new Sport("langrenn");
		}
		boolean action = model.WorkoutRegistrationModelInit(Integer.parseInt(lengde), p, s, m, c);
		if (action) {
			// g� tilbake til brukerens profil
			return true;
		}
		else {
			update();
			return false;
		}
	}
	
	@FXML
	public void handleKryssUt() {
		// g� tilbke til brukerens profil
	}
	
	public void setApplication(FxApp app) {
		this.app = app;
	}

}
