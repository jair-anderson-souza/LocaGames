/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.jass2125.locagames.core.utilitarios;

import io.github.jass2125.locagames.core.negocio.Jogo;
import io.github.jass2125.locagames.core.negocio.Cliente;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

/**
 *
 * @author Anderson Souza
 */
public class EmailUtil implements Runnable {

    private String myEmail = "petedoherty2009@gmail.com";
    private String myPass = "";
    private Cliente user;
    private Jogo game;

    public EmailUtil(Cliente user, Jogo game) {
        this.user = user;
        this.game = game;
    }

    public EmailUtil() {
    }

    @Override
    public void run() {

        try {
            String emailU = user.getEmail();
            String gameU = game.getNomeDoJogo();

            Email email = new SimpleEmail();

            email.setHostName("smtp.googlemail.com");
            email.setSmtpPort(465);
            email.setAuthenticator(new DefaultAuthenticator(myEmail, myPass));
            email.setSSLOnConnect(true);
            email.setFrom(myEmail);
            email.setSubject("LocaGames");
            email.setMsg("Caro Sr. " + user.getNome() + ", o jogo " + game.getNomeDoJogo() + " esta disponivel para locaçao! Corra agora para a LocaGames para garantir");
            email.addTo(emailU);
            email.send();
        } catch (EmailException e) {
            e.printStackTrace();
        }
    }

}
