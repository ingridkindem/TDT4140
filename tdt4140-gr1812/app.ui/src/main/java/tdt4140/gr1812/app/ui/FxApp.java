package tdt4140.gr1812.app.ui;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import tdt4140.gr1812.app.core.models.coachModel.CoachModel;
import tdt4140.gr1812.app.ui.controllers.CoachController;
import tdt4140.gr1812.app.ui.controllers.HomeScreenController;
import tdt4140.gr1812.app.ui.controllers.LoggedInController;
import tdt4140.gr1812.app.ui.controllers.LoginController;
import tdt4140.gr1812.app.ui.controllers.SignupController;
import tdt4140.gr1812.app.ui.controllers.WorkoutRegistrationController;


public class FxApp extends Application {
    
	private FXMLLoader fxmlLoader;
	private Stage stage;
	private String currentUser; 
	private boolean coach;
	private String selectedAthlete;

    @Override
    public void start(Stage stage) throws Exception {
    		this.stage = stage;
        goToLoggedIn();
    }
    
    public void setCurrentUser(String user) {
    		this.currentUser = user; 
    }
    
    public void setCoach(boolean coach) {
    		this.coach = coach;
    }
    
    public void goToLoggedIn() {
		try {
			replaceSceneContent("views/loggedIn/LoggedIn.fxml"); //path to LoggedIn-view
			LoggedInController controller = this.fxmlLoader.getController();
			controller.setApplication(this);
			controller.setCurrentUser(this.currentUser);
			controller.setCoach(this.coach);
			controller.update();
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
    
    public void goToHome() {
        try {
            replaceSceneContent("views/homeScreen/HomeScreen.fxml"); //path to homeScreen-view
             HomeScreenController controller = this.fxmlLoader.getController();
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
    
    public void goToWorkoutRegistration() {
		try {
			replaceSceneContent("views/workoutRegistrationView/WorkoutRegistrationView.fxml"); //path to workoutRegistrationView-view
			WorkoutRegistrationController controller = this.fxmlLoader.getController();
			controller.setApplication(this);
			controller.setCurrentUser(this.currentUser);
			stage.show();
		} catch(Exception e) {
			Logger.getLogger(FxApp.class.getName()).log(Level.SEVERE, null, e);;
		}
    }
    
    public void goToCoachView() {
		try {
			replaceSceneContent("views/coach/CoachView.fxml"); //path to coachView
			CoachController controller = this.fxmlLoader.getController();
			if (this.currentUser == null) {
				System.out.println("The currentUser = null :/ ");
			}
			controller.setUser(this.currentUser);
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
				scene = new Scene(page, 1000, 700); //set pagesize to width=1000 and height=700
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
    
    public void setSelectedAthlete(String selectedAthlete) {
    		this.selectedAthlete = selectedAthlete;
    }
    
    public String getCurrentUser() {
    		return this.currentUser;
    }
    
    public String getSelectedAthlete() {
    		return this.selectedAthlete;
    }
}