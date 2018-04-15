package tdt4140.gr1812.app.core.models.coachModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.json.JSONArray;
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
	
	
	public static String getSportForCoach(String caochName ){

	    
	    String sport = "failure";
	    
	    
	    HashMap requestParam = new HashMap<String, String>();

	    requestParam.put("username", caochName);
	    System.out.println(requestParam.toString());
	    try {
	        JSONObject response = BackendConnector.makeRequest(requestParam, Method.POST, "sportForCoach");
	        System.out.println(response.toString());
	        if (response.get("status").equals("failure")) {
	            sport = "Couldn't load sport";
	        }else if(response.getString("status").equals("success")){
	            sport = response.get("sport").toString();
	        }
	    }catch (Exception e) {
	        e.printStackTrace();
	    }
	    
		return sport;
	}
	
	public static List<Athlete> getAthletesForSport(String sport){
		ArrayList<Athlete> returnList = new ArrayList();
		

		HashMap requestParam = new HashMap<String, String>();
        requestParam.put("sport", sport);
        
        try {

        		JSONObject response = BackendConnector.makeRequest(requestParam, Method.POST, "athletesInSport");
        		if (response.get("status").equals("success")) {
        			JSONArray objectArray = response.getJSONArray("athletes");
        			for (int i = 0; i < objectArray.length(); i++) {
        				JSONObject obj = objectArray.getJSONObject(i); 
        				String firstname = obj.getString("firstname");
        				String surname = obj.getString("surname");
        				String username = obj.getString("username");
        				returnList.add(new Athlete(username, surname, firstname));
        			}
        		}
        }catch (Exception e){
            e.printStackTrace();
        }	
		
		return returnList;
	}  
	
	
	public static String getAthletesFullName(String cellPhoneNumber, String sport){
        
        String returnName = "";
        
        HashMap requestParam = new HashMap<String, String>();
        requestParam.put("username", cellPhoneNumber);
        requestParam.put("sport", sport);
        
        
        try {
                
                JSONObject response = BackendConnector.makeRequest(requestParam, Method.POST, "athletesInSport");
                if (response.get("status").equals("success")) {
   
                    
                		//Creates a JSONArray who runs through all the athletes

                    JSONArray objectArray = response.getJSONArray("athletes");
                    for (int i = 0; i < objectArray.length(); i++) {
                        JSONObject obj = objectArray.getJSONObject(i); 
                        
                        if (obj.getString("username").equals(cellPhoneNumber)) {
                            String firstname = obj.getString("firstname");
                            String surname = obj.getString("surname");
                            returnName = firstname + " " + surname;
                        }
                        
                    }
                }
        }catch (Exception e){
            e.printStackTrace();
        }   
        
        return returnName;
    }  
	
	public static void main(String[] args) {
        System.out.println(getAthletesFullName("12345678", "basket"));
        
    }
}
