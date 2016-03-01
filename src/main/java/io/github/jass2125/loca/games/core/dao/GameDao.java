/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.jass2125.loca.games.core.dao;

import io.github.jass2125.loca.games.core.business.Game;
import io.github.jass2125.loca.games.core.factory.IDao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Anderson Souza
 * @since 21:17:49, 23-Feb-2016
 */
public class GameDao implements IDao {

    private String url;

    public GameDao() {
        this.url = "jdbc:mysql://localhost:3306/locagames";
    }

    public List<Game> listGames() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");
        Connection connection = DriverManager.getConnection(url, "root", "12345");
        String sql = "select * from game;";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ResultSet resulSet = preparedStatement.executeQuery();
        List<Game> listGamers = new ArrayList<>();
        Game game = null;
        while (resulSet.next()) {
            Long idGame = resulSet.getLong("idGame");
            String name = resulSet.getString("nameGame");
            String gender = resulSet.getString("gender");
            String situation = resulSet.getString("situation");
            game = new Game(idGame, name, gender, situation);
            listGamers.add(game);
        }
        return listGamers;
    }

    public Game findById(Long idGame) throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");
        Connection connection = DriverManager.getConnection(url, "root", "12345");
        String sql = "select * from game where idGame = ?;";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setLong(1, idGame);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            String nameGame = rs.getString("nameGame");
            String gender = rs.getString("gender");
            String situation = rs.getString("situation");
            return new Game(idGame, nameGame, gender, situation);
        }
        return null;
    }
}
