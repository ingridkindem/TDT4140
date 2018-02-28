package tdt4140.gr1812.app.core;

import static org.junit.Assert.*;


import org.junit.Test;

import tdt4140.gr1812.app.core.model.SignUpModel;

public class ExampleTest {

	@Test
	public void TrueTest() {
		SignUpModel model = new SignUpModel();
		model.setcellPhoneNumber(1234);
		System.out.print(model.getcellPhoneNumber());
		//assertTrue(model.getcellPhoneNumber() == 12345); 
		
		//assertTrue(true);
	}

}
