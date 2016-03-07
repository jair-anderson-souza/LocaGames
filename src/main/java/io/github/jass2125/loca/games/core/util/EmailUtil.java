/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.jass2125.loca.games.core.util;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

/**
 *
 * @author Anderson Souza
 */
public class EmailUtil {
    private String myEmai;
    private String myPass;
    

    public void sendEmail(String receiver) throws EmailException {

        Email email = new SimpleEmail();

        email.setHostName("smtp.googlemail.com");
        email.setSmtpPort(465);
        email.setAuthenticator(new DefaultAuthenticator(myEmail, myPass));
        email.setSSLOnConnect(true);
        email.setFrom("petedoherty2009@gmail.com");
        email.setSubject("TestMail");
        email.setMsg("This is a test mail ... :-)");
        email.addTo(receiver);
        email.send();

    }


}
