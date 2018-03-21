package tdt4140.gr1812.app.ui.controllers;


import java.awt.Event;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import com.sun.javafx.collections.MappingChange.Map;
import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;

import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
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
    private String sport;
    private FxApp app; 
    private Coach coach;
    
    private final ObservableList<athleteObject> data = 
            FXCollections.observableArrayList(this.createAthleteObjectList());
            
    @FXML
    private Button sportsButton;
    
    @FXML
    private MenuButton athletesButton;
    
    @FXML
    private TableView<athleteObject> athletesTable;
    @FXML
    private TableColumn<athleteObject, String> ColumnName; 
    @FXML
    private TableColumn<athleteObject, String> ColumnPhoneNumber; 
    
    @FXML
    public void initialize() {
        this.atCoachView = true;
        this.athletesTable.setVisible(false);
        this.sportsButton.setText(this.handleSport());
        
        this.setColumnsInTable();
        
        
        athletesButton.showingProperty().addListener(new ChangeListener<Boolean>() {
            public void changed(ObservableValue<? extends Boolean> observableValue, 
                    Boolean oldValue, Boolean newValue) 
            {
                 if(newValue.booleanValue()) {
                	 	athletesTable.setVisible(true);
                 }
                 else {
                	 	athletesTable.setVisible(false);
                 }
                      
            }
                      
       });
        update();
    }
    
    public void update() {
        athletesTable.getItems().clear();
    }
        
    public String handleSport() { 
    //method for handling sports to each coach 
    		this.sport = CoachModel.getSportForCoach(coach.getName());
    		return "hei"; //skal returnere sport sånn egt.
    }
    
    public void setColumnsInTable() {
        athletesTable.setEditable(true);
        ColumnName.setCellValueFactory(new PropertyValueFactory<athleteObject, String> ("NAVN"));
        ColumnPhoneNumber.setCellValueFactory(new PropertyValueFactory<athleteObject, String> ("MOBILNUMMER"));
        
        athletesTable.getColumns().addAll(this.ColumnName, this.ColumnPhoneNumber);  
        athletesTable.setItems(data);
    }
    
    @FXML
    public void athletesButtonAction() {
           System.out.println("We are in Athlete click");
    }
   
   
    
    public boolean getAtCoachView() {
        return this.atCoachView;
    }
    
    
    public void setApplication(FxApp app) {
        this.app = app;
    }
    
    private class athleteObject{
        private final String fullName;
        private final String phoneNumber;
        
        public athleteObject(String fullName, String phoneNumber) {
            this.fullName = fullName;
            this.phoneNumber = phoneNumber;
        }
    }
    
    public List<athleteObject> createAthleteObjectList(){
        List<athleteObject> temporaryAthletes = new ArrayList<>();
        for (int i = 0; i < CoachModel.getFullNames.length(); i++) {
            String fullName = CoachModel.getFullNames.get(i);
            String phoneNumber = CoachModel.getPhoneNumbers.get(i);
            temporaryAthletes.add(new athleteObject(fullName, phoneNumber));
        }
        return temporaryAthletes;
        
    }
    
    
}
