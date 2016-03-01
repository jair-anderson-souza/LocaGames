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
    }

    public Location(String idUser, Long idGame, String strategy) {
        this.idUser = idUser;
        this.idGame = idGame;
        this.strategy = strategy;
    }

    public Location(Long idLocation, String idUser, Long idGame, LocalDate dateLocation, LocalDate dateDevolution, String strategy) {
        this.idLocation = idLocation;
        this.idUser = idUser;
        this.dateLocation = dateLocation;
        this.dateDevolution = dateDevolution;
        this.strategy = strategy;
    }

    public Long getIdLocation() {
        return idLocation;
    }

    public void setIdLocation(Long idLocation) {
        this.idLocation = idLocation;
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

    public void setIdGame(Long idGame) {
        this.idGame = idGame;
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

    public String getStrategy() {
        return strategy;
    }

    public void setStrategy(String strategy) {
        this.strategy = strategy;
    }

}
