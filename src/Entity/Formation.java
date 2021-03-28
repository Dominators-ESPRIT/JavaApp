/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.sql.Date;



/**
 *
 * @author mahdi
 */
public class Formation {
    private int id_formation ;
    private String nom ; 
    private String theme ; 
    private Date date_debut ;  
    private Date date_fin ; 

    public Formation(int id_formation, String nom, String theme, Date date_debut, Date date_fin) {
        this.id_formation = id_formation;
        this.nom = nom;
        this.theme = theme;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
    }

    public int getId_formation() {
        return id_formation;
    }

    public String getNom() {
        return nom;
    }

    public String getTheme() {
        return theme;
    }

    public Date getDate_debut() {
        return date_debut;
    }

    public Date getDate_fin() {
        return date_fin;
    }
    
}


