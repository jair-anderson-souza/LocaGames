/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.jass2125.loca.games.core.actions;

import io.github.jass2125.loca.games.core.business.Game;
import io.github.jass2125.loca.games.core.repository.GameDao;
import io.github.jass2125.loca.games.core.repository.GameRepository;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Anderson Souza
 * @since 16:55:23, 24-Feb-2016
 */
public class LoaderGamesAction implements Action {

    private GameRepository dao;

    public LoaderGamesAction() {
        
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            List<Game> listGames = getListaGames();
            request.getSession().setAttribute("listGames", listGames);
            return "funcionario/alugar.jsp";
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            request.getSession().setAttribute("error", "Ocorreu um erro, retorne e tente novamente");
            return "home.jsp";
        }

    }
    
    public List<Game> getListaGames() throws SQLException, ClassNotFoundException{
        dao = new GameDao();
        List<Game> listGames = dao.listGames();
        return listGames;
    }

}
