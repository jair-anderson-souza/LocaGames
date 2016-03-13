/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.jass2125.loca.games.core.filters;

import io.github.jass2125.loca.games.core.business.User;
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
@WebFilter(urlPatterns = {"/funcionario/*"})
public class AuthorizationFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        chain.doFilter(request, response);
        HttpSession session = ((HttpSession)((HttpServletRequest) request).getSession());
        HttpServletResponse resp = ((HttpServletResponse) response);
        
        HttpServletRequest req = ((HttpServletRequest) request);
        String path = req.getContextPath();
        
        
        User user = (User) session.getAttribute("user");
        if(user == null){
            resp.sendRedirect(path + "/home.jsp");
        }
//        chain.doFilter(request, response);
        //chain.doFilter(request, response);
//        HttpServletRequest req = ((HttpServletRequest) request);
//        User user = (User) req.getSession().getAttribute("user");
//
//        if (user != null) {
//            chain.doFilter(request, response);
////            request.getRequestDispatcher("home.jsp").forward(request, response);
//        } else {
//            request.getRequestDispatcher("home.jsp").forward(request, response);
//        }
//        else {
////            chain.doFilter(request, response);
//        }
//        chain.doFilter(request, response);

    }

    @Override
    public void destroy() {
    }

}
