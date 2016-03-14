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
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.mail.EmailException;

/**
 * @author Anderson Souza
 * @version 1.0
 */
public class GameDevolutionAction implements Action {

    private LocationDao daoLocation;
    private LocationCalcStrategy strategy;
    private GameRepository dao;
    private ObserverRepository daoObserver;
    private LocationCalcStrategy strategyCalc;
    
    /**
     * Executa a ação geral de devolver game
     * @param request Requisiçao do cliente
     * @param response Reposta do cliente
     * @return URL da pagina de resposta
     */
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
                game.setListObservers(loadObservers(idGame));
                game.notifyObservers();
                removeObservers(idGame);

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
    
    /**
     * Recupera o game locado
     * @param idGame Id do game que está sendo pesquisado
     * @return Game Game pra ser devolvido
     * @throws SQLException Retorna caso ele não consiga recuperar essa informação
     * @throws ClassNotFoundException Classe do Driver MySQL não está disponivel
     */
    private Game getGameLocated(Long idGame) throws SQLException, ClassNotFoundException {
        dao = new GameDao();
        Game game = dao.findById(idGame);
        return (game.getState().equals(GameState.RENT) ? game : null);
    }
    
    /**
     * Deleta o observadores de um game
     * @param idGame Id do game que está sendo pesquisado
     * @throws SQLException Retorna caso ele não consiga recuperar essa informação
     * @throws ClassNotFoundException Classe do Driver MySQL não está disponivel
     */
    public void removeObservers(Long idGame) throws SQLException, ClassNotFoundException {
        daoObserver = new ObserverDao();
        daoObserver.delete(idGame);
    }   
    
    /**
     * Pesquisa os observadores de um game
     * @param idGame Id do Game que está pesquisado
     * @return Set<Game> Set com todos os observadores de um game
     * @throws SQLException Retorna caso ele não consiga recuperar essa informação
     * @throws ClassNotFoundException Classe do Driver MySQL não está disponivel
     */
    private Set<Observer> loadObservers(Long idGame) throws SQLException, ClassNotFoundException, EmailException {
        daoObserver = new ObserverDao();
        return daoObserver.getListObservers(idGame);
    }
    
    /**
     * Edita o estado do Game
     * @param idGame Id do game que será editado 
     * @param state Estado do Game
     * @throws SQLException Retorna caso ele não consiga recuperar essa informação
     * @throws ClassNotFoundException Classe do Driver MySQL não está disponivel
     */
    private void editGameState(Long idGame, String state) throws ClassNotFoundException, SQLException {
        dao = new GameDao();
        dao.editState(idGame, GameState.AVAILABLE.name());
    }
    
    /**
     * Retorna o preço do aluguel
     * @param location Locaçao que esta sendo devolvida
     * @return BigDecimal Valor da locaçao
     */
    private BigDecimal getPriceLocation(Location location) {
        return location.calculateValueLocation();
    }
    
    /**
     * Recupera a locaçao
     * @param cpf CPF do cliente
     * @param idGame Id do game
     * @return Location Locaçao
     * @throws SQLException Retorna caso ele não consiga recuperar essa informação
     * @throws ClassNotFoundException Classe do Driver MySQL não está disponivel
     */
    private Location getLocation(String cpf, Long idGame) throws ClassNotFoundException, SQLException {
        daoLocation = new LocationDao();
        return daoLocation.findLocation(cpf, idGame);
    }
}
