package tdt4140.gr1812.app.core.model;

public class SignUpModel {
	
	private int cellPhoneNumber;
	private String firstName;
	private String surName;
	private int height;
	private int maxPulse;
	private int weight;
	// private List<Sport> Idretter = new List<Sport>;
	
	/*
	 * 	this.firstName = getfirstName();
		this.surName = getsurName();
		this.height = getheight();
		this.maxPulse = getmaxPulse();
		this.weight = getweight();
	 */
	
	public SignUpModel() {
		this.cellPhoneNumber = getcellPhoneNumber();
	}
	
	public int getcellPhoneNumber() {
		return cellPhoneNumber;
	}
	public void setcellPhoneNumber(int cellPhoneNumber) {
		try{
			if (String.valueOf(cellPhoneNumber).length() > 8) {
				throw new IllegalArgumentException("Du kan ikke ha et tall som er lengre enn 8 tall.");
			}
			this.cellPhoneNumber = cellPhoneNumber;
		} catch (IllegalArgumentException e){
			System.out.println("Du må skrive inn et tall");
		}
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
	
//	public static void main(String[] args) {
//		SignUpModel toralf = new SignUpModel();
//		toralf.setcellPhoneNumber(12345678);
//		System.out.println(toralf.cellPhoneNumber);
//	}

}

