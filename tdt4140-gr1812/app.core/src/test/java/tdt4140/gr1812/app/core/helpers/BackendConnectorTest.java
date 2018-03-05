package tdt4140.gr1812.app.core.helpers;

import static org.junit.Assert.*;

import java.util.HashMap;

import org.json.JSONObject;
import org.junit.Test;

public class BackendConnectorTest { // må endres til ny server
	
	@Test
	public void testMakeGetRequest() {
		try {
			JSONObject response = BackendConnector.makeRequest(new HashMap<String, String>(), Method.GET, "tst/");
			assertEquals(response.get("status"), "success");
		} catch (Exception e) {
			fail("fail: did'nt connect to server");
		}		
	}
	
}
