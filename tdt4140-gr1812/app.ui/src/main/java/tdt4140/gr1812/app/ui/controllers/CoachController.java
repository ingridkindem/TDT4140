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
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.TableView.TableViewSelectionModel;
import javafx.scene.control.TextField;
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
	@FXML
	private Button selectAthlete;
	@FXML
	private TextField infoText, infoText1;
	
	private String selectedAthlete;
	private String currentSport; 
	private String coachUsername;  

	@FXML
	public void initialize() {
		this.atCoachView = true;
		this.athletesTable.setVisible(false);
		this.selectAthlete.setVisible(false);
		
		
		this.setColumnsInTable();
		
		//If athletesButtun are pressed, show TableView (table with athletes)
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
		
		//This is retrieved from Googlio
		//Fetches the value that has been clicked in the view and returns it.
		athletesTable.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {

            @Override
		    public void changed(ObservableValue observableValue, Object oldValue, Object newValue) {
		        //Check whether item is selected and set value of selected item to Label
		        if(athletesTable.getSelectionModel().getSelectedItem() != null) 
		        {    
		           
		           TableViewSelectionModel selectionModel = athletesTable.getSelectionModel();
		           ObservableList selectedCells = selectionModel.getSelectedCells();
		           TablePosition tablePosition = (TablePosition) selectedCells.get(0);
		           Object val = tablePosition.getTableColumn().getCellData(newValue);
		           
		           //Checks if a chosen element from the column is a phonenumber
		           String nummer = (String) val;
		           if (nummer.matches("[0-9]+")) {
		               
		               selectedAthlete = nummer;
		               System.out.println(selectedAthlete);
		               
		               selectAthlete.setVisible(true);
		               athletesTable.setVisible(false);
		               
		               System.out.print("current sport = " + currentSport);
		               
		               athletesButton.setText(CoachModel.getAthletesFullName(nummer, currentSport) + "   Mob: " + selectedAthlete);
		               
		           }
		           
		           
		        }
            }   
		});
		
		update();
		
	}

	public void update() {
		athletesTable.setItems(observableAthletes);
	}

	//Fetches the sports of a coach
	public String handleSport(String coach) {
		return CoachModel.getSportForCoach(coach);
	}

	//Sets columns in a table of athletes. Firstname and number.
	public void setColumnsInTable() {
		this.ColumnName.setCellValueFactory(new PropertyValueFactory<Athlete, String>("firstName"));
		this.ColumnPhoneNumber.setCellValueFactory(new PropertyValueFactory<Athlete, String>("phoneNumber"));
	}
	
	//For testing
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
		System.out.println("-2-");
		this.coachUsername = username;
		this.currentSport = handleSport(this.coachUsername);
		sportLabel.setText(this.currentSport);
		observableAthletes.setAll(createAthleteObjectList());
	}

	//Fetches athletes who belong to the sport
	public List<Athlete> createAthleteObjectList() {
		return CoachModel.getAthletesForSport(this.currentSport);
	}
	
	/*
	 * class MyEventHandler implements EventHandler<MouseEvent> {
 w
        @Override
        public void handle(MouseEvent t) {
            TableCell c = (TableCell) t.getSource();
            int index = c.getIndex();
            System.out.println("id = " + recordList.get(index).getId());
            System.out.println("name = " + recordList.get(index).getName());
            System.out.println("lastName = " + recordList.get(index).getLastName());
            System.out.println("email = " + recordList.get(index).getEmail());
        }
    }
    
    
     Callback<TableColumn, TableCell> stringCellFactory =
                new Callback<TableColumn, TableCell>() {
            @Override
            public TableCell call(TableColumn p) {
                MyStringTableCell cell = new MyStringTableCell();
                cell.addEventFilter(MouseEvent.MOUSE_CLICKED, new MyEventHandler());
                return cell;
            }
        };
        
        //      List<String> sports = CoachModel.getSportForCoach(coach);
//      if (sports.size() > 0) {
//          String returen = sports.get(0); 
//          return returen; 
//      }
//      return "No sports for coach"; 
	 */


}
