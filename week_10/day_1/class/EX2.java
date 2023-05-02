import java.sql.*;

public class EX2 {
    private static final String driverName = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    private static final String url = "jdbc:sqlserver://10.200.222.100:3306;encrypt=true;database=Module06;integratedSecurity=true;";
    private static final String user = "scott";
    private static final String pwd = "tiger";

    Connection connection = null;
    Statement statement = null;

    public void connect() {
        try {
            Class.forName(driverName);
            connection = DriverManager.getConnection(url, user, pwd);
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from emp");
            while (resultSet.next()) {
                System.out.println(resultSet.getString(1));
            }
            //resultSet,statement,connection 은 데이터베이스의 누수를 막기위해 무조건 종료하여야 한다.
            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        EX2 ex2 = new EX2();
        ex2.connect();
    }
}
