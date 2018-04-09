package tdt4140.gr1812.app.core.models.loggedIn;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import tdt4140.gr1812.app.core.dataClasses.Athlete;
import tdt4140.gr1812.app.core.dataClasses.Sport;
import tdt4140.gr1812.app.core.dataClasses.Workout;
import tdt4140.gr1812.app.core.helpers.BackendConnector;
import tdt4140.gr1812.app.core.helpers.Method;

public final class LoggedInModel {
	
	public static String getName(String username) { //returns the name belonging to the phonenumber(username)
		
		String name = "failure";
		HashMap requestparam = new HashMap<String, String>();
		requestparam.put("username", username);
		try {
			JSONObject response = BackendConnector.makeRequest(requestparam, Method.POST, ""); // må endres
			if (response.get("status").equals("success")) {
				name = response.get("firstname").toString() + " " + response.get("surname").toString();
			} else if (response.get("status").equals("failure")) {
				name = "Couldn't load name";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return name;
	}

	public static List<Workout> getWorkoutsForAthlete(String username){ //returns a list of the athlete's workouts 
		ArrayList<Workout> returnList = new ArrayList();
	
		HashMap requestParam = new HashMap<String, String>();
        requestParam.put("username", username);
        
        try {
        		JSONObject response = BackendConnector.makeRequest(requestParam, Method.POST, "workoutsForAthlete");
        		if (response.get("status").equals("success")) {
        			JSONArray objectArray = response.getJSONArray("workouts");
        			for (int i = 0; i < objectArray.length(); i++) {
        				JSONObject obj = objectArray.getJSONObject(i); 
        				Sport sport = new Sport(obj.getString("sport"));
        				int duration = Integer.parseInt(obj.getString("duration"));
        				List<Integer> pulses = checkPulse(obj.getString("pulses"));
        				boolean privacy = (obj.getString("privacy").equals("1")) ? true:false;
        				String goal = obj.getString("goal");
        				Date date = stringToDate(obj.getString("date")); //date = dd:mm:yyyy
        				
        				Workout workout = new Workout(sport, privacy);
        				workout.setDate(date);
        				workout.setDuration(duration);
        				workout.setGoal(goal);
        				workout.setPulses(pulses);
        				returnList.add(workout);
        			}
        		}
        }catch (Exception e){
            e.printStackTrace();
        }	
		return returnList;
	}  
	
	@SuppressWarnings("deprecation")
	public static Date stringToDate(String s) {
		Date date = null;
		try {
			String[] list = s.split(":");
			date = new Date(Integer.parseInt(list[0]), Integer.parseInt(list[1]), Integer.parseInt(list[2])); // year, month, day
		} catch (Exception e) {
			e.printStackTrace();
		}
		return date;
	}
	
	public static List<Integer> checkPulse(String pulses) {
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
			throw new IllegalArgumentException("Pulsene må være heltall");
		}
	}
	
	public static List<List<Integer>> getPulseZones(String username) {
		List<List<Integer>> returnList = new ArrayList();
		
		HashMap requestParam = new HashMap<String, String>();
        requestParam.put("username", username);
        try {
        	JSONObject response = BackendConnector.makeRequest(requestParam, Method.POST, ""); //må endres
    		if (response.get("status").equals("success")) {
    			JSONArray objectArray = response.getJSONArray("pulsezones"); // må endres?
    			for (int i = 0; i < objectArray.length(); i++) {
    				JSONObject obj = objectArray.getJSONObject(i);
    				String pulsezones = obj.get("").toString();
    				returnList.add(getDurationInZones(pulsezones));
    			}
    		}
    		} catch (Exception e) {
    			returnList = null;
			e.printStackTrace();
		}
        return returnList;
	}
	
	public static List<Integer> getDurationInZones(String pulsezones) {
		List<Integer> returnList = new ArrayList();
		try {
			String[] durations = pulsezones.split(",");
			if (durations.length != 5) {
				throw new Exception();
			}
			for (int i = 0; i < 5; i++) {
				returnList.add(Integer.parseInt(durations[i]));
			}
			
		} catch (Exception e) {
			returnList = null;
			e.printStackTrace();
		}
		return returnList;
	}
	
}
