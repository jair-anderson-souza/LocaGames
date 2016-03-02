/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.jass2125.loca.games.core.commands;

import io.github.jass2125.loca.games.core.business.Game;
import io.github.jass2125.loca.games.core.business.User;
import io.github.jass2125.loca.games.core.dao.GameDao;
import io.github.jass2125.loca.games.core.factory.DaoFactory;
import io.github.jass2125.loca.games.core.util.DaoEnum;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Anderson Souza
 */
public class LoaderGameLocatedCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            GameDao dao = (GameDao) DaoFactory.createDao(DaoEnum.GAME.getOption());
            List<Game> listLocations = dao.listGamesLocated();
            request.getSession().setAttribute("listLocations", listLocations);
            return "devolver.jsp";
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return "index.jsp";
        }

    }

}
