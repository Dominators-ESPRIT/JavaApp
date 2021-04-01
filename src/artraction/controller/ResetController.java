/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package artraction.controller;

import artraction.service.User;
import artraction.utils.ConnexionSingleton;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author MSI
 */
public class ResetController implements Initializable {
Connection con = null;
ResultSet rs = null;
PreparedStatement pst = null;
public String user;
    @FXML
    private TextField txtVerResetPass;
    @FXML
    private TextField txtResetPass;
    @FXML
    private Button reset;
Connection conn =null;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    


    @FXML
    private void reset(ActionEvent event) {
if(txtResetPass.getText().equals(txtVerResetPass.getText())){
//check whether the user enter same password in both textfield
try{
String updateQuery = "Update `user` SET password=? WHERE email=?";
con = DriverManager.getConnection("jdbc:mysql://localhost/art", "root","");
pst=con.prepareStatement(updateQuery);
pst.setString(1, txtVerResetPass.getText());
pst.setString(2, user);
pst.executeUpdate();
JOptionPane.showMessageDialog(null, "Reset Successfully");
 
 
}catch(Exception ex){
JOptionPane.showMessageDialog(null, ex);
}
}else{
JOptionPane.showMessageDialog(null, "password do not match");
}
    }

    private void initComponents() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    void setVisible(boolean b) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
