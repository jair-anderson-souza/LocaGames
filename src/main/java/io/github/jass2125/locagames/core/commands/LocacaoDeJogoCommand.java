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
    private final JogoDao daoJogo;
    private DayOfWeek diaAtual;
    private LocalDate dataDeDevolucao;

    public LocacaoDeJogoCommand() {
        daoJogo = new JogoDaoImpl();
        this.diaAtual = LocalDate.now().getDayOfWeek();
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        this.session = request.getSession();
        Long idDoJogo = Long.parseLong(request.getParameter("idDoJogoGame"));

        Cliente cliente = getClienteDaSessao();
        Jogo jogo = getJogoAlugado(idDoJogo);
        //fluxo de jogo disponivel
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

    public String buscarDataDeDevolucaoDoJogo(Long idDoJogo) {
        daoLocacao = new LocacaoDaoImpl();
        Locacao locacao = daoLocacao.buscarLocacaoPorId(idDoJogo);
        return this.getDateDevolution(locacao);
    }

    public void adicionarObservador(Long idDoJogo, Cliente cliente) {
        this.addObserver(idDoJogo, cliente.getCpf());
    }

    public Cliente getClienteDaSessao() {
        return (Cliente) session.getAttribute("user");
    }

    public Long getIdDoJogo(HttpServletRequest request) {
        return (Long) Long.parseLong(request.getParameter("locacaoDeJogo"));
    }

    private void registraLocacao(Long idDoJogo, String cpf) {
        Locacao locacao = new Locacao();
        daoLocacao = new LocacaoDaoImpl();
        locacao.setIdDoJogo(idDoJogo);
        locacao.setDataDeDevolucao(calcularDataDeDevolucao());
        locacao.setIdDoUsuario(cpf);
        locacao.setStrategy(c());
        daoLocacao.salvar(locacao);
        alterarEstadoDoJogo(idDoJogo);
    }

    public CalculadoraDeLocacaoStrategy c() {
        if (calcularDataDeDevolucao().equals(calcularDataDeDevolucao().plusDays(2))) {
            return new CalculadoraDeLocacaoEspecialStrategy();
        }
        return new CalculadoraDeLocacaoComumStrategy();
    }

    private Jogo getJogoAlugado(Long idGame) {
        Jogo jogo = daoJogo.buscarPorId(idGame);
        return (jogo.getEstado().equals(GameState.AVAILABLE) ? jogo : null);
    }

    // TODO alterar nome desse metodo
    private LocalDate calcularDataDeDevolucao() {
        if (diaAtual.equals(DayOfWeek.SUNDAY) || diaAtual.equals(DayOfWeek.SATURDAY)) {
//            return "ESPECIAL";
            return LocalDate.now().plusDays(2);
        }
//        return "COMUM";
        return LocalDate.now().plusDays(1);
    }

    private String getDateDevolution(Locacao location) {
        LocalDate dataAvailable = location.getDataDeDevolucao();
        ConvertDate converter = new ConvertDate();
        return converter.converteToString(dataAvailable);
    }

    private void addObserver(Long idGame, String cpf) {
        daoObservadores.adicionaObservador(cpf, idGame);
    }

    private void alterarEstadoDoJogo(Long idDoJogo) {
        daoJogo.editarEstado(idDoJogo, GameState.RENT.name());
    }

}
