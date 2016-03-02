/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.jass2125.loca.games.state;

import io.github.jass2125.loca.games.core.dao.GameDao;
import java.sql.SQLException;

/**
 * @author Anderson Souza
 * @since 12:25:19, 24-Feb-2016
 */
public class GameStateRented implements GameState {
    private GameDao dao;

    @Override
    public GameState rentGame(String cpf, Long Idgame) throws SQLException, ClassNotFoundException {
//        dao.edit(this);return
        return new GameStateRented();
    }

    @Override
    public GameState availableGame(String cpf, Long Idgame) throws SQLException, ClassNotFoundException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}