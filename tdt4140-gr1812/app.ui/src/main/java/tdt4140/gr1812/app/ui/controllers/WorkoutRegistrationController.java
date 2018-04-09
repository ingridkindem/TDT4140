package tdt4140.gr1812.app.ui.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.MenuButton;
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
	TextField extraField;
	@FXML
	TextField maal;
	@FXML
	Hyperlink registrer;
	@FXML
	Hyperlink kryssUt;
	@FXML
	MenuButton idrett;
	@FXML
	RadioMenuItem basket;
	@FXML
	RadioMenuItem langrenn;
	@FXML
	RadioMenuItem fotball;
	@FXML
	CheckBox privatOkt;
	@FXML
	Text feedback;
	
	private String currentUser;
	
	WorkoutRegistrationModel model = new WorkoutRegistrationModel();
	FxApp app;
	
	@FXML
	public String setField() {
		boolean b = basket.isSelected();
		boolean f = fotball.isSelected();
		boolean l = langrenn.isSelected();
		
		String bas = basket.getText();
		String lang = langrenn.getText();
		String fot = fotball.getText();
		
		if (b) {
			return bas;
		}
		if (f) {
			return fot;
		}if (l) {
			return lang;
		}
		return "Idrett";
	}
	
	@FXML
	public void initialize() {
		idrett.setText(setField());
	}
	
	@FXML
	public void update() {
		feedback.setText(model.getText());
	}
	

	@FXML
	public boolean handleRegistrer() {
		boolean b = basket.isSelected();
		boolean f = fotball.isSelected();
		boolean l = langrenn.isSelected();
		boolean c = privatOkt.isPressed();
		String lengde = lengdePaaOkt.getText();
		String p = extraField.getText();
		String m = maal.getText();
		Sport s = null;
		if (b) {
			s = new Sport("basket");
			//kjore funksjon for aa sende til backend
			//kunne endre idrett-feltet
			//sette varierende tekstfelt med det backend sender 
		}
		if (f) {
			s = new Sport("fotball");
		}
		if (l) {
			s = new Sport("langrenn");
		}
		boolean action = model.WorkoutRegistrationModelInit(currentUser, p, lengde, s, m, c);
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
