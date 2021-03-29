/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package artraction.dao;

/**
 *
 * @author MSI
 */
public interface IdaoUser<T> {
    public T FindUser(String t1,String t2, String role);
    public void AddUser(String username, String email, String password, int numero, int age, String addresse, String role, String image);
    public void DeleteUser(String email);
    
}
