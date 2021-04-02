/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package artraction.entity;

import java.sql.Date;

/**
 *
 * @author asus
 */
public class Reservation {
    private int id_reservation ;
    private int id_formation;
    private int heure;
    private String nom;
    private String date;
    

    public Reservation(int id_reservation, int id_formation, int heure) {
        this.id_reservation = id_reservation;
        this.id_formation = id_formation;
        this.heure = heure;
    }

    public Reservation(int id_reservation, int id_formation, int heure, String nom) {
        this.id_reservation = id_reservation;
        this.id_formation = id_formation;
        this.heure = heure;
        this.nom = nom;
    }

    public Reservation(int id_formation, int heure, String nom) {
        this.id_formation = id_formation;
        this.heure = heure;
        this.nom = nom;
    }

    public Reservation(int id_reservation, int id_formation, int heure, String nom, String date) {
        this.id_reservation = id_reservation;
        this.id_formation = id_formation;
        this.heure = heure;
        this.nom = nom;
        this.date = date;
    }

    public Reservation(int id_formation, int heure, String nom, String date) {
        this.id_formation = id_formation;
        this.heure = heure;
        this.nom = nom;
        this.date = date;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
    

    public Reservation(int id_formation, int heure) {
        this.id_formation = id_formation;
        this.heure = heure;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }


    public int getId_reservation() {
        return id_reservation;
    }

    public void setId_reservation(int id_reservation) {
        this.id_reservation = id_reservation;
    }

    public int getId_formation() {
        return id_formation;
    }

    public void setId_formation(int id_formation) {
        this.id_formation = id_formation;
    }

    public int getHeure() {
        return heure;
    }

    public void setHeure(int heure) {
        this.heure = heure;
    }

    @Override
    public String toString() {
        return "Reservation{" + "id_reservation=" + id_reservation + ", id_formation=" + id_formation + ", heure=" + heure + '}';
    }
    
}
