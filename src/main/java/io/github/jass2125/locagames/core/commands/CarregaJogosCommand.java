/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.jass2125.locagames.core.commands;

import io.github.jass2125.locagames.core.negocio.Jogo;
import io.github.jass2125.locagames.core.repository.JogoDao;
import io.github.jass2125.locagames.core.repository.JogoDaoImpl;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Anderson Souza
 * @since 16:55:23, 24-Feb-2016
 * @version 1.0
 */
public class CarregaJogosCommand implements Command {

    private JogoDao dao;

    public CarregaJogosCommand() {
        dao = new JogoDaoImpl();
    }

    /**
     * Executa a ação de setar na sessão todos os games
     *
     * @param request Requisiçao do cliente
     * @param response Reposta do cliente
     * @return URL da pagina de resposta
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        request.getSession().setAttribute("listGames", getListaGames());
        return "funcionario/alugar.jsp";
    }

    /**
     * Recupera a lista com todos os games cadastrados
     *
     * @return List LIsta com todos jogos da aplicação
     */
    public List<Jogo> getListaGames() {
        return dao.listaDeJogos();
    }

}
