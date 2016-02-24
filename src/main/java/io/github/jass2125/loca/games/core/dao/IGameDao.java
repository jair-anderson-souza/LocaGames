/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package io.github.jass2125.loca.games.core.dao;

import io.github.jass2125.loca.games.core.business.Game;
import java.sql.SQLException;
import java.util.List;

/**
 * @author Anderson Souza <jair_anderson_bs@hotmail.com> 
 * @since 12:16:52, 24-Feb-2016
 */
public interface IGameDao {

    public List<Game> listGames() throws SQLException, ClassNotFoundException;
    public Game findById(int idGame) throws SQLException, ClassNotFoundException;
}
