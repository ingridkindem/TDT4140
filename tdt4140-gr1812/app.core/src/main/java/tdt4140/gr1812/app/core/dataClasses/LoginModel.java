package tdt4140.gr1812.app.core.dataClasses;

import java.util.HashMap;

import org.json.JSONObject;

import tdt4140.gr1812.app.core.helpers.BackendConnector;
import tdt4140.gr1812.app.core.helpers.Method;

public class LoginModel {

	public static Tuple<Boolean, Boolean> login(String phoneNumber, String password) {
		if (phoneNumber == null) {
			throw new NullPointerException("Phone number cannot bo blank");
		}
		if (password == null) {
			throw new NullPointerException("Password cannot be blank");
		}
		HashMap myMap = new HashMap<String, String>();
	    myMap.put("username", phoneNumber);
	    myMap.put("password", password);

		try {
			JSONObject response = BackendConnector.makeRequest(myMap, Method.POST, "login");
			System.out.print(response.toString());
			if (response.get("status").equals("success")) {
				if (response.getString("coach").equals("true")) {
					return new Tuple(true, true);
				}
				else {
					return new Tuple(true, false);
				}
			}
			else {
				return new Tuple(false, false); 
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return new Tuple(false, false);
	}
		
	
	
}


