/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.jass2125.locagames.core.fabricas;

import io.github.jass2125.locagames.core.commands.Command;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Anderson Souza
 */
public interface FabricaDeCommands {

    /**
     * Retorna a instância de um command específico a ser executado
     *
     * @param request {@link HttpServletRequest} Requisiçao Http do cliente
     * @return {@link Command} Commando a ser executado
     */
    public Command getCommand(HttpServletRequest request);
}
