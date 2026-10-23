package com.banking.dao.impl;

import com.banking.dao.CustomerDAO;
import com.banking.model.Customer;
import com.banking.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CustomerDAOImpl implements CustomerDAO {
    private static final String COLUMNS = "customer_id, name, address, contact";

    @Override
    public long create(Customer customer) throws SQLException {
        if (customer == null) {
            throw new IllegalArgumentException("Customer cannot be null.");
        }
        String sql = "INSERT INTO customer (name, address, contact) VALUES (?, ?, ?)";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, customer.getName());
            statement.setString(2, customer.getAddress());
            statement.setString(3, customer.getContact());
            statement.executeUpdate();
            try (ResultSet keys = statement.getGeneratedKeys()) {
                if (keys.next()) return keys.getLong(1);
                throw new SQLException("Creating customer did not return an ID.");
            }
        }
    }

    @Override
    public Optional<Customer> findById(long customerId) throws SQLException {
        String sql = "SELECT " + COLUMNS + " FROM customer WHERE customer_id = ?";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, customerId);
            try (ResultSet result = statement.executeQuery()) {
                return result.next() ? Optional.of(map(result)) : Optional.empty();
            }
        }
    }

    @Override
    public List<Customer> findAll() throws SQLException {
        String sql = "SELECT " + COLUMNS + " FROM customer ORDER BY customer_id";
        List<Customer> customers = new ArrayList<>();
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet result = statement.executeQuery()) {
            while (result.next()) customers.add(map(result));
        }
        return customers;
    }

    @Override
    public boolean update(Customer customer) throws SQLException {
        if (customer == null) {
            throw new IllegalArgumentException("Customer cannot be null.");
        }
        String sql = "UPDATE customer SET name = ?, address = ?, contact = ? WHERE customer_id = ?";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, customer.getName());
            statement.setString(2, customer.getAddress());
            statement.setString(3, customer.getContact());
            statement.setLong(4, customer.getCustomerId());
            return statement.executeUpdate() == 1;
        }
    }

    @Override
    public boolean delete(long customerId) throws SQLException {
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "DELETE FROM customer WHERE customer_id = ?")) {
            statement.setLong(1, customerId);
            return statement.executeUpdate() == 1;
        }
    }

    private Customer map(ResultSet result) throws SQLException {
        return new Customer(result.getLong("customer_id"), result.getString("name"),
                result.getString("address"), result.getString("contact"));
    }
}
