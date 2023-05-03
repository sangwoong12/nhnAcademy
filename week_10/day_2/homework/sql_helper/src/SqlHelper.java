import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.Semaphore;

public class SqlHelper {
    private final String url;
    private final String user;
    private final String password;
    //
    private static Semaphore semaphore = null;

    public SqlHelper(String connectionString) throws SQLException {
        String[] split = connectionString.split(";");
        if (split.length != 3) {
            throw new SQLException("connect 에 필요한 정보가 부족합니다.");
        }
        url = split[0];
        user = extract(split[1], "user=");
        password = extract(split[2], "password=");

        try (Connection connection = DriverManager.getConnection(url, user, password); PreparedStatement statement = connection.prepareStatement("show variables like '%max_connections%'")) {
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            //max_connections -1  값으로 세마포어 최대값 설정
            //mysql.stop 이후 start 하면 Threads_connected = 1
            semaphore = new Semaphore(resultSet.getInt("Value") - 1);
        }
    }

    public int execute(String query, Object... params) throws SQLException {
        try {
            semaphore.acquire();
            try (Connection connection = DriverManager.getConnection(url, user, password); PreparedStatement statement = connection.prepareStatement(query)) {
                setParams(statement, params);
                return statement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
                throw new SQLException("update error");
            } finally {
                semaphore.release();
            }
        } catch (InterruptedException e) {
            throw new SQLException("semaphore error");
        }
    }

    public <T> List<T> execute(String query, Class<T> clazz, Object... params) throws SQLException {
        if (!query.split(" ")[0].equalsIgnoreCase("SELECT")) {
            execute(query, params);
        }
        try {
            semaphore.acquire();
            try (Connection connection = DriverManager.getConnection(url, user, password); PreparedStatement statement = connection.prepareStatement(query)) {
                setParams(statement, params);
                ResultSet resultSet = statement.executeQuery();
                return extractResults(resultSet, clazz);
            } catch (SQLException e) {
                throw new SQLException("select error");
            } finally {
                semaphore.release();
            }
        } catch (InterruptedException e) {
            throw new SQLException("semaphore error");
        }
    }

    private String extract(String str, String key) throws SQLException {
        if (!str.contains(key)) {
            throw new SQLException("parsing error usage :" + key + " [" + key + "]");
        }
        return str.replace(key, "");
    }

    private void setParams(PreparedStatement statement, Object... params) throws SQLException {
        for (int i = 0; i < params.length; i++) {
            statement.setObject(i + 1, params[i]);
        }
    }

    private <T> List<T> extractResults(ResultSet resultSet, Class<T> clazz) throws SQLException {
        List<T> list = new ArrayList<>();
        ResultSetMetaData metaData = resultSet.getMetaData();

        while (resultSet.next()) {
            T instance = createInstance(clazz);
            setFields(instance, metaData, resultSet);
            list.add(instance);
        }

        return list;
    }

    private <T> T createInstance(Class<T> clazz) throws SQLException {
        try {
            return clazz.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            e.printStackTrace();
            throw new SQLException("unable to create instance of " + clazz.getName());
        }
    }

    private <T> void setFields(T instance, ResultSetMetaData metaData, ResultSet resultSet) throws SQLException {
        for (int i = 1; i <= metaData.getColumnCount(); i++) {
            String columnName = metaData.getColumnName(i);
            Object value = resultSet.getObject(i);

            try {
                Field field = instance.getClass().getDeclaredField(columnName);
                field.setAccessible(true);
                if (Objects.nonNull(value)) {
                    field.set(instance, value);
                }
            } catch (NoSuchFieldException | IllegalAccessException e) {
                e.printStackTrace();
                throw new SQLException("unable to set value for " + columnName);
            }
        }
    }
}
