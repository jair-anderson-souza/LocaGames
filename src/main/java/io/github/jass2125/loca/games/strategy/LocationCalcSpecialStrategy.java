/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.jass2125.loca.games.strategy;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author Anderson Souza
 * @since 14:56:26, 24-Feb-2016
 */
public class LocationCalcSpecialStrategy extends LocationCalcStrategy {
    private static final int TAXA = 3;
    private Date dateCurrently;

    public LocationCalcSpecialStrategy() {
        dateCurrently = new Date();
    }

    @Override
    public BigDecimal calculatePriceGame() {
        return new BigDecimal(20);
    }
    
    
}
