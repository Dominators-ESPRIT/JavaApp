/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package artraction.entity;

/**
 *
 * @author zeyne
 */
public class codepromo {
    int id;
    String label;
    int valeur;
    public codepromo(){
        
    }
    public codepromo(String label){
        this.label=label;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public int getValeur() {
        return valeur;
    }

    public void setValeur(int valeur) {
     
        this.valeur = valeur;
    }

 

    
}
