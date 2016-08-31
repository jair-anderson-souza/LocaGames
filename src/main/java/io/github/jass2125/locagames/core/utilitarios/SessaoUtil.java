/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.jass2125.locagames.core.utilitarios;

import javax.servlet.http.HttpSession;

/**
 *
 * @author Anderson Souza
 */
public class SessaoUtil {

    public static void limpaSessao(HttpSession session) {
        session.removeAttribute("info");
        session.removeAttribute("preco");
        session.removeAttribute("preco");
        session.removeAttribute("erro");
        session.removeAttribute("sucesso");
        session.removeAttribute("listaDeJogos");
        session.removeAttribute("listaDeLocacoes");
    }

}
