package tdt4140.gr1812.app.ui.controllers;


import java.util.ArrayList;
import java.util.List;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import tdt4140.gr1812.app.core.dataClasses.Athlete;
import tdt4140.gr1812.app.core.dataClasses.Coach;
import tdt4140.gr1812.app.core.models.coachModel.CoachModel;
import tdt4140.gr1812.app.ui.FxApp;


public class CoachController {

    private boolean atCoachView; //for testing
    private List<Athlete> athletes = new ArrayList<>();
    private List<String> sports = new ArrayList<>();
    private FxApp app; 
    
    @FXML
    private MenuButton sportsButton;
    
    @FXML
    private MenuButton athletesButton;
    
    @FXML
    public void initialize() {
        this.atCoachView = true;
        update();
    }
    
    public void update() {
        sportsButton.getItems().clear();
        athletesButton.getItems().clear();
    }
    
    @FXML
    public void sportsButtonAction(ArrayList<String> differentSports){
        
        for (String item : differentSports) {
            
            sportsButton.getItems().add(new MenuItem(item));
        } 
    }
    
    @FXML
    public void athletesButtonAction(ArrayList<Athlete> differentAthletes){
        
        for (Athlete item : differentAthletes) {
            
            String name = item.getName();
            String phoneNumber = item.getPhoneNumber();
            
            athletesButton.getItems().add(new MenuItem(name + phoneNumber));
        } 
    }
        
    @FXML
    public void handleSport() { 
    //method for handling sports to each coach 
    		this.sports = CoachModel.getSportsForCoach("46643025");
    		
    }
    
    @FXML
    public void handleAthletes() {
    //method for handling athletes to each sport
        this.athletes =  CoachModel.getAthletesForSport("basket");
    }
   
    
    public boolean getAtCoachView() {
        return this.atCoachView;
    }
    
    
    public void setApplication(FxApp app) {
        this.app = app;
    }
    
}
