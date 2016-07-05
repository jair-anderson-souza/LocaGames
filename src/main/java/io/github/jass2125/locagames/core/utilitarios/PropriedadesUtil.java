/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.jass2125.locagames.core.utilitarios;

import io.github.jass2125.locagames.core.enums.ExcecoesEnum;
import io.github.jass2125.locagames.core.excecoes.ArquivoNaoEncontradoException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 *
 * @author Anderson Souza
 */
public class PropriedadesUtil {

    private Properties properties;

    public PropriedadesUtil() {
    }

    /**
     * Método que carrega o arquivo de propriedades com as informações d banco
     * de dados
     *
     * @return Um {@link java.util.Properties} com as informações da conexão
     * @throws ArquivoNaoEncontradoException Exceção lançada quando o arquivo
     * não puder ser encontrado
     */
    public Properties carregarArquivoDePropriedade() throws ArquivoNaoEncontradoException {
        try (InputStream stream = getClass().getResourceAsStream("/bd.properties")) {
            properties = new Properties();
            properties.load(stream);
            return properties;
        } catch (IOException | NullPointerException e) {
            throw new ArquivoNaoEncontradoException(ExcecoesEnum.ARQUIVO_NAO_ENCONTRADO)
                    .putMap("Mensagem de erro: ", "O arquivo de propriedades não pôde ser encontrado!!!");
        }
    }
}
