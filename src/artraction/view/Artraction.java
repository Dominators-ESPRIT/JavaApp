/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package artraction.view;

import java.io.IOException;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author boukhris
 */
public class Artraction extends Application {
    private static Artraction instance;
     private Stage stage;
    
    public static Artraction getInstance() {
        return instance;
    }
    
     public Stage getStage() {
        return stage;
     }
    @Override
    public void start(Stage stage) throws IOException {
        this.stage=stage;
         Button btn = new Button();
        Parent root = FXMLLoader.load(getClass().getResource("ArtisteConsulterCompetition.fxml"));
        
        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.show();
    }
    

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
