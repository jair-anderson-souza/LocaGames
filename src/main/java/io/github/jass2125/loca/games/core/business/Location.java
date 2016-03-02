/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.jass2125.loca.games.core.business;

import java.time.LocalDate;

/**
 * @author Anderson Souza
 * @since 13:51:29, 24-Feb-2016
 */
public class Location {

    private Long idLocation;
    private String idUser;
    private Long idGame;
    private LocalDate dateLocation;
    private LocalDate dateDevolution;
    private String strategy;

    public Location() {
        this.dateLocation = LocalDate.now();
        this.dateDevolution = this.dateLocation.plusDays(1);
    }

    public void setIdGame(Long idGame) {
        this.idGame = idGame;
    }

    public Long getIdLocation() {
        return idLocation;
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public Long getIdGame() {
        return idGame;
    }

    public LocalDate getDateLocation() {
        return dateLocation;
    }

    public void setDateLocation(LocalDate dateLocation) {
        this.dateLocation = dateLocation;
    }

    public LocalDate getDateDevolution() {
        return dateDevolution;
    }

    public void setDateDevolution(LocalDate dateDevolution) {
        this.dateDevolution = dateDevolution;
    }

    public void setStrategy(String strategy) {
        this.strategy = strategy;
    }

    public String getStrategy() {
        return strategy;
    }

}
