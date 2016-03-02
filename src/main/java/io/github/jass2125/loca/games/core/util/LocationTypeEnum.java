/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.jass2125.loca.games.core.util;

import io.github.jass2125.loca.games.strategy.LocationCalcStrategy;
import java.math.BigDecimal;

/**
 *
 * @author Anderson Souza
 */
public enum LocationTypeEnum implements LocationCalcStrategy {

    COMUM{
        @Override
        public BigDecimal calculatePriceGame() {
            return null;
        }
        
    },
    SPECIAL{

        @Override
        public BigDecimal calculatePriceGame() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
        
    }
    
    
    
     
    
}
