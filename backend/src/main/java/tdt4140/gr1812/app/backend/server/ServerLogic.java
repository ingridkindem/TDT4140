package tdt4140.gr1812.app.backend.server;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

import com.sun.org.apache.xpath.internal.operations.Bool;

import tdt4140.gr1812.app.backend.dataclasses.Athlete;
import tdt4140.gr1812.app.backend.dataclasses.Sport;
import tdt4140.gr1812.app.backend.dataclasses.Workout;
import tdt4140.gr1812.app.backend.helpers.Tuple;
import tdt4140.gr1812.app.backend.server.ServerController;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.management.RuntimeErrorException;

import org.apache.tomcat.util.codec.binary.Base64;
import org.json.JSONException;
import org.json.JSONObject;

//Class mainly for handling connection to mySQL
//General comments in signup(). Specific comments in other methods.

public class ServerLogic {

	// Encrypting passwords
	public static String sha256(String base) {
		try {
			MessageDigest digest = MessageDigest.getInstance("SHA-256");
			byte[] hash = digest.digest(base.getBytes("UTF-8"));
			StringBuffer hexString = new StringBuffer();

			for (int i = 0; i < hash.length; i++) {
				String hex = Integer.toHexString(0xff & hash[i]);
				if (hex.length() == 1)
					hexString.append('0');
				hexString.append(hex);
			}

			return hexString.toString();
		} catch (Exception ex) {
			throw new RuntimeException(ex);
		}
	}

