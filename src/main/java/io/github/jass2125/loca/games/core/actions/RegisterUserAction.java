/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.jass2125.loca.games.core.actions;

import io.github.jass2125.loca.games.core.business.User;
import io.github.jass2125.loca.games.core.repository.UserDao;
import io.github.jass2125.loca.games.core.repository.UserRepository;
import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Anderson Souza
 * @since 15:33:48, 20-Feb-2016
 * @version 1.0
 */
public class RegisterUserAction implements Action {

    private UserRepository repository;

    public RegisterUserAction() {
    }

    /**
     * Executa a ação de cadastrar um usuario
     *
     * @param request Requisição do cliente
     * @param response Reposta do cliente
     * @return URL da página
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            String name = request.getParameter("name");
            String cpf = request.getParameter("cpf");
            String email = request.getParameter("email");
            boolean verification = validaCpf(cpf);
            if (verification) {
                User user = new User(name, cpf, email);
                saveUser(user);
                request.getSession().setAttribute("user", user);
                return "funcionario/home.jsp";
            }
            request.getSession().setAttribute("error", "Padrao de CPF: 000.000.000-00");
            return "home.jsp";
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            request.getSession().setAttribute("error", "Erro, retorne e tente novamente");
            return "home.jsp";
        } catch (SQLException e) {
            e.printStackTrace();
            request.getSession().setAttribute("error", e.getMessage());
            return "home.jsp";
        }
    }

    public boolean validaCpf(String cpf) {
        String regex = "(\\d{3})[.](\\d{3})[.](\\d{3})-(\\d{2})";
        boolean verification = cpf.matches(regex);
        return verification;
    }

    private void saveUser(User user) throws ClassNotFoundException, SQLException {
        repository = new UserDao();
        repository.save(user);
    }
}
