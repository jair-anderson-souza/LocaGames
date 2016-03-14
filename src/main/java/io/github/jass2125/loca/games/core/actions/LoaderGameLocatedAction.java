/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.jass2125.loca.games.core.actions;

import io.github.jass2125.loca.games.core.business.Game;
import io.github.jass2125.loca.games.core.business.User;
import io.github.jass2125.loca.games.core.repository.GameDao;
import io.github.jass2125.loca.games.core.repository.GameRepository;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Anderson Souza
 * @version 1.0
 */
public class LoaderGameLocatedAction implements Action {

    private GameRepository dao;

    public LoaderGameLocatedAction() {
        dao = new GameDao();
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            User user = this.getSessionUser(request);
            
            if (user != null) {
                String cpf = user.getCpf();
                List<Game> listGames = dao.listGamesLocatedByUser(cpf);
                request.getSession().setAttribute("listLocations", listGames);
                return "funcionario/devolver.jsp";
            }
            request.getSession().setAttribute("error", "Faça o login/cadastro!");
            return "home.jsp";
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return "home.jsp";
        }

    }

    public User getSessionUser(HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        return user;
    }

    public List<Game> getListGamesAvailables(String cpf) throws ClassNotFoundException, SQLException {
        List<Game> listGames = new ArrayList<>();
        listGames = dao.listGamesLocatedByUser(cpf);
        return listGames;
    }
}
