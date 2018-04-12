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

@RunWith(SpringRunner.class)
@SpringBootTest(classes= Server.class)
@AutoConfigureMockMvc
public class ServerTest{

    @Autowired // 
    private MockMvc mvc;
    
    @Before
    public void Before() {
    	this.deleteUserFromDB("99765432");
    	this.deleteWorkoutFromDB("99765432");
    }
    
   
    
 
    				
    @Test
    public void TestSignUp() throws Exception{
    		String username = "99765432";
    		String password = "98765432";
    		String firstname = "hakon";
    		String surname = "collett";
    		String maxpulse = "200";
    		String weight = "85";
    		String gender  = "male";
    		String sport = "Fotball";
    		
    		mvc.perform(MockMvcRequestBuilders.get("/signup")
    				.param("username", username)
    				.param("password", password)
    				.param("firstname", firstname)
    				.param("surname", surname)
    				.param("maxpulse", maxpulse)
    				.param("weight", weight)
    				.param("gender", gender)
    				.param("sport", sport)).andExpect(status().isOk())
    				.andExpect(content().string(containsString("success")));
    }  
    
    @Test
    public void TestFailedSignUp() throws Exception{
    		String username = "12345678";
    		String password = "98765432";
    		String firstname = "hakon";
    		String surname = "collett";
    		String maxpulse = "200";
    		String weight = "85";
    		String gender  = "male";
    		String sport = "Fotball";
    		
    		mvc.perform(MockMvcRequestBuilders.get("/signup")
    				.param("username", username)
    				.param("password", password)
    				.param("firstname", firstname)
    				.param("surname", surname)
    				.param("maxpulse", maxpulse)
    				.param("weight", weight)
    				.param("gender", gender)
    				.param("sport", sport)).andExpect(status().isOk())
    				.andExpect(content().string(containsString("failed")));
    		
    			  
    }
    
    @Test
    public void TestLogin() throws Exception{
    	
    	String username = "12345678";
    	String password = "12345678";
    	
    				mvc.perform(MockMvcRequestBuilders.get("/login")
    				.param("username", username)
    				.param("password", password))
    				.andExpect(status().isOk())
    				//.andExpect(content().contentType("application/json"))
    	            .andExpect(content().string(containsString("success")));
    	
    				
    }
    
    @Test
    public void TestFailedLogin() throws Exception{
    	
    	String username = "00000000";
    	String password = "12345678";
    	
    				mvc.perform(MockMvcRequestBuilders.get("/login")
    				.param("username", username)
    				.param("password", password))
    				.andExpect(status().isOk())
    				//.andExpect(content().contentType("application/json"))
    	            .andExpect(content().string(containsString("failed")));
    	
    				
    }
    
    @Test //Testing that endpoint responds properly
    public void welcomeMessage() throws Exception {
    	
        mvc.perform(MockMvcRequestBuilders.get("").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("Welcome. I am your server.")));
        
        
    }
    
    @Test
    public void TestWorkoutRegistration() throws Exception{
    	
    		String username = "99765432";
    		String duration = "100";
    		String pulses = "100,200,100";
    		String goal = "Stronger";
    		String sport = "fotball";
    		String privacy = "1";
    		
    		mvc.perform(MockMvcRequestBuilders.get("/workoutRegistration")
    				.param("username", username)
    				.param("duration", duration)
    				.param("pulses", pulses)
    				.param("goal", goal)
    				.param("sport", sport)
    				.param("privacy", privacy))
    				.andExpect(status().isOk())
    				.andExpect(content().string(containsString("success")));
    }
    
    @Test
    public void TestFailedWorkoutRegistration() throws Exception{
    	
    		String username = "99765432";
    		String duration = "hei";
    		String pulses = "100,200,100";
    		String goal = "Stronger";
    		String sport = "fotball";
    		String privacy = "1";
    		
    		mvc.perform(MockMvcRequestBuilders.get("/workoutRegistration")
    				.param("username", username)
    				.param("duration", duration)
    				.param("pulses", pulses)
    				.param("goal", goal)
    				.param("sport", sport)
    				.param("privacy", privacy))
    				.andExpect(status().isOk())
    				.andExpect(content().string(containsString("failed")));
    }
    
    @Test
    public void TestRequestAthletesInSport() throws Exception{
    	
		String sport = "Fotball";
		
		mvc.perform(MockMvcRequestBuilders.get("/athletesInSport")
				.param("sport", sport))
				.andExpect(status().isOk())
				.andExpect(content().string(containsString("success")));
}
    @Test
    public void TestFailedRequestAthletesInSport() throws Exception{
    	
		String sport = "44";
		
		mvc.perform(MockMvcRequestBuilders.get("/athletesInSport")
				.param("sport", sport))
				.andExpect(status().isOk())
				.andExpect(content().string(containsString("failed")));
}
    @Test
    public void TestRequestSportForCoach() throws Exception{
    	
		String username = "12345678";
		
		mvc.perform(MockMvcRequestBuilders.get("/sportForCoach")
				.param("username", username))
				.andExpect(status().isOk())
				.andExpect(content().string(containsString("Basket")));
}
    @Test
    public void TestFailedRequestSportForCoach() throws Exception{
    	
		String username = "000000";
		
		
		mvc.perform(MockMvcRequestBuilders.get("/sportForCoach")
				.param("username", username))
				.andExpect(status().isOk())
				.andExpect(content().string(containsString("coach")));
		
}
    
    	public String deleteUserFromDB(String username) {
    		
    		     MysqlDataSource dataSource = new MysqlDataSource();
             dataSource.setUser("pu");
             dataSource.setPassword("ZAmrUsPWD3vd");
             dataSource.setServerName("larserikfagernaes.com");
             dataSource.setPort(3306);
             dataSource.setDatabaseName("PU");
             
             String sql = "delete from users where username = ?";
             String feedback;
             Connection conn = null;
             try {
 	            	 conn = dataSource.getConnection();
 	                 PreparedStatement ps = conn.prepareStatement(sql);
 	                 ps.setInt(1,  Integer.parseInt(username));
 	                 ps.execute();
 	                 ps.close();
 	                 feedback = "success";
 	                 
             }catch (SQLException e) {
            	 	feedback = "failure";
            	 	throw new RuntimeException(e);
             } finally {
            	 	if (conn!=null) {
            	 		try {
            	 			conn.close();
            	 		}catch (SQLException e) {
            	 			
            	 		}
            	 	}
             }
			return feedback;
             
      
    }
    	
    	public String deleteWorkoutFromDB(String username) {
    		
		     MysqlDataSource dataSource = new MysqlDataSource();
        dataSource.setUser("pu");
        dataSource.setPassword("ZAmrUsPWD3vd");
        dataSource.setServerName("larserikfagernaes.com");
        dataSource.setPort(3306);
        dataSource.setDatabaseName("PU");
        
        String sql = "delete from workouts where username = ?";
        String feedback;
        Connection conn = null;
        try {
            	 conn = dataSource.getConnection();
                 PreparedStatement ps = conn.prepareStatement(sql);
                 ps.setString(1,  username);
                 ps.execute();
	             ps.close();
                 feedback = "success";
                 
        }catch (SQLException e) {
       	 	feedback = "failure";
       	 	throw new RuntimeException(e);
        } finally {
       	 	if (conn!=null) {
       	 		try {
       	 			conn.close();
       	 		}catch (SQLException e) {
       	 			
       	 		}
       	 	}
        }
		return feedback;
        
 
}       


}
    				
    		
    		
    
    
    
    
    

