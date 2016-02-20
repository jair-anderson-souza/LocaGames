/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.jass2125.loca.games.core.util;

/**
 * @author Anderson Souza 
 * @since 19:51:25, 20-Feb-2016 
 */
public enum RoleEnum {
    EMPLOYEE("employeed"), CLIENT("client");
    private String role;

    private RoleEnum(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
    
    

}
