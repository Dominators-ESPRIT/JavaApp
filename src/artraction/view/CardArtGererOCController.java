/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package artraction.view;

import artraction.entity.Competition;
import artraction.entity.OeuvreCompetitionnModel;
import artraction.service.CompetitionService;
import artraction.service.OeuvreCompetitionService;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author boukhris
 */
public class CardArtGererOCController implements Initializable {

    @FXML
    private Label lLabel;
    @FXML
    private Label ldescription;
    @FXML
    private Label lUsername;
    @FXML
    private ImageView img;
    private int Ref_Ov;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    public void setData(OeuvreCompetitionnModel ocm){
         lLabel.setText(ocm.getLabel());
         ldescription.setText(ocm.getUsername());
         lUsername.setText(ocm.getUsername());
         Ref_Ov= ocm.getRef_oc();
         
          InputStream is;
        OutputStream os;
        try {
            is = ocm.getImage();
            os = new FileOutputStream(new File("photo.jpg"));
            byte[] content = new byte[1024];
            int size = 0;
            while ((size = is.read(content)) != -1) {
                os.write(content, 0, size);
            }

            Image imagex = new Image("file:photo.jpg", 250, 250, true, true);
            img.setImage(imagex);
                        os.close();
            is.close();
        } catch (Exception e) {
        }
    }
    
    
    
    @FXML
    private void supprimer(ActionEvent event) {
       
        OeuvreCompetitionService ocs = new OeuvreCompetitionService();
        System.out.println(Ref_Ov);
       
        try {
            
            ocs.delateoeuvrecompetitionbyId(Ref_Ov, 3);
        } catch (Exception e) {
        }
        
        
    }
    
}
