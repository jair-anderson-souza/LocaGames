/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.jass2125.loca.games.core.repository;

import io.github.jass2125.locagames.dbunit.DBUnitHelper;
import io.github.jass2125.loca.games.core.business.Cliente;
import java.sql.Connection;
import java.util.Calendar;
import java.util.List;
import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.fail;
import org.dbunit.operation.DatabaseOperation;
import org.junit.After;
import static org.junit.Assert.assertThat;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Anderson Sousa
 */
public class ClienteDaoImplTest {

    private static DBUnitHelper helper;
    private static Connection connection;

    @BeforeClass
    public static void beforeClass() {
        helper = new DBUnitHelper("DbUnitXml");
    }

    @Before //antes da execução de cada método
    public void init() {
        helper.execute(DatabaseOperation.DELETE_ALL, "Carro.xml");

        helper.execute(DatabaseOperation.INSERT, "Carro.xml");

        //manager = factory.createEntityManager();
        //this.carroDAO = new CarroDAO(manager);
    }

    @After //depois da execução de cada método
    public void end() {
        //this.manager.close();
    }

    @Test
    public void deveRetornarCarrosZeroKm() {
//        List<Carro> resultado = carroDAO.buscarCarrosZero();

//        assertThat(resultado, hasItems(new Carro(1L), new Carro(4L)));
    }

    @Test
    public void deveRetornarCarrosMenosDoisAnosUso() {
        Integer doisAnosAntes = Calendar.getInstance().get(Calendar.YEAR) - 2;
//        List<Carro> resultado = carroDAO.buscarCarrosComIdadeInferiorA(doisAnosAntes);
//
//        assertThat(resultado, hasItems(new Carro(1L), new Carro(2L), new Carro(3L), new Carro(4L)));
    }

    /**
     * Test of salvar method, of class ClienteDaoImpl.
     */
    public void testSalvar() throws Exception {
        System.out.println("salvar");
        Cliente cliente = null;
        ClienteDaoImpl instance = new ClienteDaoImpl();
        instance.salvar(cliente);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
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
        fail("The test case is a prototype.");
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
        fail("The test case is a prototype.");
    }

}
