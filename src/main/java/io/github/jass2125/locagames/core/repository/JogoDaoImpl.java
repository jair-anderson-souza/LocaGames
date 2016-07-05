/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.jass2125.locagames.core.repository;

import io.github.jass2125.locagames.core.enums.ExcecoesEnum;
import io.github.jass2125.locagames.core.negocio.Jogo;
import io.github.jass2125.locagames.core.fabricas.FabricaDeConexoes;
import io.github.jass2125.locagames.core.excecoes.PersistenciaException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Anderson Souza
 * @since 21:17:49, 23-Feb-2016
 */
public class JogoDaoImpl implements JogoDao {

    private FabricaDeConexoes fabricaConexao;

    public JogoDaoImpl() {
        fabricaConexao = new FabricaDeConexoes();
    }

    /**
     * Funcionando
     *
     * @return
     */
    @Override
    public List<Jogo> listaDeJogos() {
        List<Jogo> listaDeJogos;
        try (Connection connection = fabricaConexao.getConexao()) {
            String sql = "select * from jogo;";

            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                try (ResultSet resulSet = preparedStatement.executeQuery()) {
                    listaDeJogos = new ArrayList<>();
                    Jogo jogo;
                    while (resulSet.next()) {
                        Long idDoJogo = resulSet.getLong("idDoJogo");
                        String nomeDoJogo = resulSet.getString("nomeDoJogo");
                        String genero = resulSet.getString("genero");
                        String estado = resulSet.getString("estado");
                        jogo = new Jogo(idDoJogo, nomeDoJogo, genero, estado);
                        listaDeJogos.add(jogo);
                    }
                }
            }
        } catch (SQLException e) {
            throw new PersistenciaException(ExcecoesEnum.ERRO_NA_CONSULTA)
                    //aqui pode ficar o log, sendo uma atributo na exceção
                    .inserirMensagemDeErro("Mensagem de Erro: ", "Não foi possível realizar uma consulta no seu banco de dados.");
        }
        return listaDeJogos;

    }

    /**
     * Funcionando
     *
     * @param idDoJogo
     * @return
     * @throws PersistenciaException
     */
    @Override
    public Jogo buscarPorId(Long idDoJogo) throws PersistenciaException {
        try (Connection connection = fabricaConexao.getConexao()) {
            String sql = "select * from jogo where idDoJogo = ?;";
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setLong(1, idDoJogo);
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        String nomeDoJogo = rs.getString("nomeDoJogo");
                        String genero = rs.getString("genero");
                        String estado = rs.getString("estado");
                        return new Jogo(idDoJogo, nomeDoJogo, genero, estado);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new PersistenciaException();
        }
        return null;
    }

    @Override
    public void editarEstado(Long idDoJogo, String estado) throws PersistenciaException {
        try (Connection connection = fabricaConexao.getConexao()) {
            String sql = "update jogo set estado = ? where idDoJogo = ?";
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setString(1, estado);
                ps.setLong(2, idDoJogo);
                ps.execute();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new PersistenciaException();
        }
    }

    public List<Jogo> listGamesLocated() throws ClassNotFoundException, SQLException {
        Connection connection = fabricaConexao.getConexao();
        String sql = "select * from game inner join location where location.idGame = game.idGame and game.state = 'RENT'";
        PreparedStatement ps = connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        List<Jogo> listGames = new ArrayList<>();
        Jogo game = null;
        while (rs.next()) {
            Long idGame = rs.getLong("idGame");
            String nameGame = rs.getString("nameGame");
            String gender = rs.getString("gender");
            String state = rs.getString("state");
            game = new Jogo(idGame, nameGame, gender, state);

            listGames.add(game);
        }
        rs.close();
        ps.close();
        connection.close();
        return listGames;
    }

    @Override
    public List<Jogo> listaDeJogosLocadosDeUmUsuario(String cpf) throws PersistenciaException {
        List<Jogo> listaDeJogos;
        try (Connection connection = fabricaConexao.getConexao()) {
            String sql = "select distinct j.idDoJogo, j.nomeDoJogo, j.genero, j.estado from jogo as j inner join locacao as l on j.estado = 'RENT' and l.idDoCliente = ? and j.idDoJogo = l.idDoJogo;";
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setString(1, cpf);
                try (ResultSet rs = ps.executeQuery()) {
                    listaDeJogos = new ArrayList<>();
                    Jogo jogo = null;
                    while (rs.next()) {
                        Long idDoJogo = rs.getLong("idDoJogo");
                        String nomeDoJogo = rs.getString("nomeDoJogo");
                        String genero = rs.getString("genero");
                        String estado = rs.getString("estado");
                        jogo = new Jogo(idDoJogo, nomeDoJogo, genero, estado);
                        listaDeJogos.add(jogo);
                    }
                }
            }
        } catch (SQLException e) {
            throw new PersistenciaException();
        }
        return listaDeJogos;
    }

}
