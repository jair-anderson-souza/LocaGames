/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.jass2125.locagames.core.commands;

import io.github.jass2125.locagames.core.negocio.Jogo;
import io.github.jass2125.locagames.core.negocio.Locacao;
import io.github.jass2125.locagames.core.negocio.Cliente;
import io.github.jass2125.locagames.core.observer.Observable;
import io.github.jass2125.locagames.core.repository.JogoDao;
import io.github.jass2125.locagames.core.repository.JogoDaoImpl;
import io.github.jass2125.locagames.core.repository.LocacaoDaoImpl;
import io.github.jass2125.locagames.core.repository.ObserverDaoImpl;
import io.github.jass2125.locagames.core.observer.Observer;
import io.github.jass2125.locagames.core.state.GameState;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.mail.EmailException;
import io.github.jass2125.locagames.core.repository.ObserverDao;
import io.github.jass2125.locagames.core.strategy.CalculadoraDeLocacaoStrategy;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpSession;

/**
 * @author Anderson Souza
 * @version 1.0
 */
public class DevolucaoDeJogoCommand implements Command {

    private HttpSession session;
    private LocacaoDaoImpl daoLocacao;
    private CalculadoraDeLocacaoStrategy strategy;
    private JogoDao dao;
    private ObserverDao daoObserver;
    private CalculadoraDeLocacaoStrategy strategyCalc;

    /**
     * Executa a ação geral de devolver game
     *
     * @param request Requisiçao do cliente
     * @param response Reposta do cliente
     * @return URL da pagina de resposta
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        session = request.getSession();
        Long idDoJogo = Long.parseLong(request.getParameter("idDoJogo"));

        Cliente cliente = getCpfDoClienteDaSessao();
        Jogo jogo = getJogoAlugado(idDoJogo);

        if (jogo != null) {
            Locacao locacao = this.getLocacao(cliente.getCpf(), idDoJogo);
            BigDecimal valorDoAluguel = this.getPrecoDoAluguel(locacao);

            try {
                jogo.setListaDeObservadores(recuperarObservadoresDeUmJogo(idDoJogo));
                jogo.notificarObservadores();
            } catch (EmailException ex) {
            }

            request.getSession().setAttribute("price", valorDoAluguel);
            request.getSession().setAttribute("success", "Jogo devolvido com sucesso");

            removeObservers(idDoJogo);

            return "funcionario/home.jsp";
        }

        request.getSession().setAttribute("error", "Você não alugou este jogo!");
        return "funcionario/home.jsp";
    }
    /**
     * Recupera o usuario da sessão {@link HttpSession}
     * @return {@link Cliente} Cliente
     */
    public Cliente getCpfDoClienteDaSessao() {
        return ((Cliente) session.getAttribute("user"));
    }

    /**
     * Recupera o game locado
     *
     * @param idDoJogo Id do game que está sendo pesquisado
     * @return {@link Jogo} Jogo pra ser devolvido
     */
    private Jogo getJogoAlugado(Long idDoJogo) {
        dao = new JogoDaoImpl();
        Jogo game = dao.buscarPorId(idDoJogo);
        return (game.getEstado().equals(GameState.RENT) ? game : null);
    }

    /**
     * Deleta o observadores de um game
     *
     * @param idDoJogo Id do game que está sendo pesquisado
     */
    public void removeObservers(Long idDoJogo) {
        daoObserver = new ObserverDaoImpl();
        daoObserver.deleteObservador(idDoJogo);
    }

    /**
     * Pesquisa os observadores de um jogo
     * @param idDoJogo Id do Jogo que está pesquisado
     * @return {@link Set} Set com todos os observadores de um game
     * @throws {@link EmailException} Exceção lançada quando ocorrer um erro na library commoms
     */
    private Set<Observer> recuperarObservadoresDeUmJogo(Long idDoJogo) throws EmailException {
        daoObserver = new ObserverDaoImpl();
        return daoObserver.getListaDeObservadores(idDoJogo);
    }

    /**
     * Edita o estado do Jogo
     * @param idDoJogod Id do game que será editado
     * @param estado Estado do Jogo
     * @throws SQLException Retorna caso ele não consiga recuperar essa
     * informação
     * @throws ClassNotFoundException Classe do Driver MySQL não está disponivel
     */
    private void devolveJogo(Long idDoJogod, String estado) {
        dao = new JogoDaoImpl();
        dao.editarEstado(idDoJogod, GameState.AVAILABLE.name());
    }

    /**
     * Retorna o preço do jogo
     * @param locacao Locaçao que esta sendo devolvida
     * @return {@link BigDecimal} Valor da locaçao
     */
    private BigDecimal getPrecoDoAluguel(Locacao locacao) {
        return locacao.calculateValueLocation();
    }

    /**
     * Recupera a instancia da locação
     *
     * @param cpf CPF do cliente que está devolvendo o jogo
     * @param idDoJogo Id do jogo que será devolvido
     * @return {@link Locacao} Locaçao
     */
    private Locacao getLocacao(String cpf, Long idDoJogo) {
        daoLocacao = new LocacaoDaoImpl();
        Locacao locacao = daoLocacao.buscarLocacaoPorUsuario(cpf, idDoJogo);
        if (locacao != null) {
            devolveJogo(idDoJogo, cpf);
            return locacao;
        }
        return locacao;
    }
}
