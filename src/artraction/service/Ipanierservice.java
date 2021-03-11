/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package artraction.service;

import java.util.List;


public interface Ipanierservice<T> {
    public void insert(T o);
    public void delete(T o);
    public List<T> displayAll();
    public int displayId();
    
    public int update(T os);
    
}
