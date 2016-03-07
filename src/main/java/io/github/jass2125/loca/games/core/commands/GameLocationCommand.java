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
import io.github.jass2125.loca.games.core.dao.ObserverDao;
import io.github.jass2125.loca.games.core.factory.DaoFactory;
import io.github.jass2125.loca.games.core.util.DaoEnum;
import io.github.jass2125.loca.games.exceptions.GameException;
import io.github.jass2125.loca.games.state.GameState;
import io.github.jass2125.loca.games.strategy.LocationCalcStrategyEnum;
import java.sql.SQLException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Anderson Souza
 * @since 15:18:13, 24-Feb-2016
 */
public class GameLocationCommand implements Command {
    private LocationDao daoLocation;
    private ObserverDao daoObserver;
    private GameDao dao;
    private String day;
    private LocalDate devolutionDate;
    
    public GameLocationCommand() {
        dao = (GameDao) DaoFactory.createDao(DaoEnum.GAME.getOption());
        daoLocation = (LocationDao) DaoFactory.createDao(DaoEnum.LOCATION.getOption());
        day = this.verifyTypeOfLocation();
        devolutionDate = this.getNumberDayLocation();
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            
            Long idGame = Long.parseLong(request.getParameter("idGame"));
            User user = (User) request.getSession().getAttribute("user");
            Game game = dao.findById(idGame);
            
            if (game.getState().equals(GameState.AVAILABLE)) {
                game.location();
                Location location = new Location();
                location.setIdGame(idGame);
                location.setDateDevolution(devolutionDate);
                location.setIdUser(user.getCpf());
                location.setStrategy(LocationCalcStrategyEnum.valueOf(day));
                daoLocation.save(location);
                request.getSession().setAttribute("success", "Jogo locado com sucesso");
                dao.editState(idGame, GameState.RENT.name());
                return "home.jsp";
            }
            
            daoObserver = (ObserverDao) DaoFactory.createDao(DaoEnum.OBSERVER.getOption());
            daoObserver.addObserver(user.getCpf(), idGame);
            game.addObserver(user);
            Location location = daoLocation.findLocationById(idGame);
            String date = ConvertDate(location.getDateDevolution());
            request.getSession().setAttribute("info", date);
            request.getSession().setAttribute("error", "Jogo já esta alugado");
            return "home.jsp";
            
        } catch (NumberFormatException | SQLException | ClassNotFoundException | GameException e) {
            e.printStackTrace();
            request.getSession().setAttribute("error", "Ocorreu um erro, retorne e tente novamente");
            return "home.jsp";
        } 
    }

    private String verifyTypeOfLocation() {
        DayOfWeek currentdate = LocalDate.now().getDayOfWeek();
        if (currentdate.equals(DayOfWeek.SUNDAY) || currentdate.equals(DayOfWeek.SATURDAY)) {
            return "SPECIAL";
        }
        return "COMUM";
    }

    private LocalDate getNumberDayLocation() {
        if (this.day.equals("SPECIAL")) {
            return LocalDate.now().plusDays(2);
        }
        return LocalDate.now().plusDays(1);

    }

    private String ConvertDate(LocalDate dateDevolution) {
        return dateDevolution.plusDays(1).format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

}