	public static void signup(String username, String password, String firstname, String surname, String maxpulse,
			String weight, String gender, String sport) {
		// connecting to database
		MysqlDataSource dataSource = new MysqlDataSource();
		dataSource.setUser(Config.dbUser);
		dataSource.setPassword(Config.dbPass);
		dataSource.setServerName(Config.dbHost);
		dataSource.setPort(Config.dbPort);
		dataSource.setDatabaseName(Config.dbName);
		password = ServerLogic.sha256(password + Config.serverSalt).substring(0, 25); // password encryption

		// query to database
		String sql = "Insert into users" + "(username, password, firstname, surname, maxpuls, weight, gender, sport)"
				+ "values (?, ?, ?, ?, ?, ?, ?, ?)";

		Connection conn = null;

		try {

			conn = dataSource.getConnection(); // attempting to establish connection to database
			PreparedStatement ps = conn.prepareStatement(sql); // compiler for SQL-statement
			ps.setInt(1, Integer.parseInt(username)); // Setting variables listed as "?" in SQL-string
			ps.setString(2, password);
			ps.setString(3, firstname);
			ps.setString(4, surname);
			ps.setInt(5, Integer.parseInt(maxpulse));
			ps.setInt(6, Integer.parseInt(weight));
			ps.setString(7, gender);
			ps.setString(8, sport);
			ps.executeUpdate(); // Updating database
			ps.close();

		} catch (SQLException e) { // E. g. already existing Primary Key will be caught here

			throw new RuntimeException(e);
		} finally {

			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
				}
			}
		}
	}

	public static Tuple<Boolean, Boolean> login(String username, String password) {

		MysqlDataSource dataSource = new MysqlDataSource();
		dataSource.setUser(Config.dbUser);
		dataSource.setPassword(Config.dbPass);
		dataSource.setServerName(Config.dbHost);
		dataSource.setPort(Config.dbPort);
		dataSource.setDatabaseName(Config.dbName);
		password = ServerLogic.sha256(password + Config.serverSalt).substring(0, 25);

		String sql = "select * from users where username = ? and password = ?";

		Connection conn = null;
		ResultSet resultSet = null; // needed for reading output from database

		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, Integer.parseInt(username));
			ps.setString(2, password);
			resultSet = ps.executeQuery();

			if (resultSet.next()) { // seeing if query returns empty table of data, meaning that user/pw-combo
									// doesn't exist and login not possible
				return new Tuple(true, resultSet.getBoolean("trener"));

			} else {
				return new Tuple(false, false);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
				}
			}
		}
	}

	public static boolean registerWorkout(String username, String duration, String pulses, String goal, String sport,
			String privacy, String extraField) {

		MysqlDataSource dataSource = new MysqlDataSource();
		dataSource.setUser(Config.dbUser);
		dataSource.setPassword(Config.dbPass);
		dataSource.setServerName(Config.dbHost);
		dataSource.setPort(Config.dbPort);
		dataSource.setDatabaseName(Config.dbName);

		String sql = "insert into workouts (username, duration, pulses, goal, sport, privacy, extraField)"
				+ "values (?, ?, ?, ?, ?, ?, ?)";

		Connection conn = null;
		Boolean feedback = true;

		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, username);
			ps.setInt(2, Integer.parseInt(duration));
			ps.setString(3, pulses);
			ps.setString(4, goal);
			ps.setString(5, sport);
			ps.setString(6, privacy);
			ps.setString(7, extraField);
			int ex = ps.executeUpdate();
			if (ex >= 1) {
				feedback = true;
			} else {
				feedback = false;
			}

		} catch (SQLException e) {
			feedback = false;
			throw new RuntimeException(e);

		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {

				}
			}
		}
		return feedback;
	}

	public static ArrayList<Athlete> getAthletesInSport(String sport) {
		MysqlDataSource dataSource = new MysqlDataSource();
		dataSource.setUser(Config.dbUser);
		dataSource.setPassword(Config.dbPass);
		dataSource.setServerName(Config.dbHost);
		dataSource.setPort(Config.dbPort);
		dataSource.setDatabaseName(Config.dbName);

		String sql = "select firstname, surname, username from users where sport = ?";

		Connection conn = null;
		ResultSet resultSet = null; // needed for reading output from database
		ArrayList<Athlete> users = new ArrayList<Athlete>();

		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, sport);
			resultSet = ps.executeQuery();
			while (resultSet.next()) {
				String firstName = resultSet.getString(1);
				String surname = resultSet.getString(2);
				String username = resultSet.getString(3);
				users.add(new Athlete(firstName, surname, username));
			}

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
				}
			}
		}

		return users;
	}

	public static ArrayList<Workout> getLastWorkouts(String username) {

		MysqlDataSource dataSource = new MysqlDataSource();
		dataSource.setUser(Config.dbUser);
		dataSource.setPassword(Config.dbPass);
		dataSource.setServerName(Config.dbHost);
		dataSource.setPort(Config.dbPort);
		dataSource.setDatabaseName(Config.dbName);

		String sql = "select duration, pulses, sport, goal, privacy, extraField, date from workouts where username = ?";

		Connection conn = null;
		ResultSet resultSet = null; // needed for reading output from database
		ArrayList<Workout> workouts = new ArrayList<Workout>();

		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, username);
			resultSet = ps.executeQuery();
			while (resultSet.next()) {

				Sport sport = new Sport(resultSet.getString(3));
				boolean privacy = resultSet.getBoolean(5);
				Workout workout = new Workout(sport, privacy);
				workout.setDuration(resultSet.getInt(1));
				workout.setGoal(resultSet.getString(4));
				workout.setDate(resultSet.getDate(7));
				String pulsesString = resultSet.getString(2);
				List<Integer> pulses = Stream.of(pulsesString.split(",")).map(Integer::parseInt)
						.collect(Collectors.toList()); // oulses are stored as String e. g. "123,155,143,100". This
														// handles that.
				workout.setPulses(pulses);
				workouts.add(workout);

			}

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
				}
			}
		}

		return workouts;
	}

	public static Map<Date, List<Integer>> getGraphInformation(String username) {

		MysqlDataSource dataSource = new MysqlDataSource();
		dataSource.setUser(Config.dbUser);
		dataSource.setPassword(Config.dbPass);
		dataSource.setServerName(Config.dbHost);
		dataSource.setPort(Config.dbPort);
		dataSource.setDatabaseName(Config.dbName);

		Calendar date = Calendar.getInstance();
		date.add(Calendar.DATE, -7); // Setting to today - 7 days
		Date dato = new Date(date.getTime().getTime()); // jav.sql.Date for usage in DB. Calendar not compatible.
		String sql = "select pulses, date, duration from workouts where username = ? and date >= ? order by date asc";
		String sqlMaxpuls = "select maxpuls from users where username = ?"; // for future use if percentage in puls zone
																			// is needed

		Connection conn = null;
		ResultSet resultSet = null;
		ResultSet resultMaxpuls = null;// needed for reading output from database
		Map<Date, List<Integer>> exercise = new HashMap<Date, List<Integer>>();
		Map<Date, List<Integer>> dateandintesity = new HashMap<Date, List<Integer>>();
		int Maxpuls;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, username);
			ps.setDate(2, dato);
			resultSet = ps.executeQuery();
			PreparedStatement mp = conn.prepareStatement(sqlMaxpuls);
			mp.setString(1, username);
			resultMaxpuls = mp.executeQuery();
			resultMaxpuls.next();
			Maxpuls = resultMaxpuls.getInt(1);

			while (resultSet.next()) {
				String puls = resultSet.getString(1);
				List<Integer> pulses = Stream.of(puls.split(",")).map(Integer::parseInt).collect(Collectors.toList());
				Date workoutDate = resultSet.getDate(2);
				if (exercise.containsKey(workoutDate)) { // checking for multiple exercises on one day.
					exercise.get(workoutDate).addAll(pulses);

				} else {
					exercise.put(workoutDate, pulses);

				}
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
				}
			}
		}

		int s1 = (int) (Maxpuls * 0.72);
		int s2 = (int) (Maxpuls * 0.82);
		int s3 = (int) (Maxpuls * 0.87);
		int s4 = (int) (Maxpuls * 0.92);

		List<Integer> numberofpulses = new ArrayList<Integer>(Collections.nCopies(5, 0));
		for (Date exercisedate : exercise.keySet()) {
			int length = exercise.get(exercisedate).size();

			for (int number : exercise.get(exercisedate)) {
				if (number < s1) {
					int temp = numberofpulses.get(0);
					temp += 1;
					numberofpulses.set(0, temp);
				} else if (number < s2) {
					int temp = numberofpulses.get(1);
					temp += 1;
					numberofpulses.set(1, temp);
				} else if (number < s3) {
					int temp = numberofpulses.get(2);
					temp += 1;
					numberofpulses.set(2, temp);
				} else if (number < s4) {
					int temp = numberofpulses.get(3);
					temp += 1;
					numberofpulses.set(3, temp);
				} else {
					int temp = numberofpulses.get(4);
					temp += 1;
					numberofpulses.set(4, temp);
				}

			}
			// calculating percentage
			double p1 = (double) numberofpulses.get(0) / (double) length;
			double p2 = (double) numberofpulses.get(1) / (double) length;
			double p3 = (double) numberofpulses.get(2) / (double) length;
			double p4 = (double) numberofpulses.get(3) / (double) length;
			double p5 = (double) numberofpulses.get(4) / (double) length;

			// System.out.println(numberofpulses.toString());
			List<Double> precentageinsone = Arrays.asList(p1, p2, p3, p4, p5);
			// System.out.println(datoMongo.toGMTString());
			dateandintesity.put(exercisedate, numberofpulses);
		}
		return dateandintesity;

	}

	public static String getSportForCoach(String username) {

		MysqlDataSource dataSource = new MysqlDataSource();
		dataSource.setUser(Config.dbUser);
		dataSource.setPassword(Config.dbPass);
		dataSource.setServerName(Config.dbHost);
		dataSource.setPort(Config.dbPort);
		dataSource.setDatabaseName(Config.dbName);

		String sql = "select sport from users where username = ?";

		Connection conn = null;
		ResultSet resultSet = null; // needed for reading output from database
		String feedback = "";
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, username);
			resultSet = ps.executeQuery();
			if (resultSet.next()) { // seeing if query returns empty table of data
				feedback = resultSet.getString(1);
			} else {
				feedback = "Not a registered coach";
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
				}
			}
		}

		return feedback;
	}

	public static String getNameForUser(String username) {

		MysqlDataSource dataSource = new MysqlDataSource();
		dataSource.setUser(Config.dbUser);
		dataSource.setPassword(Config.dbPass);
		dataSource.setServerName(Config.dbHost);
		dataSource.setPort(Config.dbPort);
		dataSource.setDatabaseName(Config.dbName);

		String sql = "select firstname, surname from users where username = ?";

		Connection conn = null;
		ResultSet resultSet = null; // needed for reading output from database
		String feedback = "";

		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, username);
			resultSet = ps.executeQuery();
			if (resultSet.next()) { // seeing if query returns empty table of data
				feedback = resultSet.getString(1);
				feedback += " ";
				feedback += resultSet.getString(2);

			} else {
				feedback = "Couldn't find user";
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
				}
			}
		}

		return feedback;
	}

    
	public static String deleteUserFromDB(String username) {
		
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
	                 ps.setString(1,  username);
	                 resultSet = ps.executeQuery();
	                 if (resultSet.next() ) { //seeing if query returns empty table of data	    
	                	    feedback = resultSet.getString(1);
	                	} 
	                 else {
	                	 feedback = "Not a registered coach";
	                 }
            }catch (SQLException e) {            	 
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
       
public static String getNameForUser(String username) {
           
          MysqlDataSource dataSource = new MysqlDataSource();
          dataSource.setUser(Config.dbUser);
          dataSource.setPassword(Config.dbPass);
          dataSource.setServerName(Config.dbHost);
          dataSource.setPort(Config.dbPort);
          dataSource.setDatabaseName(Config.dbName);
          
          String sql = "select firstname, surname from users where username = ?";
          
          Connection conn = null;
          ResultSet resultSet = null; // needed for reading output from database
          String feedback = "";
          
          try {
                   conn = dataSource.getConnection();
                   PreparedStatement ps = conn.prepareStatement(sql);
                   ps.setString(1,  username);
                   resultSet = ps.executeQuery();
                   if (resultSet.next() ) { //seeing if query returns empty table of data  
 
                          feedback = resultSet.getString(1);
                          feedback += " ";
                          feedback+= resultSet.getString(2);
                          
                      } 
                   else {
                       feedback = "Couldnt find user";
                   }
          }catch (SQLException e) {                
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
	
	public static String deleteWorkoutFromDB(String username) {
		
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
