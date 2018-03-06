package test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.sql.DataSource;
import test.Utover;
import test.UtoverDAO;

public class JdbcUtoverDAO implements UtoverDAO {

    private DataSource datasource;

    public void setDatasource(DataSource datasource){
        this.datasource = datasource;
    }

    public void insert (Utover utover){

        String sql = "Insert into Utover" + "(UtøverID, Alder, Navn)" + "values (?, ?, ?)";

        Connection conn = null;

        try{
            conn = datasource.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, utover.getUtoverID());
            ps.setInt(2, utover.getAlder());
            ps.setString(3, utover.getNavn());
            ps.executeUpdate();
            ps.close();

        }catch (SQLException e){
            throw new RuntimeException(e);
        }finally {
            if (conn != null){
                try {
                    conn.close();
                }
                    catch(SQLException e){
                    }
                }
            }
        }
    public Utover findByUtoverID(int utoverID){

        String sql = "select * from Utover where UtoverID = ?";

        Connection conn = null;

        try{
            conn = datasource.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, utoverID);
            Utover utover = null;
            ResultSet rs = ps.executeQuery();
            if (rs.next()){

                utover = new Utover(
                        rs.getInt("UtoverID"),
                        rs.getInt("Alder"),
                        rs.getString("Navn"));

            }
            rs.close();
            ps.close();
            return utover;


        }catch (SQLException e){
            throw new RuntimeException(e);
        }finally {
            if(conn != null){
                try{
                    conn.close();
                } catch (SQLException e){

                }
            }
        }
    }
}




