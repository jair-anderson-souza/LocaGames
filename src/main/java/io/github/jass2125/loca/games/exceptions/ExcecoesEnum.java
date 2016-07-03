/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.jass2125.loca.games.exceptions;

/**
 *
 * @author Anderson Souza
 */
public enum ExcecoesEnum implements Erro {
    ERRO_NA_CONEXAO(1),
    ARQUIVO_NAO_ENCONTRADO(2),
    DRIVER_INVALIDO(3),
    SENHA_INVALIDA(4);

    private final int codigoDeErro;

    private ExcecoesEnum(int codigoDeErro) {
        this.codigoDeErro = codigoDeErro;
    }

    public int getCodigoDeErro() {
        return this.codigoDeErro;
    }

}
