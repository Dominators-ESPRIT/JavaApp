/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package artraction.service;

import java.util.ArrayList;
import java.util.List;
import javafx.scene.control.TextField;


public interface Ipanierservice<T> {
    public void insert(T o);
    public void delete(T o);
    public String returncodepromo(TextField ch);
    public int displayId();
    public ArrayList<String> displaycodepromo() ;
    public int update(int x);
    public int displaycode(String ch);
    
}
