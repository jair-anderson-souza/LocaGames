/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.jass2125.loca.games.services;

import io.github.jass2125.loca.games.core.business.Game;
import io.github.jass2125.loca.games.core.business.User;
import io.github.jass2125.loca.games.core.util.Factory;
import io.github.jass2125.loca.games.core.util.FactoryDao;
import io.github.jass2125.loca.games.core.util.SituationEnum;
import io.github.jass2125.loca.games.state.GameRenderState;

/**
 * @author Anderson Souza
 * @since 13:45:58, 24-Feb-2016
 */
public class LocationService {
    
    public void realizeLocation(User user, Game game) {
        Factory factory = new FactoryDao();
        ILocationDao dao = factory.createLocationDao();
        if (game.getSituation().equals(SituationEnum.AVAILABLE.getSituation())) {
            game.setState(new GameRenderState());
            
        }
    }

}
