/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package artraction.service;
import artraction.entity.oeuvre;
import javafx.collections.ObservableList;
import static javafx.scene.input.KeyCode.T;
/**
 *
 * @author zeyne
 */
public interface Iajoutoeuvservice<T> {
     public void insert(T o);
     public  ObservableList<T> displaypanier(int id);
     public int updateidpanier(T o,int x);
 public ObservableList<oeuvre> displaycommande(int id);
}
