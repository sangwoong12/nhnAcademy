import java.sql.*;

public class Test {
    public static void main(String[] args) throws SQLException {
        String url = "jdbc:mysql://localhost:3306/flight";
        String user = "root";
        String pwd = "";

        Connection connection = DriverManager.getConnection(url, user, pwd);
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from airline");
        System.out.println("airlineId\t| airlineName\t| airlineCode");
        while (resultSet.next()) {
            String airlineId = resultSet.getString("airline_id");
            String airlineName = resultSet.getString("airline_name");
            String airlineCode = resultSet.getString("airline_code");
            String a = airlineName.length() > 4 ? "\t\t|" : "\t\t\t|";
            System.out.println(airlineId + "\t\t\t|" + airlineName + a + airlineCode);
        }
        resultSet.close();
        statement.close();
        connection.close();
    }
}
