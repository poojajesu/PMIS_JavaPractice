package com.banking;

import com.banking.util.DBConnection;
import java.sql.Connection;
import java.sql.SQLException;

public class TestConnection {
    public static void main(String[] args) {
        try (Connection connection = DBConnection.getConnection()) {
            System.out.println("Database Connected Successfully");
        } catch (SQLException exception) {
            System.err.println("Database Connection Failed: " + exception.getMessage());
        }
    }
}