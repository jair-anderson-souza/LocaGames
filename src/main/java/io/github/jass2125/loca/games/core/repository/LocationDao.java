/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.jass2125.loca.games.core.repository;

import io.github.jass2125.loca.games.core.business.Location;
import io.github.jass2125.loca.games.core.factory.GenericRepository;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Anderson Souza
 * @since 13:49:16, 24-Feb-2016
 */

public class LocationDao implements LocationRepository {

    private String url;

    public LocationDao() {
        this.url = "jdbc:mysql://localhost:3306/locagames";
    }

    public void save(Location location) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        Connection connection = DriverManager.getConnection(url, "root", "12345");
        String sql = "insert into location(idUser, idGame, dateLocation, dateDevolution, strategy) values(?, ?, ? ,?, ?);";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, location.getIdUser());
        preparedStatement.setLong(2, location.getIdGame());
        preparedStatement.setString(3, location.getDateLocation().toString());
        preparedStatement.setString(4, location.getDateDevolution().toString());
        preparedStatement.setString(5, location.getStrategy().toString());
        preparedStatement.execute();
    }

    public List<Location> listLocations() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");
        Connection connection = DriverManager.getConnection(url, "root", "12345");
        String sql = "select * from location where location.idGame in();";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ResultSet resulSet = preparedStatement.executeQuery();
        List<Location> listGames = new ArrayList<>();
        Location location = null;
        while (resulSet.next()) {
            Long idLocation = resulSet.getLong("idLocation");
            String idUser = resulSet.getString("idUser");
            Long idGame = resulSet.getLong("idGame");
            LocalDate dateLocation = resulSet.getDate("dateLocation").toLocalDate();
            LocalDate dateDevolution = resulSet.getDate("dateDevolution").toLocalDate();
            Object strategy = resulSet.getObject("strategy");
//            location = new Location(idLocation, idUser, idGame, dateLocation, dateDevolution, (LocationCalcStrategy) strategy);
            listGames.add(location);
        }
        return listGames;
    }

    public Location findLocation(String cpf, Long idGame) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        Connection connection = DriverManager.getConnection(url, "root", "12345");
        String sql = "select * from location inner join game where location.idUser = ? and game.idGame = ? and location.idGame = game.idGame and game.state = 'RENT';";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, cpf);
        preparedStatement.setLong(2, idGame);
        ResultSet rs = preparedStatement.executeQuery();

        if (rs.next()) {
            Long idLocation = rs.getLong("idLocation");
            String idUser = rs.getString("idUser");
//            String idGame = rs.getString("idGame");
            LocalDate dateLocation = rs.getDate("dateLocation").toLocalDate();
            LocalDate dateDevolution = rs.getDate("dateDevolution").toLocalDate();
            String strategy = rs.getString("strategy");
            return new Location(idLocation, idUser, idGame, dateLocation, dateDevolution, strategy);
        }
        return null;

    }

    public Location findLocationById(Long idGame) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        Connection connection = DriverManager.getConnection(url, "root", "12345");
        String sql = "select * from location inner join game where game.state = 'RENT' and location.idGame = ?;";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setLong(1, idGame);
        ResultSet rs = preparedStatement.executeQuery();

        if (rs.next()) {
            Long idLocation = rs.getLong("idLocation");
            String idUser = rs.getString("idUser");
//            String idGame = rs.getString("idGame");
            LocalDate dateLocation = rs.getDate("dateLocation").toLocalDate();
            LocalDate dateDevolution = rs.getDate("dateDevolution").toLocalDate();
            String strategy = rs.getString("strategy");
            return new Location(idLocation, idUser, idGame, dateLocation, dateDevolution, strategy);
        }
        return null;

    }

}
