package tdt4140.gr1812.app.ui.controllers;


import java.awt.Event;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;

import javafx.scene.control.PasswordField;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
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
    private TableView<String> athletestable;
    
    @FXML
    public void initialize() {
        this.atCoachView = true;
        this.athletestable.setVisible(false);
        
        athletesButton.showingProperty().addListener(new ChangeListener<Boolean>() {
            public void changed(ObservableValue<? extends Boolean> observableValue, Boolean oldValue, Boolean newValue) 
            {
                 if(newValue.booleanValue()) {
                	 	athletestable.setVisible(true);
                 }
                 else {
                	 	athletestable.setVisible(false);
                 }
                      
            }
                      
       });
        update();
    }
    
    public void update() {

        sportsButton.getItems().clear();
        athletesButton.getItems().clear();
    }
    
    @FXML
    public void athletesButtonAction() {
    	   System.out.println("We are in Athlete click");
//       this.athletestable.setVisible(true);
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

    


    /*@Override
    public void handle(javafx.event.ActionEvent event) {
        if (event.equals(sportsButton)) {
            this.sportsButtonAction();
        }
        else if (event.equals(athletesButton)) {
            this.athletesButtonAction();
        }
        
    }
    
    @FXML
    public void sportsButtonAction(){ //ArrayList<String> differentSports Legg til denne i args her
        
        //for (String item : differentSports) {
            sportsButton.getItems().add(new MenuItem("new")); //Her skal det legges til Item.
        //} 

    }
    
    @FXML
    public void athletesButtonAction(){ //ArrayList<Athlete> differentAthletes skal inni args her
        
        for (Athlete item : differentAthletes) {
            
            String name = item.getFullName();
            String phoneNumber = item.getPhoneNumber();    
        
            MenuItem utøver = new MenuItem(name);
            utøver.setAccelerator(KeyCombination.keyCombination(phoneNumber));
            athletesButton.getItems().add(utøver);
        } 
        MenuItem utøver = new MenuItem("Hei");
        utøver.setAccelerator(KeyCombination.keyCombination("18282109"));
        athletesButton.getItems().add(utøver);
    }
    */
    
}
