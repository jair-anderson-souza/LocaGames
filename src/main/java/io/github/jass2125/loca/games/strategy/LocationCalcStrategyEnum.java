/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.jass2125.loca.games.strategy;

import io.github.jass2125.loca.games.core.business.Location;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.ChronoUnit;

/**
 * @author Anderson Souza
 * @since 14:56:26, 24-Feb-2016
 */
public enum LocationCalcStrategyEnum implements LocationCalcStrategy {
    
    COMUM{
        @Override
        public BigDecimal calculatePriceGame(Location location) {
            long days  = ChronoUnit.DAYS.between(location.getDateDevolution(), LocalDate.now());
                if (days <= 0) {     
                    return new BigDecimal(3);
                }
                return new BigDecimal(3 + 1 + (days * 3));
        }
        },
        SPECIAL{
            @Override
            public BigDecimal calculatePriceGame(Location location) {
                
                long days  = ChronoUnit.DAYS.between(location.getDateDevolution(), LocalDate.now());
                if (days <= 0) {     
                    return new BigDecimal(5);
                }
                return new BigDecimal(5 + 3 + (days * 3));
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
//}
