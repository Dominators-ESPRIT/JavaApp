package artraction.controller;
import artraction.controller.*;
import artraction.utils.ConnexionSingleton;
import artraction.utils.mysqlconnect;
import artraction.entity.userEntity;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
import javax.swing.JOptionPane;

/**
 *
 * @author amir
 */
public class CompteController implements Initializable {

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

    @FXML
    private TextField filterField;
    ObservableList<userEntity> listM;
    ObservableList<userEntity> dataList;

    int index = -1;

    Connection conn = null;
    ResultSet rs = null;
    PreparedStatement pst = null;
    @FXML
    private Button afficheruser;

    

    //////// methode select users ///////
    @FXML
    void getSelected(MouseEvent event) {
        index = table_users.getSelectionModel().getSelectedIndex();
        if (index <= -1) {

            return;
        }

        tfusername.setText(colusername.getCellData(index).toString());
        tfemail.setText(colemail.getCellData(index).toString());
        tfpassword.setText(colpassword.getCellData(index).toString());
        tfpassword.setText(colpassword.getCellData(index).toString());
        tfnumero.setText(colnumero.getCellData(index).toString());
         tfage.setText(colage.getCellData(index).toString());
        tfadresse.setText(coladresse.getCellData(index).toString());
        tftype.setText(coltype.getCellData(index).toString());

    }

    @FXML
    public void Edit() {
        try {
            conn = mysqlconnect.ConnectDb();

            String value1 = tfusername.getText();
            String value2 = tfemail.getText();
            String value3 = tfpassword.getText();
            String value4 = tfnumero.getText();
            String value5 = tfage.getText();

            String value6 = tfadresse.getText();
            String value7 = tftype.getText();

            String sql;
            sql = "update user set username= '" + value1 + "',email= '"
                    + value2 + "',password= '" + value3 + "',numero= '" + value4 + "',age='" + value5 + "', addresse='" + value6 + "', role='" + value7 + "' ";
            pst = conn.prepareStatement(sql);
            pst.execute();
            JOptionPane.showMessageDialog(null, "Update");
            UpdateTable();
            search_user();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }

    }

    @FXML
    public void Delete() {
        conn = mysqlconnect.ConnectDb();
        String sql = "delete from user where username = ?";
        try {
            pst = conn.prepareStatement(sql);
            pst.setString(1, tfusername.getText());
            pst.execute();
            JOptionPane.showMessageDialog(null, "Delete");
            UpdateTable();
            search_user();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }

    }

    public void UpdateTable() {

        colusername.setCellValueFactory(new PropertyValueFactory<userEntity, String>("username"));
        colemail.setCellValueFactory(new PropertyValueFactory<userEntity, String>("email"));
        colpassword.setCellValueFactory(new PropertyValueFactory<userEntity, String>("password"));
        colnumero.setCellValueFactory(new PropertyValueFactory<userEntity, String>("numero"));
          colage.setCellValueFactory(new PropertyValueFactory<userEntity, String>("age"));
        coladresse.setCellValueFactory(new PropertyValueFactory<userEntity, String>("adresse"));
        coltype.setCellValueFactory(new PropertyValueFactory<userEntity, String>("role"));

        listM = mysqlconnect.getDatausers();
        table_users.setItems(listM);
    }

    @FXML
    void search_user() {

         colusername.setCellValueFactory(new PropertyValueFactory<userEntity, String>("username"));
        colemail.setCellValueFactory(new PropertyValueFactory<userEntity, String>("email"));
        colpassword.setCellValueFactory(new PropertyValueFactory<userEntity, String>("password"));
        
        colnumero.setCellValueFactory(new PropertyValueFactory<userEntity, String>("numero"));
          colage.setCellValueFactory(new PropertyValueFactory<userEntity, String>("age"));
        coladresse.setCellValueFactory(new PropertyValueFactory<userEntity, String>("adresse"));
        coltype.setCellValueFactory(new PropertyValueFactory<userEntity, String>("role"));

        dataList = mysqlconnect.getDatausers();
        table_users.setItems(dataList);
        FilteredList<userEntity> filteredData = new FilteredList<>(dataList, b -> true);
        filterField.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            filteredData.setPredicate((userEntity person) -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();

                if (person.getUsername().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches username
                } else if (person.getPassword().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches password
                } else if (person.getRole().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches password
                } else if (String.valueOf(person.getEmail()).indexOf(lowerCaseFilter) != -1) {
                    return true;// Filter matches email
                } else if (person.getAdresse().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches password
                } else {
                    return String.valueOf(person.getNumero()).toLowerCase().indexOf(lowerCaseFilter) != -1; // Filter matches password
                }                // Does not match.

            });
        });
        SortedList<userEntity> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(table_users.comparatorProperty());
        table_users.setItems(sortedData);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        UpdateTable();
        search_user();
        // Code Source in description
    }

    private Connection getConnection() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void oncliquereturn(MouseEvent event) throws Exception {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("CPanele.fxml"));
        rootpane.getChildren().setAll(pane);
    }

    @FXML
    private void afficheruser(ActionEvent event) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/artraction/view/afficheruser.fxml"));
        Stage mainStage = new Stage();
      Scene scene = new Scene(root);
      mainStage.setScene(scene);
     mainStage.show();
    }
}
