/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.jass2125.locagames.core.fabricas;

import io.github.jass2125.locagames.core.commands.Command;
import io.github.jass2125.locagames.core.enums.CommandsEnum;
import io.github.jass2125.locagames.core.excecoes.AplicacaoException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

/**
 * @author Anderson Souza
 * @since 15:32:23, 20-Feb-2016
 * @version 1.0
 */
public class FabricaDeCommandsImpl implements FabricaDeCommands {

    private static final Map<String, CommandsEnum> commands = new HashMap();

    static {
        commands.put("loginCliente", CommandsEnum.LOGIN_CLIENTE);
        commands.put("carregaJogos", CommandsEnum.CARREGA_JOGOS);
        commands.put("locacaoDeJogo", CommandsEnum.LOCACAO_DE_JOGO);
        commands.put("devolverJogo", CommandsEnum.DEVOLUCAO_DE_JOGO);
        commands.put("carregarJogoAlugadosDeUmCliente", CommandsEnum.CARREGA_JOGOS_DE_CLIENTE);
        commands.put("sair", CommandsEnum.LOGOUT);
        commands.put("cadastroNovoCliente", CommandsEnum.CADASTRO_NOVO_CLIENTE);
        commands.put("carregaJogosAlugados", CommandsEnum.CARREGA_JOGOS_ALUGADOS);
    }

    /**
     * Retorna a instância de um command específico a ser executado
     *
     * @param request {@link HttpServletRequest} Requisiçao Http do cliente
     * @return {@link Command} Commando a ser executado
     */
    @Override
    public Command getCommand(HttpServletRequest request) {
        Command command = commands.get(request.getParameter("command")).getCommand();
        if (command == null) {
            throw new AplicacaoException();
        }
        return command;

    }

}
