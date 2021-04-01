package artraction.controller;

import artraction.controller.*;
import artraction.utils.ConnexionSingleton;
import artraction.entity.userEntity;
import java.net.URL;
import java.sql.Connection;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javax.swing.JOptionPane;

/**
 *
 * @author amir
 */
public class ProfilartisteController implements Initializable {
    

   @FXML
    private AnchorPane rootpane;

    @FXML
    private TextField tfusername;

    @FXML
    private TextField tfpassword;

    @FXML
    private TextField tfemail;

    @FXML
    private TextField tftype;

    @FXML
    private TextField tfnumero;

    @FXML
    private TextField tfadresse;

    @FXML
    private TextField tfage;

    @FXML
    private TableView table_users;

    @FXML
    private TableColumn<userEntity, String> colusername;

    @FXML
    private TableColumn<userEntity, String> colemail;

    @FXML
    private TableColumn<userEntity, String> colpassword;

    @FXML
    private TableColumn<userEntity, String> colnumero;

    @FXML
    private TableColumn<userEntity, String> colage;

    @FXML
    private TableColumn<userEntity, String> coladresse;

    @FXML
    private TableColumn<userEntity, String> coltype;
    
       
    ObservableList<userEntity> listM;
    ObservableList<userEntity> dataList;
    
   
    
    int index = -1;
    
    Connection conn =null;
    ResultSet rs = null;
    PreparedStatement pst = null;
   
    
     
    
    

    //////// methode select users ///////
    @FXML
    void getSelected (MouseEvent event){
    index = table_users.getSelectionModel().getSelectedIndex();
    if (index <= -1){
    
        return;
    }
   
     tfusername.setText(colusername.getCellData(index).toString());
    tfemail.setText(colemail.getCellData(index).toString());
    tfpassword.setText(colpassword.getCellData(index).toString());
    
    tfadresse.setText(coladresse.getCellData(index).toString());
    tftype.setText(coltype.getCellData(index).toString());
    tfnumero.setText(colnumero.getCellData(index) + "");
    tfage.setText(colage.getCellData(index) + "");
    
    
    }

    @FXML
    public void Edit (){   
        try {
            conn = ConnexionSingleton.getInstance().getCnx();
           
            String value1 = tfusername.getText();
            String value2 = tfemail.getText();
            String value3 = tfpassword.getText();
            
            String value4 = tfnumero.getText();
             String value5 = tfage.getText();
              String value6 = tfadresse.getText();
                String value7 = tftype.getText();
            
            String sql = "update user set username= '"+value1+"',email= '"+
                    value2+"',password= '"+value3+"',numero= '"+value4+"',age='"+value5+"', adresse='"+value6+"', role='"+value7+"' ";
            pst= conn.prepareStatement(sql);
            pst.execute();
            JOptionPane.showMessageDialog(null, "Update");
            UpdateTable();
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        
    }
    
    @FXML
    public void Delete() throws Exception{
    conn = ConnexionSingleton.getInstance().getCnx();
    String sql = "delete from users where username = ?";
        try {
            pst = conn.prepareStatement(sql);
            pst.setString(1, tfusername.getText());
            pst.execute();
            JOptionPane.showMessageDialog(null, "Delete");
            UpdateTable();
          
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    
    }

    
    public void UpdateTable() throws Exception{
        
         colusername.setCellValueFactory(new PropertyValueFactory<userEntity, String>("username"));
        colemail.setCellValueFactory(new PropertyValueFactory<userEntity, String>("email"));
        colpassword.setCellValueFactory(new PropertyValueFactory<userEntity, String>("password"));
        colnumero.setCellValueFactory(new PropertyValueFactory<userEntity, String>("numero"));
          colage.setCellValueFactory(new PropertyValueFactory<userEntity, String>("age"));
        coladresse.setCellValueFactory(new PropertyValueFactory<userEntity, String>("adresse"));
        coltype.setCellValueFactory(new PropertyValueFactory<userEntity, String>("role"));

        listM = ConnexionSingleton.getInstance().getDatauserById(ConnexionSingleton.getInstance().uInfos.getId());
        table_users.setItems(listM);
    }
    
    

    
 
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
        
    
       try {
           UpdateTable();
           
           // Code Source in description
       } catch (Exception ex) {
           Logger.getLogger(ProfilartisteController.class.getName()).log(Level.SEVERE, null, ex);
       }
    } 

    private Connection getConnection() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


    @FXML
    private void search_user(KeyEvent event) {
    }
    private void oncliquereturn(MouseEvent event) throws Exception{
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/artraction/view/compteartiste.fxml"));
       rootpane.getChildren().setAll( pane);
    }
}

