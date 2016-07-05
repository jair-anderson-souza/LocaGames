/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.jass2125.locagames.core.negocio;

import io.github.jass2125.locagames.core.utilitarios.NotificationEmail;
import io.github.jass2125.locagames.core.observer.Observer;
import java.io.Serializable;

/**
 * @author Anderson Souza
 * @since 14:06:27, 20-Feb-2016
 */
public class Cliente implements Serializable, Observer<Jogo> {

    private String nome;
    private String cpf;
    private String email;

    public Cliente() {
    }

    public Cliente(String nome, String email, String cpf) {
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return this.cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public void update(Jogo game) {
        NotificationEmail not = new NotificationEmail();
        not.notifyUser(game, this);
    }

}
