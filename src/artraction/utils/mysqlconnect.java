package artraction.utils;

import artraction.entity.userEntity;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javax.swing.JOptionPane;

/**
 *
 * @author amir
 */
public class mysqlconnect {
    
    Connection conn = null;
    public static Connection ConnectDb(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/art","root","");
           // JOptionPane.showMessageDialog(null, "Connection Established");
            return conn;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            return null;
        }
    
    }
    
    public static ObservableList<userEntity> getDatausers(){
        Connection conn = ConnectDb();
        ObservableList<userEntity> list = FXCollections.observableArrayList();
        try {
            PreparedStatement ps = conn.prepareStatement("select * from user");
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()){   
                list.add(new userEntity(Integer.parseInt(rs.getString("id")), rs.getString("username"), rs.getString("email"), rs.getString("password"), rs.getInt("numero"), rs.getInt("age"), rs.getString("addresse"), rs.getString("role")));
            }
        } catch (Exception e) {
        }
        return list;
    }
    
        public static ObservableList<userEntity> getDatauserById(int id){
        Connection conn = ConnectDb();
        ObservableList<userEntity> list = FXCollections.observableArrayList();
        try {
            PreparedStatement ps = conn.prepareStatement("select * from users where id="+id);
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()){   
                list.add(new userEntity(Integer.parseInt(rs.getString("id")), rs.getString("username"), rs.getString("email"), rs.getString("password"), rs.getInt("numero"), rs.getInt("age"), rs.getString("addresse"), rs.getString("role")));
            }
        } catch (Exception e) {
        }
        return list;
    }
    
}
