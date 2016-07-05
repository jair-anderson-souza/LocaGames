/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.jass2125.locagames.core.repository;

import io.github.jass2125.locagames.core.excecoes.DadosInvalidosException;
import io.github.jass2125.locagames.core.negocio.Cliente;
import io.github.jass2125.locagames.core.excecoes.PersistenciaException;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Anderson Souza
 */
public interface ClienteDao {

    public Cliente salvar(Cliente cliente) throws DadosInvalidosException;

    public List<Cliente> buscarPorCpf(String cpf) throws SQLException, ClassNotFoundException;

    public Cliente buscarPorCpfEEmail(String cpf, String email) throws PersistenciaException;
}
