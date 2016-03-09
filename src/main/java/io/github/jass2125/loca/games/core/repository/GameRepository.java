/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.jass2125.loca.games.core.repository;

import io.github.jass2125.loca.games.core.business.Game;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Anderson Souza
 */
public interface GameRepository<T> {

    public List<Game> listGames() throws SQLException, ClassNotFoundException;
    public Game findById(Long idGame) throws SQLException, ClassNotFoundException;
    public void editState(Long idGame, String state) throws ClassNotFoundException, SQLException;
    public List<Game> listGamesLocatedByUser(String cpf) throws ClassNotFoundException, SQLException;

}
