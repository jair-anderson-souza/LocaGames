/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.jass2125.locagames.core.repository;

import io.github.jass2125.locagames.core.negocio.Locacao;
import io.github.jass2125.locagames.core.fabricas.FabricaDeConexoes;
import io.github.jass2125.locagames.excecoes.ExcecoesEnum;
import io.github.jass2125.locagames.excecoes.PersistenciaException;
import java.sql.Connection;
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
public class LocacaoDaoImpl implements LocacaoDao {

    private FabricaDeConexoes fabricaDeConexoes;

    public LocacaoDaoImpl() {
        fabricaDeConexoes = new FabricaDeConexoes();
    }

    public void salvar(Locacao locacao) throws PersistenciaException {
        try (Connection connection = fabricaDeConexoes.getConexao()) {
            String sql = "insert into locacao(idDoCliente, idDoJogo, dataDaLocacao, dataDeDevolucao, estrategia) values(?, ?, ? ,?, ?);";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, locacao.getIdDoUsuario());
                preparedStatement.setLong(2, locacao.getIdDoJogo());
                preparedStatement.setString(3, locacao.getDataDeLocacao().toString());
                preparedStatement.setString(4, locacao.getDataDeDevolucao().toString());
                preparedStatement.setString(5, locacao.getStrategy().toString());
                preparedStatement.execute();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new PersistenciaException(ExcecoesEnum.ERRO_NA_CONSULTA)
                    //aqui pode ficar o log, sendo uma atributo na exceção
                    .inserirMensagemDeErro("Mensagem de Erro: ", "Não foi possível realizar uma locação.");
        }
    }

    public List<Locacao> listarlocacoes() throws PersistenciaException {
        List<Locacao> listGames;
        try (Connection connection = fabricaDeConexoes.getConexao()) {
            String sql = "select * from location where location.idGame in();";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                try (ResultSet resulSet = preparedStatement.executeQuery()) {
                    listGames = new ArrayList<>();
                    Locacao location = null;
                    while (resulSet.next()) {
                        Long idLocation = resulSet.getLong("idLocation");
                        String idUser = resulSet.getString("idUser");
                        Long idGame = resulSet.getLong("idGame");
                        LocalDate dateLocation = resulSet.getDate("dateLocation").toLocalDate();
                        LocalDate dateDevolution = resulSet.getDate("dateDevolution").toLocalDate();
                        Object strategy = resulSet.getObject("strategy");
//            location = new Locacao(idLocation, idUser, idGame, dateLocation, dateDevolution, (CalculadoraDeLocacaoStrategy) strategy);
                        listGames.add(location);
                    }
                }
            }
        } catch (SQLException e) {
            throw new PersistenciaException();
        }
        return listGames;
    }

    public Locacao buscarLocacaoPorUsuario(String cpf, Long idGame) throws PersistenciaException {
        try (Connection connection = fabricaDeConexoes.getConexao()) {
            String sql = "select l.idDaLocacao, l.idDoCliente, l.dataDaLocacao, l.dataDedevolucao, l.estrategia from locacao as l join jogo as j on l.idDoJogo = j.idDoJogo where l.idDoCliente = ? and j.idDoJogo = ? and j.estado = 'RENT';";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, cpf);
                preparedStatement.setLong(2, idGame);
                try (ResultSet rs = preparedStatement.executeQuery()) {
                    if (rs.next()) {
                        Long idDaLocacao = rs.getLong("idDaLocacao");
                        String idDoCliente = rs.getString("idDoCliente");
                        LocalDate dateDaLocacao = rs.getDate("dataDaLocacao").toLocalDate();
                        LocalDate dataDaDevolucao = rs.getDate("dataDedevolucao").toLocalDate();
                        String estrategia = rs.getString("estrategia");
                        return new Locacao(idDaLocacao, idDoCliente, idGame, dateDaLocacao, dataDaDevolucao, estrategia);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new PersistenciaException();
        }
        return null;

    }

    public Locacao buscarLocacaoPorId(Long idDoJogo) throws PersistenciaException {
        try (Connection connection = fabricaDeConexoes.getConexao()) {
            
            String sql = "select * from locacao inner join jogo on locacao.idDoJogo = jogo.idDoJogo where jogo.estado = 'RENT' and locacao.idDoJogo = ?;";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setLong(1, idDoJogo);
                try (ResultSet rs = preparedStatement.executeQuery()) {
                    if (rs.next()) {
                        Long idDaLocacao = rs.getLong("idDaLocacao");
                        String idUser = rs.getString("idDoCliente");
//            String idGame = rs.getString("idGame");
                        LocalDate dateLocation = rs.getDate("dataDaLocacao").toLocalDate();
                        LocalDate dateDevolution = rs.getDate("dataDeDevolucao").toLocalDate();
                        String strategy = rs.getString("estrategia");
                        return new Locacao(idDaLocacao, idUser, idDoJogo, dateLocation, dateDevolution, strategy);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new PersistenciaException();
        }
        return null;

    }

}
