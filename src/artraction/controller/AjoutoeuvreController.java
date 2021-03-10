/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package artraction.controller;

import artraction.entity.oeuvre;
import artraction.service.ajoutoeuvservice;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author zeyne
 */
public class AjoutoeuvreController implements Initializable {

    /**
     * Initializes the controller class.
     */
        @FXML
    private Button addtocart;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
            try {
                // TODO
                oeuvre cp1 = new oeuvre();
                ajoutoeuvservice cp = new ajoutoeuvservice();
                    cp.insert(cp1);
            } catch (SQLException ex) {
                Logger.getLogger(AjoutoeuvreController.class.getName()).log(Level.SEVERE, null, ex);
            }

        addtocart.setOnAction(event->{
            Stage popupwindow=new Stage();
            
      
        popupwindow.initModality(Modality.APPLICATION_MODAL);
        popupwindow.setTitle("This is a pop up window");


                Label label1= new Label("Produit ajouté au panier avec succés");
                Button button1= new Button("poursuivre mon shopping");
                Button button2= new Button("consulter le panier");


                button1.setOnAction(e -> popupwindow.close());

                button2.setOnAction(e -> {
                     popupwindow.close();
                try {
                    Parent page1 = FXMLLoader.load(getClass().getResource("/artraction/view/panier.fxml"));
                    Scene scene = new Scene(page1);
                    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    stage.setScene(scene);
                    stage.show();
                } catch (IOException ex) {
                    Logger.getLogger(AjoutoeuvreController.class.getName()).log(Level.SEVERE, null, ex);
                }
                                   

                }
                );

                VBox layout= new VBox(50);
               layout.getChildren().addAll(label1,button1,button2);
                layout.setAlignment(Pos.CENTER);
                Scene scene1= new Scene(layout, 270, 250);
                popupwindow.setScene(scene1);
                popupwindow.showAndWait();

                        });
    }    
    
}
