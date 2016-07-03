package io.github.jass2125.locagames.dbunit;

import io.github.jass2125.loca.games.core.factory.FabricaDeConexoes;
import io.github.jass2125.loca.games.exceptions.ConexaoException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import javax.resource.cci.ConnectionFactory;
import org.dbunit.DatabaseUnitException;
import org.dbunit.database.DatabaseConfig;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.ext.mysql.MySqlDataTypeFactory;
import org.dbunit.operation.DatabaseOperation;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Anderson Souza
 */
public class DBUnitHelper {

    private Connection connection;
    private String xmlFolder;
    private DatabaseConnection dbConnection;

    public DBUnitHelper(String xmlFolder) {
        this.xmlFolder = xmlFolder;
        try {
            connection = new FabricaDeConexoes().getConexao();
            dbConnection = new DatabaseConnection(connection);
            DatabaseConfig config = dbConnection.getConfig();
            config.setProperty(DatabaseConfig.PROPERTY_DATATYPE_FACTORY, new MySqlDataTypeFactory());

        } catch (DatabaseUnitException e) {
            e.printStackTrace();
        }
    }

    public void execute(DatabaseOperation op, String xml) {
        try {
            InputStream stream = getClass().getResourceAsStream("/" + xmlFolder + "/" + xml);
            FlatXmlDataSetBuilder builder = new FlatXmlDataSetBuilder();
            IDataSet set = builder.build(stream);
            op.execute(dbConnection, set);
        } catch (DatabaseUnitException | SQLException e) {
            e.printStackTrace();
        }
    }

    public void close() {
        try {
            dbConnection.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
