package tdt4140.gr1812.app.core.dataClasses;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import tdt4140.gr1812.app.core.dataClasses.Sport;
import tdt4140.gr1812.app.core.models.loggedIn.LoggedInModel;

public class Workout {
	
	private List<Integer> pulses= new ArrayList<Integer>();
	private int duration;
	private Sport sport;
	private String goal;
	private Date date;
	private boolean privacy; //true=private
	private int maxPulse;
	private String dateString;
	
	//Constructor to create a workout. Initialized with sport and possibilty for privacy mode.
	public Workout(Sport sport, boolean privacy) {
		this.sport = sport;
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
	
	public void setPulses(List<Integer> pulses) {
//		for (int pulse : pulses) {
//			if (pulse<=0 || pulse>250) {
//				throw new IllegalArgumentException("See a doctor.");
//			}
//			this.pulses.add(pulse);
//		}
		setMaxpulse(pulses);
		this.pulses=pulses;
	}
	
	private void setMaxpulse(List<Integer> pulses) { //called from setPulses()
		int max = 0;
		for (int pulse : pulses) {
			if (pulse > max) {
				max = pulse;
			}
		} 
		this.maxPulse = max;
	}
	
	public void setDate(Date date) {
		this.date=date;
	}
	
	public void setDateString(String date) {
		this.dateString = date;
	}
	
	public void setGoal(String goal) {
		this.goal=goal;
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
	
	public Sport getSport() {
		return sport;
	}
	
	public boolean getPrivacy() {
		return privacy;
	}
	
	public List<Integer> getPulses() {
		return pulses;
	}
	
	public int getMaxpulse() {
		return this.maxPulse;
	}
	
	public Date getDate() {
		return date;
	}
	
	public String getGoal() {
		return goal;
	}
	
	public String getDateString() {
		return this.dateString;
	}
}
