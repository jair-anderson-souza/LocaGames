/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.jass2125.loca.games.strategy;

import io.github.jass2125.loca.games.core.business.Location;
import java.math.BigDecimal;

/**
 * @author Anderson Souza
 * @since 14:56:26, 24-Feb-2016
 */
public enum LocationCalcSpecialStrategyEnum implements LocationCalcStrategy {
    
    COMUM{
        @Override
        public BigDecimal calculatePriceGame(Location location) {
            return null;
        }
    
        },
        SPECIAL{
            @Override
            public BigDecimal calculatePriceGame(Location location) {
                return null;
            }
        }
}
    
    
//    private static final int TAXA = 3;
//    private Date dateCurrently;
//
//    public LocationCalcSpecialStrategy() {
//        dateCurrently = new Date();
//    }
//
//    @Override
//    public BigDecimal calculatePriceGame(Location location) {
//        return new BigDecimal(20);
//    }
}
