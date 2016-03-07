/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.jass2125.loca.games.core.business;

import io.github.jass2125.loca.games.core.util.EmailUtil;
import io.github.jass2125.loca.games.observer.Observer;
import java.io.Serializable;
import org.apache.commons.mail.EmailException;

/**
 * @author Anderson Souza
 * @since 14:06:27, 20-Feb-2016
 */
public class User implements Serializable, Observer<Game> {

    private String name;
    private String cpf;
    private String email;

    public User() {
    }

    public User(String name, String cpf, String email) {
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
    public void update(Game game) throws EmailException {
        EmailUtil emailSender = new EmailUtil();
        emailSender.sendEmail(this, game);
    }

}
