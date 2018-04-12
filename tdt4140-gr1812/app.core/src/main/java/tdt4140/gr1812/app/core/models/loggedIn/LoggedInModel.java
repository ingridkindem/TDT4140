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
	
	// må få en metode i backend som "svarer til" denne. 
	// Vet ikke om denne metoden skal være slik - har kopiert litt fra andre lignende metoder
	
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

	// må få en metode i backend som "svarer til" denne. 
	// Vet ikke om denne metoden skal være slik - har kopiert litt fra andre lignende metoder (har brukt getAthletesForSport(String sport) i CoachModel)
	
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
        				List<Integer> pulses = getPulsesAsList(obj.getString("pulses")); //[1,1]
        				boolean privacy = (obj.getString("privacy").equals("1")) ? true:false;
        				String goal = obj.getString("goal");  
        				String date = obj.getString("date"); // 10 apr 2018 22:00:00 GMT
        				String extraField = obj.getString("extraField");
        				
        				if (sport.getSport().equals("langrenn")) {
        					extraField = "Distanse: " + extraField;
        				} else if (sport.getSport().equals("basket")) {
        					extraField = "Antall kast: " + extraField;
        				} else if (sport.getSport().equals("fotball")) {
        					extraField = "Spilletid: " + extraField;
        				}
        				
        				Workout workout = new Workout(sport, privacy);
        				//workout.setDate(date);
        				workout.setDuration(duration);
        				workout.setGoal(goal);
        				workout.setPulses(pulses);
        				//workout.setExtraField(extraField);
        				returnList.add(workout);
        			}
        		}
        }catch (Exception e){
            e.printStackTrace();
        }	
		return returnList;
	} 
	
	// må få en metode i backend som "svarer til" denne. Snakket litt med Håkon om den. 
	// Vet ikke om denne metoden skal være slik - har kopiert litt fra andre lignende metoder (har brukt getAthletesForSport(String sport) i CoachModel)
		
	// used to create the chart that displays the athlete's duration in the different pulsezones
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
	    			returnList.add(getPulsesAsList(pulsezones));
	    		}
	    	}
	    	} catch (Exception e) {
			e.printStackTrace();
		}
	    return returnList;
		}
	
	// må sjekke at strengen vi mottar fra backend i getWorkoutsForAthlete() (metoden over) er på formen yyyy:mm:dd. 
	// hvis ikke må metoden endres
	
	@SuppressWarnings("deprecation")
	public static Date stringToDate(String s) { //returns the string as an Date object. 
		Date date = null;
		try {
			String[] list = s.split(":");
			date = new Date(Integer.parseInt(list[0]), Integer.parseInt(list[1]), Integer.parseInt(list[2])); // s of the form "year:month:day"
		} catch (Exception e) {
		}
		return date;
	}
	
	public static int hoursToMinutes(String s) { // "h:mm" -> mm
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
	
	
	public static List<Integer> getPulsesAsList(String pulses) { // returns the String as a list of integers. 
		List<Integer> pulsesList = new ArrayList<Integer>();
		try {
			String[] p = pulses.split(","); // pulses of the form "i ,i,i,i...,i" where i is an integer. 
			for (String i : p) {
				i.trim();
				int pulseInt = Integer.parseInt(i);
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
	
}
