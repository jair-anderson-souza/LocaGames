/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.jass2125.loca.games.state;

import java.sql.SQLException;

/**
 * @author Anderson Souza
 * @since 08:29:40, 24-Feb-2016
 */
public interface GameState {

    public GameState rentGame(String cpf, Long Idgame) throws SQLException, ClassNotFoundException;

    public GameState availableGame(String cpf, Long Idgame) throws SQLException, ClassNotFoundException;

}
