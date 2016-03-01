/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.jass2125.loca.games.core.dao;

import io.github.jass2125.loca.games.core.business.User;
import io.github.jass2125.loca.games.core.factory.IDao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @author Anderson Souza
 * @since 14:23:27, 20-Feb-2016
 */
public class UserDao implements IDao {

    private String url;
    private Properties propertie = new Properties();

    public UserDao() {
        this.url = "jdbc:mysql://localhost:3306/locagames";
        propertie.setProperty("user", "root");
        propertie.setProperty("passord", "12345");
    }

    public User findByCPFAndEmail(String cpf, String email) throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");
        Connection connection = DriverManager.getConnection(url, "root", "12345");
        String sql = "select * from user where binary cpf = ? and binary email = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, cpf);
        preparedStatement.setString(2, email);
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            String name = resultSet.getString("name");
            return new User(name, cpf, email);
        }
        return null;
    }

    public void persist(User user) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        Connection connection = DriverManager.getConnection(url, "root", "12345");
        String sql = "insert into user(name, cpf, email) values(?, ?, ?);";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, user.getName());
        preparedStatement.setString(2, user.getCpf());
        preparedStatement.setString(3, user.getEmail());
        preparedStatement.execute();
    }

}
