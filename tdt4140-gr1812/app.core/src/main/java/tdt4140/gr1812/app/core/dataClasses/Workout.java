package tdt4140.gr1812.app.core.dataClasses;

import java.util.ArrayList;
import java.util.List;
import tdt4140.gr1812.app.core.dataClasses.Sport;

public class Workout {
	
	private List<Integer> pulses= new ArrayList<Integer>();
	private int duration;
	private int distance;
	private List<Sport> sports = new ArrayList<Sport>();
	private int calories;
	private String comment;
	private String date;
	private boolean privacy; //true=private
	
	//Constructor to create a workout. Initialized with sport and possibilty for privacy mode.
	public Workout(Sport sport, boolean privacy) {
		this.sports.add(sport);
		this.privacy=privacy;
	}
	
	//Set-methods to set distance pulse, distance, calories, comments and date
	
	//duration in minutes
	public void setDuration(int duration) {
		if (duration<0) {
			throw new IllegalArgumentException("An exercise has to last for a time");
		}
		this.duration=duration*60; //converting from minutes to seconds
	}
	
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
		
		String min="";
		
		int hours=this.duration/3600;
		int minutes=(this.duration%3600)/60;
		
		String h="";
		
		if(hours==0) {
			h+="0";
		}
		else {
			h+=Integer.toString(hours);
		}
		
		if (minutes<10) {
			min+="0"+Integer.toString(minutes);
		}
		else {
			min+=Integer.toString(minutes);
		}
		
		String durationString=h+":"+min;
		
		return durationString;
	}
	
	public List<Sport> getSport() {
		return sports;
	}
	
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
}
