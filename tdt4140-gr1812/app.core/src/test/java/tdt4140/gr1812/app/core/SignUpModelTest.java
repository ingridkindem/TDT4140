package tdt4140.gr1812.app.core;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import tdt4140.gr1812.app.core.helpers.Gender;
import tdt4140.gr1812.app.core.models.signup.SignUpModel;

public class SignUpModelTest {
	
	SignUpModel model = new SignUpModel();
	
	@Test 
	public void testCheckPhoneNumber() {
		  try {
		        model.checkPhoneNumber("123456789");
		    }
		    catch (IllegalArgumentException e){
		        assertTrue(true);
		    }
		  try {
	            model.checkPhoneNumber("1234567");
	        }
	        catch (IllegalArgumentException e){
	            assertTrue(true);
	        }
		  try {
	            model.checkPhoneNumber("abc");
	        }
	        catch (IllegalArgumentException e){
	            assertTrue(true);
	        }
	}
	
	@Test
	public void testCheckFirstname() {
		try{
			model.checkFirstName("1234");
		} catch (IllegalArgumentException e) {
			assertTrue(true);
		}
		try{
	        model.checkFirstName("");
	    } catch (IllegalArgumentException e) {
	        assertTrue(true);
	    }
	}

	
	@Test
    public void testCheckLastName() {
        SignUpModel model = new SignUpModel();
        try{
            model.checkLastName("1234");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        }
        try{
            model.checkLastName("");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        }
        
    }
    
    @Test
    public void testSignupUser() {
        SignUpModel model = new SignUpModel();
        try {
            boolean signupResponse = model.signupUser(46643025, "46643025", "basket", "lars erik", "fagernaes", 244, 254, Gender.MALE); 
            assert(signupResponse == false); 
            
        } catch (IllegalArgumentException e){
            assertTrue(true);
        }
    }
  
    
    @Test
    public void testCheckMaxPulse() {
        try{
            model.checkMaxPulse("-190");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        }
        try {
            model.checkMaxPulse("200");
            assert true;
        } catch (IllegalArgumentException e){
            assert false;
        }
    }
    
    @Test
    public void testCheckWeight() {
        try{
            model.checkWeight("-190");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        }
        try {
            model.checkWeight("200");
            assert true;
        } catch (IllegalArgumentException e){
            assert false;
        }
    }

}
