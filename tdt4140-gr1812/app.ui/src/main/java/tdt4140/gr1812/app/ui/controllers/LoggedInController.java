package tdt4140.gr1812.app.ui.controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.SubScene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.StackedBarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
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
	SubScene chartScene;
	
	public StackedBarChart getChart() {
		return this.chart;
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
		this.setChart(); //update chart
	}
	
	
	final CategoryAxis xdays = new CategoryAxis();
    final NumberAxis ytime = new NumberAxis();
    final StackedBarChart<String, Number> chart =
            new StackedBarChart<String, Number>(xdays, ytime);
	final XYChart.Series<String, Number> series1 =
            new XYChart.Series<String, Number>();
    final XYChart.Series<String, Number> series2 =
            new XYChart.Series<String, Number>();
    final XYChart.Series<String, Number> series3 =
            new XYChart.Series<String, Number>();
    final XYChart.Series<String, Number> series4 =
            new XYChart.Series<String, Number>();
    final XYChart.Series<String, Number> series5 =
            new XYChart.Series<String, Number>();
    final static String mandag = "Mandag";
    final static String tirsdag = "Tirsdag";
    final static String onsdag = "Onsdag";
    final static String torsdag = "Torsdag";
    final static String fredag = "Fredag";
    final static String lordag = "Lordag";
    final static String sondag = "Sondag";
	
	@SuppressWarnings("unchecked")
	public void setChart() {
		this.xdays.setLabel("Dager");
		this.ytime.setLabel("Tid");
		xdays.setCategories(FXCollections.<String>observableArrayList(
                Arrays.asList(mandag, tirsdag, onsdag, torsdag, fredag, lordag, sondag)));
		List<List<Integer>> testList = new ArrayList();
		for (int i = 0; i< 7; i++) {
			testList.add(new ArrayList());
			for (int j= 0; j<5; j++) {
				testList.get(i).add(7);
			}
		}
		
		List<List<Integer>> durationInPulsezones = LoggedInModel.getPulseZones(this.currentUser);
		List<String> weekdays = Arrays.asList(mandag, tirsdag, onsdag, torsdag, fredag, lordag, sondag);
		
		for (int i = 0; i<7; i++) {
			series1.setName("Pulssone " + 1);
		    series1.getData().add(new XYChart.Data<String, Number>(weekdays.get(i), durationInPulsezones.get(i).get(0)));
		}
		for (int i = 0; i<7; i++) {
			series2.setName("Pulssone " + 2);
		    series2.getData().add(new XYChart.Data<String, Number>(weekdays.get(i), durationInPulsezones.get(i).get(1)));
		}
		for (int i = 0; i<7; i++) {
			series3.setName("Pulssone " + 3);
		    series3.getData().add(new XYChart.Data<String, Number>(weekdays.get(i), durationInPulsezones.get(i).get(2)));
		}
		for (int i = 0; i<7; i++) {
			series4.setName("Pulssone " + 4);
		    series4.getData().add(new XYChart.Data<String, Number>(weekdays.get(i), durationInPulsezones.get(i).get(3)));
		}
		for (int i = 0; i<7; i++) {
			series5.setName("Pulssone " + 5);
		    series5.getData().add(new XYChart.Data<String, Number>(weekdays.get(i), durationInPulsezones.get(i).get(4)));
		}
		chart.getData().addAll(series1, series2, series3, series4, series5);
		chartScene.setRoot(chart);
	}
	
	@SuppressWarnings("unchecked")
	public void setWorkoutsInTable() {
		//get workouts from backend
		this.observableWorkouts.addAll(LoggedInModel.getWorkoutsForAthlete(this.currentUser)); //for coach - currentuser has to be changed to the athlete
		Workout w = new Workout(new Sport("Fotball"), false);
		w.setDate(new Date());
		w.setDuration(120);
		w.setGoal("bli raskere");
		w.setPulses(Arrays.asList(50,60,90));
		//this.observableWorkouts.add(w);
		
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
	
	//set-methods:
		public void setApplication(FxApp app) {
			this.app = app;
		}
		
		public void setCoach(boolean coach) {
			this.coach = coach;
		}
		
		public void setCurrentUser(String user) {
			this.currentUser = user;
		}
	
	//get-methods:
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
