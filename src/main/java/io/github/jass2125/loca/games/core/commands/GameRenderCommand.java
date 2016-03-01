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
import io.github.jass2125.loca.games.state.GameAvailableState;
import io.github.jass2125.loca.games.state.GameRenderState;
import io.github.jass2125.loca.games.core.util.DaoEnum;
import io.github.jass2125.loca.games.state.GameState;
import java.sql.SQLException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Anderson Souza
 * @since 22:42:32, 23-Feb-2016
 */
public class GameRenderCommand implements Command {

    public GameRenderCommand() {

    }

    public String execute(HttpServletRequest request, HttpServletResponse response) {

        try {
            Long idGame = Long.parseLong(request.getParameter("idGame"));
            LocationDao daoLocation = (LocationDao) DaoFactory.createDao(DaoEnum.LOCATION.getOption());
            String cpf = ((User) request.getSession().getAttribute("user")).getCpf();
            GameDao daoGame = (GameDao) DaoFactory.createDao(DaoEnum.GAME.getOption());
            Game game = daoGame.findById(idGame);

            String strategy = verifyTypeOfLocation();
            GameState state = verifyStateOfGame(game.getStatus());
            boolean verifying = state.availableGame(game, cpf, strategy);
            if (verifying) {
                request.getSession().setAttribute("success", "A locação foi efetuado com sucesso");
                return "index.jsp";
            }
            request.getSession().setAttribute("error", "Jogo ja esta alugado");
            return "index.jsp";
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            request.getSession().setAttribute("error", "Erro, retorne e tente novamente");
            return "index.jsp";
        }
    }

    private String verifyTypeOfLocation() {
        String currentdate = LocalDate.now().getDayOfWeek().name();
        if (currentdate.equals(DayOfWeek.SUNDAY) || currentdate.equals(DayOfWeek.SATURDAY)) {
            return "COMUM";
        }
        return "SPECIAL";
    }

    private GameState verifyStateOfGame(String status) {
        if (status.equals("AVAILABLE")) {
            return new GameAvailableState();
        }
        return new GameRenderState();
    }
}
