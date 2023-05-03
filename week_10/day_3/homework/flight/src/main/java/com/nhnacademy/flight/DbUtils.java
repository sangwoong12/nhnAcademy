package com.nhnacademy.flight;

import org.apache.commons.dbcp2.BasicDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbUtils {
    private static final DataSource DATASOURCE;

    private DbUtils() {
    }

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/module06",
                    "$(아이디}",
                    "${패스워드}");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static DataSource getDataSource() {
        return DATASOURCE;
    }

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        }
        catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        BasicDataSource basicDataSource = new BasicDataSource();
        basicDataSource.setUrl("jdbc:mysql://localhost:3306/module06");
        basicDataSource.setUsername("root");
        basicDataSource.setPassword("");
        basicDataSource.setInitialSize(10);
        basicDataSource.setMaxTotal(10);

        DATASOURCE = basicDataSource;
    }
}
