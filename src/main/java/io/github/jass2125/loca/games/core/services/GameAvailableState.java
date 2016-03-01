/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.jass2125.loca.games.core.services;

import io.github.jass2125.loca.games.core.business.Game;
import io.github.jass2125.loca.games.core.util.SituationEnum;

/**
 * @author Anderson Souza
 * @since 08:30:18, 24-Feb-2016
 */
public class GameAvailableState implements GameState {

    @Override
    public GameState renderGame(Game game) {
        
        return null;
    }

    @Override
    public GameState availableGame(Game game) {
        if(game.getSituation().equals(SituationEnum.AVAILABLE)){
            return new GameRenderState();
        }
        return null;
    }

}
