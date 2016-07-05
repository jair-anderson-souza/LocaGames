/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.jass2125.locagames.core.commands;

import io.github.jass2125.locagames.core.negocio.Cliente;
import io.github.jass2125.locagames.core.repository.ClienteDao;
import io.github.jass2125.locagames.core.repository.ClienteDaoImpl;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author Anderson Souza
 * @since 15:40:48, 20-Feb-2016
 */
public class LoginClienteCommand implements Command {

    private HttpSession session;
    private ClienteDao clienteDao;

    public LoginClienteCommand() {
    }

    /**
     * @param request Requisiçao {@link HttpServletRequest} do cliente
     * @param response Reposta {@link HttpServletResponse} do cliente
     * @return URL da pagina de resposta
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        session = request.getSession();
        Cliente cliente = this.recuperaDadosDoClienteDaRequisicao(request);

        if (cliente != null) {
            session.setMaxInactiveInterval(60 * 30);
            session.setAttribute("success", "Autenticação feita com sucesso");
            session.setAttribute("user", cliente);
            return "funcionario/home.jsp";
        } else {
            request.getSession().setAttribute("error", "Erro na autenticação");
            return "home.jsp";
        }
    }

    /**
     *
     * @param request Requisiçao {@link HttpServletRequest} do cliente
     * @return {@link Cliente} Cliente registrado na sessão
     */
    private Cliente recuperaDadosDoClienteDaRequisicao(HttpServletRequest request) {
        String cpf = request.getParameter("cpf");
        String email = request.getParameter("email");
        return buscaCliente(cpf, email);
    }

    /**
     * Caso haja cliente registrado na {@link HttpSession}, esse método o
     * retorna totalmente preenchido
     *
     * @param cpf Cpf do cliente registrado na sessão
     * @param email Email do cliente registrado na sessão
     * @return {@link Cliente} Cliente
     */
    private Cliente buscaCliente(String cpf, String email) {
        clienteDao = new ClienteDaoImpl();
        return clienteDao.buscarPorCpfEEmail(cpf, email);
    }

}
