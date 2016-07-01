/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.jass2125.loca.games.core.repository;

import io.github.jass2125.loca.games.core.business.Cliente;
import io.github.jass2125.loca.games.core.factory.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Anderson Souza
 * @since 14:23:27, 20-Feb-2016
 */
public class ClienteDaoImpl implements ClienteDao {

    private final ConnectionFactory factoryConnect;

    public ClienteDaoImpl() {
        factoryConnect = new ConnectionFactory();
    }

    @Override
    public void salvar(Cliente cliente) throws ClassNotFoundException, SQLException {
        try (Connection connection = factoryConnect.getConnection()) {
            String sql = "insert into cliente(nome, email, cpf) values(?, ?, ?);";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, cliente.getName());
                preparedStatement.setString(2, cliente.getEmail());
                preparedStatement.setString(3, cliente.getCpf());
                preparedStatement.execute();
            }
        } catch (SQLIntegrityConstraintViolationException e) {
            throw new SQLException("Número de CPF já existe", e);
        }

    }

    public List<Cliente> buscarPorCpf(String cpf) throws SQLException, ClassNotFoundException {
        List<Cliente> listObservers;
        try (Connection connection = factoryConnect.getConnection()) {
            String sql = "select * from cliente where idDoCliente = ?;";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, cpf);
            ResultSet rs = preparedStatement.executeQuery();
            listObservers = new ArrayList<>();
            Cliente user = new Cliente();
            while (rs.next()) {
                String name = rs.getString("nome");
                String email = rs.getString("email");
                String cpf2 = rs.getString("cpf");
                user = new Cliente(name, cpf, email);
                listObservers.add(user);
            }
            rs.close();
            preparedStatement.close();
        }
        return listObservers;
    }

    public Cliente buscarPorCPFEEmail(String cpf, String email) throws SQLException, ClassNotFoundException {
        try (Connection connection = factoryConnect.getConnection()) {
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
        }
        return null;
    }
}
