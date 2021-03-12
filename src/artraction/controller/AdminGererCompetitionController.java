/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package artraction.controller;

import artraction.entity.Competition;
import artraction.service.CompetitionService;
import artraction.utilis.ConnexionSingleton;
import static com.sun.deploy.panel.JreFindDialog.search;
import static com.sun.jndi.toolkit.dir.DirSearch.search;
import static com.sun.jndi.toolkit.dir.DirSearch.search;
import static com.sun.jndi.toolkit.dir.DirSearch.search;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.controlsfx.control.Notifications;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLDataException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;

import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javax.management.Notification;
import javax.swing.JOptionPane;
import static jdk.nashorn.internal.objects.NativeJava.type;
import static jdk.nashorn.internal.objects.NativeString.search;
import jdk.nashorn.internal.runtime.regexp.joni.Syntax;


/**
 * FXML Controller class
 *
 * @author boukhris
 */
public class AdminGererCompetitionController implements Initializable {

    @FXML
    private TableView<Competition> tvcomp;
    @FXML
    private TableColumn<Competition , String> colnom;
    @FXML
    private TableColumn<Competition, String> coltheme;
    @FXML
    private TableColumn<Competition, Date> coldatedebut;
    @FXML
    private TableColumn<Competition, Date> coldatefin;
    @FXML
    private TableColumn<Competition, String> coltype;
    
    ObservableList<Competition> listcomp;
    ObservableList<Competition> dataList;

    int index =-1;
    Connection conn =null;
    ResultSet rs = null;
    PreparedStatement pst =null;
    @FXML
    private TextField tfnom;
    @FXML
    private TextField tftheme;
    @FXML
    private DatePicker tfdatedebut;
    @FXML
    private DatePicker tfdatefin;
    @FXML
    private TextField tftype;
    @FXML
    private TextField tffilter;
    
    private ObservableList<Competition> CompData = FXCollections.observableArrayList();
     
