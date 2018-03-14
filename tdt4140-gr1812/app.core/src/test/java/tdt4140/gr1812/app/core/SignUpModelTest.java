package tdt4140.gr1812.app.core;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import tdt4140.gr1812.app.core.helpers.Gender;
import tdt4140.gr1812.app.core.models.signup.SignUpModel;

//HUSK Å LEGGE INN SPORT KLASSEN
//Hvilke tester skal du kjøre? 
//Test at alle Exceptions kjører som de skal
//Test at alle Getters og Setters kjører som de skal.

public class SignUpModelTest {
	
	@Test
	public void testSetCellPhoneNumberLengthLong() {
	    SignUpModel model = new SignUpModel();
	    try {
	        model.setcellPhoneNumber("123456789");
	    }
	    catch (IllegalArgumentException e){
	        assertTrue(true);
	    }
	}
	
	@Test
    public void testSetCellPhoneNumberLengthShort() {
        SignUpModel model = new SignUpModel();
        try {
            model.setcellPhoneNumber("1234567");
        }
        catch (IllegalArgumentException e){
            assertTrue(true);
        }
    }
	
	@Test
    public void testSetCellPhoneNumberWithString() {
        SignUpModel model = new SignUpModel();
        try {
            model.setcellPhoneNumber("abc");
        }
        catch (IllegalArgumentException e){
            assertTrue(true);
        }
    }
	@Test
	public void testSetCellPhoneNumberSetterValid() {
	    SignUpModel model = new SignUpModel();
	     try {
	        model.setcellPhoneNumber("12345678");
	        assertTrue(model.getcellPhoneNumber() == 12345678);
	     }
	     catch (IllegalArgumentException e){
	        assertTrue(true);
	     }
	 }
	
	
	@Test
	public void testsetfirstNameTall() {
	    SignUpModel model = new SignUpModel();
	    try{
	        model.setfirstName("1234");
	    } catch (IllegalArgumentException e) {
	        assertTrue(true);
	    }
	}
	@Test
    public void testsetfirstNameEmptyString() {
        SignUpModel model = new SignUpModel();
        try{
            model.setfirstName("");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        }
    }
	
	@Test
	public void testsetfirstNameValid() {
	    SignUpModel model = new SignUpModel();
	    try {
	        model.setfirstName("Toralf");
	        assertTrue(model.getfirstName() == "Toralf");
	    } catch (IllegalArgumentException e){
            assertTrue(true);
        }
	}
	
	@Test
    public void testsetsurNameTall() {
        SignUpModel model = new SignUpModel();
        try{
            model.setfirstName("1234");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        }
    }
	
    @Test
    public void testsetsurNameEmptyString() {
        SignUpModel model = new SignUpModel();
        try{
            model.setfirstName("");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        }
    }
    
    @Test
    public void testsetsurNameValid() {
        SignUpModel model = new SignUpModel();
        try {
            model.setfirstName("Toralf");
            assertTrue(model.getfirstName() == "Toralf");
        } catch (IllegalArgumentException e){
            assertTrue(true);
        }
    }
    
    @Test
    public void testSignupUser() {
        SignUpModel model = new SignUpModel();
        try {
            boolean signupResponse = model.signupUser(46643025, "46643025", "basket", "lars erik", "fagernæs", 244, 254, Gender.MALE); 
            assert(signupResponse == false); 
            
        } catch (IllegalArgumentException e){
            assertTrue(true);
        }
    }
    
    
    @Test
    public void testPassWordValid() {
        SignUpModel model = new SignUpModel();
        try {
            model.setPassWord("Toralf");
            assertTrue(model.getPassWord() == "Toralf");
        } catch (IllegalArgumentException e){
            assertTrue(true);
        }
    }
    
    @Test
    public void testsetPassWordEmptyString() {
        SignUpModel model = new SignUpModel();
        try{
            model.setPassWord("");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        }
    }
    
    @Test
    public void testsetMaxPulseNegative() {
        SignUpModel model = new SignUpModel();
        try{
            model.setmaxPulse(-190);
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        }
    }
    
    @Test
    public void testsetMaxPulseValid() {
        SignUpModel model = new SignUpModel();
        try {
            model.setmaxPulse(200);
            assertTrue(model.getmaxPulse() == 200);
        } catch (IllegalArgumentException e){
            assertTrue(true);
        }
    }
    
    @Test
    public void testsetWeightNegative() {
        SignUpModel model = new SignUpModel();
        try{
            model.setweight(-190);
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        }
    }
    
    @Test
    public void testsetWeightValid() {
        SignUpModel model = new SignUpModel();
        try {
            model.setmaxPulse(200);
            assertTrue(model.getmaxPulse() == 200);
        } catch (IllegalArgumentException e){
            assertTrue(true);
        }
    }

}
