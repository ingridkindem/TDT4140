package tdt4140.gr1812.app.core.Model;

public class SignUpModel {
	
	private int cellPhoneNumber;
	private String firstName;
	private String surName;
	private int height;
	private int maxPulse;
	private int weight;
	// private List<Sport> Idretter = new List<Sport>;
	
	public SignUpModel() {
		this.cellPhoneNumber = getcellPhoneNumber();
		this.firstName = getfirstName();
		this.surName = getsurName();
		this.height = getheight();
		this.maxPulse = getmaxPulse();
		this.weight = getweight();
	}
	
	public int getcellPhoneNumber() {
		return cellPhoneNumber;
	}
	public void setcellPhoneNumber(int cellPhoneNumber) {
		this.cellPhoneNumber = cellPhoneNumber;
	}
	public String getfirstName() {
		return firstName;
	}
	public void setfirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getsurName() {
		return surName;
	}
	public void setsurName(String surName) {
		this.surName = surName;
	}
	public int getheight() {
		return height;
	}
	public void setheight(int height) {
		this.height = height;
	}
	public int getmaxPulse() {
		return maxPulse;
	}
	public void setmaxPulse(int maxPulse) {
		this.maxPulse = maxPulse;
	}
	public int getweight() {
		return weight;
	}
	public void setweight(int weight) {
		this.weight = weight;
	}
	

}
