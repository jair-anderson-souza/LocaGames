/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.jass2125.locagames.core.commands;

import io.github.jass2125.locagames.core.excecoes.DadosInvalidosException;
import io.github.jass2125.locagames.core.negocio.Cliente;
import io.github.jass2125.locagames.core.repository.ClienteDao;
import io.github.jass2125.locagames.core.repository.ClienteDaoImpl;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Anderson Souza
 * @since 15:33:48, 20-Feb-2016
 * @version 1.0
 */
public class CadastroDeClienteCommand implements Command {

    private ClienteDao dao;

    public CadastroDeClienteCommand() {
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

        Cliente cliente = getDadosDoCliente(request);
        if (validaCpf(cliente.getCpf())) {
            try {
                cadastrarNovoCliente(cliente);
                request.getSession().setAttribute("user", cliente);
                return "funcionario/home.jsp";
            } catch (DadosInvalidosException e) {
                request.getSession().setAttribute("error", "Esse CPF já consta nos nossos registros.");
                return "home.jsp";
            }
        } else {
            request.getSession().setAttribute("error", "Padrao de CPF: xxx.xxx.xxx-xx");
            return "home.jsp";
        }
    }

    /**
     * Método que recuperaos dados do cliente da sessão
     *
     * @param request {@link HttpServletRequest} Requisição HTTP do cliente
     * @return {@link Cliente} Cliente
     */
    public Cliente getDadosDoCliente(HttpServletRequest request) {
        String name = request.getParameter("name");
        String cpf = request.getParameter("cpf");
        String email = request.getParameter("email");
        return new Cliente(name, email, cpf);
    }

    /**
     * Método que valida o CPF np formato especifico
     *
     * @param cpf Cpf do {@link Cliente
     * @return True esteja no formato esperado, false caso não esteja
     */
    public boolean validaCpf(String cpf) {
        String regex = "(\\d{3})[.](\\d{3})[.](\\d{3})-(\\d{2})";
        boolean verification = cpf.matches(regex);
        return verification;
    }

    /**
     * Método que cadastra um novo cliente
     *
     * @param cliente {@link Cliente}
     * @return
     * @throws DadosInvalidosException
     */
    private Cliente cadastrarNovoCliente(Cliente cliente) throws DadosInvalidosException {
        dao = new ClienteDaoImpl();
        return dao.salvar(cliente);
    }
}
