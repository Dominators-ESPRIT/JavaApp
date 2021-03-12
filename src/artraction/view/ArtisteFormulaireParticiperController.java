/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package artraction.view;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author boukhris
 */
public class ArtisteFormulaireParticiperController implements Initializable {

    @FXML
    private Button btnannulerao;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void annulerajout(ActionEvent event) throws IOException {
         Parent root = FXMLLoader.load(getClass().getResource("UserListCompetition.fxml"));
        
        Scene scene = new Scene(root);
        Stage stage = new Stage();
//        stage.setTitle("Gérer compétitions");
        stage.setScene(scene);
       
        
        Stage oldstage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        oldstage.close();
                 stage.show();
    }
    
}
