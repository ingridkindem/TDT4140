package tdt4140.gr1812.app.ui.controllers;


import java.util.List;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import tdt4140.gr1812.app.core.dataClasses.Athlete;
import tdt4140.gr1812.app.core.dataClasses.Coach;
import tdt4140.gr1812.app.core.models.coachModel.CoachModel;
import tdt4140.gr1812.app.ui.FxApp;


public class CoachController {


    //Coach coach;
    private boolean atCoachView; //for testing
    @FXML
    private Text prompt;
    @FXML
    private PasswordField setPassword;
    @FXML
    private TextField setPhoneNumber;
    
    private FxApp app;
    
    private String p = "";
   
    
    @FXML
    public void initialize() {
        this.atCoachView = true;
        update();
    }
    
    public void update() {
        // need to reset sport -> athlete -> table
    }
        
    @FXML
    public void handleSport() { 
    //method for updating the athlete list after choosing sport    
    		List<Athlete> athleter =  CoachModel.getAthletesForSport("basket");
    		List<String> sporter = CoachModel.getSportsForCoach("46643025");
    }
    

    
    
    public boolean getAtCoachView() {
        return this.atCoachView;
    }
    
    
    public void setApplication(FxApp app) {
        this.app = app;
    }
    
}
