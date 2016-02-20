/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package io.github.jass2125.loca.games.core.dao;

import io.github.jass2125.loca.games.core.business.User;
import java.sql.SQLException;

/**
 * @author Anderson Souza 
 * @since 17:22:34, 20-Feb-2016 
 */
public interface IUserDao {
    
    public void persist(User user);
    
    public User findByCPFAndEmail(String cpf, String email) throws SQLException, ClassNotFoundException;
    
}
