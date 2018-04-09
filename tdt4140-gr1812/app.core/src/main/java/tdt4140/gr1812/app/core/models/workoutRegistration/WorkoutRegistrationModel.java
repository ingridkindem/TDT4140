package tdt4140.gr1812.app.core.models.workoutRegistration;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import org.json.JSONObject;

import tdt4140.gr1812.app.core.dataClasses.Sport;
import tdt4140.gr1812.app.core.dataClasses.Workout;
import tdt4140.gr1812.app.core.helpers.BackendConnector;
import tdt4140.gr1812.app.core.helpers.Method;

public class WorkoutRegistrationModel {
	
	private String text = "";
	//private String sportParametre = "";
	
	
	public boolean WorkoutRegistrationModelInit(String username, String extraField, String duration, Sport sport, String goal, boolean privacy) {		
		try {
			int d = checkDuration(duration);
			
			checkSport(sport);
			text = "";
			if (sport.getSport().equals("langrenn")) {
				List<Integer> pulsesList = checkPulse(extraField);
			}
			if (sport.getSport().equals("basket")) {
				int basketThrows = checkThrows(extraField);
			}
			if (sport.getSport().equals("fotball")) {
				int penalties = checkPenalties(extraField);
			}
			Workout workout = new Workout(sport, privacy);		
			workout.setDuration(d);
			workout.setGoal(goal);
			workout.setDate(Calendar.getInstance().getTime());
			String p;
			if (privacy) {
				p = "1";
			} else {
				p = "0";
			}
			//send to database. Hvis godkjent - return true. Hvis feil - return false
			HashMap requestParam = new HashMap<String, String>();
			requestParam.put("duration", String.valueOf(duration));
			requestParam.put("username", username);
			requestParam.put("extraField", extraField);
			requestParam.put("goal", goal);
			requestParam.put("sport", sport.getSport());
			requestParam.put("privacy", p);

			
			System.out.println(requestParam.toString());
			JSONObject response = BackendConnector.makeRequest(requestParam, Method.POST, "workoutRegistration");
			System.out.println("Response from server = " + response.toString());
			if (response.get("status").equals("success")) {
	               return true; 
	           }
			return false;
		} catch(Exception e) {
			e.printStackTrace();
			return false;
		}
		
	}
	
	public List<Integer> checkPulse(String pulses) {
		try {
			List<Integer> pulsesList = new ArrayList<Integer>();
			String[] p = pulses.split(",");
			for (String i : p) {
				i.trim();
				int pulseInt = Integer.parseInt(i);
				if (pulseInt < 0) {
					throw new Exception();
				}
				pulsesList.add(pulseInt);
			}
			return pulsesList;
		} catch (Exception e) {
			text = "Illegal pulses.";
			throw new IllegalArgumentException("Pulses must be integers.");
		}
		
	}
	
	public int checkThrows(String basketThrows) {
		try {
			int bThrows = Integer.parseInt(basketThrows);
			if (bThrows < 0) {
				throw new Exception();
			}
			return bThrows;
		}
		catch (Exception e) {
			text = "Illegal throws.";
			throw new IllegalArgumentException("Throws must me a positive integer.");
		}
	}
	
	public int checkPenalties(String penalties) {
		try {
			int p = Integer.parseInt(penalties);
			if (p < 0) {
				throw new Exception();
			}
			return p;
		}
		catch (Exception e) {
			text = "Illegal penalties.";
			throw new IllegalArgumentException("Penalties must me a positive integer.");
		}
	}
	
	public int checkDuration(String duration) {
		try {
			int d = Integer.parseInt(duration);
			if (d <= 0) {
				throw new Exception();
			} return d;
		} catch (Exception e) {
			text = "Can't have negative duration.";
			throw new IllegalArgumentException("Can't have negative duration.");
		}
		
	}
	
	public String getText() { 
		return text;
	}
	
	public void checkSport(Sport sport) {
		if (sport == null) {
			text = "Sport field is empty";
			throw new IllegalArgumentException("Sport field is empty");
		}
	}
}
