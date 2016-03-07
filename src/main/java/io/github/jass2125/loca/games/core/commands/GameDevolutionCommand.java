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
import io.github.jass2125.loca.games.exceptions.GameException;
import io.github.jass2125.loca.games.state.GameState;
import io.github.jass2125.loca.games.strategy.LocationCalcStrategy;
import java.math.BigDecimal;
import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.mail.EmailException;


/**
 *
 * @author Anderson Souza
 */
public class GameDevolutionCommand implements Command {
    private LocationDao daoLocation;
    private LocationCalcStrategy strategy;
    private GameDao dao;
    private LocationCalcStrategy strategyCalc;

    public GameDevolutionCommand() {
        dao = (GameDao) DaoFactory.createDao(DaoEnum.GAME.getOption());
        daoLocation = (LocationDao) DaoFactory.createDao(DaoEnum.LOCATION.getOption());
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            Long idGame = Long.parseLong(request.getParameter("idGame"));
            String cpf = ((User) request.getSession().getAttribute("user")).getCpf();
            Game game = dao.findById(idGame);

            if (game.getState().equals(GameState.RENT)) {
                game.devolution();
                Location location = daoLocation.findLocation(cpf, idGame);
                request.getSession().setAttribute("success", "Jogo devolvido com sucesso");
                BigDecimal price = location.calculateValueLocation();
                request.getSession().setAttribute("price", price);
                dao.editState(idGame, GameState.AVAILABLE.name());
                return "home.jsp";

            }
            
            game.notifyObservers();
            request.getSession().setAttribute("error", "Você não alugou este jogo!");
            return "home.jsp";

        } catch (SQLException | ClassNotFoundException | GameException | EmailException e) {
            e.printStackTrace();
            request.getSession().setAttribute("error", "Erro, retorne e tente novamente");
            return "home.jsp";

        } 
    }

}
