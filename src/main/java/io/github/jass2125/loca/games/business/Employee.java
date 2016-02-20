/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package io.github.jass2125.loca.games.business;

import java.io.Serializable;

/**
 * @author Anderson Souza <jair_anderson_bs@hotmail.com> <jair_anderson_bs@hotmail.com>
 * @since 15:44:08, 20-Feb-2016 
 * Package io.github.jass2125.loca.games.business
 * Project Name loca-games
 * Encoding UTF-8
 * File Name Employee.java
 */
public class Employee implements Serializable {
    private Long idClient;
    private String name;
    private String cpf;
    private String email;

    public Employee() {
    }

    public Employee(String name, String cpf, String email) {
        this.name = name;
        this.cpf = cpf;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getIdClient() {
        return idClient;
    }
    
    
}
