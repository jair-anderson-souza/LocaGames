/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package io.github.jass2125.loca.games.core.commands;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Anderson Souza <jair_anderson_bs@hotmail.com> <jair_anderson_bs@hotmail.com>
 * @since 15:32:48, 20-Feb-2016 
 * Package io.github.jass2125.loca.games.commands
 * Project Name loca-games
 * Encoding UTF-8
 * File Name Command.java
 */
public interface Command {
    
    public String execute(HttpServletRequest request, HttpServletResponse response);

}
