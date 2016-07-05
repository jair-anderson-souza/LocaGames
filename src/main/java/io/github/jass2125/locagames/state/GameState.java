/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.jass2125.locagames.state;

import io.github.jass2125.locagames.core.excecoes.GameException;
import java.sql.SQLException;


/**
 * @author Anderson Souza
 * @since 12:25:19, 24-Feb-2016
 */
public enum GameState implements State {
    AVAILABLE{

        @Override
        public State rentedGame() throws SQLException, ClassNotFoundException, GameException {
            return RENT;
        }

        @Override
        public State availableGame() throws SQLException, ClassNotFoundException, GameException {
            throw new GameException("O jogo está disponivel!!");
        }

        
        
    },
    RENT{

        @Override
        public State rentedGame() throws SQLException, ClassNotFoundException, GameException {
                throw new GameException("O jogo está alugado!!");
        }

        @Override
        public State availableGame() throws SQLException, ClassNotFoundException, GameException {
            return AVAILABLE;
        }
    }
    

}