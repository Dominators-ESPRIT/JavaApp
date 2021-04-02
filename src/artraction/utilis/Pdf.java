/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package artraction.utilis;

import artraction.entity.Competition;
import artraction.service.CompetitionService;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;

import com.itextpdf.text.pdf.PdfWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.sql.SQLException;
import java.util.List;


/**
 *
 * @author boukhris
 */
public class Pdf {
    public void portfolioPDF() throws FileNotFoundException, DocumentException, SQLException 
    {
        Document document = new Document();

        CompetitionService cs = new CompetitionService();
        List<Competition> res=cs.getAllCompetition();
        PdfWriter.getInstance(document, new FileOutputStream (new File ("Liste Des Competition.pdf")));
        document.open();
       Pdf pdf=new Pdf();
        for (Competition c :res) {

            Paragraph Competition= new Paragraph ("Competition n°"+ c.getRef_competition());
            Competition.setAlignment (Element.ALIGN_CENTER);
            document.add (Competition);
//            Paragraph ref_competition = new Paragraph ("Ref_competition : " + c.getRef_competition());
//            ref_competition.setAlignment (Element.ALIGN_LEFT);
            Paragraph nom = new Paragraph ("Nom : " + c.getNom());
            nom.setAlignment (Element.ALIGN_LEFT);
            Paragraph  theme = new Paragraph ("Theme: " + c.getTheme());
            theme.setAlignment (Element.ALIGN_LEFT);
            Paragraph  date_debut = new Paragraph ("Date_debut: " + c.getDate_debut());
            date_debut.setAlignment (Element.ALIGN_LEFT);
            Paragraph  date_fin = new Paragraph ("Date_fin: " + c.getDate_fin());
            date_fin.setAlignment (Element.ALIGN_LEFT);
             Paragraph  type = new Paragraph ("Type: " + c.getType());
            type.setAlignment (Element.ALIGN_LEFT);
           
            //document.add (ref_competition);
            document.add (nom);
            document.add (theme); 
            document.add(date_debut);
            document.add(date_fin);
            document.add(type);


        }
        document.close();
}
}
