/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package artraction.view;

import artraction.entity.OeuvreCompetitionnModel;
import artraction.entity.Oeuvre_competition;
import artraction.service.OeuvreCompetitionService;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.sql.SQLDataException;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import static javafx.scene.input.KeyCode.A;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author boukhris
 */
public class CardOeuvreCompetitionController implements Initializable {

    @FXML
    private Label ldescription;

    @FXML
    private Label lLabel;
    @FXML
    private Label lUsername;
    @FXML
    private ImageView image;
    @FXML
    private Label tfnbvote;
    
    int refOC;
    int refCompetition;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {


    }

    public void setData(Oeuvre_competition oc) {

        ldescription.setText(oc.getDescription());

    }

    public void setDataModel(OeuvreCompetitionnModel ocm, int refCompetition) {
        this.refCompetition = refCompetition;
        refOC = ocm.getRef_oc();
        ldescription.setText(ocm.getDescription());
        lLabel.setText(ocm.getLabel());
        lUsername.setText(ocm.getUsername());
                OeuvreCompetitionService ocs = new OeuvreCompetitionService();
        try {
            tfnbvote.setText(ocs.getVoteByRefOC(ocm.getRef_oc())+"");
        } catch (SQLDataException ex) {
            Logger.getLogger(CardOeuvreCompetitionController.class.getName()).log(Level.SEVERE, null, ex);
        }
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
            image.setImage(imagex);
                        os.close();
            is.close();
        } catch (Exception e) {
        }

    }

    @FXML
    private void voter(ActionEvent event) {
        OeuvreCompetitionService ocs = new OeuvreCompetitionService();
        try {
            ocs.Add_Vote_Oeuvre_Competition(1, refOC, this.refCompetition);
            int currentNbVote = Integer.parseInt(tfnbvote.getText())+1;
            tfnbvote.setText(currentNbVote+"");
        } catch (SQLException ex) {
           
            JOptionPane.showMessageDialog(null, "vote impossible)");
        }
    }

    
}
