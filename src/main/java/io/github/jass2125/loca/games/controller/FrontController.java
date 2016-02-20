/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.jass2125.loca.games.controller;

import io.github.jass2125.loca.games.core.commands.Command;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Anderson Souza <jair_anderson_bs@hotmail.com>
 * <jair_anderson_bs@hotmail.com>
 * @since 15:31:16, 20-Feb-2016 Package io.github.jass2125.loca.games.controller
 * Project Name loca-games Encoding UTF-8 File Name FrontController.java
 */
@WebServlet(urlPatterns = {"/front"})
public class FrontController extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Command command = CommandFactory.getCommand(request);
        String view = command.execute(request, response);
        response.sendRedirect(view);
        
    }

}
