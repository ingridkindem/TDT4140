package tdt4140.gr1812.app.core.dataClasses;

public class Sport {

	private String sportsName; 

	//Constructor to create a sport with a sports name. 
	public Sport(String sportsName) {
		this.sportsName = sportsName;
	}

	//A method to get the sport.  
	public String getSport() {
		return this.sportsName; 
	}

}
