/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.jass2125.locagames.core.fabricas;

import io.github.jass2125.locagames.core.enums.ExcecoesEnum;
import io.github.jass2125.locagames.core.utilitarios.PropriedadesUtil;
import io.github.jass2125.locagames.core.excecoes.ArquivoNaoEncontradoException;
import io.github.jass2125.locagames.core.excecoes.ConexaoException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 *
 * @author Anderson Souza
 */
public class FabricaDeConexoes {

    private String url = "jdbc:postgresql://localhost:5432/locagames";
    private String usuario = "postgres";
    private String senha = "123";
    private String driver = "org.postgresql.Driver";
    private Properties properties;

    public FabricaDeConexoes() {
    }

    public FabricaDeConexoes setDriver(String driver) {
        this.driver = driver;
        return this;
    }

    public FabricaDeConexoes setSenha(String senha) {
        this.senha = senha;
        return this;
    }

    public FabricaDeConexoes setUrl(String url) {
        this.url = url;
        return this;
    }

    public FabricaDeConexoes setUsuario(String usuario) {
        this.usuario = usuario;
        return this;
    }

    private FabricaDeConexoes inicializaParametrosDoBanco() {
        this.url = "jdbc:postgresql://localhost:5432/locagames";
        this.usuario = "postgres";
        this.senha = "123";
        this.driver = "org.postgresql.Driver";
        return this;
    }

    /**
     * Método que realiza a conexão com o banco de dados
     *
     * @return Um objeto {@link java.sql.Connection}
     */
    public Connection getConexao() {
        try {
            PropriedadesUtil util = new PropriedadesUtil();
            properties = util.carregarArquivoDePropriedade();

            return this.setDriver(properties.getProperty("driver"))
                    .setUrl(properties.getProperty("url"))
                    .setUsuario(properties.getProperty("usuario"))
                    .setSenha(properties.getProperty("senha"))
                    .estabeleConexao();

        } catch (ArquivoNaoEncontradoException e) {
            inicializaParametrosDoBanco();
            return estabeleConexao();
        }
    }

    private Connection estabeleConexao() {
        try {
            Class.forName(driver);
            Connection connection = DriverManager.getConnection(url, usuario, senha);
            return connection;
        } catch (SQLException | ClassNotFoundException r) {
            throw new ConexaoException(ExcecoesEnum.ERRO_NA_CONEXAO)
                    .putMap("Mensagem de Erro", "Não foi possivel realizar uma conexão com o banco de dados, "
                            + "verifique os parametros de conexão e tente novamente");
        }
    }

}
