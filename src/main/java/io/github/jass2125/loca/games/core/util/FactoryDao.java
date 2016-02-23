/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package io.github.jass2125.loca.games.core.util;

import io.github.jass2125.loca.games.core.dao.IUserDao;
import io.github.jass2125.loca.games.core.dao.UserDao;

/**
 * @author Anderson Souza 
 * @since 16:52:22, 23-Feb-2016
 */
public class FactoryDao extends Factory {

    @Override
    public IUserDao createDao() {
        return new UserDao();
    }

}
