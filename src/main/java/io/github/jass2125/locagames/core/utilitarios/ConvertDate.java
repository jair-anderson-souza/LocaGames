/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package io.github.jass2125.locagames.core.utilitarios;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author Anderson Souza
 */
public class ConvertDate {
    private String pattern;

    public ConvertDate() {
        this.pattern = "dd/MM/yyyy";
    }
    /**
     * Método que retorna uma data formatada
     * @param dateDevolution {@link LocalDate} Data de Devolução
     * @return String Data formatada no padrão BR
     */
     public String converteToString(LocalDate dateDevolution) {
        return dateDevolution.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }


}
