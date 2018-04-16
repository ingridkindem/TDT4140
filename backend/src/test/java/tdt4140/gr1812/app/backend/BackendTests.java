package tdt4140.gr1812.app.backend;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.hamcrest.Matchers.containsString;

import org.json.JSONObject;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

import tdt4140.gr1812.app.backend.server.Server;
import tdt4140.gr1812.app.backend.server.ServerController;
import tdt4140.gr1812.app.backend.server.ServerLogic;

@RunWith(SpringRunner.class) // Switching from jUnit runner to Spring runner
@AutoConfigureMockMvc // Allowing MockMvc
@SpringBootTest(classes = Server.class) // Setting up test server and testing it.


// tests all endpoints with valid and invalid input. Most endpoints are tested for success/failure feeback.

public class BackendTests {

	private static String usernameTest = "12233445"; 
	private static String passwordTest = "12233445";

	@Autowired 
	private MockMvc mvc; // object for creating mock-requests to server

	@BeforeClass //runs tests 
	public static void Before() {
		try {
		ServerLogic.deleteUserFromDB("99999999");  //has to be deleted for TestSignup to pass more than once
		} catch (Exception e) {
			System.out.println("Error in @BeforeClass");
		}
	}

	@AfterClass //runs after tests
	public static void After() {
		
		ServerLogic.deleteWorkoutFromDB("99999999"); // to avoid entries in db from testing
		ServerLogic.deleteUserFromDB(BackendTests.usernameTest);// deleting test user after tests
	}
	
	@Test
	public void TestSignUp() throws Exception {
		String username = "99999999";
		String password = "98765432";
		String firstname = "hakon";
		String surname = "collett";
		String maxpulse = "200";
		String weight = "85";
		String gender = "male";
		String sport = "Fotball";

		mvc.perform(MockMvcRequestBuilders.get("/signup").param("username", username).param("password", password)
				.param("firstname", firstname).param("surname", surname).param("maxpulse", maxpulse)
				.param("weight", weight).param("gender", gender).param("sport", sport)).andExpect(status().isOk())
				.andExpect(content().string(containsString("success")));
	}

	@Test
	public void TestFailedSignUp() throws Exception {
		String username = "12345678";
		String password = "98765432";
		String firstname = "hakon";
		String surname = "collett";
		String maxpulse = "200";
		String weight = "85";
		String gender = "male";
		String sport = "Fotball";

		mvc.perform(MockMvcRequestBuilders.get("/signup").param("username", username).param("password", password)
				.param("firstname", firstname).param("surname", surname).param("maxpulse", maxpulse)
				.param("weight", weight).param("gender", gender).param("sport", sport)).andExpect(status().isOk())
				.andExpect(content().string(containsString("failed")));

	}

	@Test
	public void TestLogin() throws Exception {

		String username = "12345678";
		String password = "12345678";

		mvc.perform(MockMvcRequestBuilders.get("/login").param("username", username).param("password", password))
				.andExpect(status().isOk())
				// .andExpect(content().contentType("application/json"))
				.andExpect(content().string(containsString("success")));

	}

	@Test
	public void TestFailedLogin() throws Exception {

		String username = "12345678";
		String password = "18";

		mvc.perform(MockMvcRequestBuilders.get("/login").param("username", username).param("password", password))
				.andExpect(status().isOk())
				// .andExpect(content().contentType("application/json"))
				.andExpect(content().string(containsString("failed")));

	}

