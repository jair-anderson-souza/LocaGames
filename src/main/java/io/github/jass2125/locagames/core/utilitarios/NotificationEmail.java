/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package io.github.jass2125.locagames.core.utilitarios;

import io.github.jass2125.locagames.core.negocio.Jogo;
import io.github.jass2125.locagames.core.negocio.Cliente;

/**
 *
 * @author Anderson Souza
 */
public class NotificationEmail {
    private EmailUtil email;

    public NotificationEmail() {
    }
    
    public void notifyUser(Jogo game, Cliente user){
        this.email = new EmailUtil(user, game);
        Thread thred = new Thread(email);
        thred.start();
        
    }
    

}
