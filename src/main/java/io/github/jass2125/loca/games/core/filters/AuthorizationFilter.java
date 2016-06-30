/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.jass2125.loca.games.core.filters;

import io.github.jass2125.loca.games.core.business.Cliente;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Anderson Souza
 */
@WebFilter(filterName = "filterUserLogon", urlPatterns = {"/funcionario/*"})
public class AuthorizationFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        chain.doFilter(request, response);
        HttpServletRequest req = ((HttpServletRequest) request);
        HttpSession session = req.getSession();
        HttpServletResponse resp = ((HttpServletResponse) response);

        String path = req.getContextPath();

        Cliente user = (Cliente) session.getAttribute("user");
        //System.out.println(user.toString());
        if (user == null) {
            session.setAttribute("error", "Realize o login");
            resp.sendRedirect(path + "/home.jsp");
        }
    }

    @Override
    public void destroy() {
    }

}
