package tdt4140.gr1812.app.ui.controllers;

import java.util.ArrayList;
import java.util.List;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import tdt4140.gr1812.app.core.dataClasses.Athlete;
import tdt4140.gr1812.app.core.dataClasses.Coach;
import tdt4140.gr1812.app.core.models.coachModel.CoachModel;
import tdt4140.gr1812.app.ui.FxApp;

public class CoachController {

	private boolean atCoachView; // for testing
	private List<Athlete> athletes = new ArrayList<>();
	private String sport;
	private FxApp app;
	private Coach coach;

	private final ObservableList<Athlete> observableAthletes = FXCollections.observableArrayList();

	@FXML
	private MenuButton athletesButton;
	@FXML
	private TableView athletesTable;
	@FXML
	private TableColumn<Athlete, String> ColumnName;
	@FXML
	private TableColumn<Athlete, String> ColumnPhoneNumber;
	@FXML
	private Label sportLabel;
	
	
	private String currentSport; 
	private String coachUsername;  

	@FXML
	public void initialize() {
		this.atCoachView = true;
		this.athletesTable.setVisible(false);
		
		System.out.println("-1-");
		this.setColumnsInTable();

		athletesButton.showingProperty().addListener(new ChangeListener<Boolean>() {
			public void changed(ObservableValue<? extends Boolean> observableValue, Boolean oldValue,
					Boolean newValue) {
				if (newValue.booleanValue()) {
					athletesTable.setVisible(true);
				} else {
					athletesTable.setVisible(false);
				}
			}

		});
		update();
	}

	public void update() {
		athletesTable.setItems(observableAthletes);
		
	}

	public String handleSport(String coach) {
		return CoachModel.getSportForCoach(coach);
//		List<String> sports = CoachModel.getSportForCoach(coach);
//		if (sports.size() > 0) {
//			String returen = sports.get(0); 
//			return returen; 
//		}
//		return "No sports for coach"; 
	}

	public void setColumnsInTable() {
		this.ColumnName.setCellValueFactory(new PropertyValueFactory<Athlete, String>("firstName"));
		this.ColumnPhoneNumber.setCellValueFactory(new PropertyValueFactory<Athlete, String>("phoneNumber"));
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
	
	public void setUser(String username) {
		this.coachUsername = username;
		this.currentSport = handleSport(this.coachUsername);
		sportLabel.setText(this.currentSport);
		observableAthletes.setAll(createAthleteObjectList());
	}

	private class athleteObject {
		private final String fullName;
		private final String phoneNumber;

		public athleteObject(String fullName, String phoneNumber) {
			this.fullName = fullName;
			this.phoneNumber = phoneNumber;
		}
	}

	public List<Athlete> createAthleteObjectList() {
		return CoachModel.getAthletesForSport(this.currentSport);
	}


}
