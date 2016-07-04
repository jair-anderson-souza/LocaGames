/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.jass2125.locagames.core.commands;

import io.github.jass2125.locagames.core.negocio.Cliente;
import io.github.jass2125.locagames.core.negocio.Jogo;
import io.github.jass2125.locagames.core.repository.JogoDao;
import io.github.jass2125.locagames.core.repository.JogoDaoImpl;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Anderson Souza
 * @version 1.0
 */
public class LoaderGameLocatedAction implements Command {

    private JogoDao dao;

    public LoaderGameLocatedAction() {
        dao = new JogoDaoImpl();
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        Cliente user = this.getSessionUser(request);
        if (user != null) {
            String cpf = user.getCpf();
            List<Jogo> listGames = dao.listaDeJogosLocadosDeUmUsuario(cpf);
            request.getSession().setAttribute("listLocations", listGames);
            return "funcionario/devolver.jsp";
        }
        request.getSession().setAttribute("error", "Faça o login/cadastro!");
        return "home.jsp";

    }

    public Cliente getSessionUser(HttpServletRequest request) {
        Cliente user = (Cliente) request.getSession().getAttribute("user");
        return user;
    }

    public List<Jogo> getListGamesAvailables(String cpf) throws ClassNotFoundException, SQLException {
        List<Jogo> listGames = new ArrayList();
        listGames = dao.listaDeJogosLocadosDeUmUsuario(cpf);
        return listGames;
    }
}
