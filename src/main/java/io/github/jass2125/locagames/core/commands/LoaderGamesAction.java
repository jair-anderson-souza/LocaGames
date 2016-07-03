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
public class LoaderGamesAction implements Command {

    private JogoDao dao;

    public LoaderGamesAction() {

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
        try {
            List<Jogo> listGames = getListaGames();
            request.getSession().setAttribute("listGames", listGames);
            return "funcionario/alugar.jsp";
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            request.getSession().setAttribute("error", "Ocorreu um erro, retorne e tente novamente");
            return "home.jsp";
        }

    }

    /**
     * Recupera a lista com todos os games cadastrados
     *
     * @return List LIsta com todos jogos da aplicação
     * @throws SQLException Retorna caso ele não consiga recuperar essas
     * informações
     * @throws ClassNotFoundException Classe do Driver MySQL não está disponivel
     */
    public List<Jogo> getListaGames() throws SQLException, ClassNotFoundException {
        dao = new JogoDaoImpl();
        List<Jogo> listGames = dao.listaDeJogos();
        return listGames;
    }

}
