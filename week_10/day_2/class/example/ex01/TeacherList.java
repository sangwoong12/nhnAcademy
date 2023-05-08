package ex01;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TeacherList {
    private static final List<Teacher> TEACHER_LIST = new ArrayList<>();
    private static final String DRIVER_NAME = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/nhn_academy_50";
    private static final String USER = "root";
    private static final String PWD = "";

    Connection connection = null;
    Statement statement = null;
    ResultSet resultSet;


    public void connect() {
        try {
            Class.forName(DRIVER_NAME);
            connection = DriverManager.getConnection(URL, USER, PWD);
            statement = connection.createStatement();
            resultSet = statement.executeQuery("select * from JdbcTeachers");

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String createdAt = resultSet.getString("created_at");
                TEACHER_LIST.add(new Teacher(id, name, createdAt));
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
    public List<Teacher> getTeacherList(){
        connect();
        return TEACHER_LIST;
    }
}
