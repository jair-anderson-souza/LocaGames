/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.jass2125.loca.games.core.repository;

import io.github.jass2125.loca.games.core.business.User;
import io.github.jass2125.loca.games.observer.Observer;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Anderson Souza
 */
public class ObserverDao implements ObserverRepository<Observer> {

    private String url;

    public ObserverDao() {
        this.url = "jdbc:mysql://localhost:3306/locagames";
    }

    @Override
    public void addObserver(String cpf, Long idGame) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        Connection connection = DriverManager.getConnection(url, "root", "12345");
        String sql = "insert into observers(idUser, idGame) values(?, ?);";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, cpf);
        preparedStatement.setLong(2, idGame);
        preparedStatement.execute();
    }
    
    @Override
    public List<Observer> getListObservers(Long idGame) throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");
        Connection connection = DriverManager.getConnection(url, "root", "12345");
        String sql = "select distinct user.name, user.email, user.cpf from user inner join game inner join observers where user.cpf = observers.idUser and ? = observers.idGame;";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setLong(1, idGame);
        ResultSet rs = preparedStatement.executeQuery();
        List<Observer> listObservers = new ArrayList<>();
        User user = new User();
        while(rs.next()){
            String name = rs.getString("name");
            String email = rs.getString("email");
            String cpf = rs.getString("cpf");
            user = new User(name, cpf, email);
            listObservers.add(user);
        }
        return listObservers;
    }

    @Override
    public void delete(Observer observer) throws SQLException, ClassNotFoundException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
}
