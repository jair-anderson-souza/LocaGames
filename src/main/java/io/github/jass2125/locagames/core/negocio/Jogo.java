/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.jass2125.locagames.core.negocio;

import io.github.jass2125.locagames.excecoes.GameException;
import io.github.jass2125.locagames.observer.Observable;
import io.github.jass2125.locagames.observer.Observer;
import io.github.jass2125.locagames.state.GameState;
import io.github.jass2125.locagames.state.State;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;
import org.apache.commons.mail.EmailException;

/**
 * @author Anderson Souza
 * @since 14:17:38, 20-Feb-2016
 */
public class Jogo implements Serializable, Observable {

    private Long idDoJogo;
    private String nomeDoJogo;
    private String genero;
    private State estado;
    private Set<Observer> listaDeObservadores = new HashSet<>();

    public Jogo() {
//        estado = GameStateEnum.getAVAILABLE();
    }

    public Jogo(Long idDoJogo, String nome, String genero, String estado) {
        this.idDoJogo = idDoJogo;
        this.nomeDoJogo = nome;
        this.genero = estado;
        this.estado = GameState.valueOf(estado);
    }

    public Long getIdDoJogo() {
        return idDoJogo;
    }

    public String getNomeDoJogo() {
        return nomeDoJogo;
    }

    public void setNomeDoJogo(String nomeDoJogo) {
        this.nomeDoJogo = nomeDoJogo;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public State getEstado() {
        return estado;
    }

    public void setEstado(State estado) {
        this.estado = estado;
    }

    public Set<Observer> getListaDeObservadores() {
        return listaDeObservadores;
    }

    public void setListaDeObservadores(Set<Observer> listaDeObservadores) {
        this.listaDeObservadores = listaDeObservadores;
    }

    @Override
    public void notificarObservadores() throws EmailException {
        for (Observer it : listaDeObservadores) {
            it.update(this);
        }
    }

    @Override
    public void deleteObserver(Observer observer) {
        this.listaDeObservadores.remove(this);
    }

    @Override
    public void addObserver(Observer observer) {
        this.listaDeObservadores.add(observer);
    }

    public State devolution() throws SQLException, ClassNotFoundException, GameException {
        State state = this.estado.availableGame();
        this.setEstado(state);
        return state;
    }

    public State location() throws SQLException, ClassNotFoundException, GameException {
        State state = this.estado.rentedGame();
        this.setEstado(state);
        return state;
    }

}
