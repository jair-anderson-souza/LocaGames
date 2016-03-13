/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package io.github.jass2125.loca.games.core.factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 *
 * @author Anderson Souza
 */
public class ConnectionFactory {
    private Connection connection;
    private String url;
    private Properties propertie;
    private String classDriver;
            
    public ConnectionFactory() {
        this.url = "jdbc:mysql://localhost:3306/locagames";
        propertie = new Properties();
        propertie.setProperty("user", "root");
        propertie.setProperty("password", "12345");
        this.classDriver = "com.mysql.jdbc.Driver";
    }
    
    
    public Connection getConnection() throws SQLException, ClassNotFoundException{
        Class.forName(classDriver);
        return DriverManager.getConnection(url, propertie);
    }
    
    
    

}
