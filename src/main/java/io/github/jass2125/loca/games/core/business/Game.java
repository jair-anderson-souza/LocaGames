/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.jass2125.loca.games.core.business;

import io.github.jass2125.loca.games.core.util.SituationEnum;
import io.github.jass2125.loca.games.core.services.GameAvailableState;
import io.github.jass2125.loca.games.core.services.GameState;

/**
 * @author Anderson Souza
 * @since 14:17:38, 20-Feb-2016
 */
public class Game {

    private Long idGame;
    private String name;
    private String gender;
    private SituationEnum situation;
    private GameState state;

    public Game() {
        state = new GameAvailableState();
    }

    public Game(String name, String gender) {
        this.name = name;
        this.gender = gender;

    }

    public Game(Long idGame, String nameGame, String gender) {
        this.idGame = idGame;
        this.name = nameGame;
        this.gender = gender;
    }

    public Long getId() {
        return this.idGame;
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

    public String getSituation() {
        return this.situation.getSituation();
    }

    public void setSituation(String situation) {
        this.situation.setSituation(situation);
    }

    public GameState getState() {
        return state;
    }

    public void setState(GameState state) {
        this.state = state;
    }
}
