package tdt4140.gr1812.app.core.models.signup;

import java.util.HashMap;
import org.json.JSONObject;
import tdt4140.gr1812.app.core.helpers.BackendConnector;
import tdt4140.gr1812.app.core.helpers.Gender;
import tdt4140.gr1812.app.core.helpers.Method;

public class SignUpModel {
	
	private int cellPhoneNumber;
	private String firstName;
	private String surName;
	private String passWord;
	private int maxPulse;
	private int weight;
	private String feedback;
	// private List<Sport> Idretter = new List<Sport>;

	public SignUpModel() {
		
		this.feedback = "";
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
	
	public String getFeedback() {
		return this.feedback;
	}
	public void setcellPhoneNumber(String cellPhoneNumber) {
		if (cellPhoneNumber.length() != 8) {
			this.feedback = "Illegal phonenumber.";
			throw new IllegalArgumentException("Et telefonnummer må være 8 sifre langt.");
		}
		try
		{
		    this.cellPhoneNumber = Integer.parseInt(cellPhoneNumber);
		}
		catch (NumberFormatException nfe)
		{
			this.feedback = "Illegal phonenumber.";
			throw new IllegalArgumentException("Could not convert from string to int."); 
		}
	}
		
	public String getfirstName() {
		return firstName;
	}
	
	public boolean signupUser(String phonenumber, String password, String sport, String firstName, String surname, String maxpulse, String weight, Gender gender) {
		try {
			this.setfirstName(firstName);
			this.setsurName(surname);
			this.setcellPhoneNumber(phonenumber);
			this.setmaxPulse(maxpulse);
			this.setPassWord(password);
			this.setweight(weight);

			String genderString = gender == gender.FEMALE ? "female" : "male";  

			HashMap requestParam = new HashMap<String, String>();
			requestParam.put("username", String.valueOf(phonenumber));
			requestParam.put("password", password);
			requestParam.put("sport", sport);
			requestParam.put("firstname", firstName);
			requestParam.put("surname", surname);
			requestParam.put("maxpulse", String.valueOf(maxpulse));
			requestParam.put("weight", String.valueOf(weight));
			requestParam.put("gender", genderString);

			JSONObject response = BackendConnector.makeRequest(requestParam, Method.POST, "signup");

			if (response.get("status").equals("success")) {
				this.feedback = "";
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
			this.feedback = "Illegal name.";
			throw new IllegalArgumentException("Du kan ikke skrive inn tall i et navn.");
		} else if (firstName == ""){
			this.feedback = "Illegal name.";
			throw new IllegalArgumentException("Du kan ikke ikke ha noe navn");
		}
		this.firstName = firstName;
	}
	
	public String getsurName() {
		return surName;
	}
	
	public void setsurName(String surName) {
	    if (!surName.matches("[a-åA-Å]+$")) {
	    		this.feedback = "Illegal name.";
            throw new IllegalArgumentException("Du kan ikke skrive inn tall i et navn.");
        } else if (surName == ""){
        		this.feedback = "Illegal name.";
            throw new IllegalArgumentException("Du kan ikke ikke ha noe navn");
        }
        this.surName = surName;
    }
	
	public String getPassWord() {
		return this.passWord;
	}
	public void setPassWord(String password) {
	    if (password == ""){
	    		this.feedback = "Illegal password.";
            throw new IllegalArgumentException("Du kan ikke ikke ha noe navn");
        }
		this.passWord = password;
	}
	public int getmaxPulse() {
		return maxPulse;
	}
	public void setmaxPulse(String maxPulse) {
		try {
			int mp = Integer.parseInt(maxPulse);
			if (mp < 0) {
				throw new Exception();
			}
			this.maxPulse = mp;
	    } catch (Exception e) {
	    		this.feedback = "Illegal maxpulse";
	    		throw new IllegalArgumentException("Ulovlig maxpuls");
		}
	    
	}
	
	public int getweight() {
		return weight;
	}
	public void setweight(String weight) {
	    try {
	    		int w = Integer.parseInt(weight);
		    	if (w < 0) {
		    		throw new Exception();
	        }
	        this.weight = w;	
		} catch (Exception e) {
			this.feedback = "Illegal weight.";
			throw new IllegalArgumentException("Du kan ikke ha en negativ puls!");
		}
	}
		
}