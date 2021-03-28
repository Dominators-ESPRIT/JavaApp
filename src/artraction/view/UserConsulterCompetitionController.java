/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package artraction.view;

import artraction.entity.Competition;
import artraction.service.CompetitionService;
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
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author boukhris
 */
public class UserConsulterCompetitionController implements Initializable {

    @FXML
    private GridPane competitioncontainer;
     private List<Competition> Liste_competition;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
     int column=0;
        int row=1;
        try {
             Liste_competition=new ArrayList<>(competitions());
            
             for (Iterator it = Liste_competition.iterator(); it.hasNext();) {
                Competition c = (Competition) it.next();
                //CompUpdate.liste.add(c);
                FXMLLoader fxmlloader = new FXMLLoader();
               
                
                /*fxmlloader.setLocation(getClass().getResource("/com/pidev/view/Event.fxml"));
                FXMLLoader fxmlLoader1 = new FXMLLoader();*/
                fxmlloader.setLocation(getClass().getResource("/artraction/view/UserCardCompetition.fxml"));
                VBox cardbox = fxmlloader.load() ;
                UserCardCompetitionController ucc=(UserCardCompetitionController) fxmlloader.getController();
                 //System.out.println(c.getNom());
                ucc.setData(c);
                if(column ==3){
                   column =0;
                           ++row;
                 }
                 competitioncontainer.add(cardbox, column++, row);
               GridPane.setMargin(cardbox, new Insets(10));
                
                
 
             }     
      
        } catch (IOException ex) {
            Logger.getLogger(UserCardCompetitionController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLDataException ex) {
            Logger.getLogger(UserConsulterCompetitionController.class.getName()).log(Level.SEVERE, null, ex);
        }
      }

//
    private List<Competition> competitions() throws SQLDataException{

   CompetitionService cs = new CompetitionService();
   List<Competition> ls = cs.getAllCompetition() ;
    return ls;
    }
    }    
    

