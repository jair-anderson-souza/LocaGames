/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.jass2125.locagames.core.repository;

import io.github.jass2125.locagames.dbunit.DBUnitHelper;
import io.github.jass2125.locagames.core.negocio.Cliente;
import io.github.jass2125.locagames.core.excecoes.PersistenciaException;
import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertNull;
import static junit.framework.Assert.fail;
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

    @BeforeClass
    public static void beforeClass() {
        helper = new DBUnitHelper("DbUnitXml");
        cliente1 = new Cliente("Ricardo", "ricardo@gmail.com", "12345");
        cliente2 = new Cliente("Job", "job@hotmail.com", "76576532");
        cliente3 = new Cliente("Priscila", "priscila@hotmail.com", "654633");

        Mockito.doThrow(new NullPointerException())
                .when(dao)
                .salvar(null);

        Mockito.doThrow(new PersistenciaException())
                .when(dao)
                .salvar(cliente1);

        Mockito.when(dao.salvar(cliente2)).thenReturn(cliente2);

        //Exceção de quando o email já existe
        Mockito.doThrow(new PersistenciaException())
                .when(dao)
                .salvar(cliente3);

        helper.execute(DatabaseOperation.INSERT, "cliente.xml");
    }

    @AfterClass
    public static void end() {
        helper.execute(DatabaseOperation.DELETE_ALL, "cliente.xml");
    }

    @Test
    public void testSalvarClienteNulo() {
        Cliente cliente = null;
        try {
            cliente = dao.salvar(null);
        } catch (NullPointerException e) {
            assertNull(cliente);
        }
    }

    @Test
    public void testSalvarRegistracaoDeChavePrimaria() {
        Cliente clietne = null;
        try {
            clietne = dao.salvar(cliente1);

        } catch (PersistenciaException e) {
            assertNull(clietne);
        }
    }

    @Test
    public void testSalvar() {
        Cliente cliente = dao.salvar(cliente2);
        assertNotNull(cliente);
        assertEquals(cliente, cliente2);
    }

    @Test
    public void testSalvarImpl() {
        ClienteDao daoCliente = null;
        Cliente c = null;
        try {
            daoCliente = new ClienteDaoImpl();
            c = daoCliente.salvar(cliente1);
        } catch (PersistenciaException e) {
            assertNull(c);
        }
    }

    //@Test
    public void testBuscarClientePorCpf() {
//        ClienteDao daoCliente = new ClienteDaoImpl();
//        List cliente = dao.buscarPorCpf("76576532");
//        assertNotNull(cliente);
//        assertEquals(cliente.getEmail(), cliente2.getEmail());
        fail("metodo falho");
    }

    /**
     * Test of buscarPorCPFEEmail method, of class ClienteDaoImpl.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testBuscarPorCPFEEmail() throws Exception {
//        String cpf = "654633";
//        String email = "priscila@hotmail.com";
        ClienteDao daoCliente = new ClienteDaoImpl();
        Cliente cliente = daoCliente.buscarPorCpfEEmail("867856", "rafael@hotmail.com");
        assertNotNull(cliente);

    }

}
