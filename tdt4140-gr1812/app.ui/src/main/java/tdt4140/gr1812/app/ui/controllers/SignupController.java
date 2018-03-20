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
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.text.Text;
import tdt4140.gr1812.app.core.helpers.Gender;
import tdt4140.gr1812.app.core.models.signup.SignUpModel;
import tdt4140.gr1812.app.ui.FxApp;



public class SignupController {
    @FXML
    private TextField mobilnummer ;
    
    @FXML
    private Button registerButton;
    
    @FXML
    private TextField fornavn; 
    
    @FXML
    private TextField etternavn;
    
    @FXML
    private PasswordField passord;
    
    @FXML
    private TextField makspuls;
    
    @FXML
    private TextField vekt;
    
    @FXML
    private MenuButton idretter;
    
    @FXML
    private ToggleButton kvinneToggle;

    @FXML
    private ToggleButton manToggle;
    
    @FXML
    private Text feedback;
    
    @FXML
    private void registerButtonClicked(ActionEvent event) {
        // Button was clicked, do something...
        SignUpModel model = new SignUpModel();

        
        Gender gender;
        if (manToggle.isSelected()){
            gender = Gender.MALE; 
        }
        else {
            gender = Gender.FEMALE; 
        }
     
        	boolean registerSuccess = model.signupUser(mobilnummer.getText(), passord.getText(), 
            selectedSport, fornavn.getText(), etternavn.getText(), makspuls.getText(), vekt.getText(), gender);
        
        	this.feedback.setText(model.getFeedback());
        	
        if(registerSuccess) {
            app.goToSuccessfullSignup();
        }
        else {
            System.out.println("No success in register :,( ");
        }
    }
    
    @FXML 
    private void backButtonClicked(ActionEvent event) {
    		app.goToLogin();
    }
    
    private String selectedSport = "Basket";
    private FxApp app; 
    
    
    public void setApplication(FxApp app) {
        this.app = app; 
    }
    
    public void initialize() {
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