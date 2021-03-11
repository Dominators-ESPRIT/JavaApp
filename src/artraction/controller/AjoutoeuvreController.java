/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package artraction.controller;

import artraction.entity.oeuvre;
import artraction.entity.panier;
import artraction.service.ajoutoeuvservice;
import artraction.service.panierService;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author zeyne
 */
public class AjoutoeuvreController implements Initializable {

    /**
     * Initializes the controller class.
     */
        @FXML
    private Button addtocart;
        
        
    @FXML
    private TextField reference;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
            panierService panier1 = null;
            try {
                panier1 = panierService.getInstance();
            } catch (SQLException ex) {
                Logger.getLogger(AjoutoeuvreController.class.getName()).log(Level.SEVERE, null, ex);
            }
                    panier p1=new panier();
                    p1.setetat(1);
                    panier1.insert(p1);
                     int x=panier1.displayId();
             
        addtocart.setOnAction(event->{
             Stage popupwindow=new Stage();
                String ch=reference.getText().toString();
                if(ch.equals("") || ch==null){
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Alert");
                    alert.setHeaderText(null);
                    alert.setContentText("Vous devez saisir une Réference valide");
                    alert.showAndWait();
                
                } else {
                try {
                    oeuvre cp1 = new oeuvre(ch);
                    ajoutoeuvservice  cp = new ajoutoeuvservice();
                    cp.insert(cp1);    
                    ajoutoeuvservice prd1= new ajoutoeuvservice();
                    oeuvre oeuv = new oeuvre(ch);
                    System.out.println("REF: "+ch);
                    int updstatus=prd1.updateidpanier(oeuv, x); 
                    if (updstatus>=1)
                        System.out.println("update FK reussi id_panier= '"+x+"' where ref = '"+ch+"");
                    else System.out.println("id_panier= '"+x+"' where ref = '"+oeuv.getRef()+"'/"+updstatus);
                } catch (SQLException ex) {
                    Logger.getLogger(AjoutoeuvreController.class.getName()).log(Level.SEVERE, null, ex);
                }
            
      
            popupwindow.initModality(Modality.APPLICATION_MODAL);
            popupwindow.setTitle("This is a pop up window");
            Label label1= new Label("Produit ajouté au panier avec succés");
            Button button1= new Button("poursuivre mon shopping");
            Button button2= new Button("consulter le panier");
            button1.setOnAction(e -> popupwindow.close());
            
            button2.setOnAction(e -> {
                popupwindow.close();

                    try {
                        Parent page1 = FXMLLoader.load(getClass().getResource("/artraction/view/panier.fxml"));
                        Scene scene = new Scene(page1);
                        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        stage.setScene(scene);
                        stage.show();
                    } catch (IOException ex) {
                        Logger.getLogger(AjoutoeuvreController.class.getName()).log(Level.SEVERE, null, ex);
                    }
             });

            VBox layout= new VBox(50);
            layout.getChildren().addAll(label1,button1,button2);
            layout.setAlignment(Pos.CENTER);
            Scene scene1= new Scene(layout, 270, 250);
            popupwindow.setScene(scene1);
            popupwindow.showAndWait();
                }
        });
    }    
    
}
