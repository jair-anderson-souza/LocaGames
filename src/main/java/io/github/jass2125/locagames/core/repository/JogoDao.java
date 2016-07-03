/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.jass2125.locagames.core.repository;

import io.github.jass2125.locagames.core.negocio.Jogo;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Anderson Souza
 */
public interface JogoDao {

    public List<Jogo> listaDeJogos() throws SQLException, ClassNotFoundException;

    public Jogo buscarPorId(Long idGame) throws SQLException, ClassNotFoundException;

    public void editarEstado(Long idGame, String state) throws ClassNotFoundException, SQLException;

    public List<Jogo> listaDeJogosLocadosDeUmUsuario(String cpf) throws ClassNotFoundException, SQLException;

}
