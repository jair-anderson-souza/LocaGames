/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.jass2125.loca.games.core.commands;

import io.github.jass2125.loca.games.core.business.Game;
import io.github.jass2125.loca.games.core.business.Location;
import io.github.jass2125.loca.games.core.business.User;
import io.github.jass2125.loca.games.core.dao.GameDao;
import io.github.jass2125.loca.games.core.dao.LocationDao;
import io.github.jass2125.loca.games.core.factory.DaoFactory;
import io.github.jass2125.loca.games.core.util.DaoEnum;
import java.sql.SQLException;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Anderson Souza
 * @since 15:18:13, 24-Feb-2016
 */
public class GameAvailableCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            GameDao daoGame = (GameDao) DaoFactory.createDao(DaoEnum.GAME.getOption());
            Long idGame = Long.parseLong(request.getParameter("idGame"));
            Game game = daoGame.findById(idGame);

            LocationDao daoLocation = (LocationDao) DaoFactory.createDao(DaoEnum.LOCATION.getOption());
            //Data atual
            Location location = daoLocation.findByUserAndGame(idGame, new Date());

            User user = (User) request.getSession().getAttribute("user");
//            game.setState(new GameAvailableState());
//            location.
            //pagina a qual sera endereçada
            return "home.jsp";

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return null;
    }

}
