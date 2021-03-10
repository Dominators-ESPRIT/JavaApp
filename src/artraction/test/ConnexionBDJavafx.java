package artraction.test;

import artraction.entity.panier;
import artraction.service.panierService;
import artraction.service.codepromoservice;
import artraction.controller.CodepromoController;
import java.io.IOException;
import java.sql.SQLException;
import javafx.application.Application;
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
         //Application.setUserAgentStylesheet(getClass().getResource("/artraction/view/fxml.css").toExternalForm());
        Parent root = loader.load();
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     * @throws java.sql.SQLException
     */
    public static void main(String[] args) throws SQLException {
     
       launch(args);
    }

}
