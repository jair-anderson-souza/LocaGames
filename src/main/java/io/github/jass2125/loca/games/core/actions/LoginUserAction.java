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
import io.github.jass2125.loca.games.core.repository.UserDao;
import io.github.jass2125.loca.games.core.repository.UserRepository;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Anderson Souza
 */
public class LoginUserAction implements Action {

    private UserRepository daoUser;
    private GameRepository daoGame;

    public LoginUserAction() {
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            String cpf = request.getParameter("cpf");
            String email = request.getParameter("email");
            User user = this.findUserByCredentials(cpf, email);

            if (user != null) {
                request.getSession().setAttribute("sucess", "Autenticação feita com sucesso");
                request.getSession().setAttribute("user", user);
                return "funcionario/home.jsp";
            }

            request.getSession().setAttribute("error", "Erro na autenticação");
            return "home.jsp";

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            request.getSession().setAttribute("error", "Ocorreu um erro, retorne e tente novamente");
            return "home.jsp";
        }

    }

    private User findUserByCredentials(String cpf, String email) throws SQLException, ClassNotFoundException {
        daoUser = new UserDao();
        User user = daoUser.findByCPFAndEmail(cpf, email);
        return user;
    }

    private List<Game> findGamesLocatedByUser(String cpf) throws SQLException, ClassNotFoundException {
        daoGame = new GameDao();
        List<Game> listGames = daoGame.listGamesLocatedByUser(cpf);
        return listGames;
    }

}
