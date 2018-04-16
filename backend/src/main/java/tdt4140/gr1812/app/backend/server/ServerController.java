package tdt4140.gr1812.app.backend.server;

import org.json.JSONArray;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import tdt4140.gr1812.app.backend.dataclasses.Athlete;
import tdt4140.gr1812.app.backend.dataclasses.Workout;
import tdt4140.gr1812.app.backend.helpers.Tuple;

import java.lang.reflect.Array;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.RequestMapping;


@RestController //Allows @RequestMapping

public class ServerController {
	
	/* All endpoints work in ish the same way. See comments in signup() for common 
	features. Only endpoint specific comments at other endpoints.
	*/
	
	
	// Maps to signup-endpoint
	@RequestMapping("/signup")
    //@RequestMapping(method = RequestMethod.POST)
    public String signup(@RequestParam("username") String username, //gets all parameters from request-body
                        @RequestParam("password") String password,
                        @RequestParam("firstname") String firstname,
                        @RequestParam("surname") String surname,
                        @RequestParam("maxpulse") String maxpulse,
                        @RequestParam("weight") String weight,
                        @RequestParam("gender") String gender,
                        @RequestParam("sport") String sport
                        ) {
     JSONObject feedback = new JSONObject(); //Object server returns 
   
		try {
			// ServerLogic handles queries to DB
	        ServerLogic.signup(username, password, firstname, surname, maxpulse, weight, gender, sport); 
	        feedback.put("status", "success");
                  
		} catch (Exception e) { // Catches all outcomes that are not successful.
			try {
				feedback.put("status", "failed");
				          
			} catch (JSONException e1) {
				e1.printStackTrace();
			}
		}
        return feedback.toString(); // What the server sends back to client
    }
	
