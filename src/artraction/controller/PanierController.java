/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package artraction.controller;

import artraction.entity.codepromo;
import artraction.entity.oeuvre;
import artraction.entity.panier;
import artraction.service.ajoutoeuvservice;
import artraction.service.codepromoservice;
import artraction.service.panierService;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

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
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class PanierController implements Initializable {


    @FXML
    private RadioButton exp;

    @FXML
    private ToggleGroup livraison;

    @FXML
    private RadioButton stand;
      @FXML

    private Button appliquer_bt;

      @FXML
    private TextField codefield;

    @FXML
    private Button valider_btn;
    
    @FXML
    private Text soustot;

    @FXML
    private Text liv;
    
    
    @FXML
    private Text err;

    @FXML
    private Text tot;
    
    
   
   private ObservableList<oeuvre> listpanier = FXCollections.observableArrayList();
    Double soustotal=0.0;
    Double total= 0.0;
    Double s=0.0;
     ArrayList<String> codes = new ArrayList<String>(); 

   int codeid=-1;
   
   
   
   
   public double sommetotale(ObservableList<oeuvre> listpanier){
       double s=0.0;
         for(int i=0;i<listpanier.size();i++)
       {
         s+=listpanier.get(i).getPrix();
       }
       return s;
   }
   
   
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {

      try {
        ajoutoeuvservice cp = new ajoutoeuvservice();
        panierService pan1=panierService.getInstance();
        codepromoservice code=codepromoservice.getInstance();
       int x=pan1.displayId();
       
       
       listpanier=cp.displaypanier(x);
       for(int i=0;i<listpanier.size();i++)
       {
            System.out.println("Ref: "+listpanier.get(i).getRef()+" | Label: "+listpanier.get(i).getLabel()+" | Prix: "+listpanier.get(i).getPrix());
       }
     soustotal=sommetotale(listpanier);
        soustot.setText(soustotal.toString());
        
          appliquer_bt.setOnAction(event->{
              exp.setSelected(false);
              stand.setSelected(false);
              liv.setText("");
              total=soustotal;
              
        tot.setText(total.toString());
               int z;
           
                String chp=codefield.getText();
                boolean existe=false;
                
                codepromo codep=new codepromo();
                codes=pan1.displaycodepromo();

                for (int i=0; i<codes.size(); i++) 
                
                      if (chp.equals(codes.get(i)))
                      {
                        err.setText(""); 
                        z = pan1.displaycode(chp);
                        total=total-((total*z)/100);
                        tot.setText(total.toString());  
                        codeid=code.displayId(chp);
                        break;
                    }else err.setText("Code promo non valide");
                         
          });
        

        livraison.selectedToggleProperty().addListener(new ChangeListener<Toggle>()  
        { 
         public void changed(ObservableValue<? extends Toggle> ob, Toggle o, Toggle n) 
            { 
                if (livraison.getSelectedToggle()==exp)
                { 
                    s=total+15.0;
                    liv.setText("15DT");
                }else if(livraison.getSelectedToggle()==stand)
                    
                {   s=total+7.0;
                    liv.setText("7DT");
                }else{
                     Alert alert = new Alert(Alert.AlertType.INFORMATION);
                     alert.setTitle("Erreur");
                     alert.setHeaderText("Veuillez séléctonner la méthode de livraison");
                     alert.showAndWait();
                }
                
               tot.setText(s.toString());
             
                } 
            }); 
        
       total=soustotal;
       tot.setText(total.toString());

          valider_btn.setOnAction(event -> {
              
               if (livraison.getSelectedToggle()==null)
               {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                           alert.setTitle("Erreur");
                           alert.setHeaderText("Veuillz séléctionner une méthode de livraison!");
                           alert.showAndWait();
               }
               else{
              if(codefield.getText().equals("")==false && codeid!=-1)
                pan1.updatecode(codeid,x );
            try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/artraction/view/commande.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(PanierController.class.getName()).log(Level.SEVERE, null, ex);
            }
               }
          });
      } catch (SQLException ex) {
          Logger.getLogger(PanierController.class.getName()).log(Level.SEVERE, null, ex);
      }
    
    }


    
}