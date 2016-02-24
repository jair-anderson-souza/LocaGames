/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.jass2125.loca.games.core.commands;

import io.github.jass2125.loca.games.core.business.Game;
import io.github.jass2125.loca.games.core.dao.IGameDao;
import io.github.jass2125.loca.games.core.util.Factory;
import io.github.jass2125.loca.games.core.util.FactoryDao;
import io.github.jass2125.loca.games.core.util.SituationEnum;
import io.github.jass2125.loca.games.services.LocationService;
import io.github.jass2125.loca.games.state.GameRenderState;
import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Anderson Souza
 * @since 22:42:32, 23-Feb-2016
 */
public class GameRenderCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            int idGame = Integer.parseInt(request.getParameter("idGame"));
            Factory factory = new FactoryDao();
            IGameDao dao = factory.createGameDao();
            Game game = dao.findById(idGame);
            
           
                
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return null;

    }

}
