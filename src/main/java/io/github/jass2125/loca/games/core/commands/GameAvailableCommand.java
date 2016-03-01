/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.jass2125.loca.games.core.commands;

import io.github.jass2125.loca.games.core.business.Game;
import io.github.jass2125.loca.games.core.dao.GameDao;
import io.github.jass2125.loca.games.core.dao.LocationDao;
import io.github.jass2125.loca.games.core.factory.DaoFactory;
import io.github.jass2125.loca.games.core.util.DaoEnum;
import java.sql.SQLException;
import java.util.List;
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

            String cpf = request.getParameter("cpf");
            String email = request.getParameter("email");
            
            LocationDao dao = (LocationDao) DaoFactory.createDao(DaoEnum.GAME.getOption());
            List<Long> listGames = dao.listLocationsByUser(cpf);
            
            GameDao daoGame = (GameDao) DaoFactory.createDao(DaoEnum.GAME.getOption());
            daoGame.listGamesById(email);
            
            
            
//            Long idGame = Long.parseLong(request.getParameter("idGame"));
//            GameDao daoGame = (GameDao) DaoFactory.createDao(DaoEnum.GAME.getOption());
//            LocationDao daoLocation = (LocationDao) DaoFactory.createDao(DaoEnum.LOCATION.getOption());
//            Game game = daoGame.findById(idGame);
//            User user = (User) request.getSession().getAttribute("user");
//            String si = game.getSituation();
//            if (game.getSituation().equals(SituationEnum.AVAILABLE.getSituation())) {
////                game.setState(new GameRenderState());
//                Location location = new Location();
//                location.setIdGame(game.getId());
//                location.setIdUser(user.getCpf());
//                daoLocation.save(location);
//                request.getSession().setAttribute("success", "A locação foi efetuado com sucesso");
//                //pagina a qual sera endereçada
//                return "index.jsp";
//            } else {
//                request.getSession().setAttribute("error", "Occoreu um erro, tente novamente");
//                return "index.js";
//            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


return null;
    }

}
