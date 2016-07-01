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
public class ArquivoNaoEncontradoException extends RuntimeException {

    public ArquivoNaoEncontradoException() {
    }

    public ArquivoNaoEncontradoException(String msg, Throwable th) {
        super(msg, th);
    }

}
