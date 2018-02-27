package tdt4140.gr1812.app.core;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import tdt4140.gr1812.app.core.Model.SignUpModel;

//HUSK Å LEGGE INN SPORT KLASSEN

public class signUpModelTest {
	
	SignUpModel model;
	
	@Before
	public void setup() {
		this.model = new SignUpModel();
		model.setcellPhoneNumber(98989898);
		model.setfirstName("Toralf");
		model.setsurName("Frich");
		model.setheight(202);
		model.setmaxPulse(200);
		model.setweight(100);
		
	}
	
	@Test
	public void testSetCellPhoneNumber() {
		assertTrue(this.model.getcellPhoneNumber().isDigit());
	}

	@Test
	public void test() {
		fail("Not yet implemented");
	}

}
