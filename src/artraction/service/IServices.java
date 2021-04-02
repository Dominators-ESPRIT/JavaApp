/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package artraction.service;

import java.util.List;

/**
 *
 * @author asus
 */
public interface IServices<T> {
    public void Ajouter(T t);
    public void Supprimer(T t);
    public void Modifier(T t);
    public List<T> Afficher();
}
