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
import io.github.jass2125.locagames.core.commands.DevolucaoDeJogoCommand;
import io.github.jass2125.locagames.core.commands.CarregaJogosDeUmClienteCommand;
import io.github.jass2125.locagames.core.commands.LocacaoDeJogoCommand;
import io.github.jass2125.locagames.core.commands.LogoutCommand;

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
        commands.put("devolverJogo", CommandEnum.DEVOLUCAO_DE_JOGO);
        commands.put("carregarJogoAlugadosDeUmCliente", CommandEnum.CARREGA_JOGOS_DE_CLIENTE);
        commands.put("sair", CommandEnum.LOGOUT);

        commands.put("carregaJogosAlugados", CommandEnum.CARREGA_JOGOS_ALUGADOS);
        commands.put("registerUser", CommandEnum.REGISTER_USER);
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
        CARREGA_JOGOS_ALUGADOS {
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
        DEVOLUCAO_DE_JOGO {
            @Override
            public Command getCommand() {
                return new DevolucaoDeJogoCommand();
            }
        },
        LOGOUT {
            @Override
            public Command getCommand() {
                return new LogoutCommand();
            }
        },
        LOCACAO_DE_JOGO {
            @Override
            public Command getCommand() {
                return new LocacaoDeJogoCommand();
            }
        }, CARREGA_JOGOS_DE_CLIENTE {
            @Override
            public Command getCommand() {
                return new CarregaJogosDeUmClienteCommand();
            }
        };

        public abstract Command getCommand();

    }

}
