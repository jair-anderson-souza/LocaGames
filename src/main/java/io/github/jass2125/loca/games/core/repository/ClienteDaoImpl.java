/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.jass2125.loca.games.core.repository;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import io.github.jass2125.loca.games.core.business.Cliente;
import io.github.jass2125.loca.games.core.factory.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = factoryConnect.getConnection();
            String sql = "insert into user(name, cpf, email) values(?, ?, ?);";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, cliente.getName());
            preparedStatement.setString(2, cliente.getCpf());
            preparedStatement.setString(3, cliente.getEmail());
            preparedStatement.execute();
        } catch (MySQLIntegrityConstraintViolationException e) {
            throw new SQLException("Número de CPF já existe", e);
        } finally {
            preparedStatement.close();
            connection.close();
        }

    }

    public List<Cliente> buscarPorCpf(String cpf) throws SQLException, ClassNotFoundException {
        List<Cliente> listObservers;
        try (Connection connection = factoryConnect.getConnection()) {
            String sql = "select * from user where idUser = ?;";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, cpf);
            ResultSet rs = preparedStatement.executeQuery();
            listObservers = new ArrayList<>();
            Cliente user = new Cliente();
            while (rs.next()) {
                String name = rs.getString("name");
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
            String sql = "select * from user where binary cpf = ? and binary email = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, cpf);
            preparedStatement.setString(2, email);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String name = resultSet.getString("name");
                return new Cliente(name, cpf, email);
            }
            resultSet.close();
            preparedStatement.close();
        }
        return null;
    }
}
