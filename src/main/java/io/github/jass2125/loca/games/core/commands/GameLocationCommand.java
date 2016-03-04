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
import io.github.jass2125.loca.games.exceptions.RentException;
import io.github.jass2125.loca.games.state.GameState;
import java.sql.SQLException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Anderson Souza
 * @since 15:18:13, 24-Feb-2016
 */
public class GameLocationCommand implements Command {
private LocationDao daoLocation;
    private GameDao dao;

    public GameLocationCommand() {
        dao = (GameDao) DaoFactory.createDao(DaoEnum.GAME.getOption());
        daoLocation = (LocationDao) DaoFactory.createDao(DaoEnum.LOCATION.getOption());
    }
    
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            Long idGame = Long.parseLong(request.getParameter("idGame"));
            String cpf = ((User) request.getSession().getAttribute("user")).getCpf();
            Game game = dao.findById(idGame);
            //pega o valor do enum
            //String state = game.getState();

//            State state  = game.rent();
//            game.getState().
            if (game.getState().equals(GameState.AVAILABLE)) {
                game.location();
                Location location = new Location();
                location.setIdGame(idGame);
                location.setDateDevolution(getDevolutionDay());
                location.setIdUser(cpf);
                location.setStrategy(verifyTypeOfLocation());
                daoLocation.save(location);
                request.getSession().setAttribute("success", "Jogo locado com sucesso");
                dao.editState(idGame, GameState.RENT.name());
                return "home.jsp";

            }

            request.getSession().setAttribute("error", "Jogo já esta alugado");
            return "home.jsp";

//            if (state.equals("RENT")) {
//                request.getSession().setAttribute("error", "Jogo ja esta alugado");
////                game.setState("AVAILABLE");
////                dao.editState(idGame, "AVAILABLE");
//                return "home.jsp";
//            } else {
//                daoLocation.save(location);
//                request.getSession().setAttribute("success", "Jogo alugado com sucesso");
//                game.setState("RENT");
//                dao.editState(idGame, "RENT");
//                return "home.jsp";
//            }
//            
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            request.getSession().setAttribute("error", "Erro, retorne e tente novamente");
            return "index.jsp";

        } catch (RentException e) {
            e.printStackTrace();
            request.getSession().setAttribute("error", "Erro, retorne e tente novamente");
            return "index.jsp";
        }
    }

    private String verifyTypeOfLocation() {
        String currentdate = LocalDate.now().getDayOfWeek().name();
        if (currentdate.equals(DayOfWeek.SUNDAY) || currentdate.equals(DayOfWeek.SATURDAY)) {
            return "SPECIAL";
        }
        return "COMUM";
    }

    public LocalDate getDevolutionDay() {
        String verif = this.verifyTypeOfLocation();
        if (verif.equals("SPECIAL")) {
            return LocalDate.now().plusDays(2);
        }
        return LocalDate.now().plusDays(1);

    }

}
