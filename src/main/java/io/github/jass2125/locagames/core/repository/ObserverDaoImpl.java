/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.jass2125.locagames.core.repository;

import io.github.jass2125.locagames.core.enums.ExcecoesEnum;
import io.github.jass2125.locagames.core.negocio.Cliente;
import io.github.jass2125.locagames.core.fabricas.FabricaDeConexoes;
import io.github.jass2125.locagames.core.excecoes.PersistenciaException;
import io.github.jass2125.locagames.core.observer.Observer;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Anderson Souza
 */
public class ObserverDaoImpl implements ObserverDao<Observer> {

    private FabricaDeConexoes fabricaDeConexoes;

    public ObserverDaoImpl() {
        fabricaDeConexoes = new FabricaDeConexoes();
    }

    @Override
    public void adicionaObservador(String cpf, Long idGame) throws PersistenciaException {
        try (Connection connection = fabricaDeConexoes.getConexao()) {
            String sql = "insert into observadores(iddoCliente, idDoJogo) values(?, ?);";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, cpf);
                preparedStatement.setLong(2, idGame);
                preparedStatement.execute();
            }
        } catch (SQLException e) {
            throw new PersistenciaException(ExcecoesEnum.ERRO_NA_CONSULTA).
                    inserirMensagemDeErro(12, "Verifique seus dados e tente novamente!!!");
        }
    }

    @Override
    public Set<Observer> getListaDeObservadores(Long idGame) throws PersistenciaException {
        Set<Observer> listaDeObservadores;
        try (Connection connection = fabricaDeConexoes.getConexao()) {
            String sql = "select * from cliente as c left join observadores as o on c.cpf = o.idDoCliente left join jogo as j on j.idDoJogo = o.idDoJogo where ? = o.idDoJogo;";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setLong(1, idGame);
                try (ResultSet rs = preparedStatement.executeQuery()) {
                    listaDeObservadores = new HashSet<>();
                    Cliente cliente = null;
                    while (rs.next()) {
                        String nome = rs.getString("nome");
                        String email = rs.getString("email");
                        String cpf = rs.getString("cpf");
                        cliente = new Cliente(nome, email, cpf);
                        listaDeObservadores.add(cliente);
                    }
                }
            }
        } catch (SQLException e) {
            throw new PersistenciaException(ExcecoesEnum.ERRO_NA_CONSULTA).
                    inserirMensagemDeErro(13, "Verifique seus dados e tente novamente!!!");
        }
        return listaDeObservadores;
    }

    @Override
    public void deleteObservador(Long idGame) throws PersistenciaException {
        try (Connection connection = fabricaDeConexoes.getConexao()) {
            String sql = "delete from observadores where idDoJogo = ?;";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setLong(1, idGame);
                preparedStatement.execute();
            }
        } catch (SQLException e) {
            throw new PersistenciaException(ExcecoesEnum.ERRO_NA_CONSULTA).
                    inserirMensagemDeErro(14, "Verifique seus dados e tente novamente!!!");
        }
    }

}
