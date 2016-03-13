/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.jass2125.loca.games.core.repository;

import io.github.jass2125.loca.games.core.business.Game;
import io.github.jass2125.loca.games.core.factory.ConnectionFactory;
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
public class GameDao implements GameRepository<Game> {
    private ConnectionFactory factoryConnect;

    public GameDao() {
        factoryConnect = new ConnectionFactory();
    }

    @Override
    public List<Game> listGames() throws SQLException, ClassNotFoundException {
        Connection connection = factoryConnect.getConnection();
        String sql = "select * from game;";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ResultSet resulSet = preparedStatement.executeQuery();
        List<Game> listGamers = new ArrayList<>();
        Game game = null;
        while (resulSet.next()) {
            Long idGame = resulSet.getLong("idGame");
            String name = resulSet.getString("nameGame");
            String gender = resulSet.getString("gender");
            String state = resulSet.getString("state");
            game = new Game(idGame, name, gender, state);
            listGamers.add(game);
        }
        resulSet.close();
        preparedStatement.close();
        connection.close();
        return listGamers;
    }
    @Override
    public Game findById(Long idGame) throws SQLException, ClassNotFoundException {
        Connection connection = factoryConnect.getConnection();
        String sql = "select * from game where idGame = ?;";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setLong(1, idGame);
        ResultSet rs = ps.executeQuery();
        Game game = null;
        if (rs.next()) {
            String nameGame = rs.getString("nameGame");
            String gender = rs.getString("gender");
            String state = rs.getString("state");
            return game = new Game(idGame, nameGame, gender, state);
        }
        rs.close();
        ps.close();
        connection.close();
        return null;
    }
    
    @Override
    public void editState(Long idGame, String state) throws ClassNotFoundException, SQLException {
        Connection connection = factoryConnect.getConnection();
        String sql = "update game set state = ? where idGame = ?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, state);
        ps.setLong(2, idGame);
        ps.execute();
        ps.close();
        connection.close();
    }
    public List<Game> listGamesLocated() throws ClassNotFoundException, SQLException {
        Connection connection = factoryConnect.getConnection();
        String sql = "select * from game inner join location where location.idGame = game.idGame and game.state = 'RENT'";
        PreparedStatement ps = connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        List<Game> listGames = new ArrayList<>();
        Game game = null;
        while(rs.next()){
            Long idGame = rs.getLong("idGame");
            String nameGame = rs.getString("nameGame");
            String gender = rs.getString("gender");
            String state = rs.getString("state");
            game = new Game(idGame, nameGame, gender, state);
            
            listGames.add(game);
        }
        rs.close();
        ps.close();
        connection.close();
        return listGames;
    }
    @Override
    public List<Game> listGamesLocatedByUser(String cpf) throws ClassNotFoundException, SQLException {
        Connection connection = factoryConnect.getConnection();
        String sql = "select distinct g.idGame, g.nameGame, g.gender, g.state from game as g inner join location as l on g.state = 'RENT' and l.idUser = ? and g.idGame = l.idGame;";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, cpf);
        ResultSet rs = ps.executeQuery();
        List<Game> listGames = new ArrayList<>();
        Game game = null;
        while (rs.next()) {
            Long idGame = rs.getLong("idGame");
            String nameGame = rs.getString("nameGame");
            String gender = rs.getString("gender");
            String state = rs.getString("state");
            game = new Game(idGame, nameGame, gender, state);
            listGames.add(game);
        }
        rs.close();
        ps.close();
        connection.close();
        return listGames;
    }

       
}

   

