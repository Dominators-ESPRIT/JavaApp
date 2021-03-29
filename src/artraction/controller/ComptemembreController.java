/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package artraction.controller;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author MSI
 */
public class ComptemembreController implements Initializable {

    @FXML
    private Button logout;
     @FXML
    private AnchorPane rootpane;
    @FXML
    private Button btncompte;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    private void onArtisteCompteClicked(MouseEvent event) throws Exception{
        /*Parent root = FXMLLoader.load(getClass().getResource("profilartiste.fxml"));
        Stage mainStage = new Stage();
        Scene scene = new Scene(root);
        mainStage.setScene(scene);
        mainStage.show();*/
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/artraction/view/profilmembre.fxml"));
        rootpane.getChildren().setAll(pane);
    }

    @FXML
    private void oncliquelogout(MouseEvent event) throws Exception{
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/artraction/view/FXMLDocument.fxml"));
       rootpane.getChildren().setAll( pane);
    }

    @FXML
    private void onAdminCompteClicked(MouseEvent event) {
    }
    
}
