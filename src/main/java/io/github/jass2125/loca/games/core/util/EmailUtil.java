/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.jass2125.loca.games.core.util;

import io.github.jass2125.loca.games.core.business.Game;
import io.github.jass2125.loca.games.core.business.User;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

/**
 *
 * @author Anderson Souza
 */
public class EmailUtil {
    private String myEmail = "petedoherty2009@gmail.com";
    private String myPass = "seeyousoon";
    

    public void sendEmail(User user, Game game) throws EmailException {
        
        String emailU = user.getEmail();
        String gameU = game.getName();
        
        
        Email email = new SimpleEmail();

        email.setHostName("smtp.googlemail.com");
        email.setSmtpPort(465);
        email.setAuthenticator(new DefaultAuthenticator(myEmail, myPass));
        email.setSSLOnConnect(true);
        email.setFrom(myEmail);
        email.setSubject("Loca-Games");
        email.setMsg("Caro Sr. " + user.getName() +", o jogo " + game.getName() + " esta disponivel para locaçao! Corra agora para a Pattern Games para garantir");
        email.addTo(emailU);
        email.send();

    }


}
