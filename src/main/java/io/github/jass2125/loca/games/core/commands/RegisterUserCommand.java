/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.jass2125.loca.games.core.commands;

import io.github.jass2125.loca.games.core.business.User;
import io.github.jass2125.loca.games.core.dao.IUserDao;
import io.github.jass2125.loca.games.core.dao.UserDao;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Anderson Souza
 * @since 15:33:48, 20-Feb-2016
 */
public class RegisterUserCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String name = request.getParameter("name");
        String cpf = request.getParameter("cpf");
        String email = request.getParameter("email");

//        User user = new User(name, cpf, email);
        IUserDao dao = new UserDao();
//        dao.persist(user);
        
        return null;

    }

    
    
}
//
//create table user(    
//name
//)
//
