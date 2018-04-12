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
	
	//method to initialize a registration from the view
	public boolean WorkoutRegistrationModelInit(String username, String pulses, String extraField, String duration, Sport sport, String goal, boolean privacy) {		
		try {
			int d = checkDuration(duration);
		
			checkSport(sport);
			checkPulse(pulses);
			text = "";
			if (sport.getSport().equals("langrenn")) { //if the sport we choose is "langrenn" we have to check if the exercise data is correct
				checkDistance(extraField);
			}
			if (sport.getSport().equals("basket")) { //if the sport we choose is "basket" we have to check if the exercise data is correct
				checkThrows(extraField);
			}
			if (sport.getSport().equals("fotball")) { //if the sport we choose is "fotball" we have to check if the exercise data is correct
				checkGameTime(extraField);
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
			//send to database. If accepted - return true. If declined - return false
			HashMap requestParam = new HashMap<String, String>();
			requestParam.put("duration", String.valueOf(duration));
			requestParam.put("username", username);
			requestParam.put("pulses", pulses);
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
	
	
	
	//method to check if pulses are positive integers
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
	
	//method to check if the number of throws is a positive integer
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
	
	//method to check if disctance is a positive integer
	public int checkDistance(String distance) {
		try {
			int dist = Integer.parseInt(distance);
			if (dist < 0) {
				throw new Exception();
			}
			return dist;
		}
		catch (Exception e) {
			text = "Illegal distance.";
			throw new IllegalArgumentException("Distance must me a positive integer.");
		}
	}
	
	//method to check if number of minutes played is a positive integer
	public int checkGameTime(String gameTime) {
		try {
			int p = Integer.parseInt(gameTime);
			if (p <= 0) {
				throw new Exception();
			}
			return p;
		}
		catch (Exception e) {
			text = "Illegal time.";
			throw new IllegalArgumentException("Game time must me a positive integer.");
		}
	}
	
	//checks if duration is a positive integer
	public int checkDuration(String duration) {
		try {
			int d = Integer.parseInt(duration);
			if (d <= 0) {
				throw new Exception();
			} return d;
		} catch (Exception e) {
			throw new IllegalArgumentException("Can't have negative duration.");
		}
		
	}
	
	//returns text
	public String getText() { 
		return text; 
	}
	
	//method to check if the sport is empty
	public void checkSport(Sport sport) {
		if (sport == null) {
			text = "Sport field is empty";
			throw new IllegalArgumentException("Sport field is empty");
		}
	}
	
	//method to return the string we use in the "functionality data field" for each specific sport. 
	public String valueForExtraField(String idrett) {
		if (idrett.equals("Basket")) {
			return "Antall kast i kurven";
		}
		if (idrett.equals("Fotball")) {
			return "Antall spilte minutter";
		}
		if (idrett.equals("Langrenn")) {
			return "Antall kilometer";
		}
		return null;
	}
	
}
