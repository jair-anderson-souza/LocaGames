/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.jass2125.locagames.strategy;

import io.github.jass2125.locagames.core.negocio.Locacao;
import java.math.BigDecimal;

/**
 * @author Anderson Souza
 * @since 14:56:26, 24-Feb-2016
 */
public interface CalculadoraDeLocacaoStrategy {
    
    public BigDecimal calcularPrecoDaLocacao(Locacao location);

}
