/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package artraction.view;

import artraction.entity.Competition;
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
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author boukhris
 */
public class CardCompetitionController implements Initializable{

    @FXML
    private Label lnom;
    @FXML
    private Label ltheme;
    @FXML
    private Label ldatedebut;
    @FXML
    private Label ldatefin;
    @FXML
    private Label ltype;
    @FXML
    private Button btnparticiper;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    public void setData(Competition competition){
            lnom.setText(competition.getNom());
             ltheme.setText(competition.getTheme());
             ldatedebut.setText(competition.getDate_debut().toString());
             ldatefin.setText(competition.getDate_fin().toString());
              ltype.setText(competition.getType());
    }

    @FXML
    private void participercompetition(ActionEvent event) throws IOException {
          Parent root = FXMLLoader.load(getClass().getResource("ArtisteFormulaireParticiper.fxml"));
        
        Scene scene = new Scene(root);
        Stage stage = new Stage();
//        stage.setTitle("Gérer compétitions");
        stage.setScene(scene);
       
        
        Stage oldstage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        oldstage.close();
                 stage.show();
    }
}
