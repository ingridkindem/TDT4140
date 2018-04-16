package tdt4140.gr1812.app.core.models.loggedIn;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
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
			JSONObject response = BackendConnector.makeRequest(requestparam, Method.POST, "getName"); 
			if (response.get("status").equals("success")) {
				name = response.get("name").toString();
			} else if (response.get("status").equals("failure")) {
				name = "Couldn't load name";
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return name;
	}

	//used for creating the table with the athlete's workouts
	public static List<Workout> getWorkoutsForAthlete(String username){ //returns a list of the athlete's workouts 
		ArrayList<Workout> returnList = new ArrayList();
		HashMap requestParam = new HashMap<String, String>();
        requestParam.put("username", username);
        
        try {
        		JSONObject response = BackendConnector.makeRequest(requestParam, Method.POST, "lastWorkouts");
        		if (response.get("status").equals("success")) {
        			JSONArray objectArray = response.getJSONArray("workouts");
        			for (int i = 0; i < objectArray.length(); i++) {
        				JSONObject obj = objectArray.getJSONObject(i); 
        				Sport sport = new Sport(obj.getString("sport"));
        				int duration = hoursToMinutes(obj.getString("duration")); //h:mm
        				List<Integer> pulses = getStringAsList(obj.getString("pulses").replace("[", "").replace("]", "")); //[1,1]
        				boolean privacy = (obj.getString("privacy").equals("1")) ? true:false;
        				String goal = obj.getString("goal"); 
        				String date = stringToDate(obj.getString("date")); // date = "10 apr 2018 22:00:00 GMT"
        				String extraField = obj.getString("extraField");
        				
        				if (sport.getSport().equals("langrenn")) {
        					extraField = "Distanse: " + extraField + " km";
        				} else if (sport.getSport().equals("basket")) {
        					extraField = "Antall kast: " + extraField;
        				} else if (sport.getSport().equals("fotball")) {
        					extraField = "Spilletid: " + extraField + " min";
        				}
        				
        				Workout workout = new Workout(sport, privacy);
        				workout.setDateString(date); 
        				workout.setDuration(duration);
        				workout.setGoal(goal);
        				workout.setPulses(pulses);
        				workout.setExtraField(extraField);
        				returnList.add(workout);
        			}
        		}
        }catch (Exception e){
            e.printStackTrace();
        }	
		return returnList;
	} 
	
	// used to create the chart that displays the athlete's duration in the different pulsezones
	public static HashMap<String, List<Integer>> getPulseZones(String username) {
		HashMap<String, List<Integer>> returnHashMap = new HashMap();
		 
		HashMap requestParam = new HashMap<String, String>();
	    requestParam.put("username", username);
	    try {
	    JSONObject response = BackendConnector.makeRequest(requestParam, Method.POST, "graph"); 
	    if (response.get("status").equals("success")) {
	    		JSONArray objectArray = response.getJSONArray("workouts"); 
	    		for (int i = 0; i < objectArray.length(); i++) {
	    			JSONObject obj = objectArray.getJSONObject(i);
	    			String durationInPulsezones = obj.get("pulses").toString().replace("[", "").replace("]", ""); // pulses: "[p,p,p,p,p]"
	    			List<Integer> durationInPulsezonesList = getStringAsList(durationInPulsezones);
	    			String date = stringToDate(obj.get("Dato").toString());
	    			returnHashMap.put(date, durationInPulsezonesList);
	    		}
	    	}
	    	} catch (Exception e) {
			e.printStackTrace();
		}
	    System.out.println(returnHashMap.toString());
	    return returnHashMap;
	}
	
	public static boolean deleteUser(String username) {
		HashMap requestparam = new HashMap<String, String>();
		requestparam.put("username", username);
		try {
			JSONObject response = BackendConnector.makeRequest(requestparam, Method.POST, "deleteUser"); 
			if (response.get("status").equals("success")) {
				System.out.println("User deleted.");
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} return false;
	}
	
	public static int hoursToMinutes(String s) { // turns a string, s, of the form "h:mm" to an integer(number of minutes)
		String[] min = s.split(":");
		int minutes;
		if(Integer.parseInt(min[0]) == 0) {
			minutes = Integer.parseInt(min[1]); 
		}
		else {
			minutes = Integer.parseInt(min[0])*60 + Integer.parseInt(min[1]);
		}
		
		return minutes; 
	}
	
	
	public static List<Integer> getStringAsList(String pulses) { // returns the String as a list of integers.
		System.out.println("puslses string = " + pulses);
		List<Integer> pulsesList = new ArrayList<Integer>();
		try {
			String[] p = pulses.split(", "); // pulses of the form "i ,i,i,i...,i" where i is an integer. 
			for (String i : p) {
				i.trim();
				i.replace(" ","");
				int pulseInt = Integer.parseInt(i);
				System.out.println(pulseInt);
				if (pulseInt < 0) {
					throw new Exception();
				}
				pulsesList.add(pulseInt);
			}
		} catch (Exception e) {
			pulsesList = null;
			throw new IllegalArgumentException("Pulsene må være heltall");
		}
		return pulsesList;
	}
	
	public static String stringToDate(String s) { // s = "10 apr 2018 22:00:00 GMT"
		String returnDate = null;
		System.out.println("prepared to do this string to date: " + s);
		try {
			String[] dateList = s.split(" ");
			int day = Integer.parseInt(dateList[0]);
			int month = getMonth(dateList[1]);
			int year = Integer.parseInt(dateList[2]);
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			returnDate = dateFormat.format(new Date(year-1900, month, day));
		} catch(Exception e) {
			e.printStackTrace();
		} return returnDate;
	}
	
	public static int getMonth(String s) {
		if (s.equals("Jan")) {
			return 0;
		}
		else if (s.equals("Feb")) {
			return 1;
		}
		else if (s.equals("Mar")) {
			return 2;
		}
		else if (s.equals("Apr")) {
			return 3;
		}
		else if (s.equals("May")) {
			return 4;
		}
		else if (s.equals("Jun")) {
			return 5;
		}
		else if (s.equals("Jul")) {
			return 6;
		}
		else if (s.equals("Aug")) {
			return 7;
		}
		else if (s.equals("Sep")) {
			return 8;
		}
		else if (s.equals("Oct")) {
			return 9;
		}
		else if (s.equals("Nov")) {
			return 10;
		}
		else {
			return 11;
		}
	}
	
	
	
}
