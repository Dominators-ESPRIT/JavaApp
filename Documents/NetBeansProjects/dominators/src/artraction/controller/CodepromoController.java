/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package artraction.controller;

import artraction.controller.CommandeController;
import artraction.controller.CommandeController;
import artraction.entity.codepromo;
import artraction.service.codepromoservice;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author zeyne
 */
public class CodepromoController implements Initializable {

    /**
     * Initializes the controller class.
     */
        @FXML
    private TextField nom_code;

    @FXML
    private Button add_code;

    @FXML
    private TextField val_code;
    

    @FXML
    private Button supp_code;
    
 
       @FXML
    private Button maj_code;

    @FXML
    private TextField nom_code1;

    @FXML
    private TextField val_code1;
    
     @FXML
    private TableView<codepromo> table;

    @FXML
    private TableColumn<?, ?> label;

    @FXML
    private TableColumn<?, ?> valeur;
    
   private ObservableList<codepromo> codepromos=FXCollections.observableArrayList();
   private ObservableList<codepromo> listcode = FXCollections.observableArrayList();
  
   public boolean isnumber(String ch) {
       boolean ok= false;
    try {
           
        int x = Integer.parseInt(ch);
          ok= true;

    } catch (NumberFormatException notamuber) {
        ok= false;
    }
    return ok;
    }
    public boolean existe(String ch){
        boolean ok=false;
            try {
                codepromoservice pdao = codepromoservice.getInstance();
                codepromos= (ObservableList<codepromo>) pdao.displayLabel();
                         for(int i=0; i<codepromos.size(); i++)
                         {
                            if((codepromos.get(i).getLabel()).equals(ch))
                            {ok= true;
                            break;}
                            
                            } 
                          } catch (SQLException ex) {
                Logger.getLogger(CodepromoController.class.getName()).log(Level.SEVERE, null, ex);
            }
             
            return ok;
    }

    @Override
   
