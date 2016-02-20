/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package io.github.jass2125.loca.games.core.util;

/**
 * @author Anderson Souza 
 * @since 17:42:58, 20-Feb-2016 
 */
public enum PersistenceUnitEnum {
    MYSQL("default-mysql"), POSTGRESQL("default-postgresql");
    
    private String unitPersistence;

    private PersistenceUnitEnum(String unitPersistence) {
        this.unitPersistence = unitPersistence;
    }

    public String getUnitPersistence() {
        return this.unitPersistence;
    }
    
    
    

}
