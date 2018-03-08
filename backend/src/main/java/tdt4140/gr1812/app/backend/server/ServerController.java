package tdt4140.gr1812.app.backend.server;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.RequestMapping;


@RestController

public class ServerController {
	// Maps to signup-endpoint
	@RequestMapping("/signup")
    //@RequestMapping(method = RequestMethod.POST)
    public String signup(@RequestParam("username") String username, //gets all parameters from request-body
                        @RequestParam("password") String password,
                        @RequestParam("sport") String sport,
                        @RequestParam("firstname") String firstname,
                        @RequestParam("surname") String surname,
                        @RequestParam("maxpulse") String maxpulse,
                        @RequestParam("weight") String weight,
                        @RequestParam("gender") String gender
                        ) {
     String feedback; //Variable letting user know outcome. Only success/failure implemented.
   
		try {
        ServerLogic.signup(username, password, sport, firstname, surname, maxpulse, weight, gender);
        feedback = new JSONObject()
                  .put("status", "success").toString();
		} catch (Exception e) { // Catches all outcomes that are not successful. Should be specified in more detail in later versions.
			feedback = "failure";
			
		}
        return feedback;
    }
	
    @RequestMapping("/login") //mapping to login endpoint
    public String login(@RequestParam("username") String username,
    					   @RequestParam("password") String password) {
    		
    	String feedback = ""; 
    		try{
    			boolean loginResult = ServerLogic.login(username, password);
    			if (loginResult) {
    				JSONObject responseObject = new JSONObject().put("status", "success");  
    				feedback = responseObject.toString(); 
    			}
    			else {
    				feedback = new JSONObject()
  		                  .put("status", "failed").toString();
    			}
    			
    		}catch (Exception e) {
    			
    		}
    		
        return feedback;
    }
    @RequestMapping("")
    public String index() {
    		return "Welcome. I am your server.";
    }
}