/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package artraction.controller;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
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
public class CPanelController implements Initializable {

    @FXML
    private Button logout;
    @FXML
    private Button codepromo;
     @FXML
    private AnchorPane rootpane;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        codepromo.setOnAction(event->{
            try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/artraction/view/codepromo.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(CPanelController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }    

    @FXML
    private void onAdminCompteClicked(MouseEvent event)throws Exception{
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/artraction/view/compte.fxml"));
       rootpane.getChildren().setAll(pane);
//            throws Exception{
//        Parent root = FXMLLoader.load(getClass().getResource("/artration/view/compte.fxml"));
//        Stage mainStage = new Stage();
//        Scene scene = new Scene(root);
//        mainStage.setScene(scene);
//        mainStage.show();
    }

    @FXML
    private void oncliquelogout(MouseEvent event) throws Exception{
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/artraction/view/FXMLDocument.fxml"));
       rootpane.getChildren().setAll(pane);
    }
    
    
    
}
