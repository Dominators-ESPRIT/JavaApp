/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package artraction.controller;

import artraction.entity.codepromo;
import artraction.entity.commande;
import artraction.entity.oeuvre;
import artraction.entity.panier;
import artraction.entity.userEntity;
import artraction.service.User;
import artraction.service.ajoutoeuvservice;
import artraction.service.codepromoservice;
import artraction.service.commandeservice;
import artraction.service.panierService;
import static java.awt.SystemColor.text;
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
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.Background;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javax.swing.JTextArea;

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
    private GridPane grid;
    @FXML
    private Text soustot;

    @FXML
    private Text liv;
    
    
    @FXML
    private Text err;

    @FXML
    private Text tot;
    
      @FXML
    private ScrollPane affichage;
   
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
        //affichage.setContent(new ImageView(roses));
        affichage.setVbarPolicy(ScrollBarPolicy.ALWAYS);
        Text ref = null;
        Text label = null;
        Text prix=null;
  
      try {

        ajoutoeuvservice cp = new ajoutoeuvservice();
        panierService pan1=panierService.getInstance();
        codepromoservice code=codepromoservice.getInstance();
       int x=pan1.displayId();
       
                 
            commandeservice com=commandeservice.getInstance();
            commande cmd = new commande();
            User user=User.getInstance();
            userEntity u = new userEntity();
            u =user.displayUser();
            int reff=com.displayRef();
            int iduser=u.getId();
            cmd.setRef(reff);
            cmd.setId_panier(x);
            cmd.setId_user(iduser);
            com.insert(cmd);

       
       
       
       listpanier=cp.displaypanier(x);
       
        for( int i=0;i<listpanier.size();i++)
       {   int j=i;
           
           Button del = new Button("-");
            //del.setStyle("-fx-background-color: #e5cbcb");
           grid.setConstraints(del,1,i+1);
            ref=new Text(listpanier.get(i).getRef());
            label=new Text(listpanier.get(i).getLabel());
            prix = new Text(listpanier.get(i).getPrix().toString());
            ref.setFont(Font.font("", FontWeight.BOLD,  13));
            label.setFont(Font.font("", FontWeight.BOLD,  20));
            prix.setFont(Font.font("", FontWeight.BOLD,  20));
            grid.setConstraints(ref,2,i+1);
            grid.setConstraints(label,3,i+1);
            grid.setConstraints(prix,4,i+1);
            grid.getChildren().addAll(del,ref,label,prix);
            RowConstraints row = new RowConstraints();
            row.setPrefHeight(30);
           grid.getRowConstraints().addAll(row);
            del.setOnAction(event->{
                pan1.deleteoeuv(listpanier.get(j).getRef());
                Parent page1;
           try {
               page1 = FXMLLoader.load(getClass().getResource("/artraction/view/panier.fxml"));
           
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
           } catch (IOException ex) {
               Logger.getLogger(PanierController.class.getName()).log(Level.SEVERE, null, ex);
           }
            });
            
       }
        affichage.setContent(grid);


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
      } catch (Exception ex) {
            Logger.getLogger(PanierController.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }


    
}
