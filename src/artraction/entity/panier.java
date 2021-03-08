/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package artraction.entity;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.Objects;


public class panier {
    
    private int id_panier;
    private int etat;

    public panier() {
    }

 
    public int getid_panier() {
        return id_panier;
    }

 

    public int getetat() {
        return etat;
    }

    public void setetat(int etat) {
        while (etat!=0 && etat!=1)
            System.out.println("etat doit etre 0 ou 1");
        this.etat = etat;
        }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 13 * hash + this.id_panier;
        hash = 13 * hash + this.etat;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final panier other = (panier) obj;
        if (this.id_panier != other.id_panier) {
            return false;
        }
        if (this.etat != other.etat) {
            return false;
        }
        return true;
    }
    


    
    
}

