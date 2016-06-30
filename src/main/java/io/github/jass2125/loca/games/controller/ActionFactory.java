/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.jass2125.loca.games.controller;

import io.github.jass2125.loca.games.core.actions.Action;
import io.github.jass2125.loca.games.core.actions.LoaderGamesAction;
import io.github.jass2125.loca.games.core.actions.LoginClienteAction;
import io.github.jass2125.loca.games.core.actions.RegisterUserAction;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

/**
 * @author Anderson Souza
 * @since 15:32:23, 20-Feb-2016
 * @version 1.0
 */
public class ActionFactory {

    private static final Map<String, Action> commands = new HashMap<>();

    static {
        commands.put("loginCliente", ActionEnum.LOGIN.getAction());
        commands.put("loadGames", ActionEnum.LOAD_GAMES.getAction());
        commands.put("loadGamesLocated", ActionEnum.LOAD_GAMES_LOCATED.getAction());
        commands.put("registerUser", ActionEnum.REGISTER_USER.getAction());
        commands.put("devolutionGame", ActionEnum.DEVOLUTION_GAME.getAction());
        commands.put("logout", ActionEnum.LOGOUT.getAction());
        commands.put("locationGame", ActionEnum.LOCATION_GAME.getAction());
    }

    /**
     * Retorna a operaçao a ser executada
     *
     * @param request Requisiçao Http do cliente
     * @return Açao a ser executada
     */
    public static Action getAction(HttpServletRequest request) throws IOException {
        Action action = commands.get(request.getParameter("action"));
        if (action == null) {
            throw new IOException();
        }
        return action;
    }

    public enum ActionEnum {
        LOGIN("loginCliente") {
            @Override
            public Action getAction() {
                return new LoginClienteAction();
            }
        },
        LOAD_GAMES("loadGames") {
            @Override
            public Action getAction() {
                return new LoaderGamesAction();
            }
        },
        LOAD_GAMES_LOCATED("loadGamesLocated") {
            @Override
            public Action getAction() {
                return new LoaderGamesAction();
            }
        },
        REGISTER_USER("registerUser") {
            @Override
            public Action getAction() {
                return new RegisterUserAction();
            }
        },
        DEVOLUTION_GAME("devolutionGame") {
            @Override
            public Action getAction() {
                return new RegisterUserAction();
            }
        },
        LOGOUT("logout") {
            @Override
            public Action getAction() {
                return new RegisterUserAction();
            }
        },
        LOCATION_GAME("locationGame") {
            @Override
            public Action getAction() {
                return new RegisterUserAction();
            }
        };

        public abstract Action getAction();

        private final String action;

        private ActionEnum(String action) {
            this.action = action;
        }

    }

}
