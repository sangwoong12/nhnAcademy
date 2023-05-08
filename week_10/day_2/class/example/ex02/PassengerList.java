package ex02;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PassengerList {
    private static final List<Passenger> PASSENGER_LIST = new ArrayList<>();
    private static final String DRIVER_NAME = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/module06";
    private static final String USER = "root";
    private static final String PWD = "";

    private static final String a = "server=localhost;database=module06;id=root;password="

    Connection connection = null;
    Statement statement = null;
    ResultSet resultSet;

    public void connect() {
        try {
            Class.forName(DRIVER_NAME);
            connection = DriverManager.getConnection(URL, USER, PWD);
            statement = connection.createStatement();
            resultSet = statement.executeQuery("select * from Passenger ");

            while (resultSet.next()) {
                int id = resultSet.getInt("PassengerNo");
                String name = resultSet.getString("PassengerName");
                int grade = resultSet.getInt("Grade");
                int age = resultSet.getInt("Age");
                PASSENGER_LIST.add(new Passenger(id, name, grade, age));
            }
            //resultSet,statement,connection 은 데이터베이스의 누수를 막기위해 무조건 종료하여야 한다.
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                resultSet.close();
                statement.close();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public List<Passenger> getDate() {
        connect();
        return PASSENGER_LIST;
    }
}
