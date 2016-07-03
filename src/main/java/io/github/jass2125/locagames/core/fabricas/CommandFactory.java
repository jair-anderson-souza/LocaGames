/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.jass2125.locagames.core.fabricas;

import io.github.jass2125.locagames.core.commands.LoaderGamesAction;
import io.github.jass2125.locagames.core.commands.LoginClienteCommand;
import io.github.jass2125.locagames.core.commands.RegisterUserAction;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import io.github.jass2125.locagames.core.commands.Command;

/**
 * @author Anderson Souza
 * @since 15:32:23, 20-Feb-2016
 * @version 1.0
 */
public class CommandFactory {

    private static final Map<String, CommandEnum> commands = new HashMap<>();

    static {
        commands.put("loginCliente", CommandEnum.LOGIN_CLIENTE);
        commands.put("loadGames", CommandEnum.LOAD_GAMES);
        commands.put("loadGamesLocated", CommandEnum.LOAD_GAMES_LOCATED);
        commands.put("registerUser", CommandEnum.REGISTER_USER);
        commands.put("devolutionGame", CommandEnum.DEVOLUTION_GAME);
        commands.put("logout", CommandEnum.LOGOUT);
        commands.put("locationGame", CommandEnum.LOCATION_GAME);
    }

    /**
     * Retorna a operaçao a ser executada
     *
     * @param request Requisiçao Http do cliente
     * @return Açao a ser executada
     * @throws java.io.IOException
     */
    public static Command getCommand(HttpServletRequest request) throws IOException {
        Command command = commands.get(request.getParameter("command")).getCommand();
        if (command == null) {
            throw new IOException();
        }
        return command;
    }

    public enum CommandEnum {
        LOGIN_CLIENTE("loginCliente") {
            @Override
            public Command getCommand() {
                return new LoginClienteCommand();
            }
        },
        LOAD_GAMES("loadGames") {
            @Override
            public Command getCommand() {
                return new LoaderGamesAction();
            }
        },
        LOAD_GAMES_LOCATED("loadGamesLocated") {
            @Override
            public Command getCommand() {
                return new LoaderGamesAction();
            }
        },
        REGISTER_USER("registerUser") {
            @Override
            public Command getCommand() {
                return new RegisterUserAction();
            }
        },
        DEVOLUTION_GAME("devolutionGame") {
            @Override
            public Command getCommand() {
                return new RegisterUserAction();
            }
        },
        LOGOUT("logout") {
            @Override
            public Command getCommand() {
                return new RegisterUserAction();
            }
        },
        LOCATION_GAME("locationGame") {
            @Override
            public Command getCommand() {
                return new RegisterUserAction();
            }
        };

        public abstract Command getCommand();

        private final String command;

        private CommandEnum(String command) {
            this.command = command;
        }

    }

}
