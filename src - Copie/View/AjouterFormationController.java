/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Entity.Formation;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import static java.time.temporal.TemporalQueries.localDate;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;


/**
 * FXML Controller class
 *
 * @author mahdi
 */
public class AjouterFormationController implements Initializable { 
    @FXML
    private TextField tfidformation;
    @FXML
    private TextField tfidnom;
    @FXML
    private TextField tfidtheme;
    @FXML
    private DatePicker dpdatedebut;

    @FXML
    private DatePicker dpdatefin;
    @FXML
    private TableView<Formation> tvformation;
    @FXML
    private TableColumn<Formation,Integer> colidformation;
    @FXML
    private TableColumn<Formation, String> colidnom;
    @FXML
    private TableColumn<Formation, String> colidtheme;
    @FXML
    private TableColumn<Formation, DatePicker> coliddebut;
    @FXML
    private TableColumn<Formation, DatePicker> colidfin;
    @FXML
    private Button btnajouter;
    @FXML
    private Button btnmodifier;
    @FXML
    private Button btnsupprimer;
    @FXML
    
    private void HandleButtonAction(ActionEvent event) throws SQLException {
        if(event.getSource()== btnajouter){
            AjouterFormation();
        }else if(event.getSource()== btnmodifier){
            ModifierFormation();
         }else if(event.getSource() == btnsupprimer){
            SupprimerFormation();   
        
        }
    }
   


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            showFormation();
        } catch (SQLException ex) {
            Logger.getLogger(AjouterFormationController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

        
        // TODO
        
    public Connection getConnection(){
        Connection conn;
        try{
            conn= DriverManager.getConnection("jdbc:mysql://localhost:3306/formation","root","");
            return conn;
        }catch(Exception ex){
           
            System.out.println("Error:" + ex.getMessage());
             
        return null;    
        }
        
    }
    public ObservableList<Formation> getFormationList() throws SQLException {
        ObservableList<Formation> FormatList = FXCollections.observableArrayList() ;
        Connection conn = getConnection();
        String query = "SELECT * FROM liste_formation" ;
        Statement st  ;
        ResultSet rs ; 
        
        try {
            st = conn.createStatement() ;
            rs = st.executeQuery(query) ;
            Formation formation;
            while(rs.next()) {
                formation = new Formation(rs.getInt("id_formation"), rs.getString("nom"), rs.getString("theme"), rs.getDate("date_debut"), rs.getDate("date_fin")) ;
                FormatList.add(formation);
            }
           
        }
        catch(Exception ex){
            ex.printStackTrace();
    }
        return FormatList;
    }
   
    public void showFormation() throws SQLException{
        ObservableList<Formation> list_form = getFormationList();
        colidformation.setCellValueFactory(new PropertyValueFactory<Formation,Integer>("id_formation"));
        colidnom.setCellValueFactory(new PropertyValueFactory<Formation,String>("nom"));
        colidtheme.setCellValueFactory(new PropertyValueFactory<Formation,String>("theme"));
        coliddebut.setCellValueFactory(new PropertyValueFactory<Formation,DatePicker>("date_debut"));
        colidfin.setCellValueFactory(new PropertyValueFactory<Formation,DatePicker>("date_fin")); 
       
       
        tvformation.setItems(list_form);
    }
   
    private void AjouterFormation() throws SQLException{
        /* ZoneId defaultZoneId = ZoneId.systemDefault();
        Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant()); */
        LocalDate localDate = dpdatedebut.getValue();
        Instant instant = Instant.from(localDate.atStartOfDay(ZoneId.systemDefault()));
        Date date = Date.from(instant);
        /*java.util.Date date = java.sql.Date.valueOf(dpdatedebut.getValue());
        java.util.Date date1 = java.sql.Date.valueOf(dpdatefin.getValue()); */
        /*Formation f = new Formation(Integer.parseInt(tfidformation.getText()), tfidnom.getText(), tfidtheme.getText(), date , date) ; */
        String query = "INSERT INTO liste_formation VALUES(" +tfidformation.getText() + ", '" + tfidnom.getText() + "', '" + tfidtheme.getText() +"','"+dpdatedebut.getValue()+"','"+dpdatefin.getValue()+"')" ;
        Statement st = getConnection().createStatement() ;
        st.executeUpdate(query); 
        
    showFormation();
    }
   
//   
//    }
   
private void ModifierFormation() throws SQLException {
        Connection connection = getConnection();
        PreparedStatement stmt;

        try {
           /* stmt = connection.prepareStatement("UPDATE liste_formation SET nom=?, theme=?, date_debut=?, date_fin=?, WHERE id_formation=?");
            stmt.setInt(6, Integer.parseInt(tfidformation.getText()));
            stmt.setString(1, tfidnom.getText());
            stmt.setString(2, tfidtheme.getText());
            stmt.setString(3, dpdatedebut.getValue());
            stmt.setString(4, dpdatefin.getValue()) ;*/
           String query = "UPDATE liste_formation SET nom='"+tfidnom.getText()+"', theme='"+tfidtheme.getText()+"', date_debut='"+dpdatedebut.getValue()+"', date_fin='"+dpdatefin.getValue()+"' WHERE id_formation="+tfidformation.getText()+"";
           Statement st = getConnection().createStatement() ;
           st.executeUpdate(query); 

            
            connection.close();

        }catch (Exception e){
            e.printStackTrace();
        }
        showFormation();
    }
      
      private void SupprimerFormation() throws SQLException {
        Connection connection = getConnection();
        PreparedStatement stmt;

       try {
           stmt=connection.prepareStatement("delete from liste_formation where id_formation=?");
           stmt.setInt(1,Integer.parseInt(tfidformation.getText()));

           int i=stmt.executeUpdate();
           System.out.println(i+" formation deleted");
       }catch (Exception e){
           e.printStackTrace();
       }
       showFormation();
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


    private void Formation() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @FXML
    private void getClickedFormation(MouseEvent event) {
        Formation f = tvformation.getSelectionModel().getSelectedItem() ;
        tfidformation.setText(f.getId_formation()+""); 
        tfidnom.setText(f.getNom());
        tfidtheme.setText(f.getTheme()); 
        Date input = f.getDate_debut();
        LocalDate date = input.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        dpdatedebut.setValue(date);
    }  
        
}    
    


    
   

            
        
    




