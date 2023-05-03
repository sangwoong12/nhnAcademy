package ex02;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public abstract class SQLHelper {
    private static final String connString = "jdbc:sqlserver://localhost:1433;databaseName=DataMotionMovieDatabase;user=sa;password=kogpsd1!;";
    private static final Map<String, Object[]> parameterCache = new HashMap<>();

    public static ResultSet executeReader(String sqlQuery) throws SQLException {
        Connection myConnection = DriverManager.getConnection(connString);
        PreparedStatement myCommand = myConnection.prepareStatement(sqlQuery, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);

        try {
            return myCommand.executeQuery();
        } catch (SQLException e) {
            myConnection.close();
            throw new RuntimeException(e);
        }
    }

    public static ResultSet executeReader(String sqlQuery, int commandType, Object... parameters) throws SQLException {
        Connection myConnection = DriverManager.getConnection(connString);
        PreparedStatement myCommand = myConnection.prepareStatement(sqlQuery, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);

        try {
            for (int i = 0; i < parameters.length; i++) {
                myCommand.setObject(i + 1, parameters[i]);
            }
            return myCommand.executeQuery();
        } catch (SQLException e) {
            myConnection.close();
            throw new RuntimeException(e);
        }
    }

    public static Object executeScalar(String sqlQuery) throws SQLException {
        try (Connection myConnection = DriverManager.getConnection(connString)) {
            PreparedStatement myCommand = myConnection.prepareStatement(sqlQuery);
            ResultSet resultSet = myCommand.executeQuery();
            if (resultSet.next()) {
                return resultSet.getObject(1);
            }
            return null;
        }
    }

    public static Object executeScalar(String sqlQuery, int commandType, Object... parameters) throws SQLException {
        try (Connection myConnection = DriverManager.getConnection(connString)) {
            PreparedStatement myCommand = myConnection.prepareStatement(sqlQuery);
            for (int i = 0; i < parameters.length; i++) {
                myCommand.setObject(i + 1, parameters[i]);
            }
            ResultSet resultSet = myCommand.executeQuery();
            if (resultSet.next()) {
                return resultSet.getObject(1);
            }
            return null;
        }
    }

    public static int executeNonQuery(String sqlQuery) throws SQLException {
        try (Connection myConnection = DriverManager.getConnection(connString)) {
            PreparedStatement myCommand = myConnection.prepareStatement(sqlQuery);
            return myCommand.executeUpdate();
        }
    }

    public static int executeNonQuery(String sqlQuery, int commandType, Object... parameters) throws SQLException {
        try (Connection myConnection = DriverManager.getConnection(connString)) {
            PreparedStatement myCommand = myConnection.prepareStatement(sqlQuery);
            for (int i = 0; i < parameters.length; i++) {
                myCommand.setObject(i + 1, parameters[i]);
            }
            return myCommand.executeUpdate();
        }
    }

    public static int executeNonQuery(String sqlQuery, Connection transactionConnection, Object... parameters) throws SQLException {
        try (PreparedStatement myCommand = transactionConnection.prepareStatement(sqlQuery)) {
            for (int i = 0; i < parameters.length; i++) {
                myCommand.setObject(i + 1, parameters[i]);
            }
            return myCommand.executeUpdate();
        }
    }

    public static ResultSet executeTable(String sqlQuery) throws SQLException {
        return executeReader(sqlQuery);
    }

    public static ResultSet executeTable(String sqlQuery, int commandType, Object... parameters) throws SQLException {
        return executeReader(sqlQuery, commandType, parameters);
    }

    public static ResultSet executeDataSet(String sqlQuery) throws SQLException {
        return executeReader(sqlQuery);
    }

    public static ResultSet executeDataSet(String sqlQuery, int commandType, Object... parameters) throws SQLException {
        return executeReader(sqlQuery, commandType, parameters);
    }

    public static void cacheParameters(String cacheKey, Object... parameters) {
        parameterCache.put(cacheKey, parameters);
    }

    public static Object[] getCachedParameters(String cacheKey) {
        Object[] cachedParameters = parameterCache.get(cacheKey);

        if (cachedParameters == null) {
            return null;
        }

        Object[] cloneParameters = new Object[cachedParameters.length];
        System.arraycopy(cachedParameters, 0, cloneParameters, 0, cachedParameters.length);

        return cloneParameters;
    }
}