	@Test // Testing that endpoint responds properly
	public void TestWelcomeMessage() throws Exception {

		mvc.perform(MockMvcRequestBuilders.get("").accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(content().string(equalTo("Welcome. I am your server.")));

	}

	@Test
	public void TestWorkoutRegistration() throws Exception {

		String username = "12345678";
		String duration = "100";
		String pulses = "100,200,100";
		String goal = "Stronger";
		String sport = "fotball";
		String privacy = "1";
		String extraField = "40";

		mvc.perform(MockMvcRequestBuilders.get("/workoutRegistration").param("username", username)
				.param("duration", duration).param("pulses", pulses).param("goal", goal).param("sport", sport)
				.param("privacy", privacy).param("extraField", extraField)).andExpect(status().isOk())
				.andExpect(content().string(containsString("success")));
	}

	@Test
	public void TestFailedWorkoutRegistration() throws Exception {

		String username = "12345678";
		String duration = "hei"; // this is the error
		String pulses = "100,200,100";
		String goal = "Stronger";
		String sport = "fotball";
		String privacy = "1";
		String extraField = "40";

		mvc.perform(MockMvcRequestBuilders.get("/workoutRegistration").param("username", username)
				.param("duration", duration).param("pulses", pulses).param("goal", goal).param("sport", sport)
				.param("privacy", privacy).param("extraField", extraField)).andExpect(status().isOk())
				.andExpect(content().string(containsString("failed")));
	}

	@Test
	public void TestRequestAthletesInSport() throws Exception {

		String sport = "Fotball";

		mvc.perform(MockMvcRequestBuilders.get("/athletesInSport").param("sport", sport)).andExpect(status().isOk())
				.andExpect(content().string(containsString("success")));
	}

	@Test
	public void TestFailedRequestAthletesInSport() throws Exception {

		String sport = "44"; // This is the error

		mvc.perform(MockMvcRequestBuilders.get("/athletesInSport").param("sport", sport)).andExpect(status().isOk())
				.andExpect(content().string(containsString("failed")));
	}

	@Test
	public void TestRequestSportForCoach() throws Exception {

		String username = "12345678";

		mvc.perform(MockMvcRequestBuilders.get("/sportForCoach").param("username", username)).andExpect(status().isOk())
				.andExpect(content().string(containsString("success")));
	}

	@Test
	public void TestFailedRequestSportForCoach() throws Exception {

		String username = "743216"; // this is the error

		mvc.perform(MockMvcRequestBuilders.get("/sportForCoach").param("username", username)).andExpect(status().isOk())
				.andExpect(content().string(containsString("coach")));

	}

	@Test
	public void TestLastWorkouts() throws Exception {

		String username = "12345678";

		mvc.perform(MockMvcRequestBuilders.get("/lastWorkouts").param("username", username).param("coach", "0")).andExpect(status().isOk())
				.andExpect(content().string(containsString("success")));

	}

	@Test
	public void TestFailedLastWorkouts() throws Exception {

		String username = "743216"; // this is the error

		mvc.perform(MockMvcRequestBuilders.get("/lastWorkouts").param("username", username).param("coach", "0")).andExpect(status().isOk())
				.andExpect(content().string(containsString("failed")));

	}

	@Test
	public void TestGraphInformation() throws Exception {

		String username = "12345678";

		mvc.perform(MockMvcRequestBuilders.get("/graph").param("username", username).param("coach", "0")).andExpect(status().isOk())
				.andExpect(content().string(containsString("success")));

	}

	@Test
	public void TestFailedGraphInformation() throws Exception {

		String username = "743216"; // This is the error

		mvc.perform(MockMvcRequestBuilders.get("/graph").param("username", username).param("coach", "0")).andExpect(status().isOk())
				.andExpect(content().string(containsString("failed")));

	}

	@Test
	public void TestGetName() throws Exception {

		String username = "12345678";

		mvc.perform(MockMvcRequestBuilders.get("/getName").param("username", username)).andExpect(status().isOk())
				.andExpect(content().string(containsString("success")));

	}

	@Test
	public void TestFailedGetName() throws Exception {

		String username = "743216"; // this is the error

		mvc.perform(MockMvcRequestBuilders.get("/getName").param("username", username)).andExpect(status().isOk())
				.andExpect(content().string(containsString("failed")));

	}

}
