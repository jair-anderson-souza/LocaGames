/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.jass2125.loca.games.core.factory;

import java.sql.SQLException;

/**
 * @author Anderson Souza
 * @param <T>
 * @since 16:52:22, 23-Feb-2016
 */
public abstract class GenericRepository<T> {

    public abstract void save(T obj) throws SQLException, ClassNotFoundException;

    public abstract void search(T id) throws SQLException, ClassNotFoundException;

    public abstract void list() throws SQLException, ClassNotFoundException;

    public abstract void update(T obj) throws SQLException, ClassNotFoundException;

}
