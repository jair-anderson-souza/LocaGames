/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.jass2125.locagames.excecoes;

/**
 *
 * @author Anderson Souza
 */
public enum ExcecoesEnum implements Erro {
    ERRO_NA_CONEXAO(1),
    ARQUIVO_NAO_ENCONTRADO(2),
    ERRO_NA_CONSULTA(3);

    private final int codigoDeErro;

    private ExcecoesEnum(int codigoDeErro) {
        this.codigoDeErro = codigoDeErro;
    }

    public int getCodigoDeErro() {
        return this.codigoDeErro;
    }

}
