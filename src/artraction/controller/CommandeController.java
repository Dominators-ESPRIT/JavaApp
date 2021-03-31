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
import artraction.entity.oeuvre;
import artraction.entity.userEntity;
import artraction.service.User;
import artraction.service.ajoutoeuvservice;
import artraction.utils.JavaMailUtil;
import artraction.utils.PDF;
import com.itextpdf.text.DocumentException;
import java.io.File;
import java.io.FileNotFoundException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

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
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
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
    private GridPane grid;
  @FXML
    private ScrollPane affichage;
    @FXML
    private TextField adresse_livraison;
    /**
     * Initializes the controller class.
     */
    
    
    public PanierController panier=new PanierController();
      private ObservableList<oeuvre> listpanier = FXCollections.observableArrayList();
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
   
        try {
            panierService pan1=panierService.getInstance();
             ajoutoeuvservice cp = new ajoutoeuvservice();
             commandeservice com=commandeservice.getInstance();
                     
            userEntity u= new userEntity();
            User user=User.getInstance();
            u=user.displayUser();
            String email=u.getEmail();
            String username=u.getUsername();
            int iduser =u.getId();
            int y = pan1.displayId();
             listpanier=cp.displaypanier(y);
       
        for( int i=0;i<listpanier.size();i++)
       {   
            Text ref = new Text(listpanier.get(i).getRef());
            Text label = new Text(listpanier.get(i).getLabel());
            Text prix = new Text(listpanier.get(i).getPrix().toString());
            ref.setFont(Font.font("", FontWeight.BOLD,  13));
            label.setFont(Font.font("", FontWeight.BOLD,  20));
            prix.setFont(Font.font("", FontWeight.BOLD,  20));
            grid.setConstraints(ref,0,i);
            grid.setConstraints(label,1,i);
            grid.setConstraints(prix,2,i);
            grid.getChildren().addAll(ref,label,prix);
            RowConstraints row = new RowConstraints();
            row.setPrefHeight(30);
           grid.getRowConstraints().addAll(row);
       }
        affichage.setContent(grid);
        confirmer_btn.setOnAction(event -> {
            
            String adr=adresse_livraison.getText().trim().toString();
            int ref=com.displayRef();
            if(!(adr.equals("")))
                com.updateadr(adr,ref);
            System.out.println("adresse:"+adr+" refcom:"+ref);
            
            
            
            
                pan1.update(y);
                try {
                    PDF pdf=new  PDF();
                                                                             String pdffile = "C:/Users/zeyne/Documents/NetBeansProjects/dominators/"+pdf.commandePDF();
                    System.out.println(pdffile);
                    JOptionPane.showMessageDialog(null, "Facture envoyée. Consulter votre boite mail", "", JOptionPane.INFORMATION_MESSAGE);
                    JavaMailUtil.sendMail(email,pdffile,username);
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
        } catch (Exception ex) {
            Logger.getLogger(CommandeController.class.getName()).log(Level.SEVERE, null, ex);
        }
      
    }    
    
}
