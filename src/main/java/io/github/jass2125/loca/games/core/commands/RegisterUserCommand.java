/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.jass2125.loca.games.core.commands;

import io.github.jass2125.loca.games.core.business.User;
import io.github.jass2125.loca.games.core.dao.UserDao;
import io.github.jass2125.loca.games.core.factory.DaoFactory;
import io.github.jass2125.loca.games.core.util.DaoEnum;
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

            UserDao dao = (UserDao) DaoFactory.createDao(DaoEnum.USER.getOption());
            User user = new User(name, cpf, email);
            dao.persist(user);
            request.getSession().setAttribute("user", user);
            return "index.jsp";
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}

//create table user(
//    name varchar(50) not null,
//    email varchar(50) not null,
//    cpf varchar(15) not null,
//    primary key(cpf)
//);
//create table game(
//    idGame bigint AUTO_INCREMENT,
//    nameGame varchar(50) not null,
//    gender varchar(30) not null,
//    state varchar(40) not null,   
//    primary key(idGame)
//);
//
//create table location(
//    idLocation bigint AUTO_INCREMENT,
//    idUser varchar(50),
//    idGame bigint,
//    dateLocation date not null, 
//    dateDevolution date not null,
//    strategy varchar(30) not null,
//    foreign key(idGame) references game(idGame) on update cascade on delete restrict,
//    foreign key(idUser) references user(cpf) on update cascade on delete restrict,
//    primary key(idLocation)
//);
//insert into user values('Anderson Souza', 'jair_anderson_bs@hotmail.com','98765445678');
//insert into user values('Diogo Moreira', 'diogomoreira@hotmail.com','765678987');
//insert into user values('Dijalma', 'dijalma@hotmail.com','9658');
//insert into game values(1, 'Donkey Kong 3','Aventura', 'AVAILABLE');
//insert into game values(2, 'Resident Evil','Suspense', 'AVAILABLE');
//insert into game values(3, 'Mario','Aventura', 'available');
//insert into location values(1, '9658',1, '2016/10/10', '11/10/2016', 'COMUM');
//insert into location values(2, '98765445678', 2, '24/02/16', '25/02/16', 'SPECIAL');
//insert into location values(3, '765678987',3, '20/02/16', '21/02/16', 'COMUM');
//
//
