/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package artraction.controller;

import artraction.entity.commande;
import artraction.entity.panier;
import artraction.service.codepromoservice;
import artraction.service.commandeservice;
import artraction.service.panierService;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.Initializable;
import artraction.controller.PanierController;

/**
 * FXML Controller class
 *
 * @author zeyne
 */

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;



public class CommandeController implements Initializable {
    @FXML
    private Button confirmer_btn;
    @FXML
    private Button annuler_btn;
    
   @FXML
    private TextField codefield;

    @FXML
    private TextField adresse_livraison;
    /**
     * Initializes the controller class.
     */
    
    
    public PanierController panier=new PanierController();
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        System.out.println("codefield "+panier.getCodefield());
        try {
            // TODO
            codepromoservice code=codepromoservice.getInstance();
            panierService pan1=panierService.getInstance();
            commandeservice com=commandeservice.getInstance();
            int y = pan1.displayId();
            int x=pan1.update(y);
            int id=code.displayId();
            commande cmd = new commande();
            cmd.setId_panier(y);
            int ref=com.displayRef();
            cmd.setId_user(0);
            com.insert(cmd);
            cmd.setRef(ref);

            confirmer_btn.setOnAction(event -> {
                System.out.println("id:---"+id);
                System.out.println("refff"+cmd.getRef());
       com.updatecode(id,cmd);
                    confirmer_btn.setDisable(true);
               
            });
            annuler_btn.setOnAction(event -> {
                
                try {
                    Parent page1 = FXMLLoader.load(getClass().getResource("/artraction/view/panier.fxml"));
                    Scene scene = new Scene(page1);
                    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    stage.setScene(scene);
                    stage.show();
                } catch (IOException ex) {
                    Logger.getLogger(CommandeController.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
        } catch (SQLException ex) {
            Logger.getLogger(CommandeController.class.getName()).log(Level.SEVERE, null, ex);
        }
      
    }    
    
}
