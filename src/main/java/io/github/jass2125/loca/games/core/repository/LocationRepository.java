/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.jass2125.loca.games.core.repository;

import io.github.jass2125.loca.games.core.business.Location;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Anderson Souza
 */
public interface LocationRepository {

    public void save(Location location) throws ClassNotFoundException, SQLException;

    public List<Location> listLocations() throws SQLException, ClassNotFoundException;

    public Location findLocation(String cpf, Long idGame) throws ClassNotFoundException, SQLException;

    public Location findLocationById(Long idGame) throws ClassNotFoundException, SQLException;
}
