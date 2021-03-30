package artraction.test;

import artraction.entity.panier;
import artraction.service.panierService;
import artraction.service.codepromoservice;
import artraction.controller.CodepromoController;
import artraction.entity.userEntity;
import static artraction.utils.mysqlconnect.ConnectDb;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ConnexionBDJavafx extends Application {

    private Stage primaryStage;
    private Parent parentPage;
   
    @Override
  public void start(Stage primaryStage) throws IOException {
       
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/artraction/view/ajoutoeuvre.fxml"));
      //  FXMLLoader loader = new FXMLLoader(getClass().getResource("/artraction/view/codepromo.fxml"));

        //Application.setUserAgentStylesheet(getClass().getResource("/artraction/view/fxml.css").toExternalForm());
        Parent root = loader.load();
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
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

    /**
     * @param args the command line arguments
     * @throws java.sql.SQLException
     */
    public static void main(String[] args) throws SQLException {
     
       launch(args);
    }

}
