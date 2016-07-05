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
import io.github.jass2125.locagames.core.utilitarios.ConvertDate;
import io.github.jass2125.locagames.state.GameState;
import io.github.jass2125.locagames.strategy.CalculadoraDeLocacaoComumStrategy;
import java.time.DayOfWeek;
import java.time.LocalDate;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import io.github.jass2125.locagames.core.repository.LocacaoDao;
import io.github.jass2125.locagames.core.repository.ObserverDao;
import io.github.jass2125.locagames.core.repository.ObserverDaoImpl;
import io.github.jass2125.locagames.strategy.CalculadoraDeLocacaoEspecialStrategy;
import io.github.jass2125.locagames.strategy.CalculadoraDeLocacaoStrategy;

/**
 * @author Anderson Souza
 * @since 15:18:13, 24-Feb-2016
 */
public class LocacaoDeJogoCommand implements Command {

    private HttpSession session;
    private LocacaoDao daoLocacao;
    private ObserverDao daoObservadores;
    private JogoDao daoJogo;
    private DayOfWeek diaAtual;

    public LocacaoDeJogoCommand() {
        this.diaAtual = LocalDate.now().getDayOfWeek();
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        this.session = request.getSession();
        Long idDoJogo = Long.parseLong(request.getParameter("idDoJogo"));

        Cliente cliente = getClienteDaSessao();
        Jogo jogo = getJogoAlugado(idDoJogo);
        if (jogo != null) {
            registraLocacao(idDoJogo, cliente.getCpf());
            request.getSession().setAttribute("success", "Jogo locado com sucesso");
            return "funcionario/home.jsp";
        }

        String dataDeEntrega = buscarDataDeDevolucaoDoJogo(idDoJogo);
        adicionarObservador(idDoJogo, cliente);

        request.getSession().setAttribute("info", dataDeEntrega);
        request.getSession().setAttribute("error", "Jogo já esta alugado");
        return "funcionario/home.jsp";
    }
    /**
     * Método que busca o dia da devolução do jogo
     * @param idDoJogo {@link Long} Identificador do jogo
     * @return {@link String} Data de Devolução
     */
    public String buscarDataDeDevolucaoDoJogo(Long idDoJogo) {
        daoLocacao = new LocacaoDaoImpl();
        Locacao locacao = daoLocacao.buscarLocacaoPorId(idDoJogo);
        return this.getDataDeDevolucaoDoJogo(locacao);
    }
    /**
     * Método que adiciona um observador ao jogo
     * @param idDoJogo {@link Long} Identificador do jogo
     * @param cliente {@link Cliente}
     */
    
    public void adicionarObservador(Long idDoJogo, Cliente cliente) {
        daoObservadores = new ObserverDaoImpl();
        this.adicionaObservador(idDoJogo, cliente.getCpf());
    }
    
    /**
     * Método que retorna o cliente da sessão
     * @return {@link Cliente} Cliente
     */
    public Cliente getClienteDaSessao() {
        return (Cliente) session.getAttribute("user");
    }
    
    /**
     * Método que retorna o id do jogo
     * @param request {@link HttpServletRequest} Requisição HTTP
     * @return {@link Long} Long
     */
    public Long getIdDoJogo(HttpServletRequest request) {
        return (Long) Long.parseLong(request.getParameter("locacaoDeJogo"));
    }

    /**
     * Registra a locação de um jogo por um cliente
     *
     * @param idDoJogo Atributo identificador do Jogo
     * @param cpf Cpf do cliente
     */
    private void registraLocacao(Long idDoJogo, String cpf) {
        Locacao locacao = new Locacao();
        daoLocacao = new LocacaoDaoImpl();
        locacao.setIdDoJogo(idDoJogo);
        locacao.setDataDeDevolucao(calcularDataDeDevolucao());
        locacao.setIdDoUsuario(cpf);
        locacao.setStrategy(recuperaTipoDeLocacao());
        daoLocacao.salvar(locacao);
        alterarEstadoDoJogo(idDoJogo);
    }

    /**
     * Método que retorna uma instancia do tipo de calculo que deverá efetuado
     * para o preço do jogo
     *
     * @return {@link CalculadoraDeLocacaoStrategy}
     */

    public CalculadoraDeLocacaoStrategy recuperaTipoDeLocacao() {
        if (calcularDataDeDevolucao().equals(calcularDataDeDevolucao().plusDays(2))) {
            return new CalculadoraDeLocacaoEspecialStrategy();
        }
        return new CalculadoraDeLocacaoComumStrategy();
    }
    /**
     * Método que que informa se o jogo tá alugado
     * @param idDoJogo Identificador do {@link Jogo}
     * @return {@link Jogo} Jogo
     */
    private Jogo getJogoAlugado(Long idDoJogo) {
        daoJogo = new JogoDaoImpl();
        Jogo jogo = daoJogo.buscarPorId(idDoJogo);
        return (jogo.getEstado().equals(GameState.AVAILABLE) ? jogo : null);
    }

    /**
     * Método que calcula a data de devolução do jogo
     * @return {@link LocalDate} LocaDate
     */
    private LocalDate calcularDataDeDevolucao() {
        if (diaAtual.equals(DayOfWeek.SUNDAY) || diaAtual.equals(DayOfWeek.SATURDAY)) {
            return LocalDate.now().plusDays(2);
        }
        return LocalDate.now().plusDays(1);
    }

    private String getDataDeDevolucaoDoJogo(Locacao locacao) {
        LocalDate dataAvailable = locacao.getDataDeDevolucao();
        ConvertDate converter = new ConvertDate();
        return converter.converteToString(dataAvailable);
    }

    private void adicionaObservador(Long idDoJogo, String cpf) {
        daoObservadores.adicionaObservador(cpf, idDoJogo);
    }

    /**
     * Altera o estado do jogo de alugado para disponivel, e de disponivel para
     * alugado
     *
     * @param idDoJogo Atributo identificador do Jogo
     */
    private void alterarEstadoDoJogo(Long idDoJogo) {
        daoJogo.editarEstado(idDoJogo, GameState.RENT.name());
    }

}
