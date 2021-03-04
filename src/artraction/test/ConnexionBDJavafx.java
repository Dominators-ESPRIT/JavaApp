package artraction.test;

import artraction.utils.ConnexionSingleton;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javax.swing.JOptionPane;


public class ConnexionBDJavafx extends Application {

    private Stage primaryStage;
    private Parent parentPage;
   
    @Override
    public void start(Stage primaryStage) throws IOException {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("hello ");
        
        parentPage = FXMLLoader.load(getClass().getResource("/artraction/view/panier.fxml"));
        Scene scene = new Scene(parentPage);
        this.primaryStage.setScene(scene);
        this.primaryStage.show();
 

    }

    /**
     * @param args the command line arguments
     * @throws java.sql.SQLException
     */
    public static void main(String[] args) throws SQLException {
        /*Connection con=null;
        PreparedStatement pst;
        
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con=DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/artraction", "root", "");
            System.out.println(con);
            pst=con.prepareStatement("insert into panier (etat) values('1')");
            int status=pst.executeUpdate();
            if (status==1)
                System.out.println("mala jaaaaaaaaaaaaaaaaaaaaaaaaaaaaaw");
            else System.out.println("nshalah lyouuuum");
            
            
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ConnexionBDJavafx.class.getName()).log(Level.SEVERE, null, ex);
        }
                
                
                
                
                
                
          */
        launch(args);
    }

}