     CompetitionService compservice =  new CompetitionService();
    @FXML
    private Button Addcompetition;
    @FXML
    private Button updatecompetition;
    @FXML
    private Button delatecompetition;
    
     
    int c;
    int file;
    File pDir;
    File pfile;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        afficher_competition();
        
    }   
    
    public void afficher_competition()
    {
         try {
            List<Competition> listCompetition= new ArrayList<Competition>();
            listCompetition = compservice.getAllCompetition();
        CompData.clear();
        CompData.addAll(listCompetition);
        tvcomp.setItems(CompData);
        
        
        colnom.setCellValueFactory
        (
            new PropertyValueFactory<>("nom")
        );
        
        coltheme.setCellValueFactory
        (
            new PropertyValueFactory<>("theme")
        );
        
         coldatedebut.setCellValueFactory
        (
            new PropertyValueFactory<>("date_debut")
        );
         
          coldatefin.setCellValueFactory
        (
            new PropertyValueFactory<>("date_fin")
        );
          
         coltype.setCellValueFactory
        (
            new PropertyValueFactory<>("type")
        );  
         
         
        
        
        
    
        } catch (SQLDataException ex) {
            Logger.getLogger(AdminGererCompetitionController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    

  

  

  

   


    public void setnom(TableColumn<Competition, String> nom) {
        this.colnom.setText(nom.getText());
    }

    public void setLieu(TableColumn<Competition, String> theme) {
        this.coltheme.setText(theme.getText());
    }

    public void setDatedebut(TableColumn<Competition, Date> date_debut) {
        this.coldatedebut.setText(date_debut.getText());
    }

    public void setDatefin(TableColumn<Competition, Date> date_fin) {
        this.coldatefin.setText(date_fin.getText());
    }

    public void setNbplaces(TableColumn<Competition, Integer> type) {
        this.coltype.setText(type.getText());
    }


   

    

    
    
    @FXML
    void Add_Competition(ActionEvent event) throws IOException, SQLDataException {

        LocalDate d = LocalDate.now();
        Date date = java.sql.Date.valueOf(d);
        Date dd = new java.sql.Date(new Date(tfdatedebut.getEditor().getText()).getTime());
//                
//                
        Date df = new java.sql.Date(new Date(tfdatefin.getEditor().getText()).getTime());

//  Date dateDebut=Date.from(tfdatedebut.getValue().atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
//                Date dateFin=Date.from(tfdatefin.getValue().atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
//                       long diff1 = df.getTime() - dd.getTime();
//
//                 long diff = dd.getTime() - date.getTime();
//       float res1 = (diff / (1000*60*60*24));
//       float res2 = (diff1 / (1000*60*60*24));
        if (tfnom.getText().equals("") || tftheme.getText().equals("") || tftype.getText() == null) {
            JOptionPane.showMessageDialog(null, "Verifier les champ(s) vide(s)");
        } else {

            String l = tfnom.getText();
            String t = tftheme.getText();

            CompetitionService cs = new CompetitionService();
            if (!cs.findByName(tfnom.getText())) {
                Competition c = new Competition(l, t, dd, df, t);

                cs.Add_Competition(c);
                afficher_competition();
            } else {
//                    Alert alert = new Alert(Alert.AlertType.ERROR);
//                    alert.setTitle("Nom existe");
                JOptionPane.showMessageDialog(null, "Nom existe");
            }

        }
    }
//    public static boolean copier(File source, File dest) {
//        try (InputStream sourceFile = new java.io.FileInputStream(source);
//                OutputStream destinationFile = new FileOutputStream(dest)) {
//            // Lecture par segment de 0.5Mo  
//            byte buffer[] = new byte[512 * 1024];
//            int nbLecture;
//            while ((nbLecture = sourceFile.read(buffer)) != -1) {
//                destinationFile.write(buffer, 0, nbLecture);
//            }
//        } catch (IOException e) {
//            System.out.println(e.getMessage());
//            return false; // Erreur 
//        }
//        return true; // Résultat OK   
//    }
       

    
     @FXML
    void Delate_Competition(ActionEvent event) throws IOException, SQLDataException, SQLException {
        Competition comptSelec = (Competition) tvcomp.getSelectionModel().getSelectedItem();

        CompetitionService es = new CompetitionService();
        List<Competition> u = new ArrayList<Competition>();

        compservice.Delate_Competition(comptSelec.getRef_competition());
        afficher_competition();

    }
    
    
       @FXML
    void Edit_Competition(ActionEvent event)  throws IOException, SQLDataException, SQLException {
            Competition comptSelec = (Competition) tvcomp.getSelectionModel().getSelectedItem();
            CompetitionService es = new CompetitionService();
            String value1 = tfnom.getText();
            String value2 = tftheme.getText();
            String value3 = tfdatedebut.getValue().toString();
            String value4 = tfdatefin.getValue().toString();
            String value5 = tftype.getText();
            comptSelec.setNom(value1);
            comptSelec.setTheme(value2);
            comptSelec.setType(value5);
            System.out.println("updaaaated"+comptSelec);
            es.Edit_Competition(comptSelec);
            
            afficher_competition();
        
    }
    
    
    public void resetTableData() throws SQLDataException
    {
        List<Competition> listcompetition = new ArrayList<>();
        listcompetition = compservice.getAllCompetition();
        ObservableList<Competition> data = FXCollections.observableArrayList(listcompetition);
        tvcomp.setItems(data);
    }

 
    
     public void UpdateTable(){
        colnom.setCellValueFactory(new PropertyValueFactory<Competition, String>("nom"));
        coltheme.setCellValueFactory(new PropertyValueFactory<Competition, String>("theme"));
        coldatedebut.setCellValueFactory(new PropertyValueFactory<Competition, Date>("date_debut"));
        coldatefin.setCellValueFactory(new PropertyValueFactory<Competition, Date>("date_fin"));
        coltype.setCellValueFactory(new PropertyValueFactory<Competition, String>("type"));

      
        tvcomp.setItems(listcomp);
    }
   

    @FXML
    private void getSelected(MouseEvent event) {
         index= tvcomp.getSelectionModel().getSelectedIndex();
        if (index <= -1){
            return;
        }
        tfnom.setText(colnom.getCellData(index).toString());
        tftheme.setText(coltheme.getCellData(index).toString());
         java.sql.Date LocalDT = (java.sql.Date) coldatedebut.getCellData(index);
        System.out.println(LocalDT);
        tfdatedebut.setValue(LocalDT.toLocalDate());
        java.sql.Date LocalDTt = (java.sql.Date) coldatefin.getCellData(index);
        System.out.println(LocalDTt);
        tfdatefin.setValue(LocalDTt.toLocalDate());

        tftype.setText(coltype.getCellData(index).toString());
    }

    @FXML
    private void search(MouseEvent event) throws SQLDataException {
         colnom.setCellValueFactory(new PropertyValueFactory<Competition, String>("nom"));
        coltheme.setCellValueFactory(new PropertyValueFactory<Competition, String>("theme"));
        
        coltype.setCellValueFactory(new PropertyValueFactory<Competition, String>("type"));
        
        dataList = (ObservableList<Competition>) compservice.getAllCompetition();
        tvcomp.setItems(dataList);
         FilteredList<Competition> filteredData = new FilteredList<>(dataList, b -> true);
         tffilter.textProperty().addListener((observable, oldValue,newValue ) -> {
             filteredData.setPredicate(person -> {
                   if (newValue == null || newValue.isEmpty()) {
     return true;
    }    
    String lowerCaseFilter = newValue.toLowerCase();
    
    if (person.getNom().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
     return true; // Filter matches username
    } else if (person.getTheme().toLowerCase().indexOf(lowerCaseFilter) != -1) {
     return true; // Filter matches password
    }else if (person.getType().toLowerCase().indexOf(lowerCaseFilter) != -1) {
     return true; // Filter matches password
    }
    
                                
         else  
          return false; // Does not match.
   });
  });  
  SortedList<Competition> sortedData = new SortedList<>(filteredData);  
  sortedData.comparatorProperty().bind(tvcomp.comparatorProperty());  
  tvcomp.setItems(sortedData);      
    }
    }

  
      

    
    
   
    
                               
    	
    

    
   

     
     

   
      



    
    
    
    
    
    
