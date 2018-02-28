package tdt4140.gr1812.app.core;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import tdt4140.gr1812.app.core.model.SignUpModel;

//HUSK Å LEGGE INN SPORT KLASSEN

public class SignUpModelTest {
	
	SignUpModel model;
	
	SignUpModel test = new SignUpModel();
	
	@Test
	public void testSetCellPhoneNumber() {
		test.setcellPhoneNumber(1234);
		assertTrue(test.getcellPhoneNumber() == 1234);
	}

	@Test
	public void test() {
		fail("Not yet implemented");
	}
	
	/*
	 * model.setfirstName("Toralf");
		model.setsurName("Frich");
		model.setheight(202);
		model.setweight(100);
		model.setmaxPulse(200);
	 */

}
