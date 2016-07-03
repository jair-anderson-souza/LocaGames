/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package io.github.jass2125.locagames.observer;

import org.apache.commons.mail.EmailException;

/**
 *
 * @author Anderson Souza
 */
public interface Observer<T> {
    
    public void update(T game) throws EmailException;
    
    
}
