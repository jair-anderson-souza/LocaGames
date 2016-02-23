/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.jass2125.loca.games.core.commands;

import io.github.jass2125.loca.games.core.business.User;
import io.github.jass2125.loca.games.core.dao.IUserDao;
import io.github.jass2125.loca.games.core.util.Factory;
import io.github.jass2125.loca.games.core.util.FactoryDao;
import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Anderson Souza
 * @since 15:33:48, 20-Feb-2016
 */
public class RegisterUserCommand implements Command {

    /**
     *
     * @param request
     * @param response
     * @return
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            String name = request.getParameter("name");
            String cpf = request.getParameter("cpf");
            String email = request.getParameter("email");

            Factory factory = new FactoryDao();
            IUserDao dao = factory.createDao();
            User user = new User(name, cpf, email);
            dao.persist(user);
            return "employee/home.jsp";
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            request.getSession().setAttribute("error", "Email e/ou CPF ja existentes, por favor tente novamente.");
            return "index.jsp";
        }
    }
}

//create table user(
//    name varchar(50),
//    email varchar(50),
//    cpf varchar(15),
//    primary key(cpf, email)
//);
