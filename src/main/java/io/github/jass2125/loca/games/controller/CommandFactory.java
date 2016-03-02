/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.jass2125.loca.games.controller;

import io.github.jass2125.loca.games.core.commands.Command;
import io.github.jass2125.loca.games.core.commands.GameAvailableCommand;
import io.github.jass2125.loca.games.core.commands.GameDevolutionCommand;
import io.github.jass2125.loca.games.core.commands.GameRenderCommand;
import io.github.jass2125.loca.games.core.commands.LoaderGameLocatedCommand;
import io.github.jass2125.loca.games.core.commands.LoaderGamesCommand;
import io.github.jass2125.loca.games.core.commands.LoginUserCommand;
import io.github.jass2125.loca.games.core.commands.RegisterUserCommand;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;



/**
 * @author Anderson Souza
 * @since 15:32:23, 20-Feb-2016
 */
public class CommandFactory {

    /**
     *
     * @param request
     * @return
     */
    public static Command getCommand(HttpServletRequest request) {
        Map<String, Command> commands = new HashMap<>();
        commands.put("loginUser", new LoginUserCommand());
        
        commands.put("loadGames", new LoaderGamesCommand());
        commands.put("renderGame", new GameRenderCommand());
        commands.put("registerUser", new RegisterUserCommand());
        commands.put("availableGame", new GameAvailableCommand());
        
        commands.put("loaderLocations", new LoaderGameLocatedCommand());
        commands.put("devolutionGame", new GameDevolutionCommand());
//        commands.put("render", new GameRenderCommand());
//        commands.put("return", new GameRenderCommand());

//        commands.put("login", new LoginCommand());
        return commands.get(request.getParameter("command"));
    }

}
