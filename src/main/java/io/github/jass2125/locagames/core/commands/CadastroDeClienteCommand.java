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
import io.github.jass2125.locagames.core.utilitarios.SessaoUtil;
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
                SessaoUtil.limpaSessao(request.getSession());
                request.getSession().setAttribute("usuarioLogado", cliente);
                request.getSession().setAttribute("sucesso", "Cadastro efetuado com sucesso");
                return "funcionario/home.jsp";
            } catch (DadosInvalidosException e) {
                SessaoUtil.limpaSessao(request.getSession());
                request.getSession().setAttribute("erro", "Esse CPF já consta nos nossos registros.");
                return "home.jsp";
            }
        } else {
            SessaoUtil.limpaSessao(request.getSession());
            request.getSession().setAttribute("erro", "Padrao de CPF: xxx.xxx.xxx-xx");
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
        String name = request.getParameter("nome");
        String cpf = request.getParameter("cpf");
        String email = request.getParameter("email");
        return new Cliente(name, email, cpf);
    }

    /**
     * método que valida o formato do CPF
     *
     * @param cpf Cpf informado pelo usuário
     * @return boolean True caso atenda o formato esperado - false caso não
     * atenda
     */
    private boolean validaCpf(String cpf) {

        String regex = "(\\d{3})[.](\\d{3})[.](\\d{3})-(\\d{2})";
        boolean verification = cpf.matches(regex);
        return verification;
    }

    /**
     * Método que cadastra um novo cliente
     *
     * @param cliente {@link Cliente}
     * @return {@link Cliente} Cliente
     * @throws DadosInvalidosException
     */
    private Cliente cadastrarNovoCliente(Cliente cliente) throws DadosInvalidosException {
        dao = new ClienteDaoImpl();
        return dao.salvar(cliente);
    }
}
