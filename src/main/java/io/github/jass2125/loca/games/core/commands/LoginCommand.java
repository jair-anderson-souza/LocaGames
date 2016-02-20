/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.jass2125.loca.games.core.commands;

import io.github.jass2125.loca.games.core.business.User;
import io.github.jass2125.loca.games.core.dao.IUserDao;
import io.github.jass2125.loca.games.core.dao.UserDao;
import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Anderson Souza
 * @since 15:33:48, 20-Feb-2016
 */
public class LoginCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            String cpf = request.getParameter("cpf");
            String email = request.getParameter("email");

            IUserDao dao = new UserDao();
            User user = dao.findByCPFAndEmail(cpf, email);

            if (user != null) {
                return user.getRole() + "/home.jsp";
            }else{
                return "error.html";
            }
            
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            e.getMessage();
            e.getCause();
                    
            return "error.html";
        }

    }
}
