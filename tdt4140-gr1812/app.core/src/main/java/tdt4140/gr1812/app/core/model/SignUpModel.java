package tdt4140.gr1812.app.core.model;

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

