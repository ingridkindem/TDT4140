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
	
	@FXML
	TextField lengdePaaOkt;
	@FXML
	TextField puls;
	@FXML
	TextField maal;
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
	CheckBox privatOokt;
	@FXML
	Text feedback;
	
	private String currentUser;
	
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
		boolean c = privatOokt.isPressed();
		String lengde = lengdePaaOkt.getText();
		String p = puls.getText();
		String m = maal.getText();
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
		boolean action = model.WorkoutRegistrationModelInit(currentUser, lengde, p, s, m, c);
		if (action) {
			app.goToWorkoutRegistration(); //will eventually go to athlete-profile
			return true;
		}
		else {
			update();
			return false;
		}
	}
	
	public void setCurrentUser(String user) {
		this.currentUser = user; 
	}
	
	@FXML
	public void handleKryssUt() {
		app.goToWorkoutRegistration(); //will eventually go to athlete-profile
	}
	
	public void setApplication(FxApp app) {
		this.app = app;
	}

}
