/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.jass2125.loca.games.controller;

import io.github.jass2125.loca.games.core.actions.Action;
import io.github.jass2125.loca.games.core.actions.GameDevolutionAction;
import io.github.jass2125.loca.games.core.actions.GameLocationAction;
import io.github.jass2125.loca.games.core.actions.LoaderGameLocatedAction;
import io.github.jass2125.loca.games.core.actions.LoaderGamesAction;
import io.github.jass2125.loca.games.core.actions.LoginUserAction;
import io.github.jass2125.loca.games.core.actions.RegisterUserAction;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

/**
 * @author Anderson Souza
 * @since 15:32:23, 20-Feb-2016
 */
public class ActionFactory {

    /**
     *
     * @param request
     * @return
     */
    public static Action getAction(HttpServletRequest request) {
        Map<String, Action> commands = new HashMap<>();
        commands.put("loadGames", new LoaderGamesAction());
        commands.put("loadGamesLocated", new LoaderGameLocatedAction());
        commands.put("loginUser", new LoginUserAction());
        commands.put("registerUser", new RegisterUserAction());
        commands.put("devolutionGame", new GameDevolutionAction());
        
        commands.put("locationGame", new GameLocationAction());

        return commands.get(request.getParameter("action"));
    }

}
