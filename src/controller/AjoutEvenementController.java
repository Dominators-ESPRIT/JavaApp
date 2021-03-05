/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entities.Evenement;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLDataException;
import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;
import org.controlsfx.control.Notifications;
import service.EvenementService;
import service.EvenementServiceInterface;

/**
 * FXML Controller class
 *
 * @author hazar
 */
public class AjoutEvenementController implements Initializable {

    @FXML
    private TextField titre;
    @FXML
    private TextField lieu;
    @FXML
    private DatePicker datedebut;
    @FXML
    private TextField nbplaces;
    @FXML
    private DatePicker datefin;
    @FXML
    private Button ajout;
    @FXML
    private Pane Pane;
    
    EvenementServiceInterface evt ;
    
    int c;
    int file;
    File pDir;
    File pfile;
    String lien;
    @FXML
    private ImageView Event_img;
    @FXML
    private Button cancel;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
           c = (int) (Math.random() * (300000 - 2 + 1)) + 2;
        pDir = new File("src/gui/img/Profile" + c + ".jpg");
        lien = "Profile" + c + ".jpg";
        
        
        
        
        
    }    


    
    
        @FXML
    private void uploadImage(ActionEvent event) throws MalformedURLException, SQLDataException {
        
        
                  FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choisir une image: ");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("JPG", "*.jpg"),
                new FileChooser.ExtensionFilter("JPEG", "*.jpeg"),
                new FileChooser.ExtensionFilter("PNG", "*.png"),
                new FileChooser.ExtensionFilter("BMP", "*.bmp")
        );
        Window stage = null;
        pfile = fileChooser.showOpenDialog(stage);

        /* - draw image */
        if (pfile != null) {
            file=1;
            Image image = new Image(pfile.toURI().toURL().toExternalForm());
            Event_img.setImage(image);
    }
    }
    
    

    @FXML
    private void AjouterEvent(ActionEvent event) throws SQLDataException {
        
        
        LocalDate d = LocalDate.now();
      Date date = java.sql.Date.valueOf(d);
        Date dd=  new java.sql.Date(  new Date(datedebut.getEditor().getText()).getTime());
                
                
        Date df = new java.sql.Date(  new Date(datefin.getEditor().getText()).getTime());

                       long diff1 = df.getTime() - dd.getTime();

                 long diff = dd.getTime() - date.getTime();
       float res1 = (diff / (1000*60*60*24));
       float res2 = (diff1 / (1000*60*60*24));
       
        
          if( titre.getText().equals("")  || lieu.getText().equals("") || nbplaces.getText().equals(0) || datefin.getValue()== null  || datedebut.getValue()==null  ){
              
              
              
                 Notifications notificationBuilder = Notifications.create()
               .title("Alert").text("Vérifier votre champs").graphic(null).hideAfter(javafx.util.Duration.seconds(5))
               .position(Pos.CENTER_LEFT)
               .onAction(new EventHandler<ActionEvent>(){
                   public void handle(ActionEvent event)
                   {
                       System.out.println("clicked ON ");
               }});
       notificationBuilder.darkStyle();
       notificationBuilder.show();
          }
       
       else if (nbplaces.getText().matches("^[a-zA-Z]+$") ){
               
                Notifications notificationBuilder = Notifications.create()
               .title("Alert").text("Le Champs nobre de place doit étre entier").graphic(null).hideAfter(javafx.util.Duration.seconds(5))
               .position(Pos.CENTER_LEFT)
               .onAction(new EventHandler<ActionEvent>(){
                   public void handle(ActionEvent event)
                   {
                       System.out.println("clicked ON ");
               }});
       notificationBuilder.darkStyle();
       notificationBuilder.show();
          }
       
       else if((res1<0) ){
        
               Notifications notificationBuilder = Notifications.create()
               .title("Alert").text("vérifier votre date").graphic(null).hideAfter(javafx.util.Duration.seconds(5))
               .position(Pos.CENTER_LEFT)
               .onAction(new EventHandler<ActionEvent>(){
                   public void handle(ActionEvent event)
                   {
                       System.out.println("clicked ON ");
               }});
       notificationBuilder.darkStyle();
       notificationBuilder.show();
          }
       else if( (res2 < 0)){
       
        Notifications notificationBuilder = Notifications.create()
               .title("Alert").text("Aujourd'hui est "+date).graphic(null).hideAfter(javafx.util.Duration.seconds(5))
               .position(Pos.CENTER_LEFT)
               .onAction(new EventHandler<ActionEvent>(){
                   public void handle(ActionEvent event)
                   {
                       System.out.println("clicked ON ");
               }});
       notificationBuilder.darkStyle();
       notificationBuilder.show();
          }
       
       
       
    
    
    
    
               
               
                       
               else{
                          copier(pfile,pDir);

           String t= titre.getText();
                String l= lieu.getText();
                int nbp= Integer.parseInt(nbplaces.getText());
             


                EvenementService Es= new EvenementService();
                Evenement e= new Evenement(nbp, l, t, (java.sql.Date)dd, (java.sql.Date)df,lien);

                Es.addEvenement(e);

                
                Parent root;
                        try {
                            root = FXMLLoader.load(getClass().getResource("/gui/listeEvenement.fxml"));
                            Stage myWindow = (Stage)datedebut.getScene().getWindow();
                            Scene sc = new Scene(root);
                            myWindow.setScene(sc);
                            myWindow.setTitle("page name");
                            //myWindow.setFullScreen(true);
                            myWindow.show();
                        } catch (IOException ex) {
                            Logger.getLogger(AjoutEvenementController.class.getName()).log(Level.SEVERE, null, ex);
                        } 
        
                
    
              
        
    
        
        
    
    }
          
    }
    
     public static boolean copier(File source, File dest) {
        try (InputStream sourceFile = new java.io.FileInputStream(source);
                OutputStream destinationFile = new FileOutputStream(dest)) {
            // Lecture par segment de 0.5Mo  
            byte buffer[] = new byte[512 * 1024];
            int nbLecture;
            while ((nbLecture = sourceFile.read(buffer)) != -1) {
                destinationFile.write(buffer, 0, nbLecture);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return false; // Erreur 
        }
        return true; // Résultat OK   
    }

    @FXML
    private void cancel(ActionEvent event) {
          
        try {
            Parent root;
            
            root = FXMLLoader.load(getClass().getResource("/gui/listeEvenement.fxml"));
            Stage myWindow = (Stage)datedebut.getScene().getWindow();
            Scene sc = new Scene(root);
            myWindow.setScene(sc);
            myWindow.setTitle("page name");
            //myWindow.setFullScreen(true);
            myWindow.show();
        } catch (IOException ex) {
            Logger.getLogger(AjoutEvenementController.class.getName()).log(Level.SEVERE, null, ex);
        }
                       
        
    }
    


    }

