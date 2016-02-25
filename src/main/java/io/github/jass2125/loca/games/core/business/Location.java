/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.jass2125.loca.games.core.business;

import io.github.jass2125.loca.games.strategy.LocationCalcStrategy;
import java.util.Date;

/**
 * @author Anderson Souza
 * @since 13:51:29, 24-Feb-2016
 */
public class Location {

    private Long idLocation;
    private Date dateLocation;
    private Date dateDevolution;
    private LocationCalcStrategy strategyCalc;

    public Location() {
        this.dateLocation = new Date();
    }

    public Long getIdLocation() {
        return idLocation;
    }

    public Date getDateLocation() {
        return dateLocation;
    }

    public void setDateLocation(Date dateLocation) {
        this.dateLocation = dateLocation;
    }

    public Date getDateDevolution() {
        return dateDevolution;
    }

    public void setDateDevolution(Date dateDevolution) {
        this.dateDevolution = dateDevolution;
    }

    public LocationCalcStrategy getStrategyCalc() {
        return strategyCalc;
    }

}
