package tdt4140.gr1812.app.core;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import tdt4140.gr1812.app.core.dataClasses.LoginModel;
import tdt4140.gr1812.app.core.dataClasses.Tuple;

public class LoginModelTest {
	LoginModel loginmodel;
	
	@Before 
	public void setUp(){
		this.loginmodel = new LoginModel();
	}
	
	@Test
	public void testLogin() {
		assertFalse(LoginModel.login("", "") == null);
		assertFalse(null == LoginModel.login("12345678", "12345678"));
		try {
			LoginModel.login(null, "");
			fail();
		} catch(NullPointerException e) {
			
		} try {
			LoginModel.login("", null);
		} catch (NullPointerException e) {
			
		}
	}
}
