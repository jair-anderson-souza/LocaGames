/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package io.github.jass2125.loca.games.core.business;

import io.github.jass2125.loca.games.core.util.RoleEnum;
import java.io.Serializable;

/**
 * @author Anderson Souza 
 * @since 14:06:27, 20-Feb-2016 
 */
public class User implements Serializable {
    private int idUser;
    private String name;
    private String cpf;
    private String email;
    private String role;

    public User() {
    }

    public User(int idUser, String name, String cpf, String email, String role) {
        this.idUser = idUser;
        this.name = name;
        this.cpf = cpf;
        this.email = email;
        this.role = role;
    }
    
    

    public User(String name, String cpf, String email, String role) {
        this.name = name;
        this.cpf = cpf;
        this.email = email;
        this.role = role;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCpf() {
        return this.cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getIdUser() {
        return this.idUser;
    }

    public String getRole() {
        return this.role;
    }

    public void setRole(String role) {
        this.role = role;
    }
    
    
    
    
    
}
