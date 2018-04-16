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
	TextField puls;
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
	
	protected String currentUser;
	
	//initiates a new WorkoutRegistrationModel
	WorkoutRegistrationModel model = new WorkoutRegistrationModel();
	
	FxApp app;
	
	//specifies the name of the sport field to the sport we choose
	//also sets the prompt text for the "extraField", defined in the model
	@FXML
	public String setField() {
		boolean b = basket.isSelected();
		boolean f = fotball.isSelected();
		boolean l = langrenn.isSelected();
		
		String bas = basket.getText();
		String lang = langrenn.getText();
		String fot = fotball.getText();
		
		if (b) {
			extraField.setPromptText(model.valueForExtraField(bas));
			return bas;
		}
		if (f) {
			extraField.setPromptText(model.valueForExtraField(fot));
			return fot;
		}if (l) {
			extraField.setPromptText(model.valueForExtraField(lang));
			return lang;
		}
		return "Idrett";
	}
	
	//sets the name of the sport field to the sport we choose
	@FXML
	public void initialize() {
		idrett.setText(setField());
	}
	
	//updates the feedback text in the FXMLfile
	@FXML
	public void update() {
		feedback.setText(model.getText());
	}
	
	//method to handle a workout registration
	@FXML
	public boolean handleRegistrer() {
		boolean b = basket.isSelected(); //returns true if the basket field is selected
		boolean f = fotball.isSelected();
		boolean l = langrenn.isSelected();
		boolean c = privatOkt.isPressed();
		String lengde = lengdePaaOkt.getText(); //sets the value of "lengde" to the text written in the "lengde"-field in the view
		String eF = extraField.getText();
		String m = maal.getText();
		String p = puls.getText();
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
		boolean action = model.WorkoutRegistrationModelInit(currentUser, p, eF, lengde, s, m, c); //WorkoutRegistrationModelInit() returns true if a register is handled
		if (action) {
			app.goToLoggedIn(); 
			return true;
		}
		else {
			update();
			return false;
		}
	}
	
	//sets the String "currentUser" to a chosen user
	public void setCurrentUser(String user) {
		this.currentUser = user; 
	}
	
	//returns the current user
	public String getCurrentUser() {
		return this.currentUser;
	}

	//method to handle exit from site
	@FXML
	public void handleKryssUt() {
		app.goToLoggedIn(); 
	}
	
	//method to communicate with the FxApp 
	public void setApplication(FxApp app) {
		this.app = app;
	}

}
