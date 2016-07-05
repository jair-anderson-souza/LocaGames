/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.jass2125.locagames.core.repository;

import io.github.jass2125.locagames.core.enums.ExcecoesEnum;
import io.github.jass2125.locagames.core.excecoes.DadosInvalidosException;
import io.github.jass2125.locagames.core.negocio.Cliente;
import io.github.jass2125.locagames.core.fabricas.FabricaDeConexoes;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import io.github.jass2125.locagames.core.excecoes.PersistenciaException;

/**
 * @author Anderson Souza
 * @since 14:23:27, 20-Feb-2016
 */
public class ClienteDaoImpl implements ClienteDao {

    private final FabricaDeConexoes fabricaDeConexao;

    public ClienteDaoImpl() {
        fabricaDeConexao = new FabricaDeConexoes();
    }

    /**
     * Método que armazena um cliente Funcionando
     *
     * @param cliente Objeto {@link Cliente} que será armazenado
     * @return {@link Cliente} Cliente
     * @throws PersistenciaException Exceção lançada quando a aplicação tentar
     * estabelecer comunicação com o banco de dados
     * <br>Ver - {@link FabricaDeConexoes#getConexao()}
     */
    @Override
    public Cliente salvar(Cliente cliente) throws DadosInvalidosException {
        try (Connection conexao = fabricaDeConexao.getConexao()) {
            String sql = "insert into cliente(nome, email, cpf) values(?, ?, ?);";
            try (PreparedStatement preparedStatement = conexao.prepareStatement(sql)) {
                preparedStatement.setString(1, cliente.getNome());
                preparedStatement.setString(2, cliente.getEmail());
                preparedStatement.setString(3, cliente.getCpf());
                preparedStatement.execute();
            }
            return cliente;
        } catch (SQLException e) {
            throw new DadosInvalidosException(e, "Ops, ocorreu um erro.!!");
        }
    }

    public List<Cliente> buscarPorCpf(String cpf) throws SQLException, ClassNotFoundException {
        List<Cliente> listObservers = null;
        try (Connection conexao = fabricaDeConexao.getConexao()) {
            String sql = "select * from cliente where cpf = ?;";
            try (PreparedStatement preparedStatement = conexao.prepareStatement(sql)) {
                preparedStatement.setString(1, cpf);
                try (ResultSet rs = preparedStatement.executeQuery()) {
                    listObservers = new ArrayList<>();
                    Cliente user = new Cliente();
                    while (rs.next()) {
                        String nome = rs.getString("nome");
                        String email = rs.getString("email");
                        String cpf2 = rs.getString("cpf");
                        user = new Cliente(nome, cpf, email);
                        listObservers.add(user);
                    }
                }
            }
        } catch (Exception e) {
        }
        return listObservers;
    }

    /**
     * Funcionando
     *
     * @param cpf CPf
     * @param email Email
     * @return {@link Cliente} Cliente de tretorno
     * @throws PersistenciaException Exceção lançada
     */
    public Cliente buscarPorCpfEEmail(String cpf, String email) throws PersistenciaException {
        try (Connection connection = fabricaDeConexao.getConexao()) {
            String sql = "select * from cliente where cpf = ? and email = ?;";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, cpf);
                preparedStatement.setString(2, email);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        String nome = resultSet.getString("nome");
                        return new Cliente(nome, email, cpf);
                    }
                }
            }
        } catch (SQLException e) {
            throw new PersistenciaException(ExcecoesEnum.ERRO_NA_CONSULTA)
                    //aqui pode ficar o log, sendo uma atributo na exceção
                    .inserirMensagemDeErro("Mensagem de Erro: ", "Não foi possível realizar uma consulta no seu banco de dados.");
        }
        return null;
    }
}
