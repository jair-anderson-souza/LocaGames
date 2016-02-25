/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.jass2125.loca.games.core.dao;

import io.github.jass2125.loca.games.core.business.Game;
import io.github.jass2125.loca.games.core.business.Location;
import io.github.jass2125.loca.games.core.factory.IDao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Anderson Souza
 * @since 13:49:16, 24-Feb-2016
 */
public class LocationDao implements IDao {
    private String url;

    public LocationDao() {
        this.url = "jdbc:mysql://localhost:3306/locagames";
    }
    
    public void save(Location location){
        
    }
    
    public List<Location> listLocations() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");
        Connection connection = DriverManager.getConnection(url, "root", "12345");
        String sql = "select * from location;";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ResultSet resulSet = preparedStatement.executeQuery();
        List<Location> listGames = new ArrayList<>();
        Location location = null;
        //Criar implementação
        while (resulSet.next()) {
//            Long idGame = resulSet.getLong("idGame");
//            String name = resulSet.getString("nameGame");
//            String gender = resulSet.getString("gender");
//            String state = resulSet.getString("state");
//            location = new Game(idGame, name, gender, state);
        }
        return listGames;
    }

    public void delete(Game game) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Location findByUserAndGame(Long idGame, Date date) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
