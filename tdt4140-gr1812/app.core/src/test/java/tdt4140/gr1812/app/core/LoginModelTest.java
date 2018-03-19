package tdt4140.gr1812.app.core;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import tdt4140.gr1812.app.core.dataClasses.LoginModel;

public class LoginModelTest {
	LoginModel loginmodel;
	
	@Before 
	public void setUp(){
		this.loginmodel = new LoginModel();
	}

	@Test
	public void testFalseLogin() {
	    assert(this.loginmodel.login("testuser22","testuser1") == false);  
	}
	
	@Test
    public void testCorrectLogin() {
        assert(this.loginmodel.login("32323232","testuser"));  
    }

	@Test
	public void testEmtyPasswordThrowsNullPointerException() {
		try {
			this.loginmodel.login("95902393",null);
	        System.out.println("Should have thrown IllegalArgumentException.");
	        assert false;
	    } catch (NullPointerException e) {
	        assert true;
	    }	
	}
	
	@Test
	public void testEmptyPhoneNumberThrowsNullPointerException() {
		try {
	        this.loginmodel.login(null,"123password");
	        System.out.println("Should have thrown NullPointerException.");
	        assert false;
	    } catch (NullPointerException e) {
	        assert true;
	    }	
	}
}
