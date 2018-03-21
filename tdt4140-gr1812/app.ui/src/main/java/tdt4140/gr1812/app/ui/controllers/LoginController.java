package tdt4140.gr1812.app.ui.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import tdt4140.gr1812.app.core.dataClasses.LoginModel;
import tdt4140.gr1812.app.ui.FxApp;


public class LoginController {
	
	//Login login;
	
	private boolean atLoginView; //for testing
	@FXML
	private Text prompt;
	@FXML
	private PasswordField setPassword;
	@FXML
	private TextField setPhoneNumber;
	
	private FxApp app;
	
	private String p = "";
	
	@FXML
	public void initialize() {
		//login = new Login();
		this.atLoginView = true;
		update();
	}
	
	public void update() {
		this.prompt.setText(p);
	}
		
	@FXML
	public boolean handleLogin() { //return true if phonenumber and password is correct
		String phoneNumber = this.setPhoneNumber.getText();
		String password = this.setPassword.getText();

		boolean checkInput = true;//LoginModel.login(phoneNumber, password); //login.login(phoneNumber, password); //check if the input is correct

		if (checkInput) {
			p = "";
			app.goToWorkoutRegistration();
			this.atLoginView = false;
			return true;
		} else {
			p = "Wrong username or password";
		}
		update();
		return false;
	}
	
	@FXML
	public void handleSignup() {
		app.goToSignup();
		this.atLoginView = false;
	}
	
	public boolean getAtLoginView() {
		return this.atLoginView;
	}
	
	public void setSetPhoneNumber(TextField tf) {
		this.setPhoneNumber = tf;
	}
	
	public void setSetPassword(PasswordField pf) {
		this.setPassword = pf;
	}
	
	public void setPrompt(Text t) {
		this.prompt = t;
	}
	
	public void setApplication(FxApp app) {
		this.app = app;
	}
	
}
