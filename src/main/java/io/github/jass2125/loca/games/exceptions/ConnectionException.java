/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.jass2125.loca.games.exceptions;

/**
 *
 * @author Anderson Souza
 */
public class ConnectionException extends Exception {

    public ConnectionException(Exception e, String string) {
        super(string, e);
    }

    public ConnectionException() {
    }

}
