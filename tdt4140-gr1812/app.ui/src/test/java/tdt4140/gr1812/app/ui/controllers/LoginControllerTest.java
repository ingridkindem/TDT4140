package tdt4140.gr1812.app.ui.controllers;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
import javafx.embed.swing.JFXPanel;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import tdt4140.gr1812.app.ui.FxApp;

public class LoginControllerTest {

	private LoginController loginController;
	
	@Before
	public void setUp() {
		this.loginController = new LoginController();
		final JFXPanel fxPanel = new JFXPanel(); // Must be here to initialize toolkit (for PasswordField() and TextField())
		Text t = new Text();
		this.loginController.setPrompt(t);
		this.loginController.setApplication(new FxApp());
	}
	
	@Test
	public void testInitialize() {
		this.loginController.initialize();
		//check that stage is LoginView
		assertTrue(this.loginController.getAtLoginView()); 
	}
	
	@Test
	public void testHandleLogin() {
		//set phone number and password correct
		this.loginController.setSetPhoneNumber(new TextField("95493939"));
		PasswordField pf1 = new PasswordField();
		pf1.setText("123");
		this.loginController.setSetPassword(pf1);
		assertTrue(this.loginController.handleLogin());
		//check that stage is not LoginView
		assertFalse(this.loginController.getAtLoginView()); 
		
		//set phonenumber and password uncorrect 
		// ! Denne delen av koden gir feil til den er riktig i LoginCotroller !
		this.loginController.setSetPhoneNumber(new TextField("432"));//Hvordan gjøre dette?
		PasswordField pf2 = new PasswordField();
		pf2.setText("123");
		this.loginController.setSetPassword(pf2);
		assertFalse(this.loginController.handleLogin());
		//check that stage is LoginView
		assertTrue(this.loginController.getAtLoginView()); 
	}
	
	@Test
	public void testHandleRegistration() {
		//check that stage is not LoginView
		this.loginController.handleSignup();
		assertFalse(this.loginController.getAtLoginView());
	}

}