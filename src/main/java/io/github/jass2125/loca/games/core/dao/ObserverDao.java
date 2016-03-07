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
import java.sql.SQLException;

/**
 *
 * @author Anderson Souza
 */
public class ObserverDao implements IDao {

    private String url;

    public ObserverDao() {
        this.url = "jdbc:mysql://localhost:3306/locagames";
    }

    public void addObserver(String cpf, Long idGame) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        Connection connection = DriverManager.getConnection(url, "root", "12345");
        String sql = "insert into observers(idUser, idGame) values(?, ?);";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, cpf);
        preparedStatement.setLong(2, idGame);
        preparedStatement.execute();
    }
}
