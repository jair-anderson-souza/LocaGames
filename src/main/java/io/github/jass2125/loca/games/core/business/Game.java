/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.jass2125.loca.games.core.business;

import io.github.jass2125.loca.games.state.GameState;
import java.io.Serializable;

/**
 * @author Anderson Souza
 * @since 14:17:38, 20-Feb-2016
 */
public class Game implements Serializable {

    private Long idGame;
    private String name;
    private String gender;
    private String status;
    private transient GameState state;

    public Game() {
    }

    public Game(String name, String gender, String status) {
        this.name = name;
        this.gender = gender;
        this.status = status;
    }    

    public Game(Long idGame, String nameGame, String gender, String status) {
        this.idGame = idGame;
        this.name = nameGame;
        this.gender = gender;
        this.status = status;
    }

    public Long getIdGame() {
        return idGame;
    }

    public void setIdGame(Long idGame) {
        this.idGame = idGame;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public GameState getState() {
        return state;
    }

    public void setState(GameState state) {
        this.state = state;
    }

}