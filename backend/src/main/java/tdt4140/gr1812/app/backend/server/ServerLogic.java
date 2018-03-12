package tdt4140.gr1812.app.backend.server;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

import tdt4140.gr1812.app.backend.server.ServerController;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ServerLogic { // class mainly for handling connection to mySQL


        public static void signup(String username,
                                  String password,
                                  String sport,
                                  String firstname,
                                  String surname,
                                  String maxpulse,
                                  String weight,
                                  String gender) {
        		//connecting to database
            MysqlDataSource dataSource = new MysqlDataSource();
            dataSource.setUser("root");
            dataSource.setPassword("cygnus6cygnus");
            dataSource.setServerName("localhost");
            dataSource.setPort(3306);
            dataSource.setDatabaseName("PU");


            	//query to database
            String sql = "Insert into users" + "(username, password, firstname, surname, maxpuls, weight, gender, sport)" +
                    "values (?, ?, ?, ?, ?, ?, ?, ?)";

            Connection conn = null;

            try {
                conn = dataSource.getConnection(); // attempting to establish connection to databse
                PreparedStatement ps = conn.prepareStatement(sql); // compile rfor SQL-statement
                ps.setInt(1, Integer.parseInt(username)); // Setting variables listet as "?" in SQL-string
                ps.setString(2, password);
                ps.setString(3, firstname);
                ps.setString(4, surname);
                ps.setInt(5, Integer.parseInt(maxpulse));
                ps.setInt(6, Integer.parseInt(weight));
                ps.setString(7, gender);
                ps.setString(8, sport);
                ps.executeUpdate(); //Updating database
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
        
        public static boolean login(String username, String password) {
    			
        	 	 MysqlDataSource dataSource = new MysqlDataSource();
             dataSource.setUser("root");
             dataSource.setPassword("cygnus6cygnus");
             dataSource.setServerName("localhost");
             dataSource.setPort(3306);
             dataSource.setDatabaseName("PU");
             
             String sql = "select * from users where username = ? and password = ?";
             
             Connection conn = null;
             ResultSet resultSet = null; // needed for reading output from database
             
             try {
	            	 conn = dataSource.getConnection();
	                 PreparedStatement ps = conn.prepareStatement(sql);
	                 ps.setInt(1, Integer.parseInt(username));
	                 ps.setString(2,  password);
	                 resultSet = ps.executeQuery();
	                 if (resultSet.next() ) { //seeing if query returns empty table of data, meaning that user/pw-combo doesn't exist and login not possible
	                	    return true;
	                	} 
	                 else {
	                	 	return false; 
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
    	
    }

}

