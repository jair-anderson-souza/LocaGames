/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.jass2125.loca.games.core.commands;

import io.github.jass2125.loca.games.core.business.Game;
import io.github.jass2125.loca.games.core.business.User;
import io.github.jass2125.loca.games.core.dao.GameDao;
import io.github.jass2125.loca.games.core.dao.UserDao;
import io.github.jass2125.loca.games.core.factory.DaoFactory;
import io.github.jass2125.loca.games.core.util.DaoEnum;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Anderson Souza
 */
public class LoginUserCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            String cpf = request.getParameter("cpf");
            String email = request.getParameter("email");
            UserDao dao = (UserDao) DaoFactory.createDao(DaoEnum.USER.getOption());
            User user = dao.findByCPFAndEmail(cpf, email);
            GameDao daoGame = (GameDao) DaoFactory.createDao(DaoEnum.GAME.getOption());
            
            
            if(user != null){
                List<Game> listGames = daoGame.listGamesLocatedByUser(cpf);
                request.getSession().setAttribute("sucess", "Autenticação feita com sucesso");
                request.getSession().setAttribute("user", user);
                request.getSession().setAttribute("listGames", listGames);
                return "home.jsp";
            }else{
                request.getSession().setAttribute("error", "Erro na autenticação");
                return "home.jsp";
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            request.getSession().setAttribute("error", "Ocorreu um erro, retorne e tente novamente");
            return "home.jsp";
        }
        
    }

}
