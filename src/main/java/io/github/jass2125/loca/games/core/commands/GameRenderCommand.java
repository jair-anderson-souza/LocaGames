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
import io.github.jass2125.loca.games.core.services.GameRenderState;
import io.github.jass2125.loca.games.core.util.DaoEnum;
import io.github.jass2125.loca.games.core.util.SituationEnum;
import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Anderson Souza
 * @since 22:42:32, 23-Feb-2016
 */
public class GameRenderCommand implements Command {

    public String execute(HttpServletRequest request, HttpServletResponse response) {

        try {
            Long idGame = Long.parseLong(request.getParameter("idGame"));
            GameDao daoGame = (GameDao) DaoFactory.createDao(DaoEnum.GAME.getOption());
            LocationDao daoLocation = (LocationDao) DaoFactory.createDao(DaoEnum.LOCATION.getOption());
            Game game = daoGame.findById(idGame);
            
            if (game.getSituation().equals(SituationEnum.AVAILABLE.getSituation())) {
                game.setState(new GameRenderState());
                Location location = new Location();
                daoLocation.save(location);
                request.getSession().setAttribute("success", "A locação foi efetuado com sucesso");
                //pagina a qual sera endereçada
                return "index.jsp";
            } else {
                request.getSession().setAttribute("error", "Occoreu um erro, tente novamente");
                return "index.jsp";
            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
