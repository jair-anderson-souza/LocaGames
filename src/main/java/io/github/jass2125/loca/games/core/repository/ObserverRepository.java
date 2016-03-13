/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.jass2125.loca.games.core.repository;

import io.github.jass2125.loca.games.observer.Observer;
import java.sql.SQLException;
import java.util.Set;

/**
 *
 * @author Anderson Souza
 * @param <T>
 */
public interface ObserverRepository<T> {
    public void addObserver(String cpf, Long idGame) throws ClassNotFoundException, SQLException;
    public Set<Observer> getListObservers(Long idGame) throws SQLException, ClassNotFoundException;
    public void delete(Long idGame) throws SQLException, ClassNotFoundException;
    
}
