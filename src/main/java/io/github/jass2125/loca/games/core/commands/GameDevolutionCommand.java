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
import java.math.BigDecimal;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JOptionPane;

/**
 *
 * @author Anderson Souza
 */
public class GameDevolutionCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            Long idGame = Long.parseLong(request.getParameter("idGame"));
            GameDao dao = (GameDao) DaoFactory.createDao(DaoEnum.GAME.getOption());
            Game game = dao.findById(idGame);
            String state = game.getState();
            User user = (User) request.getSession().getAttribute("user");

            if (game.getState().equals("RENT")) {
                game.setState("AVAILABLE");
//                LocalDate devolution = getCurrentDay();

                LocationDao daoLocation = (LocationDao) DaoFactory.createDao(DaoEnum.LOCATION.getOption());
                Location location = daoLocation.findLocation(user.getCpf(), idGame);
//             //Strategy de calculo de aluguel 
                BigDecimal price = this.calculatePrice(location);
                JOptionPane.showMessageDialog(null, price);
                return "home.jsp";

            } else {
                game.setState("AVAILABLE");

            }

            return "dds";
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

//    private LocalDate getCurrentDay() {
//        return LocalDate.now();
//
//    }
    private BigDecimal calculatePrice(Location location) {
        String typeLocation = location.getStrategy();
        LocalDate dev = location.getDateDevolution();
        LocalDate al = location.getDateLocation();

        if (typeLocation.equals("COMUM")) {
            long daysLate = ChronoUnit.DAYS.between(location.getDateLocation().plusDays(1), location.getDateDevolution());
            if (daysLate != 0) {
                return new BigDecimal(daysLate * 3 + 1);
            }
            return new BigDecimal(3);
        } else {
            long daysLate = ChronoUnit.DAYS.between(location.getDateLocation().plusDays(1), location.getDateLocation());
            if(daysLate != 0){
                return new BigDecimal(daysLate * 3 + 3);
            }
            return new BigDecimal(5);
        }

    }

}
