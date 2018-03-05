package tdt4140.gr1812.app.core.model;

import java.util.HashMap;
import org.json.JSONObject;
import tdt4140.gr1812.app.core.helpers.BackendConnector;
import tdt4140.gr1812.app.core.helpers.Method;


public class SignUpModel {
	
	private int cellPhoneNumber;
	private String firstName;
	private String surName;
	private String passWord;
	private int maxPulse;
	private int weight;
	// private List<Sport> Idretter = new List<Sport>;

	public SignUpModel() {
		this.cellPhoneNumber = getcellPhoneNumber();
		this.firstName = getfirstName();
        this.surName = getsurName();
        this.passWord = getPassWord();
        this.maxPulse = getmaxPulse();
        this.weight = getweight();
	}
	
	public int getcellPhoneNumber() {
		return cellPhoneNumber;
	}
	public void setcellPhoneNumber(String cellPhoneNumber) {
		if (cellPhoneNumber.length() != 8) {
			throw new IllegalArgumentException("Et telefonnummer må være 8 sifre langt.");
		}
		try
		{
		    this.cellPhoneNumber = Integer.parseInt(cellPhoneNumber);
		}
		catch (NumberFormatException nfe)
		{
		   throw new IllegalArgumentException("Could not convert from string to int."); 
		}
	}
		
	public String getfirstName() {
		return firstName;
	}
	
	public boolean signupUser(int phonenumber, String password, String sport, String firstName, String surname, int maxpulse, int weight) {
	       try {
	           HashMap requestParam = new HashMap<String, String>();
	           requestParam.put("username", "testuser");
	           requestParam.put("password", "testuser");
	           requestParam.put("sport", sport);
	           requestParam.put("firstname", firstName);
	           requestParam.put("surname", surname);
	           requestParam.put("maxpulse", String.valueOf(maxpulse));
	           requestParam.put("weight", String.valueOf(maxpulse));
	           JSONObject response = BackendConnector.makeRequest(requestParam, Method.POST, "testPu.php");
	           
	           if (response.get("status").equals("success")) {
	               return true;
	           }
	           else {
	               return false; 
	           }
	       } catch (Exception e) {
	           e.printStackTrace();
	           return false;
	       }
	}
	
	public void setfirstName(String firstName) {
		if (!firstName.matches("[a-åA-Å]+$")) {
			throw new IllegalArgumentException("Du kan ikke skrive inn tall i et navn.");
		} else if (firstName == ""){
			throw new IllegalArgumentException("Du kan ikke ikke ha noe navn");
		}
		this.firstName = firstName;
	}
	
	public String getsurName() {
		return surName;
	}
	
	public void setsurName(String surName) {
	    if (!surName.matches("[a-åA-Å]+$")) {
            throw new IllegalArgumentException("Du kan ikke skrive inn tall i et navn.");
        } else if (surName == ""){
            throw new IllegalArgumentException("Du kan ikke ikke ha noe navn");
        }
        this.surName = surName;
    }
	
	public String getPassWord() {
		return this.passWord;
	}
	public void setPassWord(String password) {
	    if (password == ""){
            throw new IllegalArgumentException("Du kan ikke ikke ha noe navn");
        }
		this.passWord = password;
	}
	public int getmaxPulse() {
		return maxPulse;
	}
	public void setmaxPulse(int maxPulse) {
	    if (maxPulse < 0) {
	        throw new IllegalArgumentException("Du kan ikke ha en negativ puls!");
	    }
		this.maxPulse = maxPulse;
	}
	
	public int getweight() {
		return weight;
	}
	public void setweight(int weight) {
	    if (weight < 0) {
            throw new IllegalArgumentException("Du kan ikke ha en negativ puls!");
        }
        this.weight = weight;
    }

}

