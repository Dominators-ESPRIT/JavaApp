/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package artraction.service;

import artraction.entity.codepromo;
import java.util.List;
import javafx.collections.ObservableList;
import static javafx.scene.input.KeyCode.T;

/**
 *
 * @author zeyne
 */
public interface Icodepromoservice<T> {
     public void insert(T o);
    public void delete(T o);
     public ObservableList<T> displayAll();
    public T displayById(int id);
    public int displayId(String ch);
    public int displayId();
    public List <T> displayLabel();
    public int updatevaleur(T os,String ch);
    public int updatelabel(T os,String ch);

}
