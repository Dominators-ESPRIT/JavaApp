/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package artraction.entity;

import java.util.Date;

/**
 *
 * @author boukhris
 */
public class Competition {
    private int ref_competition;
    private String nom;
    private String theme;
    private Date date_debut;
    private Date date_fin;
    private String type;

    public Competition() {
    }

    public Competition(String nom, String theme, Date date_debut, Date date_fin, String type) {
        this.nom = nom;
        this.theme = theme;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.type = type;
    }

    public Competition(String nom, String theme, String type) {
        this.nom = nom;
        this.theme = theme;
        this.type = type;
    }
    
    

    public Competition(int ref_competition, String nom, String theme, Date date_debut, Date date_fin, String type) {
        this.ref_competition = ref_competition;
        this.nom = nom;
        this.theme = theme;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.type = type;
    }

    public int getRef_competition() {
        return ref_competition;
    }

    public void setRef_competition(int ref_competition) {
        this.ref_competition = ref_competition;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public Date getDate_debut() {
        return date_debut;
    }

    public void setDate_debut(Date date_debut) {
        this.date_debut = date_debut;
    }

    public Date getDate_fin() {
        return date_fin;
    }

    public void setDate_fin(Date date_fin) {
        this.date_fin = date_fin;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Competition{" + "ref_competition=" + ref_competition + ", nom=" + nom + ", theme=" + theme + ", date_debut=" + date_debut + ", date_fin=" + date_fin + ", type=" + type + '}';
    }
    
}
