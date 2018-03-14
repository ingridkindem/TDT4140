package tdt4140.gr1812.app.core.helpers;

import static org.junit.Assert.*;

import java.util.HashMap;

import org.json.JSONObject;
import org.junit.Test;

public class BackendConnectorTest { // må endres til ny server
	
	@Test
	public void testMakeGetRequest() {
		try {
			
	           
	           HashMap requestParam = new HashMap<String, String>();
	           requestParam.put("username", String.valueOf(46643025));
	           requestParam.put("password", "46643025");

			
	           JSONObject response = BackendConnector.makeRequest(requestParam, Method.POST, "login");
	           assertEquals(response.get("status"), "success");
		} catch (Exception e) {
			fail("fail: did'nt connect to server");
		}		
	}
}