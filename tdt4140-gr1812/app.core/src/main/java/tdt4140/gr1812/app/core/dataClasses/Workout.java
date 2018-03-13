package tdt4140.gr1812.app.core.dataClasses;

import java.util.ArrayList;
import java.util.List;

//hvordan få inn sportsclass?
//hvordan fikse den ene testen

public class Workout {
	
	private List<Integer> pulses= new ArrayList<Integer>();
	private String duration;
	private int distance;
	private List<Sport> sports = new ArrayList<Sport>();
	private int calories;
	private String comment;
	private String date;
	private boolean privacy; //true=private
	
	//Constructor to create a workout. Initialized with duration (in minutes) and sport. Possibilty for privacy mode.
	public Workout(int duration, boolean privacy) {
		
		String min="";
		
		int hours=duration/60;
		int minutes=duration%60;
		
		String h=Integer.toString(hours);
		
		if (minutes<10) {
			min+="0"+Integer.toString(minutes);
		}
		else {
			min+=Integer.toString(minutes);
		}
		
		this.duration=h+":"+min;
		//this.sports.add(sport);
		
		this.privacy=privacy;
	}
	
	//Set-methods to change pulse, distance, calories, comments and date
	public void setPulses(int...pulses) {
		for (int pulse : pulses) {
			if (pulse<=0 || pulse>250) {
				throw new IllegalArgumentException("See a doctor.");
			}
			this.pulses.add(pulse);
		}
	}
	//distance in km
	public void setDistance(int distance) {
		this.distance=distance;
	}
	
	public void setCalories(int calories) {
		this.calories=calories;
	}
	
	//set date, format "dd/mm/yy"
	public void setDate(String date) {
		this.date=date;
	}
	
	public void setComment(String comment) {
		this.comment=comment;
	}
	
	//getters for attributes
	public String getDuration() {
		return duration;
	}
	
	/*public List<Sport> getSport() {
		return sports;
	}*/
	
	public boolean getPrivacy() {
		return privacy;
	}
	
	public List<Integer> getPulses() {
		return pulses;
	}
	
	public int getDistance() {
		return distance;
	}
	
	public int getCalories() {
		return calories;
	}
	
	public String getDate() {
		return date;
	}
	
	public String getComment() {
		return comment;
	}
	
	public static void main(String[] args) {
		int hours=140/60;
		int minutes=140%60;
		String min="";
		if (minutes<10) {
			min+="0"+Integer.toString(minutes);
		}
		else {
			min+=Integer.toString(minutes);
		}
		String duration=Integer.toString(hours)+":"+min;
		 
		System.out.println(duration);
		
	}

}
