/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.jass2125.locagames.core.negocio;

import io.github.jass2125.locagames.strategy.CalculadoraDeLocacaoComumStrategy;
import io.github.jass2125.locagames.strategy.CalculadoraDeLocacaoEspecialStrategy;
import java.math.BigDecimal;
import java.time.LocalDate;
import io.github.jass2125.locagames.strategy.CalculadoraDeLocacaoStrategy;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Anderson Souza
 * @since 13:51:29, 24-Feb-2016
 */
public class Locacao {

    private Long idDaLocacao;
    private String idDoUsuario;
    private Long idDoJogo;
    private LocalDate dataDeLocacao;
    private LocalDate dataDeDevolucao;
    private CalculadoraDeLocacaoStrategy strategy;
    private Map<String, CalculadoraDeLocacaoStrategy> map = Collections.unmodifiableMap(new HashMap() {
        {
            put("COMUM", new CalculadoraDeLocacaoComumStrategy());
            put("ESPECIAL", new CalculadoraDeLocacaoEspecialStrategy());
        }
    });

    public Locacao() {
        this.dataDeLocacao = LocalDate.now();
//        this.dataDeDevolucao = this.dataDeLocacao.plusDays(1);
    }

    public Locacao(Long idLocation, String idUser, Long idGame, LocalDate dateLocation, LocalDate dateDevolution, String strategy) {
        this.idDaLocacao = idLocation;
        this.idDoUsuario = idUser;
        this.idDoJogo = idGame;
        this.dataDeLocacao = dateLocation;
        this.dataDeDevolucao = dateDevolution;
        this.strategy = map.get(strategy);
    }

    public Locacao(Long idLocation, String idUser, Long idGame, LocalDate dateLocation, String strategy) {
        this.idDaLocacao = idLocation;
        this.idDoUsuario = idUser;
        this.idDoJogo = idGame;
        this.dataDeLocacao = dateLocation;
    }

    public void setIdDoJogo(Long idDoJogo) {
        this.idDoJogo = idDoJogo;
    }

    public Long getIdDaLocacao() {
        return idDaLocacao;
    }

    public String getIdDoUsuario() {
        return idDoUsuario;
    }

    public void setIdDoUsuario(String idDoUsuario) {
        this.idDoUsuario = idDoUsuario;
    }

    public Long getIdDoJogo() {
        return idDoJogo;
    }

    public LocalDate getDataDeLocacao() {
        return dataDeLocacao;
    }

    public void setDataDeLocacao(LocalDate dataDeLocacao) {
        this.dataDeLocacao = dataDeLocacao;
    }

    public LocalDate getDataDeDevolucao() {
        return dataDeDevolucao;
    }

    public void setDataDeDevolucao(LocalDate dataDeDevolucao) {
        this.dataDeDevolucao = dataDeDevolucao;
    }

    public void setStrategy(CalculadoraDeLocacaoStrategy strategy) {
        this.strategy = strategy;
    }

    public CalculadoraDeLocacaoStrategy getStrategy() {
        return strategy;
    }

    public BigDecimal calculateValueLocation() {
        return this.strategy.calcularPrecoDaLocacao(this);
    }

}
