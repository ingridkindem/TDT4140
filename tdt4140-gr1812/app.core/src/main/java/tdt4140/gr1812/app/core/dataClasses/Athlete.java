package tdt4140.gr1812.app.core.dataClasses;

import java.util.ArrayList;
import java.util.List;

public class Athlete {
	
	private String phoneNumber; 
	private String firstName; 
	private String lastName; 
	private int age;
	private double weight;
	private int maxPulse;
	private boolean gender; //true is female, false is male
	private List<Sport> sports = new ArrayList<Sport>();
	
	
	public Athlete(String phoneNumber, String firstName, String lastName) {
		this.phoneNumber = phoneNumber;
		this.firstName = firstName; 
		this.lastName = lastName;
	}
	
	public String getPhoneNumber() {
		return phoneNumber;
	}
	
	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}
	
	public String getFullName() {
		return firstName + " " + lastName;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		if ((age < 0) || (age > 200)) {
			throw new IllegalArgumentException("Age not valid.");
		}
		this.age = age;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	public int getMaxPulse() {
		return maxPulse;
	}

	public void setMaxPulse(int maxPulse) {
		this.maxPulse = maxPulse;
	}
	
	public boolean getGender() {
		return gender;
	}
	
	public void setGender(boolean gender) {
		this.gender = gender;
	}

	public List<Sport> getSports() {
		return sports;
	}
	
	public void addSport(Sport sport) {
		sports.add(sport);
	}
	
}
