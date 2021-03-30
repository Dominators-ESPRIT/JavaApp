/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package artraction.view;

import artraction.entity.Competition;
import artraction.entity.OeuvreCompetitionnModel;
import artraction.entity.Oeuvre_competition;
import artraction.service.CompetitionService;
import artraction.service.OeuvreCompetitionService;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLDataException;
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
public class UserConsulterOeuvreCompetitionController implements Initializable {

    @FXML
    private GridPane oeuvrecontainer;
    private List<Oeuvre_competition> Liste_oc;
    private List<OeuvreCompetitionnModel> listeOcm;
    
    private int refCompetition ;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        int column = 0;
        int row = 1;
        try {
            listeOcm = new ArrayList<>(Oeuvrecompetitions());
            for (Iterator it = listeOcm.iterator(); it.hasNext();) {
                OeuvreCompetitionnModel ocm = (OeuvreCompetitionnModel) it.next();
                //CompUpdate.liste.add(c);
                FXMLLoader fxmlloader = new FXMLLoader();

                /*fxmlloader.setLocation(getClass().getResource("/com/pidev/view/Event.fxml"));
                FXMLLoader fxmlLoader1 = new FXMLLoader();*/
                fxmlloader.setLocation(getClass().getResource("/artraction/view/CardOeuvreCompetition.fxml"));
                VBox cardbox = fxmlloader.load();
                CardOeuvreCompetitionController cocc = (CardOeuvreCompetitionController) fxmlloader.getController();

                //System.out.println(c.getNom());
                cocc.setDataModel(ocm, refCompetition);
                if (column == 3) {
                    column = 0;
                    ++row;
                }
                oeuvrecontainer.add(cardbox, column++, row);
                GridPane.setMargin(cardbox, new Insets(10));

            }

        } catch (IOException ex) {
            Logger.getLogger(CardCompetitionController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLDataException ex) {
            Logger.getLogger(ArtisteConsulterCompetitionController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

//
    private List<OeuvreCompetitionnModel> Oeuvrecompetitions() throws SQLDataException {

        OeuvreCompetitionService cs = new OeuvreCompetitionService();
        List<OeuvreCompetitionnModel> ls = cs.getOeuvreCompetionById(refCompetition);
        return ls;
    }

    @FXML
    private void btnretour(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("UserConsulterCompetition.fxml"));

        Scene scene = new Scene(root);
        Stage stage = new Stage();
//        stage.setTitle("Gérer compétitions");
        stage.setScene(scene);

        Stage oldstage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        oldstage.close();
        stage.show();
    }
    
    public void initData(int refComptition){
        this.refCompetition = refComptition;
    }
}
