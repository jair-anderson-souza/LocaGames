/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.jass2125.loca.games.core.business;

import io.github.jass2125.loca.games.core.util.NotificationEmail;
import io.github.jass2125.loca.games.observer.Observer;
import java.io.Serializable;

/**
 * @author Anderson Souza
 * @since 14:06:27, 20-Feb-2016
 */
public class Cliente implements Serializable, Observer<Jogo> {

    private String name;
    private String cpf;
    private String email;

    public Cliente() {
    }

    public Cliente(String name, String cpf, String email) {
        this.name = name;
        this.cpf = cpf;
        this.email = email;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
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
        NotificationEmail not  = new NotificationEmail();
        not.notifyUser(game, this);
    }

}
