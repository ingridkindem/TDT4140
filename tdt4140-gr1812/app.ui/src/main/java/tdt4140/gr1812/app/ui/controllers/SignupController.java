package tdt4140.gr1812.app.ui.controllers;

import java.util.Iterator;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.text.Text;
import tdt4140.gr1812.app.core.helpers.Gender;
import tdt4140.gr1812.app.core.models.signup.SignUpModel;
import tdt4140.gr1812.app.core.models.workoutRegistration.WorkoutRegistrationModel;
import tdt4140.gr1812.app.ui.FxApp;

public class SignupController {
    @FXML
    TextField mobilnummer ;
    @FXML
    Button registerButton;
    @FXML
    TextField fornavn; 
    @FXML
    TextField etternavn;
    @FXML
    PasswordField passord;
    @FXML
    TextField makspuls;
    @FXML
    TextField vekt;
    @FXML
    ToggleButton kvinneToggle;
    @FXML
    ToggleButton manToggle;
    @FXML
	MenuButton idretter;
	@FXML
	RadioMenuItem basket;
	@FXML
	RadioMenuItem langrenn;
	@FXML
	RadioMenuItem fotball;
	@FXML
	Text feedback;
		
	private String selectedSport = "";
	
	
	SignUpModel model = new SignUpModel();
	
	FxApp app;
    
    @FXML
	public void initialize() {
		idretter.setText(setField());
	}
	
  //updates the feedback text in the FXMLfile
  	@FXML
  	public void update() {
  		feedback.setText(model.getText());
  	}
  	
    @FXML
	public String setField() {
		boolean b = basket.isSelected();
		boolean f = fotball.isSelected();
		boolean l = langrenn.isSelected();
		
		String bas = basket.getText();
		String lang = langrenn.getText();
		String fot = fotball.getText();
		
		if (b) {
			selectedSport = "basket";
			return bas;
		}
		if (f) {
			selectedSport = "fotball";
			return fot;
		}if (l) {
			selectedSport = "langrenn";
			return lang;
		}
		return "Idrett";
    }
		
    
    @FXML
    private void registerButtonClicked(ActionEvent event) {
        // Button was clicked, do something...
        
        Gender gender;
        if (manToggle.isSelected()){
            gender = Gender.MALE; 
        }
        else {
            gender = Gender.FEMALE; 
        }
        
        boolean registerSuccess = model.signupUser(Integer.parseInt(mobilnummer.getText()), passord.getText(), 
                selectedSport, fornavn.getText(), etternavn.getText(), Integer.parseInt(makspuls.getText()), Integer.parseInt(vekt.getText()), gender);
        
        if(registerSuccess) {
        	app.setCurrentUser(mobilnummer.getText());
            app.goToWorkoutRegistration(); //will eventually go to athlete-profile
        }
        else {
           update();
           System.out.println("No success in register :,( ");  
        }
    }
           
    public void setApplication(FxApp app) {
        this.app = app; 
    }
    
    @FXML
    public void handleBack() {
        app.goToLogin();
    }
    
    public void init() {
        // do initialization and configuration work...
        Iterator<MenuItem> it = idretter.getItems().iterator(); 
        while (it.hasNext()){
            MenuItem item = it.next();
            item.setOnAction(a->{ //lambda expression
                MenuItem selectedItem = (MenuItem) a.getSource();
                selectedSport = selectedItem.getText(); 
            });
        }
    }
}