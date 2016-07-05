/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.jass2125.locagames.core.repository;

import io.github.jass2125.locagames.dbunit.DBUnitHelper;
import io.github.jass2125.locagames.core.negocio.Cliente;
import io.github.jass2125.locagames.core.excecoes.PersistenciaException;
import java.util.List;
import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertNull;
import org.dbunit.operation.DatabaseOperation;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;

/**
 *
 * @author Anderson Sousa
 */
public class ClienteDaoImplTest {

    private static DBUnitHelper helper;
    private static final ClienteDao dao = Mockito.mock(ClienteDao.class);
    private static Cliente cliente1;
    private static Cliente cliente2;
    private static Cliente cliente3;

    //@BeforeClass
    public static void beforeClass() {
        helper = new DBUnitHelper("DbUnitXml");
        cliente1 = new Cliente("Ricardo", "ricardo@gmail.com", "34235");
        cliente2 = new Cliente("Job", "job@hotmail.com", "76576532");
        cliente3 = new Cliente("Priscila", "priscila@hotmail.com", "654633");

        Mockito.doThrow(new PersistenciaException())
                .when(dao)
                .salvar(cliente1);

        Mockito.when(dao.salvar(cliente2)).thenReturn(cliente2);

        //Exceção de quando o email já existe
        Mockito.doThrow(new PersistenciaException())
                .when(dao)
                .salvar(cliente3);
//        dao.salvar(cliente3);

        Mockito.doThrow(new PersistenciaException())
                .when(dao)
                .salvar(null);

        helper.execute(DatabaseOperation.INSERT, "cliente.xml");
    }

    //@AfterClass
    public static void end() {
        helper.execute(DatabaseOperation.DELETE_ALL, "cliente.xml");
    }

    //@Test(expected = PersistenciaException.class)
    public void testSalvarCliente1() {
        Cliente cliente = dao.salvar(cliente1);
        assertNull(cliente);
    }

    //@Test
    public void testSalvarCliente2() {
        Cliente cliente = dao.salvar(cliente2);
        assertNotNull(cliente);
        assertEquals(cliente.getEmail(), cliente2.getEmail());
    }

    public void testBuscarPorCpf() throws Exception {
    }

    /**
     * Test of buscarPorCPFEEmail method, of class ClienteDaoImpl.
     * @throws java.lang.Exception
     */
    public void testBuscarPorCPFEEmail() throws Exception {
        System.out.println("buscarPorCPFEEmail");
        String cpf = "";
        String email = "";
        ClienteDaoImpl instance = new ClienteDaoImpl();
        Cliente expResult = null;
        //Cliente result = instance.buscarPorCPFEEmail(cpf, email);
        //assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
    }

}
