/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.jass2125.loca.games.core.actions;

import io.github.jass2125.loca.games.core.business.Game;
import io.github.jass2125.loca.games.core.business.Location;
import io.github.jass2125.loca.games.core.business.User;
import io.github.jass2125.loca.games.core.repository.GameDao;
import io.github.jass2125.loca.games.core.repository.GameRepository;
import io.github.jass2125.loca.games.core.repository.LocationDao;
import io.github.jass2125.loca.games.core.repository.ObserverDao;
import io.github.jass2125.loca.games.core.repository.ObserverRepository;
import io.github.jass2125.loca.games.observer.Observer;
import io.github.jass2125.loca.games.state.GameState;
import io.github.jass2125.loca.games.strategy.LocationCalcStrategy;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.mail.EmailException;

/**
 *
 * @author Anderson Souza
 */
public class GameDevolutionAction implements Action {

    private LocationDao daoLocation;
    private LocationCalcStrategy strategy;
    private GameRepository dao;
    private ObserverRepository daoObserver;
    private LocationCalcStrategy strategyCalc;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        try {
            Long idGame = Long.parseLong(request.getParameter("idGame"));
            String cpf = ((User) request.getSession().getAttribute("user")).getCpf();
            Game game = this.getGameLocated(idGame);

            if (game != null) {
                Location location = this.getLocation(cpf, idGame);
                request.getSession().setAttribute("success", "Jogo devolvido com sucesso");
                BigDecimal price = this.getPriceLocation(location);
                request.getSession().setAttribute("price", price);
                this.editGameState(idGame, GameState.AVAILABLE.name());
                this.addObservers(game, idGame);
                return "funcionario/home.jsp";
            }
        
            request.getSession().setAttribute("error", "Você não alugou este jogo!");
            return "funcionario/home.jsp";
        
        } catch (SQLException | ClassNotFoundException | EmailException e) {
            e.printStackTrace();
            request.getSession().setAttribute("error", "Erro, retorne e tente novamente");
            return "funcionario/home.jsp";
        }
    }

    private Game getGameLocated(Long idGame) throws SQLException, ClassNotFoundException {
        dao = new GameDao();
        Game game = dao.findById(idGame);
        return (game.getState().equals(GameState.RENT) ? game : null);
    }

    private void addObservers(Game game, Long idGame) throws SQLException, ClassNotFoundException, EmailException {
        daoObserver = new ObserverDao();
        List<Observer> listObservers = daoObserver.getListObservers(idGame);
        game.setListObservers(listObservers);
        game.notifyObservers();
    }

    private void editGameState(Long idGame, String state) throws ClassNotFoundException, SQLException {
        dao = new GameDao();
        dao.editState(idGame, GameState.AVAILABLE.name());
    }

    private BigDecimal getPriceLocation(Location location) {
        return location.calculateValueLocation();
    }

    private Location getLocation(String cpf, Long idGame) throws ClassNotFoundException, SQLException {
        daoLocation = new LocationDao();
        return daoLocation.findLocation(cpf, idGame);
    }
}
