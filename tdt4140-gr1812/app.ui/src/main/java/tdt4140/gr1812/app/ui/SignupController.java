package tdt4140.gr1812.app.ui;

import java.util.Iterator;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import tdt4140.gr1812.app.core.helpers.Gender;
import tdt4140.gr1812.app.core.model.SignUpModel;

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
    private TextField passord;
    
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
        
        boolean registerSuccess = model.signupUser(Integer.parseInt(mobilnummer.getText()), passord.getText(), 
                selectedSport, fornavn.getText(), etternavn.getText(), Integer.parseInt(makspuls.getText()), Integer.parseInt(vekt.getText()), gender);
        
        if(registerSuccess) {
            System.out.println("Success with register! :D ");
        }
        else {
            System.out.println("No success in register :,( ");
        }
    }
    
    private String selectedSport = "Basket";
    
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