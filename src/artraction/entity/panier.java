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
        if (etat!=0 && etat!=1)
            System.out.println("etat doit etre 0 ou 1");
        else {
            this.etat = etat;
        }
    }


    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final panier other = (panier) obj;
        if (!Objects.equals(this.id_panier, other.id_panier)) {
            return false;
        }
        return true;
    }
    
    
    
}

