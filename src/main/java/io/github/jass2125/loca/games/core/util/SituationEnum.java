/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package io.github.jass2125.loca.games.core.util;

/**
 * @author Anderson Souza <jair_anderson_bs@hotmail.com> 
 * @since 12:25:19, 24-Feb-2016
 */
public enum SituationEnum {
    AVAILABLE("available"), RENDED("rended");
    
    private String situation;

    private SituationEnum(String situation) {
        this.situation = situation;
    }

    public String getSituation() {
        return situation;
    }

    public void setSituation(String situation) {
        this.situation = situation;
    }
    

}
