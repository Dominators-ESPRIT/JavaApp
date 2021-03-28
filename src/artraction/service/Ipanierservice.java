/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package artraction.service;

import artraction.entity.oeuvre;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.ObservableList;


public interface Ipanierservice<T> {
    public void insert(T o);
    public void delete(T o);
    public int displayId();
 //;
    public ArrayList<String> displaycodepromo() ;
    public int update(int x);
    public int displaycode(String ch);
    public int displayvaleur(int id);
     public void updatecode (int id,int idp);
    
}
