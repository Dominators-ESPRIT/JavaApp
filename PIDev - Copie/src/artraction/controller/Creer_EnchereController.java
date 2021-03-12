/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package artraction.controller;

import artraction.entity.Enchere;
import artraction.service.EnchereService;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Hanine
 */
public class Creer_EnchereController implements Initializable {

    @FXML
    private SplitPane split;
    @FXML
    private ImageView logouser;
    @FXML
    private Text titre;
    @FXML
    private Pane pane;
    @FXML
    private TextField prixinit;
    @FXML
    private DatePicker Date;
    @FXML
    private TextField duree;
    @FXML
    private TextField coupant;
    @FXML
    private Button btncreerEnchere;
    @FXML
    private Button btnannuler;
    @FXML
    private Label prix_init;
    @FXML
    private Label date_enchere;
    @FXML
    private MenuBar menuBar;
    @FXML
    private MenuItem exit;
    @FXML
    private Label coupant_enchere;
    @FXML
    private Label duree_enchere;
    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
  btncreerEnchere.setOnAction((ActionEvent event) -> {
      Enchere e = new Enchere(prixinit.getText(),duree.getText(),coupant.getText());
      EnchereService eservice =EnchereService.getInstance();
      eservice.insert(e);
      
      Alert alert = new Alert(Alert.AlertType.INFORMATION);
      alert.setTitle("");
      alert.setHeaderText(null);
      alert.setContentText("Enchere créer avec succés!");
      alert.show();
      prixinit.setText("");
      //Date.setValue("");
      duree.setText("");
      coupant.setText("");
  });
     
   btnannuler.setOnAction(event -> {

            try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/artraction/view/ListEnchere.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            
        }    catch (IOException ex) { 
                 Logger.getLogger(Creer_EnchereController.class.getName()).log(Level.SEVERE, null, ex);
             }
        } 
    );
   
} } 