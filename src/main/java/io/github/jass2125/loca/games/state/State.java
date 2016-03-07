/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.jass2125.loca.games.state;

import io.github.jass2125.loca.games.exceptions.GameException;
import java.sql.SQLException;

/**
 * @author Anderson Souza
 * @since 08:29:40, 24-Feb-2016
 */
public interface State {

    public State rentedGame() throws SQLException, ClassNotFoundException, GameException;

    public State availableGame()throws SQLException, ClassNotFoundException, GameException;

}
