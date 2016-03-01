/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.jass2125.loca.games.strategy;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * @author Anderson Souza
 * @since 14:56:26, 24-Feb-2016
 */
public class LocationCalcNormalStrategy implements LocationCalcStrategy {
    private static final int TAXA = 1;
    private LocalDate currentDate;

    public LocationCalcNormalStrategy() {
        currentDate = LocalDate.now();
    }

    @Override
    public BigDecimal calculatePriceGame() {
        return null;
    }
    
    

}
