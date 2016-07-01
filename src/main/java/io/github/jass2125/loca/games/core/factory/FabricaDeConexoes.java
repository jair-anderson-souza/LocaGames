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
     * Método que realiza a conexão com o banco de dados
     * @return Um objeto {@link java.sql.Connection}
     * @throws java.sql.SQLException Exceção lançada se ocorrer algum erro ao acessar o banco de dados ou a url é nula
     * @throws java.lang.ClassNotFoundException Exceção lançada se a classe não pode ser localizada
     * <br>Ver - {@link io.github.jass2125.loca.games.core.factory.FabricaDeConexoes#carregarArquivoDePropriedade() }
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

    /**
     * Método que carrega o arquivo de propriedades com as informações d banco
     * de dados
     *
     * @return Um {@link java.util.Properties} com as informações da conexão
     * @throws ArquivoNaoEncontradoException Exceção lançada quando o arquivo
     * não puder ser encontrado
     */
    private Properties carregarArquivoDePropriedade() throws ArquivoNaoEncontradoException {
        try {
            InputStream stream = getClass().getResourceAsStream("/bd.properties");
            Properties properties = new Properties();
            properties.load(stream);
            return properties;
        } catch (IOException e) {
            throw new ArquivoNaoEncontradoException("Arquivo de propriedades com configurações do banco não foi encontrado!!", e);
        }
    }
}
