/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.jass2125.loca.games.core.repository;

import io.github.jass2125.loca.games.core.business.User;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Anderson Souza
 */
public interface UserRepository {

    public void save(User user) throws ClassNotFoundException, SQLException;

    public List<User> search(String cpf) throws SQLException, ClassNotFoundException;

    public User findByCPFAndEmail(String cpf, String email) throws SQLException, ClassNotFoundException;

}
