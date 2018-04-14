package tdt4140.gr1812.app.ui.controllers;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
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
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import tdt4140.gr1812.app.core.dataClasses.Sport;
import tdt4140.gr1812.app.core.dataClasses.Workout;
import tdt4140.gr1812.app.core.models.loggedIn.LoggedInModel;
import tdt4140.gr1812.app.ui.FxApp;

public class LoggedInController {
	
	private FxApp app;
	private String currentUser;
	private String selectedAthlete;
	private boolean coach;
	private List<Workout> workouts = new ArrayList<Workout>();
	private ObservableList<Workout> observableWorkouts = FXCollections.observableArrayList();
	private boolean atLoggedInView = false;
	@FXML
	Text name, tilbakeknapptekst;
	@FXML
	ImageView blank, tilbakeknapp;
	@FXML
	Hyperlink registrerOkt, tilbake;
	@FXML
	TableView workoutsTable;
	@FXML
	TableColumn goal, extraField, duration, date, maxpulse, sport;
	@FXML
	SubScene chartScene;
	
	public void registrerOkt() { // Registrer ookt hyperlink pressed
		this.atLoggedInView = false;
		this.app.goToWorkoutRegistration();
	}
	
	public void update() {
		this.atLoggedInView = true;
		name.setText(LoggedInModel.getName(this.currentUser)); 
		this.selectedAthlete = this.currentUser;
		if (this.coach) { //check if the logged in user is a coach
			this.selectedAthlete = this.app.getSelectedAthlete();
			//hide the "Registrer okt"-button
			this.blank.setVisible(true); 
			this.registrerOkt.setVisible(false);
			//make the "tilbakeknapp" visible
			this.tilbakeknapp.setVisible(true);
			this.tilbakeknapptekst.setVisible(true);
			this.tilbake.setVisible(true);
		} 
		this.setWorkoutsInTable(); //update workouts-table
		this.setChart(); //update chart
	}
	
	
	// fields needed to create the chart
	final CategoryAxis xdays = new CategoryAxis();
    final NumberAxis ytime = new NumberAxis();
    StackedBarChart<String, Number> chart =
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
	
	@SuppressWarnings({ "unchecked", "deprecation" })
	public void setChart() { //set values to the chart
		
		// change the layout
		this.xdays.setLabel("Dager");
		this.ytime.setLabel("Tid");
		this.xdays.setTickLabelFont(Font.font(14));
		this.ytime.setTickLabelFont(Font.font(14));
		this.chart.setBackground(new Background(new BackgroundFill(Color.valueOf("#FFF2E5"), new CornerRadii(1), new Insets(5))));
		
		xdays.setCategories(FXCollections.<String>observableArrayList(
                Arrays.asList(
        				this.mandag, this.tirsdag, this.onsdag,  this.torsdag, this.fredag, this.lordag, this.sondag)));
		List<String> weekdays = Arrays.asList(this.mandag, this.tirsdag, this.onsdag,  this.torsdag, this.fredag, this.lordag, this.sondag);
		
		// for testing without backend
		/*
		HashMap<String, List<Integer>> testHashMap = new HashMap();
		for (int i = 0; i< 7; i++) {
			List<Integer> testList = new ArrayList();
			for (int j= 0; j<5; j++) {
				testList.add(7);
			}
			testHashMap.put(getDate(i), testList);
		}*/
		
		// get duration in pulsezones from backend
		HashMap<String, List<Integer>> durationInPulsezonesHashMap = LoggedInModel.getPulseZones(this.selectedAthlete);
		
		List<List<Integer>> durationInPulsezones = new ArrayList();
		
		// create list with the dates for this week
		int todayInt = Calendar.getInstance().getTime().getDay();
		
		List<String> dates = new ArrayList();
		for (int i = 6; i >= 0; i--) {
			if (i > todayInt) {
				dates.add("");
			}
			else {
				dates.add(getDate(todayInt-i));
			}
		}
	    
		int a = 0;
		for (String d : dates) { // go through the last 7 dates
			for (String date: durationInPulsezonesHashMap.keySet()) { // go through the dates in the HashMap from backend
				if (d.equals(date)) { // check if the dates are the same
					durationInPulsezones.add(durationInPulsezonesHashMap.get(date)); // add the duration in pulsezones-list to the list
				}
			}
			if (durationInPulsezones.size() == a ) { // if nothing has been added to that date - add a list of zeroes (no workout that day)
				durationInPulsezones.add(Arrays.asList(0,0,0,0,0));
			}
			a++;	
		}
		
		// create the series for the chart
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
		
		chart.getData().addAll(series5, series4, series3, series2, series1);
		chartScene.setRoot(chart);
	}
	
	public String getDay(int dayNumber) {
		if (dayNumber == 0) {
			return this.sondag;
		}
		else if (dayNumber == 1) {
			return this.mandag;
		}
		else if (dayNumber == 2) {
			return this.tirsdag;
		}
		else if (dayNumber == 3) {
			return this.onsdag;
		}
		else if (dayNumber == 4) {
			return this.torsdag;
		}
		else if (dayNumber == 5) {
			return this.fredag;
		}
		else {
			return this.lordag;
		}
	}
	
	public String getDate(int i) {
	    final Calendar cal = Calendar.getInstance();
	    cal.add(Calendar.DATE, -i);
	    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	    return dateFormat.format(cal.getTime());
	}
	
	@SuppressWarnings("unchecked")
	public void setWorkoutsInTable() {
		//get workouts from backend
		this.observableWorkouts.addAll(LoggedInModel.getWorkoutsForAthlete(this.selectedAthlete)); 
		
		//set values in the workouts-table
		this.workoutsTable.setItems(observableWorkouts);
		this.goal.setCellValueFactory(new PropertyValueFactory<Workout, String>("goal"));
		this.date.setCellValueFactory(new PropertyValueFactory<Workout, Date>("dateString"));
		this.duration.setCellValueFactory(new PropertyValueFactory<Workout,Integer >("duration"));
		this.maxpulse.setCellValueFactory(new PropertyValueFactory<Workout, Integer>("maxpulse"));
		this.sport.setCellValueFactory(new PropertyValueFactory<Workout, Integer>("sport"));
		//this.extraField.setCellFactory(new PropertyValueFactory<Workout, Integer>("extraField"));
	}
	
	public void loggUt() {
		app.setCurrentUser(null);
		this.atLoggedInView = false;
		app.goToLogin();
	}
	
	public void tilbake() {
		this.atLoggedInView = false;
		app.goToCoachView();
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
	
	public boolean getAtLoggedInView() {
		return this.atLoggedInView;
	}
	
	public ObservableList<Workout> getObservableWorkouts() {
		return this.observableWorkouts;
	}

}
