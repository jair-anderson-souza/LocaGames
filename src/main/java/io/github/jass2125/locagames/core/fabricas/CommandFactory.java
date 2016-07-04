/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.jass2125.locagames.core.fabricas;

import io.github.jass2125.locagames.core.commands.CarregaJogosCommand;
import io.github.jass2125.locagames.core.commands.LoginClienteCommand;
import io.github.jass2125.locagames.core.commands.RegisterUserAction;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import io.github.jass2125.locagames.core.commands.Command;
import io.github.jass2125.locagames.core.commands.LocacaoDeJogoCommand;

/**
 * @author Anderson Souza
 * @since 15:32:23, 20-Feb-2016
 * @version 1.0
 */
public class CommandFactory {

    private static final Map<String, CommandEnum> commands = new HashMap<>();

    static {
        commands.put("loginCliente", CommandEnum.LOGIN_CLIENTE);
        commands.put("carregaJogos", CommandEnum.CARREGA_JOGOS);
        commands.put("locacaoDeJogo", CommandEnum.LOCACAO_DE_JOGO);
        commands.put("loadGamesLocated", CommandEnum.LOAD_GAMES_LOCATED);
        commands.put("registerUser", CommandEnum.REGISTER_USER);
        commands.put("devolutionGame", CommandEnum.DEVOLUTION_GAME);
        commands.put("logout", CommandEnum.LOGOUT);
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
        LOGIN_CLIENTE {
            @Override
            public Command getCommand() {
                return new LoginClienteCommand();
            }
        },
        CARREGA_JOGOS {
            @Override
            public Command getCommand() {
                return new CarregaJogosCommand();
            }
        },
        LOAD_GAMES_LOCATED {
            @Override
            public Command getCommand() {
                return new CarregaJogosCommand();
            }
        },
        REGISTER_USER {
            @Override
            public Command getCommand() {
                return new RegisterUserAction();
            }
        },
        DEVOLUTION_GAME {
            @Override
            public Command getCommand() {
                return new RegisterUserAction();
            }
        },
        LOGOUT {
            @Override
            public Command getCommand() {
                return new RegisterUserAction();
            }
        },
        LOCACAO_DE_JOGO {
            @Override
            public Command getCommand() {
                return new LocacaoDeJogoCommand();
            }
        };

        public abstract Command getCommand();

    }

}
