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
import artraction.service.IdaoUser;
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
    private GridPane grid;
       @FXML
    private ScrollPane affichage;
   @FXML
    private TextField codefield;

    @FXML
    private TextField adresse_livraison;
    /**
     * Initializes the controller class.
     */
    
    private ObservableList<oeuvre> listpanier = FXCollections.observableArrayList();
    public PanierController panier=new PanierController();
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
   
        try {
            // TODO
            panierService pan1=panierService.getInstance();
            commandeservice com=commandeservice.getInstance();
            commande cmd = new commande();
            User user=User.getInstance();
            int y = pan1.displayId();
            userEntity u = new userEntity();
            u =user.displayUser();
          
            int iduser=u.getId();
            
            System.out.println("iduser: "+iduser);
            String email=u.getEmail();
            String username=u.getUsername();
           

             ajoutoeuvservice cp = new ajoutoeuvservice();
               listpanier=cp.displaypanier(y);
               for( int i=0;i<listpanier.size();i++)
       {   int j=i;
          
                Text ref = new Text(listpanier.get(i).getRef());
                Text label = new Text(listpanier.get(i).getLabel());
                Text prix = new Text(listpanier.get(i).getPrix().toString());
            ref.setFont(Font.font("", FontWeight.BOLD,  16));
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
               
               
               
               
            confirmer_btn.setOnAction(event -> {
               
                try {
                    pan1.update(y);
                    PDF pdf=new  PDF();
              
                   String pdffile = "C:/Users/zeyne/Documents/NetBeansProjects/dominators/"+pdf.commandePDF();
                    System.out.println(pdffile);
                    JavaMailUtil.sendMail(email,pdffile,username);
                    JOptionPane.showMessageDialog(null, "Facture envoyée. Consulter votre boite mail", "", JOptionPane.INFORMATION_MESSAGE);
                    
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
