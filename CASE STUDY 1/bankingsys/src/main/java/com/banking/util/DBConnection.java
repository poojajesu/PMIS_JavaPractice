package com.banking.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static final String DEFAULT_URL =
            "jdbc:mysql://localhost:3306/banking_sys";

    private DBConnection() {
    }

    public static Connection getConnection() throws SQLException {
        String url = getSetting("BANKING_DB_URL", DEFAULT_URL);
        String username = getSetting("BANKING_DB_USERNAME", "root");
        String password = getSetting("BANKING_DB_PASSWORD", "Pooja@2003");
        try {
            return DriverManager.getConnection(url, username, password);
        } catch (SQLException exception) {
            throw new SQLException(
                    "Could not connect to MySQL. Verify that MySQL is running, database 'banking_sys' "
                            + "exists, and BANKING_DB_USERNAME/BANKING_DB_PASSWORD match your MySQL "
                            + "credentials. MySQL error: " + exception.getMessage(),
                    exception.getSQLState(), exception.getErrorCode(), exception);
        }
    }

    private static String getSetting(String name, String defaultValue) {
        String systemProperty = System.getProperty(name);
        if (systemProperty != null) {
            return systemProperty;
        }
        return System.getenv().getOrDefault(name, defaultValue);
    }
}