/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.jass2125.locagames.core.commands;

import io.github.jass2125.locagames.core.negocio.Jogo;
import io.github.jass2125.locagames.core.negocio.Location;
import io.github.jass2125.locagames.core.negocio.Cliente;
import io.github.jass2125.locagames.core.repository.JogoDao;
import io.github.jass2125.locagames.core.repository.JogoDaoImpl;
import io.github.jass2125.locagames.core.repository.LocationDao;
import io.github.jass2125.locagames.core.repository.LocationRepository;
import io.github.jass2125.locagames.core.repository.ObserverDao;
import io.github.jass2125.locagames.core.repository.ObserverRepository;
import io.github.jass2125.locagames.core.utilitarios.ConvertDate;
import io.github.jass2125.locagames.state.GameState;
import io.github.jass2125.locagames.strategy.LocationCalcStrategyEnum;
import java.sql.SQLException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Anderson Souza
 * @since 15:18:13, 24-Feb-2016
 */
public class GameLocationAction implements Command {

    private LocationRepository daoLocation;
    private ObserverRepository daoObserver;
    private JogoDao dao;
    private String day;
    private LocalDate devolutionDate;

    public GameLocationAction() {
        this.day = this.verifyTypeOfLocation();
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        try {

            Long idGame = Long.parseLong(request.getParameter("idGame"));
            Cliente user = ((Cliente) request.getSession().getAttribute("user"));
            Jogo game = getGameLocated(idGame);

            if (game != null) {
                saveLocation(idGame, user.getCpf());
                request.getSession().setAttribute("success", "Jogo locado com sucesso");
                dao.editarEstado(idGame, GameState.RENT.name());
                verifyTypeOfLocation();
                return "funcionario/home.jsp";
            }

            this.addObserver(idGame, user.getCpf());
            //Caso o game esteja alugado, retorne a data qe ele estará disponível
            daoLocation = new LocationDao();
            Location location = daoLocation.findLocationById(idGame);
            String date = this.getDateDevolution(location);
            request.getSession().setAttribute("info", date);
            request.getSession().setAttribute("error", "Jogo já esta alugado");
            return "funcionario/home.jsp";

        } catch (NumberFormatException | SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            request.getSession().setAttribute("error", "Ocorreu um erro, retorne e tente novamente");
            return "funcionario/home.jsp";
        }
    }

    private void save(Cliente user, Long idGame) throws ClassNotFoundException, SQLException {
        daoObserver = new ObserverDao();
        daoObserver.addObserver(user.getCpf(), idGame);
    }

    private void saveLocation(Long idGame, String cpf) throws ClassNotFoundException, SQLException {
        daoLocation = new LocationDao();
        Location location = new Location();
        location.setIdGame(idGame);
        location.setDateDevolution(getNumberDayLocation());
        location.setIdUser(cpf);
        location.setStrategy(LocationCalcStrategyEnum.valueOf(verifyTypeOfLocation()));
        daoLocation.save(location);
    }

    private Jogo getGameLocated(Long idGame) throws SQLException, ClassNotFoundException {
        dao = new JogoDaoImpl();
        Jogo game = dao.buscarPorId(idGame);
        return (game.getState().equals(GameState.AVAILABLE) ? game : null);
    }

    private String verifyTypeOfLocation() {
        DayOfWeek currentdate = LocalDate.now().getDayOfWeek();
        if (currentdate.equals(DayOfWeek.SUNDAY) || currentdate.equals(DayOfWeek.SATURDAY)) {
            return "SPECIAL";
        }
        return "COMUM";
    }

    private LocalDate getNumberDayLocation() {
        if (this.day.equals("SPECIAL")) {
            return LocalDate.now().plusDays(2);
        }
        return LocalDate.now().plusDays(1);

    }

    private String getDateDevolution(Location location) {
        LocalDate dataAvailable = location.getDateDevolution().plusDays(1);
        ConvertDate converter = new ConvertDate();
        return converter.converteToString(dataAvailable);
    }

    private void addObserver(Long idGame, String cpf) throws ClassNotFoundException, SQLException {
        daoObserver = new ObserverDao();
        daoObserver.addObserver(cpf, idGame);
    }

}
