/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.jass2125.locagames.core.repository;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Anderson Souza
 * @param <T>
 */
public abstract class GenericDao<T> {

    private Class objectClass;

    public GenericDao(Class objectClass) {
        this.objectClass = objectClass;
    }

    public abstract void salvar(T t) throws ClassNotFoundException, SQLException;

    public abstract List<T> buscarPorCpf(Serializable cpf) throws SQLException, ClassNotFoundException;

    public abstract T buscarPorCPFEEmail(Serializable cpf, Serializable email) throws SQLException, ClassNotFoundException;
}