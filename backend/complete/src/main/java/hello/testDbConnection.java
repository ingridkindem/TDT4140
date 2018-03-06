package hello;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import test.Utover;
import hello.HelloController;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class testDbConnection {

//
//    public static void connect() {
//        MysqlDataSource dataSource = new MysqlDataSource();
//        dataSource.setUser("user");
//        dataSource.setPassword("password123");
//        dataSource.setServerName("localhost");
//        dataSource.setPort(3306);
//        dataSource.setDatabaseName("PU");
//    }


//    public static void main(String[] args) {
//
//        MysqlDataSource dataSource = new MysqlDataSource();
//        dataSource.setUser("user");
//        dataSource.setPassword("password123");
//        dataSource.setServerName("localhost");
//        dataSource.setPort(3306);
//        dataSource.setDatabaseName("PU");
//
//        try {
//            Connection conn = dataSource.getConnection();
//
//            Statement stmt = conn.createStatement();
//            ResultSet rs = stmt.executeQuery("SELECT * FROM users");
//
//            System.out.println("Hello world!");
//
//            rs.close();
//            stmt.close();
//            conn.close();
//        } catch (SQLException e) {
//            e.printStackTrace();
//
//        }
//    }

        public static void signup(String username,
                                  String password,
                                  String sport,
                                  String firstname,
                                  String surname,
                                  String maxpulse,
                                  String weight,
                                  String gender){

            MysqlDataSource dataSource = new MysqlDataSource();
            dataSource.setUser("user");
            dataSource.setPassword("password123");
            dataSource.setServerName("localhost");
            dataSource.setPort(3306);
            dataSource.setDatabaseName("PU");



            String sql = "Insert into users" + "(username, password, firstname, surname, maxpuls, weight, gender, sportsID)" +
                    "values (?, ?, ?, ?, ?, ?, ?, ?)";

            Connection conn = null;

            try {
                conn = datasource.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setInt(1, Integer.parseInt(username));
                ps.setString(2, password);
                ps.setString(3, firstname);
                ps.setString(4, surname);
                ps.setInt(5, Integer.parseInt(maxpulse));
                ps.setInt(6, Integer.parseInt(weight));
                ps.set
                ps.executeUpdate();
                ps.close();

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


//        String sql = "select * from Utover where UtoverID = ?";
//
//        Connection conn = null;
//
//        try{
//            conn = datasource.getConnection();
//            PreparedStatement ps = conn.prepareStatement(sql);
//            ps.setInt(1, utoverID);
//            Utover utover = null;
//            ResultSet rs = ps.executeQuery();
//            if (rs.next()){
//
//                utover = new Utover(
//                        rs.getInt("UtoverID"),
//                        rs.getInt("Alder"),
//                        rs.getString("Navn"));
//
//            }
//            rs.close();
//            ps.close();
//            return utover;
//
//
//        }catch (SQLException e){
//            throw new RuntimeException(e);
//        }finally {
//            if(conn != null){
//                try{
//                    conn.close();
//                } catch (SQLException e){
//
//                }
//            }
//        }
//    }


//    }
}



