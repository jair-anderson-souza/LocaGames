/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package io.github.jass2125.loca.games.core.util;

/**
 * @author Anderson Souza 
 * @since 23:07:00, 23-Feb-2016 
 */
public enum StateGame {
    ALUGADO("Alugado"), DISPONIVEL("Disponível");
    private String state;

    private StateGame(String state) {
        this.state = state;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
    
    
}
