package tdt4140.gr1812.app.core.models.coachModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;
import tdt4140.gr1812.app.core.dataClasses.Athlete;
import tdt4140.gr1812.app.core.dataClasses.Coach;
import tdt4140.gr1812.app.core.helpers.BackendConnector;
import tdt4140.gr1812.app.core.helpers.Method;

public class CoachModel {
    
    private Coach coach;
    
    public CoachModel(Coach coach) {
        this.coach = coach;
    }
	
	
	public String getSportForCoach(){
	    
	    String sport = "failure";
	    
	    HashMap requestParam = new HashMap<String, String>();
	    requestParam.put("username", this.coach.getName()); // This has to be changed to username, but
	    //coach currently has no field "username" !!!!!!!
	    try {
	        JSONObject response = BackendConnector.makeRequest(requestParam, Method.POST, "sportForCoach");
	        if (response.get("status").equals("failure")) {
	            sport = "Couldn't load sport";
	        }else {
	            sport = response.get("status").toString();
	        }
	    }catch (Exception e) {
	        e.printStackTrace();
	    }
	      
		return sport;
	}
	
	public static List<Athlete> getAthletesForSport(Coach coach){
	    String sport = coach.getSport().getSport();
		List myList = new ArrayList();
		
		HashMap requestParam = new HashMap<String, String>();
        requestParam.put("sport", sport);
        try {
        String response = BackendConnector.makeRequest(requestParam, Method.POST, "athletesInSport");
        }catch (Exception e){
            e.printStackTrace();
        }
        
        
		Athlete aAthlete = new Athlete("46643025", "Lars erik", "Fagernæs");
		
		myList.add(aAthlete);
		
		
		return myList;
		
	}  
	
}
