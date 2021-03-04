package artraction;

import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javax.swing.JOptionPane;

/**
 *
 * @author amir
 */
public class ProfilartisteController implements Initializable {
    

   @FXML
    private TextField tfusername;

    @FXML
    private TextField tfpassword;

    @FXML
    private TextField tfemail;

    @FXML
    private TextField tfid;

    @FXML
    private TextField tfnumero;

    @FXML
    private TextField tfadresse;

    @FXML
    private TextField tftype;

    @FXML
    private DatePicker dpdate;

    @FXML
    private TableView table_users;

    @FXML
    private TableColumn <users , Integer> colid;

    @FXML
    private TableColumn<users , String> colusername;

    @FXML
    private TableColumn<users , String> colemail;

    @FXML
    private TableColumn<users , String> colpassword;

    @FXML
    private TableColumn <users , Date>coldate;

    @FXML
    private TableColumn<users , Integer> colnumero;

    @FXML
    private TableColumn <users , String>coladresse;

    @FXML
    private TableColumn<users , String> coltype;

    @FXML
    private TextField filterField;

    
       
    ObservableList<users> listM;
    ObservableList<users> dataList;
    
   
    
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
   tfid.setText(colid.getCellData(index).toString());
    tfusername.setText(colusername.getCellData(index).toString());
    tfemail.setText(colemail.getCellData(index).toString());
    tfpassword.setText(colpassword.getCellData(index).toString());
    Date LocalDT = coldate.getCellData(index);
    System.out.println(LocalDT);
    dpdate.setValue(LocalDT.toLocalDate());
    tfnumero.setText(colnumero.getCellData(index).toString());
    tfadresse.setText(coladresse.getCellData(index).toString());
    tftype.setText(coltype.getCellData(index).toString());
    
    
    }

    @FXML
    public void Edit (){   
        try {
            conn = mysqlconnect.ConnectDb();
            String value1 = tfid.getText();
            String value2 = tfusername.getText();
            String value3 = tfemail.getText();
            String value4 = tfpassword.getText();
            LocalDate ld = dpdate.getValue();
            String value5 = ld.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            String value6 = tfnumero.getText();
              String value7 = tfadresse.getText();
                String value8 = tftype.getText();
            
            String sql = "update users set username= '"+value2+"',email= '"+
                    value3+"',password= '"+value4+"',date= '"+value5+"',numero='"+value6+"', adresse='"+value7+"', type='"+value8+"' where id='"+value1+"' ";
            pst= conn.prepareStatement(sql);
            pst.execute();
            JOptionPane.showMessageDialog(null, "Update");
            UpdateTable();
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        
    }
    
    @FXML
    public void Delete(){
    conn = mysqlconnect.ConnectDb();
    String sql = "delete from users where id = ?";
        try {
            pst = conn.prepareStatement(sql);
            pst.setString(1, tfid.getText());
            pst.execute();
            JOptionPane.showMessageDialog(null, "Delete");
            UpdateTable();
          
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    
    }

    
    public void UpdateTable(){
        colid.setCellValueFactory(new PropertyValueFactory<users,Integer>("id"));
        colusername.setCellValueFactory(new PropertyValueFactory<users,String>("username"));
          colemail.setCellValueFactory(new PropertyValueFactory<users,String>("email"));
        colpassword.setCellValueFactory(new PropertyValueFactory<users,String>("password"));
        coldate.setCellValueFactory(new PropertyValueFactory<users,Date>("date"));
        colnumero.setCellValueFactory(new PropertyValueFactory<users,Integer>("numero"));
          coladresse.setCellValueFactory(new PropertyValueFactory<users,String>("adresse"));
            coltype.setCellValueFactory(new PropertyValueFactory<users,String>("type"));
        
        listM = mysqlconnect.getDatauserById(Artraction.getInstance().U.id);
        table_users.setItems(listM);
    }
    
    

    
 
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
        
    System.out.println(Artraction.getInstance().U.toString());
    UpdateTable();
   
    // Code Source in description
    } 

    private Connection getConnection() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @FXML
    private void Add_users(ActionEvent event) {
    }

    @FXML
    private void search_user(KeyEvent event) {
    }
}

