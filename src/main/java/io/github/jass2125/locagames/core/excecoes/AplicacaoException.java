/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.jass2125.locagames.core.excecoes;

import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Anderson Souza
 */
public class AplicacaoException extends RuntimeException {

    private Erro erro;
    private Map<String, String> map = new HashMap<>();

    public AplicacaoException() {
    }

    public AplicacaoException(Erro erro) {
        this.erro = erro;
    }

    public AplicacaoException(Erro erro, String message, Throwable cause) {
        super(message, cause);
        this.erro = erro;
    }

    public AplicacaoException(Erro erro, Throwable cause) {
        this.erro = erro;
    }

    public AplicacaoException(Erro erro, String mensagem) {
        this.erro = erro;
    }

    public AplicacaoException(Exception excecao, String mensagem) {
        super(mensagem, excecao);
    }

    public Erro getCodigoDeErro() {
        return erro;
    }

    public void setCodigoDeErro(Erro erro) {
        this.erro = erro;
    }

    public AplicacaoException inserirMensagemDeErro(String chave, String valor) {
        this.map.put(chave, valor);
        return this;
    }

    @Override
    public void printStackTrace(PrintStream stream) {
        synchronized (stream) {
            printStackTrace(new PrintWriter(stream));
        }
    }

    @Override
    public void printStackTrace(PrintWriter writer) {
        synchronized (writer) {
            if (this.erro != null) {
                System.out.println("Código de Erro: " + erro.getCodigoDeErro());
                System.out.println("Erro: " + erro);
            }

            /* Parametros sobre o Erro */
            map.keySet().stream().forEach(it -> {
                System.out.println("Descrição do Erro: " + map.get(it));
            });

            /* Pilha de Erros */
            StackTraceElement[] trace = getStackTrace();
            for (StackTraceElement tr : trace) {
                System.out.println(tr);
            }

            Throwable cause = getCause();
            if (cause != null) {
                cause.printStackTrace(writer);
            }
            writer.flush();

        }
    }

}
