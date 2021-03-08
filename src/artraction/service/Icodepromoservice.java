/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package artraction.service;

import java.util.List;
import static javafx.scene.input.KeyCode.T;

/**
 *
 * @author zeyne
 */
public interface Icodepromoservice<T> {
     public void insert(T o);
    public void delete(T o);
    public List<T> displayAll();
    public T displayById(int id);
    
    public int updatevaleur(T os,String ch);
    public int updatelabel(T os,String ch);

}
