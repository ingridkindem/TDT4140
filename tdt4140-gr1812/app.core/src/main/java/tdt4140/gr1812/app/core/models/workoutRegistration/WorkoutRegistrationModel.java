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

	public boolean WorkoutRegistrationModelInit(int duration, String pulses, Sport sport, String goal, boolean privacy) {
		try {
			List<Integer> pulsesList = checkPulse(pulses);
			checkDuration(duration);
			checkSport(sport);
			text = "";
			Workout workout = new Workout(sport, privacy);		
			workout.setDuration(duration);
			workout.setGoal(goal);
			workout.setDate(Calendar.getInstance().getTime());
			String p;
			if (privacy) {
				p = "1";
			} else {
				p = "0";
			}
			//send til database. Hvis godkjent - return true. Hvis feil - return false
			HashMap requestParam = new HashMap<String, String>();
			requestParam.put("duration", String.valueOf(duration));
			requestParam.put("pulses", pulses);
			requestParam.put("goal", goal);
			requestParam.put("sport", sport);
			requestParam.put("privacy", p);
			
			JSONObject response = BackendConnector.makeRequest(requestParam, Method.POST, "workoutRegistration");
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
		List<Integer> pulsesList = new ArrayList<Integer>();
		String[] p = pulses.split(",");
		for (String i : p) {
			i.trim();
			int pulseInt = Integer.parseInt(i);
			if (pulseInt < 0) {
				text = "Can't have negative pulse.";
				throw new IllegalArgumentException("Can't have negative pulse.");
			}
			pulsesList.add(pulseInt);
		}
		return pulsesList;
	}
	
	public void checkDuration(int duration) {
		if (duration <= 0) {
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
