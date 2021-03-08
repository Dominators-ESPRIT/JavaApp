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
    private ChoiceBox<String> codeliste;

    @FXML
    private Button supp_code;
    
       @FXML
    private ChoiceBox<String> codeliste1;

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
  
   
    @Override
   
    public void initialize(URL url, ResourceBundle rb) {
        String ch="";
            try {
                 codepromoservice pdao = codepromoservice.getInstance();
                 codepromos= pdao.displayAll();
                 } catch (SQLException ex) {
                Logger.getLogger(CodepromoController.class.getName()).log(Level.SEVERE, null, ex);
                 }
            for(int i=0; i<codepromos.size(); i++)
                {  ch=codepromos.get(i).getLabel()+": "+codepromos.get(i).getValeur()+"%";
                    codeliste.getItems().add(ch);
                    codeliste1.getItems().add(ch); 
                }
            
//---------------------------------------SELECT----------------------------------------------------------------------------          

           codepromoservice pdao;
            try {
                pdao = codepromoservice.getInstance();
                codepromos= pdao.displayAll();
                } catch (SQLException ex) {
                Logger.getLogger(CodepromoController.class.getName()).log(Level.SEVERE, null, ex);
                }
             for(int i=0; i<codepromos.size(); i++)
                 listcode.add(codepromos.get(i));
                label.setCellValueFactory(new PropertyValueFactory<>("label"));
                valeur.setCellValueFactory(new PropertyValueFactory<>("valeur"));
                table.setItems(listcode);
            
//---------------------------------------------UPDATE----------------------------------------------------------------------          
    maj_code.setOnAction(event->{
    
           
            try {
                codepromoservice cp = codepromoservice.getInstance();
                
                codepromo id = null;
                String ch1=codeliste1.getValue();
                System.out.println(ch1);
                if(ch1==null){
                    Alert alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Alert");
                    alert.setHeaderText(null);
                    alert.setContentText("Vous devez séléctionner un code à modifier");
                    alert.showAndWait();
                }else{
                    String[] ch2=ch1.split(":");
                ch1=ch2[0];
                
                if(!(nom_code1.getText().equals("")))
                {
                    for(int i=0; i<codepromos.size(); i++)
                    if(ch1.equals(codepromos.get(i).getLabel()))
                        id=codepromos.get(i);
                    int x=cp.updatelabel(id,nom_code1.getText());
                }
                if(!(val_code1.getText().equals("")))
                     {
                    for(int i=0; i<codepromos.size(); i++)
                    if(ch1.equals(codepromos.get(i).getLabel()))
                        id=codepromos.get(i);
                    int x=cp.updatevaleur(id,val_code1.getText());
                }

                Parent page1 = FXMLLoader.load(getClass().getResource("/artraction/view/codepromo.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
                }
             
            } catch (SQLException ex) {
                Logger.getLogger(CodepromoController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(CodepromoController.class.getName()).log(Level.SEVERE, null, ex);
            }
                
               

    
    });
//------------------------------------------DELETE-------------------------------------------------------------------------          
       supp_code.setOnAction(event->{
            
            try {
                codepromo id = null;
                codepromoservice cpp = codepromoservice.getInstance();
                String ch1=codeliste.getValue();
                if (ch1==null){
                    
                    Alert alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Alert");
                    alert.setHeaderText(null);
                    alert.setContentText("Vous devez séléctionner un code à supprimer");
                    alert.showAndWait();
                    
                }else{
                    try {
                        String[] ch2=ch1.split(":");
                        ch1=ch2[0];
                        
                        
                        for(int i=0; i<codepromos.size(); i++)
                            if(ch1.equals(codepromos.get(i).getLabel()))
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
           
                }
            } catch (SQLException ex) {
                Logger.getLogger(CodepromoController.class.getName()).log(Level.SEVERE, null, ex);
            }
                
               
           
       });
            
 //---------------------------------------------INSERT----------------------------------------------------------------------        
       add_code.setOnAction(event -> {
                try {
                if (nom_code.getText().equals(""))
                {                   
                Alert alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Information Dialog");
                    alert.setHeaderText(null);
                    alert.setContentText(" Vous devez spécifier le label du code!");
                    alert.show();
                    
                }
                else if (val_code.getText().equals(""))
                {                   
                Alert alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Information Dialog");
                    alert.setHeaderText(null);
                    alert.setContentText(" Vous devez spécifier la valeur du code!");
                    alert.show();
                    
                }
                codepromo cp1 = new codepromo(nom_code.getText());
                 int y;
                    y = Integer.parseInt(val_code.getText());
                
                if ((y<=0 || y>=100))
               { 
                   Alert alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Alert");
                    alert.setHeaderText(null);
                    alert.setContentText("Valeur du code promo doit etre entre 0 et 100");
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
      
                } catch (IOException ex) {   
                Logger.getLogger(CodepromoController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(CodepromoController.class.getName()).log(Level.SEVERE, null, ex);
            }   
                 });
    
                    

    
    }   
    
}

       
 

