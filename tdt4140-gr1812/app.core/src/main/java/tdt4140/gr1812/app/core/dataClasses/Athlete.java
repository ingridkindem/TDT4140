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
	
	// Constructor to create an athlete. Initialized with phone number, first name and last name.
	public Athlete(String phoneNumber, String firstName, String lastName) {
		this.phoneNumber = phoneNumber;
		this.firstName = firstName; 
		this.lastName = lastName;
	}
	
	// Getters for the attributes
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

	public double getWeight() {
        return weight;
    }
	
	public int getMaxPulse() {
        return maxPulse;
    }
	
	public boolean getGender() {
        return gender;
    }
	
	public List<Sport> getSports() {
	    return sports;
	}
	
	//Set-methods to change age, weight, max pulse and gender.
	public void setAge(int age) {
		if ((age < 0) || (age > 200)) {
			throw new IllegalArgumentException("Age not valid.");
		}
		this.age = age;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	public void setMaxPulse(int maxPulse) {
		this.maxPulse = maxPulse;
	}
	
	public void setGender(boolean gender) {
		this.gender = gender;
	}
	
	//Method to add a sport in the sports-list
	public void addSport(Sport sport) {
		sports.add(sport);
	}
	
}
