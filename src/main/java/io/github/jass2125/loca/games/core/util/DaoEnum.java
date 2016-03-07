/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package io.github.jass2125.loca.games.core.util;

/**
 * @author Anderson Souza
 * @since 16:15:50, 24-Feb-2016
 */
public enum DaoEnum {
    GAME(1), USER(2), LOCATION(3), OBSERVER(4);
    
    private int option;

    private DaoEnum(int option) {
        this.option = option;
    }

    public int getOption() {
        return option;
    }

    public void setOption(int option) {
        this.option = option;
    }
    
    
}
