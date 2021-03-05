package artraction.service;

import artraction.entity.users;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author amir
 */
public class Artraction extends Application {
    public static Artraction instance;
    public users U = null;
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        
        Scene scene = new Scene(root);   
        stage.setScene(scene);
        stage.show();
    }
    
    public static Artraction getInstance()
    {
        if(instance==null)
           instance=new Artraction();
       return instance;
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}