    public void initialize(URL url, ResourceBundle rb) {

            
//---------------------------------------SELECT----------------------------------------------------------------------------          

           codepromoservice pdao;
            try {
                pdao = codepromoservice.getInstance();
                codepromos= pdao.displayAll();
               // System.out.println("loula: "+codepromos.get(0).getId());
                } catch (SQLException ex) {
                Logger.getLogger(CodepromoController.class.getName()).log(Level.SEVERE, null, ex);
                }
             for(int i=0; i<codepromos.size(); i++)
                 listcode.add(codepromos.get(i));
                label.setCellValueFactory(new PropertyValueFactory<>("label"));
                valeur.setCellValueFactory(new PropertyValueFactory<>("valeur"));
                table.setItems(listcode);
   
        table.setOnMouseClicked(event->{
            
           codepromo item=table.getSelectionModel().getSelectedItem();
            String label=table.getSelectionModel().getSelectedItem().getLabel();
            int valeur=table.getSelectionModel().getSelectedItem().getValeur();
            int getid =table.getSelectionModel().getSelectedItem().getId();
       
       });
//---------------------------------------------UPDATE----------------------------------------------------------------------          

maj_code.setOnAction(event->{
    
           
            // System.out.println(isnumber(val_code1.getText()));
               try {
                   codepromoservice cp = codepromoservice.getInstance();
                   codepromo id = null;
                   
                   codepromo item=table.getSelectionModel().getSelectedItem();
                   int numval=0;
                 
                           
                   if(item==null){
                       Alert alert = new Alert(AlertType.INFORMATION);
                       alert.setTitle("Erreur");
                       alert.setHeaderText("Vous devez séléctionner un code à modifier");
                       alert.showAndWait();
                 }else if(nom_code1.getText().equals("") && val_code1.getText().equals("")){
                       Alert alert = new Alert(AlertType.INFORMATION);
                           alert.setTitle("Erreur");
                           alert.setHeaderText("Champs vides!");
                           alert.showAndWait();
                 }
                   else if(existe(nom_code1.getText())==true){
                        Alert alert = new Alert(AlertType.INFORMATION);
                        alert.setTitle("Erreur");
                        alert.setHeaderText("Label déja utilisé");
                        alert.show();
                    }
                 else{
                     codepromos=cp.displayAll();
                       String label=item.getLabel();
                       int val=item.getValeur();  
                      
                       if(!(nom_code1.getText().equals("")))
                       {
                           for(int i=0; i<codepromos.size(); i++)
                               if(label.equals(codepromos.get(i).getLabel()))
                                   id=codepromos.get(i);
                           int x=cp.updatelabel(id,nom_code1.getText());
                           
                       }
                       if(isnumber(val_code1.getText())==false && !(val_code1.getText().equals("")) ){
                           Alert alert = new Alert(AlertType.INFORMATION);
                           alert.setTitle("Erreur");
                           alert.setHeaderText("La valeur du code doit etre numérique");
                           alert.showAndWait();
                       }else if(isnumber(val_code1.getText())==true)
                       {
                           numval=Integer.parseInt(val_code1.getText());
                           if(numval>100 || numval<1)
                           {
                               Alert alert = new Alert(AlertType.INFORMATION);
                               alert.setTitle("Erreur");
                               alert.setHeaderText("La valeur du code doit etre entre 1 et 100");
                               alert.showAndWait();
                           }else if(!(val_code1.getText().equals("")))
                               {                                                                                                                                                                                                                                                                                                                                                                                                                       codepromos=cp.displayAll();
                                   for(int i=0; i<codepromos.size(); i++)
                                       if(label.equals(codepromos.get(i).getLabel()))
                                           id=codepromos.get(i);
                                   int x=cp.updatevaleur(id,val_code1.getText());                               
                            
                           }
                       }
                       Parent page1 = FXMLLoader.load(getClass().getResource("/artraction/view/codepromo.fxml"));
                               Scene scene = new Scene(page1);
                               Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                               stage.setScene(scene);
                               stage.show();
                       
                       
                 }
                      
        }      catch (IOException ex) {
                   Logger.getLogger(CodepromoController.class.getName()).log(Level.SEVERE, null, ex);
               } catch (SQLException ex) {
                   Logger.getLogger(CodepromoController.class.getName()).log(Level.SEVERE, null, ex);
               }          
});

//------------------------------------------DELETE-------------------------------------------------------------------------
supp_code.setOnAction(event->{
    
    try {
        codepromo id = null;
        codepromoservice cpp = codepromoservice.getInstance();
        codepromo item=table.getSelectionModel().getSelectedItem();
        
        if (item==null){
            System.out.println("item null");
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Erreur");
            alert.setHeaderText("Vous devez séléctionner un code à supprimer");
            // alert.setContentText("Vous devez séléctionner un code à supprimer");
            alert.showAndWait();
            
        }else{
            String label = item.getLabel();
            try {
                
                for(int i=0; i<codepromos.size(); i++)
                    if(label.equals(codepromos.get(i).getLabel()))
                        id=codepromos.get(i);
                cpp.delete(id);
                
                Parent page1 = FXMLLoader.load(getClass().getResource("/artraction/view/codepromo.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {   
                Logger.getLogger(CodepromoController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }} catch (SQLException ex) {
            Logger.getLogger(CodepromoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    
    
    
});

//---------------------------------------------INSERT----------------------------------------------------------------------
add_code.setOnAction(event -> {
    
    try {
        System.out.println(existe(nom_code.getText()));
        if (nom_code.getText().equals(""))
        {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Erreur");
            alert.setHeaderText("Vous devez spécifier le label du code!");
            alert.show();
            
        }
        else if(existe(nom_code.getText())==true){
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Erreur");
            alert.setHeaderText("Label déja utilisé");
            alert.show();
        }
        else if (val_code.getText().equals("") )
        {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Erreur");
            alert.setHeaderText("Vous devez spécifier la valeur du code!");
            alert.show();
            
        }else if( isnumber(val_code.getText())==false){
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Erreur");
            alert.setHeaderText("Valeur doit etre numerique");
            alert.show();
            //System.out.println(isnumber(val_code.getText()));
        }else{
            codepromo cp1 = new codepromo(nom_code.getText());
            int y;
            y = Integer.parseInt(val_code.getText());
            
            if ((y<=0 || y>100))
            {
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Erreur");
                alert.setHeaderText("Valeur du code promo doit etre entre 1 et 100");
                alert.showAndWait();
            } else{
                cp1.setValeur(y);
                codepromoservice cp = codepromoservice.getInstance();
                cp.insert(cp1);
            }
            Parent page1 = FXMLLoader.load(getClass().getResource("/artraction/view/codepromo.fxml"));
            Scene scene = new Scene(page1);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        }
        
    } catch (IOException ex) {
        Logger.getLogger(CodepromoController.class.getName()).log(Level.SEVERE, null, ex);
    } catch (SQLException ex) {
        Logger.getLogger(CodepromoController.class.getName()).log(Level.SEVERE, null, ex);
    }
});






                 
}}

       
 

