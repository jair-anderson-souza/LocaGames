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
import io.github.jass2125.locagames.core.utilitarios.SessaoUtil;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author Anderson Souza
 * @version 1.0
 */
public class CarregaJogosDeUmClienteCommand implements Command {

    private HttpSession session;
    private JogoDao dao;

    public CarregaJogosDeUmClienteCommand() {
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        session = request.getSession();
        Cliente cliente = getSessionUser();

        if (cliente != null) {
            SessaoUtil.limpaSessao(session);
            request.getSession().setAttribute("listaDeLocacoes", getListGamesAvailables(cliente.getCpf()));
            return "funcionario/devolver.jsp";
        }
        SessaoUtil.limpaSessao(session);
        request.getSession().setAttribute("erro", "Faça o login/cadastro!");
        return "home.jsp";
    }

    public Cliente getSessionUser() {
        return (Cliente) session.getAttribute("usuarioLogado");
    }

    public List<Jogo> getListGamesAvailables(String cpf) {
        dao = new JogoDaoImpl();
        return dao.listaDeJogosLocadosDeUmUsuario(cpf);
    }
}
