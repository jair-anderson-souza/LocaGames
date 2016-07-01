/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.jass2125.loca.games.core.repository;

import io.github.jass2125.locagames.dbunit.DBUnitHelper;
import io.github.jass2125.loca.games.core.business.Cliente;
import java.sql.SQLException;
import java.util.List;
import static junit.framework.Assert.assertEquals;
import org.dbunit.operation.DatabaseOperation;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

/**
 *
 * @author Anderson Sousa
 */
public class ClienteDaoImplTest {

    private static DBUnitHelper helper;
    @Mock
    private ClienteDao dao;
    private Cliente cliente1;
    private Cliente cliente2;
    private Cliente cliente3;

    @BeforeClass
    public static void beforeClass() {
        helper = new DBUnitHelper("DbUnitXml");
    }

    @Before //antes da execução de cada método
    public void init() {
        helper.execute(DatabaseOperation.INSERT, "cliente.xml");
        MockitoAnnotations.initMocks(this);
        cliente1 = new Cliente("Thomaz", "thom@gmail.com", "34235");
        cliente2 = new Cliente("Rafael", "matheus@hotmail.com", "273632");
        cliente3 = new Cliente("Rafael", "jorge@hotmail.com", "654654");
    }

    @After //depois da execução de cada método
    public void end() {
        helper.execute(DatabaseOperation.DELETE_ALL, "cliente.xml");
    }

    /**
     * Test of salvar method, of class ClienteDaoImpl.
     */
    @Test(expected = SQLException.class)
    public void testSalvar() {
        try {
            //Exceção de quando o CPF já existe
            Mockito.doThrow(new SQLException()).when(dao).salvar(cliente1);

            //Exceção de quando o email já existe
            Mockito.doThrow(new SQLException()).when(dao).salvar(cliente2);

            ClienteDao dao2 = new ClienteDaoImpl();
            dao2.salvar(cliente1);

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Test of buscarPorCpf method, of class ClienteDaoImpl.
     */
    public void testBuscarPorCpf() throws Exception {
        System.out.println("buscarPorCpf");
        String cpf = "";
        ClienteDaoImpl instance = new ClienteDaoImpl();
        List<Cliente> expResult = null;
        List<Cliente> result = instance.buscarPorCpf(cpf);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of buscarPorCPFEEmail method, of class ClienteDaoImpl.
     */
    public void testBuscarPorCPFEEmail() throws Exception {
        System.out.println("buscarPorCPFEEmail");
        String cpf = "";
        String email = "";
        ClienteDaoImpl instance = new ClienteDaoImpl();
        Cliente expResult = null;
        Cliente result = instance.buscarPorCPFEEmail(cpf, email);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
    }

}
