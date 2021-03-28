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
import artraction.utils.JavaMailUtil;
import artraction.utils.PDF;
import com.itextpdf.text.DocumentException;
import java.io.File;
import java.io.FileNotFoundException;

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
import javax.swing.JOptionPane;



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
        
   
        try {
            // TODO
            panierService pan1=panierService.getInstance();
            commandeservice com=commandeservice.getInstance();
            commande cmd = new commande();

            int y = pan1.displayId();
            com.insert(cmd);
            int ref=com.displayRef();
            
            cmd.setRef(ref);
            cmd.setId_panier(y);
            cmd.setId_user(0);
            
            pan1.update(y);

            confirmer_btn.setOnAction(event -> {
               
                try {
                    PDF pdf=new  PDF();
               
                   String pdffile = "C:/Users/zeyne/Documents/NetBeansProjects/Gestionpanier/"+pdf.commandePDF();
                    System.out.println(pdffile);
                    JOptionPane.showMessageDialog(null, "facture enregistrée", "", JOptionPane.INFORMATION_MESSAGE);
                    JavaMailUtil.sendMail("zeyneb.sdiri@gmail.com",pdffile);
                    confirmer_btn.setDisable(true);
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(CommandeController.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(CommandeController.class.getName()).log(Level.SEVERE, null, ex);
                } catch (DocumentException ex) {
                    Logger.getLogger(CommandeController.class.getName()).log(Level.SEVERE, null, ex);
                } catch (Exception ex) {
                    Logger.getLogger(CommandeController.class.getName()).log(Level.SEVERE, null, ex);
                }
               
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
