/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.jass2125.loca.games.controller;

import io.github.jass2125.loca.games.core.commands.Command;
import io.github.jass2125.loca.games.core.commands.LoginCommand;
import io.github.jass2125.loca.games.core.commands.RegisterUserCommand;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

/**
 * @author Anderson Souza
 * @since 15:32:23, 20-Feb-2016 Package io.github.jass2125.loca.games.controller
 * Project Name loca-games Encoding UTF-8 File Name CommandFactory.java
 */
public class CommandFactory {

    public static Command getCommand(HttpServletRequest request) {
        Map<String, Command> commands = new HashMap<>();
        commands.put("login", new LoginCommand());
        commands.put("registerUser", new RegisterUserCommand());
        return commands.get(request.getParameter("command"));
    }

}
