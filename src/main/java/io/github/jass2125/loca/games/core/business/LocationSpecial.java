/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.jass2125.loca.games.core.business;

import java.math.BigDecimal;

/**
 * @author Anderson Souza
 * @since 13:39:18, 24-Feb-2016
 */
public class LocationSpecial {
    private BigDecimal price;
    private int duration;
    private int ds;

    public LocationSpecial(BigDecimal price, int duration, int ds) {
        this.price = price;
        this.duration = duration;
        this.ds = ds;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getDs() {
        return ds;
    }

    public void setDs(int ds) {
        this.ds = ds;
    }
    
    

    
    
    
}
