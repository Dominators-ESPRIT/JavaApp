/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package artraction.controller;

import artraction.entity.Competition;
import java.awt.event.MouseEvent;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author boukhris
 */
public class ConsulterCompetitionController implements Initializable {

    @FXML
    private TextField tfrefcompetition;
    @FXML
    private TextField tfnom;
    @FXML
    private TextField tftheme;
    @FXML
    private DatePicker dpdatedebut;
    @FXML
    private DatePicker dpdatefin;
       @FXML
    private TextField tftype;
    @FXML
    private TableView<Competition> tvcompetition;
    @FXML
    private TableColumn<Competition, Integer> colrefcompetition;
    @FXML
    private TableColumn<Competition, String> colnom;
    @FXML
    private TableColumn<Competition, String> coltheme;
    @FXML
    private TableColumn<Competition, Date> coldatedebut;
    @FXML
    private TableColumn<Competition, Date> coldatefin;
    @FXML
    private TableColumn<Competition, String> coltype;
    @FXML
    private Button btnajouter;
    @FXML
    private Button btnmodifier;
    @FXML
    private Button btnsupprimer;
    

    
    @FXML
    private void HandleButtonAction(ActionEvent event) throws SQLException {
        if(event.getSource()== btnajouter){
            AjouterCompetition();
        }else if(event.getSource()== btnmodifier){
            ModifierCompetition();
        }else if(event.getSource() == btnsupprimer){
            SupprimerCompetition();
        }
            
        }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            showCompetition();
        } catch (SQLException ex) {
            Logger.getLogger(ConsulterCompetitionController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    public Connection getConnection(){
        Connection conn;
        try{
            conn= DriverManager.getConnection("jdbc:mysql://localhost:3306/library","root","");
            return conn;
        }catch(Exception ex){
           
            System.out.println("Error:" + ex.getMessage());
             
        return null;    
        }
        
    }
    public ObservableList<Competition> getCompetitionList() throws SQLException{
    ObservableList<Competition> competitionList =FXCollections.observableArrayList();
    Connection conn = getConnection();
    String query="SELECT * FROM competition";
        Statement st;
        ResultSet rs;
        try{
            st= conn.createStatement();
            rs = st.executeQuery(query);
            Competition competition;
            while(rs.next()){
                competition = new Competition(rs.getInt("ref_competition"), rs.getString("nom"), rs.getString("theme"), rs.getDate("date_debut"), rs.getDate("date_fin"), rs.getString("type"));
                competitionList.add(competition);
            }
            
        }
        catch(Exception ex){
            ex.printStackTrace();
    }
        return competitionList;
    }
    
    public void showCompetition() throws SQLException{
        ObservableList<Competition> list_compt = getCompetitionList();
        colrefcompetition.setCellValueFactory(new PropertyValueFactory<Competition,Integer>("ref_competition"));
        colnom.setCellValueFactory(new PropertyValueFactory<Competition,String>("nom"));
        coltheme.setCellValueFactory(new PropertyValueFactory<Competition,String>("theme"));
        coldatedebut.setCellValueFactory(new PropertyValueFactory<Competition,Date>("date_debut"));
        coldatefin.setCellValueFactory(new PropertyValueFactory<Competition,Date>("date_fin"));
        coltype.setCellValueFactory(new PropertyValueFactory<Competition,String>("type"));
        
        tvcompetition.setItems(list_compt);
    }
    
    private void AjouterCompetition() throws SQLException{
        String query = "INSERT INTO competition VALUES(" +tfrefcompetition.getText() + ", '" + tfnom.getText() + "', '" + tftheme.getText() + "', '" +dpdatedebut.getValue().toString() +"', '"+dpdatefin.getValue().toString() +"' ,'" + tftype.getText() +"')";
    executeQuery(query);
    showCompetition();
    }
    
//    private void ModifierCompetition() throws SQLException{
//        String query = "UPDATE competition SET nom = '" + tfnom.getText() +"', theme = '"+ tftheme.getText() +"', date_debut = '" + dpdatedebut.getValue().toString() +"', date_fin = '" + dpdatefin.getValue().toString() + "', type = '" + tftype.getText() + "WHERE ref_competition = " + tfrefcompetition + "";
//    executeQuery(query);
//    showCompetition();
//    }
    
private void ModifierCompetition() throws SQLException {
        Connection connection = getConnection();
        PreparedStatement stmt;

        try {
            stmt = connection.prepareStatement("UPDATE competition SET nom=?, theme=?, date_debut=?, date_fin=?, type=? WHERE ref_competition=?");
            stmt.setInt(6, Integer.parseInt(tfrefcompetition.getText()));
            stmt.setString(1, tfnom.getText());
            stmt.setString(2, tftheme.getText());
            stmt.setString(3, dpdatedebut.getValue().toString());
            stmt.setString(4, dpdatefin.getValue().toString());
             stmt.setString(5, tftype.getText());
            int i = stmt.executeUpdate();

            System.out.println(i);
            connection.close();

        }catch (Exception e){
            e.printStackTrace();
        }
        showCompetition();
    }

  private void SupprimerCompetition() throws SQLException {
        Connection connection = getConnection();
        PreparedStatement stmt;

       try {
           stmt=connection.prepareStatement("delete from competition where ref_competition=?");
           stmt.setInt(1,Integer.parseInt(tfrefcompetition.getText()));

           int i=stmt.executeUpdate();
           System.out.println(i+" compétition deleted");
       }catch (Exception e){
           e.printStackTrace();
       }
       showCompetition();

  }
  
  
 
    
    private void executeQuery(String query) {
        Connection conn = getConnection();
        Statement st;
        try{
            st =conn.createStatement();
            st.executeUpdate(query);
        }catch(Exception ex){
            ex.printStackTrace();
            
        }
    }
int index = -1;
    @FXML
    private void HandleMouseAction(javafx.scene.input.MouseEvent event) {
//        Competition com =tvcompetition.getSelectionModel().getSelectedItem();
//        tfrefcompetition.setText(""+com.getRef_competition());
//        tfnom.setText(com.getNom());
//        tftheme.setText(com.getTheme());
//        
//        tftype.setText(com.getType());
    
    index = tvcompetition.getSelectionModel().getSelectedIndex();
    if (index <= -1){
    
        return;
    }
   tfrefcompetition.setText(colrefcompetition.getCellData(index).toString());
    tfnom.setText(colnom.getCellData(index).toString());
    tftheme.setText(coltheme.getCellData(index).toString());
    Date LocalDT = coldatedebut.getCellData(index);
    System.out.println(LocalDT);
    dpdatedebut.setValue(LocalDT.toLocalDate());
    Date LocalDTt = coldatefin.getCellData(index);
    System.out.println(LocalDTt);
    dpdatefin.setValue(LocalDTt.toLocalDate());
   
    tftype.setText(coltype.getCellData(index).toString());
    
    
    }

    }
    

