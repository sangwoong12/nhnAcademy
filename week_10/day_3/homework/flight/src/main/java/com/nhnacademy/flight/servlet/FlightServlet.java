package com.nhnacademy.flight.servlet;

import com.nhnacademy.flight.DbUtils;
import com.nhnacademy.flight.item.Boarding;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "flightServlet", value = "/boarding")
public class FlightServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DataSource dataSource = DbUtils.getDataSource();
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM Passenger " +
                     "INNER JOIN Reservation ON Reservation.PassengerNo = Passenger.PassengerNo " +
                     "INNER JOIN Flight F ON Reservation.FlightNo = F.FlightNo " +
                     "INNER JOIN Aircraft A ON F.AircraftNo = A.AircraftNo " +
                     "WHERE Passenger.PassengerNo = ?")) {

            int id = Integer.parseInt(req.getParameter("id"));

            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            List<Boarding> list = new ArrayList<>();

            while (resultSet.next()) {
                list.add(
                        new Boarding(
                                resultSet.getString("ReservedDate"),
                                resultSet.getString("Departure"),
                                resultSet.getString("Arrival"),
                                resultSet.getInt("Price"),
                                resultSet.getString("FlightDate"),
                                resultSet.getString("KindOfAirCraft"),
                                resultSet.getString("Airline")
                        )
                );
            }
            req.setAttribute("list", list);

            resultSet.close();

            RequestDispatcher requestDispatcher = req.getRequestDispatcher("boarding.jsp");
            requestDispatcher.forward(req, resp);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
