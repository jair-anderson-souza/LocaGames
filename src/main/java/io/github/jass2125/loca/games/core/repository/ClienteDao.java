/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.jass2125.loca.games.core.repository;

import io.github.jass2125.loca.games.core.business.Cliente;
import io.github.jass2125.loca.games.exceptions.PersistenciaException;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Anderson Souza
 */
public interface ClienteDao {

    public void salvar(Cliente cliente) throws PersistenciaException;

    public List<Cliente> buscarPorCpf(String cpf) throws SQLException, ClassNotFoundException;

    public Cliente buscarPorCPFEEmail(String cpf, String email) throws SQLException, ClassNotFoundException;
}
