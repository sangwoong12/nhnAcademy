package com.nhnacademy.flight.servlet;

import com.nhnacademy.flight.DbUtils;
import com.nhnacademy.flight.item.Passenger;

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

@WebServlet(name = "passengerServlet", value = "/")
public class PassengerServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DataSource dataSource = DbUtils.getDataSource();
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement("select * from Passenger")) {

            ResultSet resultSet = statement.executeQuery();
            List<Passenger> list = new ArrayList<>();

            while (resultSet.next()) {
                list.add(new Passenger(resultSet.getInt("PassengerNo"), resultSet.getInt("Age"),
                        resultSet.getInt("Grade"), resultSet.getString("PassengerName")));
            }
            req.setAttribute("list", list);

            resultSet.close();

            RequestDispatcher requestDispatcher = req.getRequestDispatcher("index.jsp");
            requestDispatcher.forward(req, resp);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
