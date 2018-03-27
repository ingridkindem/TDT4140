package tdt4140.gr1812.app.ui.controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.StackedBarChart;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import tdt4140.gr1812.app.core.dataClasses.Sport;
import tdt4140.gr1812.app.core.dataClasses.Workout;
import tdt4140.gr1812.app.core.models.loggedIn.LoggedInModel;
import tdt4140.gr1812.app.ui.FxApp;

public class LoggedInController {
	
	private FxApp app;
	private String currentUser;
	private boolean coach;
	private List<Workout> workouts = new ArrayList<Workout>();
	private ObservableList<Workout> observableWorkouts = FXCollections.observableArrayList();
	@FXML
	Text name;
	@FXML
	ImageView blank;
	@FXML
	Hyperlink registrerOkt;
	@FXML
	TableView workoutsTable;
	@FXML
	TableColumn goal, other, duration, date, maxpulse, sport;
	@FXML
	StackedBarChart chart;
	
	public void setApplication(FxApp app) {
		this.app = app;
	}
	
	public void setCoach(boolean coach) {
		this.coach = coach;
	}
	
	public void setCurrentUser(String user) {
		this.currentUser = user;
	}
	
	public void registrerOkt() { // Registrer Økt hyperlink pressed
		this.app.goToWorkoutRegistration();
	}
	
	public void update() {
		name.setText(LoggedInModel.getName(this.currentUser)); 
		if (this.coach) { //check if the logged in user is a coach
			//hide the "Registrer økt"-button
			this.blank.setVisible(true); 
			this.registrerOkt.setVisible(false);
		} 
		this.setWorkoutsInTable(); //update workouts-table
	}
	
	public void setWorkoutsInTable() {
		//get workouts from backend
		this.observableWorkouts.addAll(LoggedInModel.getWorkoutsForAthlete(this.currentUser)); //for coach - currentuser has to be changed to the athlete
		
		//set values in the workout-table
		this.workoutsTable.setItems(observableWorkouts);
		this.goal.setCellValueFactory(new PropertyValueFactory<Workout, String>("goal"));
		this.date.setCellValueFactory(new PropertyValueFactory<Workout, Date>("date"));
		this.duration.setCellValueFactory(new PropertyValueFactory<Workout,Integer >("duration"));
		this.maxpulse.setCellValueFactory(new PropertyValueFactory<Workout, Integer>("maxpulse"));
		this.sport.setCellValueFactory(new PropertyValueFactory<Workout, Integer>("sport"));
	}
	
	public void loggUt() {
		app.setCurrentUser(null);
		app.goToLogin();
	}
	
	public FxApp getApplication() {
		return this.app;
	}
	
	public String getCurrentUser() {
		return this.currentUser;
	}
	
	public boolean getCoach() {
		return this.coach;
	}

}
