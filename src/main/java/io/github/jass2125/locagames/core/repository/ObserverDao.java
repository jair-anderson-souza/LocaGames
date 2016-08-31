/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.jass2125.locagames.core.repository;

import io.github.jass2125.locagames.core.excecoes.PersistenciaException;
import io.github.jass2125.locagames.core.observer.Observer;
import java.util.Set;

/**
 *
 * @author Anderson Souza
 * @param <T>
 */
public interface ObserverDao<T> {

    public void adicionaObservador(String cpf, Long idGame) throws PersistenciaException;

    public Set<Observer> getListaDeObservadores(Long idGame) throws PersistenciaException;

    public void deleteObservador(Long idGame) throws PersistenciaException;

}
