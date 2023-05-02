import java.sql.*;

public class EX1 {
    private static final String driverName = "com.mysql.cj.jdbc.Driver";
    private static final String url = "jdbc:mysql://localhost:3306/nhn_academy_50";
    private static final String user = "root";
    private static final String pwd = "";

    Connection connection = null;
    Statement statement = null;

    public void connect() {
        try {
            Class.forName(driverName);
            connection = DriverManager.getConnection(url, user, pwd);
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from JdbcTeachers");
            System.out.println("id\tname\tcreated_at");
            while (resultSet.next()) {
                String id = resultSet.getString("id");
                String name = resultSet.getString("name");
                String createdAt = resultSet.getString("created_at");
                System.out.println(id + "\t" + name + "\t\t" + createdAt + "\t");
            }
            //resultSet,statement,connection 은 데이터베이스의 누수를 막기위해 무조건 종료하여야 한다.
            resultSet.close();
            statement.close();
            connection.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        EX1 ex1 = new EX1();
        ex1.connect();
    }
}
