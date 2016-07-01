/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.jass2125.loca.games.core.repository;

import io.github.jass2125.loca.games.core.business.Cliente;
import io.github.jass2125.loca.games.core.factory.FabricaDeConexoes;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import io.github.jass2125.loca.games.exceptions.PersistenciaException;

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
     * Método que armazena um cliente
     *
     * @param cliente Objeto
     * {@link io.github.jass2125.loca.games.core.business.Cliente} que será
     * armazenado
     * @throws PersistenciaException Exceção lançada quando a aplicação tentar
     * estabelecer comunicação com o banco de dados
     * <br>Ver -
     * {@link io.github.jass2125.loca.games.core.factory.FabricaDeConexoes#getConexao()}
     */
    @Override
    public Cliente salvar(Cliente cliente) throws PersistenciaException {
        try (Connection conexao = fabricaDeConexao.getConexao()) {
            String sql = "insert into cliente(nome, email, cpf) values(?, ?, ?);";
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            preparedStatement.setString(1, cliente.getNome());
            preparedStatement.setString(2, cliente.getEmail());
            preparedStatement.setString(3, cliente.getCpf());
            preparedStatement.executeUpdate();
            return cliente;
        } catch (SQLException | ClassNotFoundException e) {
            throw new PersistenciaException(e, "Ops, ocorreu um erro.!!");
        }
    }

    public List<Cliente> buscarPorCpf(String cpf) throws SQLException, ClassNotFoundException {
        List<Cliente> listObservers = null;
        try (Connection conexao = fabricaDeConexao.getConexao()) {
            String sql = "select * from cliente where idDoCliente = ?;";
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            preparedStatement.setString(1, cpf);
            ResultSet rs = preparedStatement.executeQuery();
            listObservers = new ArrayList<>();
            Cliente user = new Cliente();
            while (rs.next()) {
                String nome = rs.getString("nome");
                String email = rs.getString("email");
                String cpf2 = rs.getString("cpf");
                user = new Cliente(nome, cpf, email);
                listObservers.add(user);
            }
            rs.close();
            preparedStatement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listObservers;
    }

    public Cliente buscarPorCpfEEmail(String cpf, String email) throws SQLException, ClassNotFoundException {
        try (Connection connection = fabricaDeConexao.getConexao()) {
            String sql = "select * from cliente where cpf = ? and email = ?;";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, cpf);
            preparedStatement.setString(2, email);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String name = resultSet.getString("nome");
                return new Cliente(name, cpf, email);
            }
            resultSet.close();
            preparedStatement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
