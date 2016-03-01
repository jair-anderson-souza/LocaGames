/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.jass2125.loca.games.core.util;

/**
 *
 * @author Anderson Souza
 */
public enum LocationTypeEnum {

    COMUM("COMUM"),SPECIAL("SPECIAL");
    
    private String type;
    private LocationTypeEnum(String type){
        this.type = type;
    }

    public String getType() {
        return type;
    }
    
    
     
    
}
