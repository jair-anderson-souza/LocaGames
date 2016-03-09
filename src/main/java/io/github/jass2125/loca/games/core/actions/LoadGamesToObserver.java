/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.jass2125.loca.games.core.actions;

import io.github.jass2125.loca.games.core.business.Game;
import io.github.jass2125.loca.games.core.repository.GameDao;
import java.sql.SQLException;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Anderson Souza
 */
public class LoadGamesToObserver implements Action {
    @EJB
    private GameDao dao;
    
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        try {
//            GameDao dao = (GameDao) DaoFactory.createDao(DaoEnum.GAME.getOption());
            List<Game> listGames = dao.listGames();
            request.getSession().setAttribute("listGames", listGames);
            return "observar.jsp";
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            request.getSession().setAttribute("error", "Ocorreu um erro, retorne e tente novamente");
            return "home.jsp";
        }

    }

}
