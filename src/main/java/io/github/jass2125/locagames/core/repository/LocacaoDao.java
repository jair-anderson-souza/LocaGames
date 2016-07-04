/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.jass2125.locagames.core.repository;

import io.github.jass2125.locagames.core.negocio.Locacao;
import io.github.jass2125.locagames.excecoes.PersistenciaException;
import java.util.List;

/**
 *
 * @author Anderson Souza
 */
public interface LocacaoDao {

    public void salvar(Locacao locacao) throws PersistenciaException;

    public List<Locacao> listarlocacoes() throws PersistenciaException;

    public Locacao buscarLocacaoPorUsuario(String cpf, Long idGame) throws PersistenciaException;

    public Locacao buscarLocacaoPorId(Long idGame) throws PersistenciaException;
}
