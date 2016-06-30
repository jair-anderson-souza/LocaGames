/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.jass2125.loca.games.core.repository;

import io.github.jass2125.loca.games.core.business.Cliente;
import io.github.jass2125.loca.games.core.factory.ConnectionFactory;
import io.github.jass2125.loca.games.observer.Observer;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Anderson Souza
 */
public class ObserverDao implements ObserverRepository<Observer> {

    private ConnectionFactory factoryConnect;

    public ObserverDao() {
        factoryConnect = new ConnectionFactory();
    }

    @Override
    public void addObserver(String cpf, Long idGame) throws ClassNotFoundException, SQLException {
        Connection connection = factoryConnect.getConnection();
        String sql = "insert into observers(idUser, idGame) values(?, ?);";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, cpf);
        preparedStatement.setLong(2, idGame);
        preparedStatement.execute();
        preparedStatement.close();
        connection.close();
    }

    @Override
    public Set<Observer> getListObservers(Long idGame) throws SQLException, ClassNotFoundException {
        Connection connection = factoryConnect.getConnection();
        String sql = "select distinct user.name, user.email, user.cpf from user inner join game inner join observers where user.cpf = observers.idUser and ? = observers.idGame;";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setLong(1, idGame);
        ResultSet rs = preparedStatement.executeQuery();
        Set<Observer> listObservers = new HashSet<>();
        Cliente user = null;
        while (rs.next()) {
            user = new Cliente();
            String name = rs.getString("name");
            String email = rs.getString("email");
            String cpf = rs.getString("cpf");
            user = new Cliente(name, cpf, email);
            listObservers.add(user);
        }
        rs.close();
        preparedStatement.close();
        connection.close();
        return listObservers;
    }

    @Override
    public void delete(Long idGame) throws SQLException, ClassNotFoundException {
        Connection connection = factoryConnect.getConnection();
        String sql = "delete from observers where idGame = ?;";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setLong(1, idGame);
        preparedStatement.execute();
        preparedStatement.close();
        connection.close();
    }

}
