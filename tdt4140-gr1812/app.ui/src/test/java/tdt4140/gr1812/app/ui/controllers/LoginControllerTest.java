package tdt4140.gr1812.app.ui.controllers;

import static org.junit.Assert.assertTrue;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;
import javafx.application.Platform;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import tdt4140.gr1812.app.ui.FxApp;



public class LoginControllerTest extends ApplicationTest {
    
    LoginController loginController; 
    Stage stage; 
    @Override
    public void start(Stage stage) throws Exception {
      this.stage = stage; 
      this.stage.show();
      this.stage.toFront();
    }
    
    @Test 
    public void handleLoginTest() {
        
        class OneShotTask implements Runnable {
            Stage stage;
            OneShotTask(Stage inputStage) { stage = inputStage; }
            public void run() {
                
                FxApp theApp = new FxApp();
                try {
                    theApp.start(this.stage);
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                LoginController loginController = new LoginController(); 
                loginController.setApplication(theApp);
                
                
                TextField phonenumberTextField = new TextField();
                phonenumberTextField.setText("46643025");
                PasswordField passwordField = new PasswordField();
                passwordField.setText("46643025");
                
                loginController.setSetPhoneNumber(phonenumberTextField);
                loginController.setSetPassword(passwordField);
                boolean loginResult = loginController.handleLogin(); 
                System.out.println(loginResult);
                assertTrue(loginResult);
            }
        }
        
        Platform.runLater(new OneShotTask(this.stage));        
    }
    
}