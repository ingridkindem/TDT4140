package tdt4140.gr1812.app.core.controllers.login;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class LoginController {
	
	//Login login;
	
	private boolean atLoginView; //for testing
	@FXML
	private Text prompt;
	@FXML
	private PasswordField setPassword;
	@FXML
	private TextField setPhoneNumber;
	
	@FXML
	public void initialize() {
		//login = new Login();
		this.atLoginView = true;
		update();
	}
	
	public void update() {
		//this.prompt.setText(login.getPrompt());
	}
		
	@FXML
	public boolean handleLogin() { //return true if phonenumber and password is correct
		String phoneNumber = this.setPhoneNumber.getText();
		String password = this.setPassword.getText();
		boolean checkInput = true; //check if the input is correct
		if (checkInput) {
			//change view to loggedIn
			this.atLoginView = false;
			return true;
		}
		update();
		return false;
	}
	
	@FXML
	public void handleRegister() {
		//change view to signUp
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
	
}
