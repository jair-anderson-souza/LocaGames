/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.jass2125.locagames.core.enums;

import io.github.jass2125.locagames.core.commands.CarregaJogosCommand;
import io.github.jass2125.locagames.core.commands.CarregaJogosDeUmClienteCommand;
import io.github.jass2125.locagames.core.commands.Command;
import io.github.jass2125.locagames.core.commands.DevolucaoDeJogoCommand;
import io.github.jass2125.locagames.core.commands.LocacaoDeJogoCommand;
import io.github.jass2125.locagames.core.commands.LoginClienteCommand;
import io.github.jass2125.locagames.core.commands.LogoutCommand;
import io.github.jass2125.locagames.core.commands.RegisterUserAction;

/**
 * Enumeração com os commandos possíveis
 * @author Anderson Souza
 */
public enum CommandsEnum {
    
    
    /**
     * Enumeração correspondente ao login do cliente na aplicação
     */
     LOGIN_CLIENTE {
        @Override
        public Command getCommand() {
            return new LoginClienteCommand();
        }
    },
    /**
     * Enumeração do carregamento de todos os jogos da aplicaçao
     */
     CARREGA_JOGOS {
        @Override
        public Command getCommand() {
            return new CarregaJogosCommand();
        }
    },
    /**
     * Enumeração do carregamento de todos os jogos alugados da aplicação
     */
     CARREGA_JOGOS_ALUGADOS {
        @Override
        public Command getCommand() {
            return new CarregaJogosCommand();
        }
    },
    /**
     * FAZER
     */
     REGISTER_USER {
        @Override
        public Command getCommand() {
            return new RegisterUserAction();
        }
    },
    /**
     * Enumeração da devolução de um jogo 
     */
    DEVOLUCAO_DE_JOGO {
        @Override
        public Command getCommand() {
            return new DevolucaoDeJogoCommand();
        }
    },
    /**
     * Enumeração do logout do cliente
     */
    LOGOUT {
        @Override
        public Command getCommand() {
            return new LogoutCommand();
        }
    },
    /**
     * Enumeração da locação de um jogo na aplicação
     */
    LOCACAO_DE_JOGO {
        @Override
        public Command getCommand() {
            return new LocacaoDeJogoCommand();
        }
    }, 
    /**
     * Enumeração do carregamento dos jogos algados por determinado cliente
     */
    CARREGA_JOGOS_DE_CLIENTE {
        @Override
        public Command getCommand() {
            return new CarregaJogosDeUmClienteCommand();
        }
    };

    public abstract Command getCommand();

}
