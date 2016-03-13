/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.jass2125.loca.games.core.repository;

import io.github.jass2125.loca.games.core.business.User;
import io.github.jass2125.loca.games.core.factory.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Anderson Souza
 * @since 14:23:27, 20-Feb-2016
 */
public class UserDao implements UserRepository {
    private ConnectionFactory factoryConnect;

    public UserDao() {
        factoryConnect = new ConnectionFactory();
    }

    @Override
    public void save(User user) throws ClassNotFoundException, SQLException {
        Connection connection = factoryConnect.getConnection();
        String sql = "insert into user(name, cpf, email) values(?, ?, ?);";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, user.getName());
        preparedStatement.setString(2, user.getCpf());
        preparedStatement.setString(3, user.getEmail());
        preparedStatement.execute();
        preparedStatement.close();
        connection.close();
    }

    public List<User> search(String cpf) throws SQLException, ClassNotFoundException {
        Connection connection = factoryConnect.getConnection();
        String sql = "select * from user where idUser = ?;";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, cpf);
        ResultSet rs = preparedStatement.executeQuery();
        List<User> listObservers = new ArrayList<>();
        User user = new User();
        while (rs.next()) {
            String name = rs.getString("name");
            String email = rs.getString("email");
            String cpf2 = rs.getString("cpf");
            user = new User(name, cpf, email);
            listObservers.add(user);
        }
        rs.close();
        preparedStatement.close();
        connection.close();
        return listObservers;
    }

    public User findByCPFAndEmail(String cpf, String email) throws SQLException, ClassNotFoundException {
        Connection connection = factoryConnect.getConnection();
        String sql = "select * from user where binary cpf = ? and binary email = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, cpf);
        preparedStatement.setString(2, email);
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            String name = resultSet.getString("name");
            return new User(name, cpf, email);
        }
        resultSet.close();
        preparedStatement.close();
        connection.close();
        return null;
    }

}
