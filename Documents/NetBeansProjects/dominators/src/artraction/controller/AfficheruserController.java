/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package artraction.controller;

import artraction.controller.*;
import artraction.service.User;
import artraction.entity.userEntity;
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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author MSI
 */
public class AfficheruserController implements Initializable {

    private List<userEntity> Liste_user;
    @FXML
    private GridPane usercontainer;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         int column=0;
        int row=1;
        try {
             Liste_user = new ArrayList<>(users());
            
             for (Iterator it = Liste_user.iterator(); it.hasNext();) {
                userEntity c = (userEntity) it.next();
                //CompUpdate.liste.add(c);
                FXMLLoader fxmlloader = new FXMLLoader();
               
                
                /*fxmlloader.setLocation(getClass().getResource("/com/pidev/view/Event.fxml"));
                FXMLLoader fxmlLoader1 = new FXMLLoader();*/
                fxmlloader.setLocation(getClass().getResource("/artraction/view/carduser.fxml"));
                VBox cardbox = fxmlloader.load() ;
                CarduserController ccc=(CarduserController) fxmlloader.getController();
                 //System.out.println(c.getNom());
                ccc.setData(c);
                if(column ==3){
                   column =0;
                           ++row;
                 }
                 usercontainer.add(cardbox, column++, row);
               GridPane.setMargin(cardbox, new Insets(10));
                
                
 
             }     
      
        } catch (IOException ex) {
            Logger.getLogger(CarduserController.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (SQLDataException ex)
//        {
//            Logger.getLogger(AfficheruserController.class.getName()).log(Level.SEVERE, null, ex);
//         }
    }   catch (SQLDataException ex) {
            Logger.getLogger(AfficheruserController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(AfficheruserController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
         private List<userEntity> users() throws SQLDataException, Exception{

   User cs = new User();
   List<userEntity> ls = cs.afficher() ;
    return ls;
      }

    @FXML
    private void btnretour(MouseEvent event) {
    }
    }    

   
    

