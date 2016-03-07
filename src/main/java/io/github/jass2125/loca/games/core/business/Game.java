/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.jass2125.loca.games.core.business;

import io.github.jass2125.loca.games.exceptions.GameException;
import io.github.jass2125.loca.games.observer.Observable;
import io.github.jass2125.loca.games.observer.Observer;
import io.github.jass2125.loca.games.state.GameState;
import io.github.jass2125.loca.games.state.State;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.mail.EmailException;

/**
 * @author Anderson Souza
 * @since 14:17:38, 20-Feb-2016
 */
public class Game implements Serializable, Observable {

    private Long idGame;
    private String name;
    private String gender;
    private State state;
    private List<Observer> listObservers = new ArrayList<>();

    public Game() {
//        state = GameStateEnum.getAVAILABLE();
    }

    public Game(Long idGame, String name, String gender, String state) {
        this.idGame = idGame;
        this.name = name;
        this.gender = gender;
        this.state = GameState.valueOf(state);
    }

    public Long getIdGame() {
        return idGame;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public List<Observer> getListObservers() {
        return listObservers;
    }

    public void setListObservers(List<Observer> listObservers) {
        this.listObservers = listObservers;
    }

    

    @Override
    public void notifyObservers() throws EmailException{
        for (Observer it : listObservers) {
            it.update(this);
        }
    }

    @Override
    public void deleteObserver(Observer observer) {
        this.listObservers.remove(this);
    }

    @Override
    public void addObserver(Observer observer) {
        this.listObservers.add(observer);
    }
    
    public State devolution() throws SQLException, ClassNotFoundException, GameException {
        State state = this.state.availableGame();
        this.setState(state);
        return state;
    }

    public State location() throws SQLException, ClassNotFoundException, GameException {
        State state = this.state.rentedGame();
        this.setState(state);
        return state;
    }

}