    @RequestMapping("/login") 
    public String login(@RequestParam("username") String username,
    					   @RequestParam("password") String password) {
    		
    	JSONObject feedback = new JSONObject(); 
    		try{
				Tuple<Boolean, Boolean> loginResult = ServerLogic.login(username, password); //Tuple-class defined in backend/.../helpers
				
				if (loginResult.x) { //loginresult.x = successful login true/false
					feedback.put("status", "success");
					feedback.put("coach", loginResult.y.toString()); //loginresult.y = user is coach true/false
					
				}
				else {
					feedback.put("status", "failed").toString();
				}

			}catch (Exception e) {
    			e.printStackTrace();
				try {
					feedback.put("status", "failed").toString();
				} catch (JSONException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
    		}
    		
        return feedback.toString();
    }
    @RequestMapping("")
    public String index() {
    		return "Welcome. I am your server.";
    }
    
    
    @RequestMapping("/workoutRegistration")
    public String workoutRegistration(@RequestParam("username") String username, 
    									 @RequestParam("duration") String duration,
    									 @RequestParam("pulses") String pulses,
    									 @RequestParam("goal") String goal,
									 @RequestParam("extraField") String extraField,
    									 @RequestParam("sport") String sport,
    									 @RequestParam("privacy") String privacy) {
    	
    	JSONObject feedback = new JSONObject();
    	
    	try{
			boolean workoutRegistrationResult = ServerLogic.registerWorkout(username, duration, pulses, goal, sport, privacy, extraField);
			if (workoutRegistrationResult) {
				feedback.put("status", "success");  
				
			}
			else {
				feedback.put("status", "failed").toString();
			}
			
		}catch (Exception e) {
			e.printStackTrace();
			try {
				feedback.put("status", "failed").toString();
			} catch (JSONException e1) {
				e1.printStackTrace();
			}
		}
    	
    	return feedback.toString();

    }
    
    @RequestMapping("/athletesInSport")
    public String requestAthletesInSport(@RequestParam("sport") String sport) {
    	
    	JSONObject feedback = new JSONObject();

		try{
			ArrayList<Athlete> athletesInSport = ServerLogic.getAthletesInSport(sport);

			if (athletesInSport.size()<1) {
				feedback.put("status", "failed");
				feedback.put("message", "No athletes in sport");
			}else if (athletesInSport.size()>=1) { 
				JSONArray jArray = new JSONArray();
				for (Athlete athlete: athletesInSport){ //iteration through athletes objects
						JSONObject athleteJson = new JSONObject();
						athleteJson.put("firstname", athlete.firstname);
						athleteJson.put("surname", athlete.surname);
						athleteJson.put("username", athlete.username);
						jArray.put(athleteJson);
				}
				feedback.put("status", "success");
				feedback.put("athletes", jArray);
			}
			else {
				feedback.put("status", "failed");
			}
			
		}catch (Exception e) {
    		try{
				feedback.put("status", "failed");
			}
			catch (Exception es){

			}
		}
    	 return feedback.toString(); //returning status and list of athletes as one continuous String
    }
   
    @RequestMapping("sportForCoach")
    public String requestSportForCoach(@RequestParam("username") String username) {
    	
    	JSONObject feedback = new JSONObject();
    	
    	try{
			String getSportForCoachResult = ServerLogic.getSportForCoach(username);
			if (getSportForCoachResult != "") {

				feedback.put("status", "success");
				feedback.put("sport", getSportForCoachResult); 
			}
			else {
				feedback.put("status", "failed").toString();
			}
			
		}catch (Exception e) {
			e.printStackTrace();
			try {
				feedback.put("status", "failed").toString();
			} catch (JSONException e1) {
				e1.printStackTrace();
			}
		}
    	
    	
    	return feedback.toString(); 

    }
    
    
    @RequestMapping("/getName")
    public String requestNameForUser(@RequestParam("username") String username) {
        
        JSONObject feedback = new JSONObject();
        
        try{
            String getNameForUserResult = ServerLogic.getNameForUser(username);
            if (getNameForUserResult != "Couldn't find user") {
                feedback.put("name", getNameForUserResult);  
                feedback.put("status", "success"); 
            }
            else {
                feedback.put("status", "failed").toString();
            }
            
        }catch (Exception e) {
            e.printStackTrace();
            try {
                feedback.put("status", "failed").toString();
            } catch (JSONException e1) {
                e1.printStackTrace();
            }
        }
       
        return feedback.toString(); 

    }
    
    @RequestMapping("lastWorkouts")
    public String getLastWorkouts(@RequestParam("username") String username) {
    	
    	JSONObject feedback = new JSONObject();

		try{
			ArrayList<Workout> workouts = ServerLogic.getLastWorkouts(username);
			if (workouts.size()<1) {
				feedback.put("status", "failed");
				feedback.put("message", "No workouts for user");
			}else if (workouts.size()>=1) {
				JSONArray jArray = new JSONArray(); //Array of JSONObject. One for each workout.
				for (Workout workout: workouts){
						JSONObject workoutJson = new JSONObject();
						boolean privacy = workout.getPrivacy();
						workoutJson.put("duration", workout.getDuration());
						workoutJson.put("goal", workout.getGoal());
						workoutJson.put("pulses", workout.getPulses());
						workoutJson.put("extraField", workout.getExtraField()); 
						if (privacy) {
							workoutJson.put("privacy", "1");
						}else {
							workoutJson.put("privacy", "0");
						}
						workoutJson.put("date", workout.getDate().toGMTString());
						workoutJson.put("sport", workout.getSport().getSport());
						jArray.put(workoutJson);
				}
				feedback.put("status", "success");
				feedback.put("workouts", jArray);
			}
			else {
				feedback.put("status", "failed");
			}
			
		}catch (Exception e) {
			try {
				feedback.put("status", "failed");
			} catch (JSONException e1) {
				
				e1.printStackTrace();
			}
		}
    	 return feedback.toString();
    }
    
    @RequestMapping("/graph")
    public String getGraphInformation(@RequestParam ("username") String username) {
    	JSONObject feedback = new JSONObject();

		try{
			Map <Date, List<Integer>> pulsInformation = ServerLogic.getGraphInformation(username);
			
			if (pulsInformation.keySet().size()<1) {
				feedback.put("status", "failed");
				feedback.put("message", "No pulses for user");
			}else if (pulsInformation.keySet().size()>=1) {
				JSONArray jArray = new JSONArray();
				for (Date dato : pulsInformation.keySet()){
						JSONObject pulsJson = new JSONObject();
						pulsJson.put("Dato", dato.toGMTString());
						//Retrieving pulses for given date (key)
						pulsJson.put("pulses", pulsInformation.get(dato)); 
						jArray.put(pulsJson);
				}
				feedback.put("status", "success");
				feedback.put("workouts", jArray);
			}
			else {
				feedback.put("status", "failed");
			}
			
		}catch (Exception e) {
    		try{
    				e.printStackTrace();
				feedback.put("status", "failed");
			}
			catch (Exception es){

			}
		}
    	 return feedback.toString();
   
    }
   
    
}