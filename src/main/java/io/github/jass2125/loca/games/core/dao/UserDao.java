/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.jass2125.loca.games.core.dao;

import io.github.jass2125.loca.games.core.business.User;
import io.github.jass2125.loca.games.core.util.RoleEnum;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
/**
 * @author Anderson Souza
 * @since 14:23:27, 20-Feb-2016
 */
public class UserDao implements IUserDao {
    private String url;
    private Properties propertie = new Properties();
    
    public UserDao() {
        this.url = "jdbc:mysql://localhost:3306/locagames";
//        propertie = new Properties();
        propertie.setProperty("user", "root");
        propertie.setProperty("passord", "12345");
    }
    
    @Override
    public User findByCPFAndEmail(String cpf, String email) throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");
        Connection connection = DriverManager.getConnection(url, "root", "12345");
//        on(url, propertie);
        
        String sql = "select * from user where binary cpf = ? and binary email = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, cpf);
        preparedStatement.setString(2, email);
        ResultSet resultSet = preparedStatement.executeQuery();
        
        while(resultSet.next()){
//            int idUser = resultSet.getInt("idUser");
            String name = resultSet.getString("name");
            String role = resultSet.getString("role");
            return new User(name, cpf, email, role);
        }
        return null;
    }

    @Override
    public void persist(User user) {
//        EntityManagerFactory emf = Persistence.createEntityManagerFactory(PersistenceUnitEnum.MYSQL.getUnitPersistence());
//        EntityManager em = emf.createEntityManager();
//        em.getTransaction().begin();
//        em.persist(user);
//        em.getTransaction().commit();
//        em.clear();
//        emf.close();
    }

}
