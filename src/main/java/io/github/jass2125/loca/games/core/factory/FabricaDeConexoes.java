/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.jass2125.loca.games.core.factory;

import io.github.jass2125.loca.games.exceptions.ArquivoNaoEncontradoException;
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
public class FabricaDeConexoes {
    private final String url = "jdbc:postgresql://localhost:5432/locagames";
    private final String usuario = "postgres";
    private final String senha = "123";
    private final String driver = "org.postgresql.Driver";

    public FabricaDeConexoes() {
    }

    /**
     *
     * @return @throws java.sql.SQLException
     * @throws java.lang.ClassNotFoundException
     */
    public Connection getConexao() throws SQLException, ClassNotFoundException {
        try {
            Properties properties = carregarArquivoDePropriedade();
            Class.forName(properties.getProperty("driver"));
            return DriverManager.getConnection(properties.getProperty("url"), properties);
        } catch (ArquivoNaoEncontradoException e) {
            Class.forName(driver);
            return DriverManager.getConnection(url, usuario, senha);
        }
    }

    private Properties carregarArquivoDePropriedade() throws ArquivoNaoEncontradoException {
        try {
            InputStream stream = getClass().getResourceAsStream("/bd.properties");
            Properties p = new Properties();
            p.load(stream);
            return p;
        } catch (IOException e) {
            throw new ArquivoNaoEncontradoException("Arquivo de propriedades com configurações do banco não foi encontrado!!", e);
        }
    }
}
