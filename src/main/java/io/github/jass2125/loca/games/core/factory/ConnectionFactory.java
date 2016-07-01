/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.jass2125.loca.games.core.factory;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 *
 * @author Anderson Souza
 */
public class ConnectionFactory {

    public ConnectionFactory() {
    }

    public Connection getConnection() throws SQLException, ClassNotFoundException {
        Properties properties = loadPropertieFile();
        Class.forName(properties.getProperty("driver"));
        return DriverManager.getConnection(properties.getProperty("url"), loadPropertieFile());
    }

    private Properties loadPropertieFile() {
        try {
            InputStream stream = getClass().getResourceAsStream("/bd.properties");
            Properties p = new Properties();
            p.load(stream);
            return p;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static class Conexao {

    }
}
