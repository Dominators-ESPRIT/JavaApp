/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package artraction.view;

import artraction.entity.Competition;
import artraction.entity.OeuvreCompetitionnModel;
import artraction.service.OeuvreCompetitionService;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLDataException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author boukhris
 */
public class ArtisteconsGerOeuvreCompetitionController implements Initializable {

    @FXML
    private GridPane competitioncontainer;

    private List<OeuvreCompetitionnModel> Liste_competition;
    
    private int refCompetition;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        int column=0;
        int row=1;
        try {
             Liste_competition=new ArrayList<>(OeuvreCompetition());
            
             for (Iterator it = Liste_competition.iterator(); it.hasNext();) {
                OeuvreCompetitionnModel ocm = (OeuvreCompetitionnModel) it.next();
                //CompUpdate.liste.add(c);
                FXMLLoader fxmlloader = new FXMLLoader();
               
                
                /*fxmlloader.setLocation(getClass().getResource("/com/pidev/view/Event.fxml"));
                FXMLLoader fxmlLoader1 = new FXMLLoader();*/
                fxmlloader.setLocation(getClass().getResource("/artraction/view/CardArtGererOC.fxml"));
                VBox cardbox = fxmlloader.load() ;
                CardArtGererOCController ccc=(CardArtGererOCController) fxmlloader.getController();
                 //System.out.println(c.getNom());
                ccc.setData(ocm);
                if(column ==3){
                   column =0;
                           ++row;
                 }
                 competitioncontainer.add(cardbox, column++, row);
               GridPane.setMargin(cardbox, new Insets(10));
                
                
 
             }     
      
        } catch (IOException ex) {
            Logger.getLogger(CardCompetitionController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ArtisteConsulterCompetitionController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private List<OeuvreCompetitionnModel> OeuvreCompetition() throws SQLException{
        OeuvreCompetitionService ocs = new OeuvreCompetitionService();
        return ocs.getOuvreCompetitionByArtist(refCompetition, 3);
    }
    
    public void setRefCompetition(int refCompetition){
        this.refCompetition = refCompetition;
    }

    @FXML
    private void retour(MouseEvent event) throws IOException {
         Parent root = FXMLLoader.load(getClass().getResource("ArtisteConsulterCompetition.fxml"));

        Scene scene = new Scene(root);
        Stage stage = new Stage();
//        stage.setTitle("Gérer compétitions");
        stage.setScene(scene);

        Stage oldstage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        oldstage.close();
        stage.show();
    }

}
