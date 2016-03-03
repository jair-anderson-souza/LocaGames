/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package io.github.jass2125.loca.games.observer;

/**
 *
 * @author Anderson Souza
 */
public interface Observable {
    
    public void notifyObservers();
    public void deleteObserver(Observer observer);
    public void addObserver(Observer observer);

}
