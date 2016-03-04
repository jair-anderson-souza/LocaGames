/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.jass2125.loca.games.state;

import io.github.jass2125.loca.games.exceptions.RentException;
import java.sql.SQLException;


/**
 * @author Anderson Souza
 * @since 12:25:19, 24-Feb-2016
 */
public enum GameState implements State {
    AVAILABLE{

        @Override
        public State rentedGame() throws SQLException, ClassNotFoundException, RentException {
            return RENT;
        }

        @Override
        public State availableGame() throws SQLException, ClassNotFoundException, RentException {
            throw new RentException("O jogo está disponivel!!");
        }

        
        
    },
    RENT{

        @Override
        public State rentedGame() throws SQLException, ClassNotFoundException, RentException {
                throw new RentException("O jogo está alugado!!");
        }

        @Override
        public State availableGame() throws SQLException, ClassNotFoundException, RentException {
            return AVAILABLE;
        }
    }
    

}