/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.jass2125.locagames.core.commands;

import io.github.jass2125.locagames.core.negocio.Jogo;
import io.github.jass2125.locagames.core.negocio.Locacao;
import io.github.jass2125.locagames.core.negocio.Cliente;
import io.github.jass2125.locagames.core.repository.JogoDao;
import io.github.jass2125.locagames.core.repository.JogoDaoImpl;
import io.github.jass2125.locagames.core.repository.LocacaoDaoImpl;
import io.github.jass2125.locagames.core.repository.ObserverDaoImpl;
import io.github.jass2125.locagames.observer.Observer;
import io.github.jass2125.locagames.state.GameState;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.mail.EmailException;
import io.github.jass2125.locagames.core.repository.ObserverDao;
import io.github.jass2125.locagames.strategy.CalculadoraDeLocacaoStrategy;
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

        try {
            session = request.getSession();
            Long idDoJogo = Long.parseLong(request.getParameter("idGame"));
            String cpf = getCpfDoClienteDaSessao();
            Jogo jogo = this.getGameLocated(idDoJogo);

            if (jogo != null) {
                Locacao location = this.getLocation(cpf, idDoJogo);
                request.getSession().setAttribute("success", "Jogo devolvido com sucesso");
                BigDecimal price = this.getPriceLocation(location);
                request.getSession().setAttribute("price", price);
                this.editGameState(idDoJogo, GameState.AVAILABLE.name());
                jogo.setListObservers(loadObservers(idDoJogo));
                jogo.notifyObservers();
                removeObservers(idDoJogo);

                return "funcionario/home.jsp";
            }

            request.getSession().setAttribute("error", "Você não alugou este jogo!");
            return "funcionario/home.jsp";

        } catch (EmailException e) {
            e.printStackTrace();
            request.getSession().setAttribute("error", "Erro, retorne e tente novamente");
            return "funcionario/home.jsp";
        }
    }

    public String getCpfDoClienteDaSessao() {
        return ((Cliente) session.getAttribute("user")).getCpf();
    }

    /**
     * Recupera o game locado
     *
     * @param idGame Id do game que está sendo pesquisado
     * @return Jogo Jogo pra ser devolvido
     * @throws SQLException Retorna caso ele não consiga recuperar essa
     * informação
     * @throws ClassNotFoundException Classe do Driver MySQL não está disponivel
     */
    private Jogo getGameLocated(Long idGame) {
        dao = new JogoDaoImpl();
        Jogo game = dao.buscarPorId(idGame);
        return (game.getEstado().equals(GameState.RENT) ? game : null);
    }

    /**
     * Deleta o observadores de um game
     *
     * @param idGame Id do game que está sendo pesquisado
     */
    public void removeObservers(Long idGame) {
        daoObserver = new ObserverDaoImpl();
        daoObserver.deleteObservador(idGame);
    }

    /**
     * Pesquisa os observadores de um game
     *
     * @param idGame Id do Jogo que está pesquisado
     * @return Set<Game> Set com todos os observadores de um game
     * @throws SQLException Retorna caso ele não consiga recuperar essa
     * informação
     * @throws ClassNotFoundException Classe do Driver MySQL não está disponivel
     */
    private Set<Observer> loadObservers(Long idGame) throws EmailException {
        daoObserver = new ObserverDaoImpl();
        return daoObserver.getListaDeObservadores(idGame);
    }

    /**
     * Edita o estado do Jogo
     *
     * @param idGame Id do game que será editado
     * @param state Estado do Jogo
     * @throws SQLException Retorna caso ele não consiga recuperar essa
     * informação
     * @throws ClassNotFoundException Classe do Driver MySQL não está disponivel
     */
    private void editGameState(Long idGame, String state) {
        dao = new JogoDaoImpl();
        dao.editarEstado(idGame, GameState.AVAILABLE.name());
    }

    /**
     * Retorna o preço do aluguel
     *
     * @param location Locaçao que esta sendo devolvida
     * @return BigDecimal Valor da locaçao
     */
    private BigDecimal getPriceLocation(Locacao location) {
        return location.calculateValueLocation();
    }

    /**
     * Recupera a locaçao
     *
     * @param cpf CPF do cliente
     * @param idGame Id do game
     * @return Locacao Locaçao
     * @throws SQLException Retorna caso ele não consiga recuperar essa
     * informação
     * @throws ClassNotFoundException Classe do Driver MySQL não está disponivel
     */
    private Locacao getLocation(String cpf, Long idGame) {
        daoLocacao = new LocacaoDaoImpl();
        return daoLocacao.buscarLocacaoPorUsuario(cpf, idGame);
    }
}
