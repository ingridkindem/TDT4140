package tdt4140.gr1812.app.ui;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import tdt4140.gr1812.app.ui.controllers.LoginController;
import tdt4140.gr1812.app.ui.controllers.SignupController;


public class FxApp extends Application {
	
	private FXMLLoader fxmlLoader;
	private Stage stage;

    @Override
    public void start(Stage stage) throws Exception {
    		this.stage = stage;
        goToLogin();
        
    		//Parent root = FXMLLoader.load(getClass().getResource("/views/login/lo.fxml"));
        //Scene scene = new Scene(root);
        //stage.setScene(scene);
        //stage.show();
    }
    
    public void goToLoggedIn() {
		try {
		    replaceSceneContent("views/login/loggedIn.fxml"); //path to loggedIn-view
		    stage.show();
		} catch(Exception e) {
		    Logger.getLogger(FxApp.class.getName()).log(Level.SEVERE, null, e);;
	    }
    }
    
    public void goToSuccessfullSignup(){
        try {
            replaceSceneContent("views/signup/signedUp.fxml"); //path to loggedIn-view
            stage.show();
        } catch(Exception e) {
            Logger.getLogger(FxApp.class.getName()).log(Level.SEVERE, null, e);;
        }
    }
    
    public void goToSignup() {
    		try {
    			replaceSceneContent("views/signup/signup.fxml"); //path to signup-view
    	         SignupController controller = this.fxmlLoader.getController();
    	         controller.setApplication(this);
    			stage.show();
    		} catch(Exception e) {
    			Logger.getLogger(FxApp.class.getName()).log(Level.SEVERE, null, e);;
    		} 		
    }
    
    public void goToLogin() {
    		try {
			replaceSceneContent("views/login/login.fxml"); //path to login-view
			LoginController controller = this.fxmlLoader.getController();
			controller.setApplication(this);
			stage.show();
		} catch(Exception e) {
			Logger.getLogger(FxApp.class.getName()).log(Level.SEVERE, null, e);;
		}
    }
    
    private void replaceSceneContent(String fxml) {
    		this.fxmlLoader = new FXMLLoader(FxApp.class.getResource(fxml));
    		try {
				Parent page = (Parent) fxmlLoader.load();
				Scene scene = this.stage.getScene();
				if (scene == null) {
					scene = new Scene(page, 1000, 700);
					stage.setScene(scene);
				} else {
					stage.getScene().setRoot(page);
				} stage.sizeToScene();
			} catch (IOException e) {
				e.printStackTrace();
			}
    }

    public static void main(String[] args) {
        launch(args);
    }
}
