package com.banking.dao.impl;

import com.banking.dao.AccountDAO;
import com.banking.model.Account;
import com.banking.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AccountDAOImpl implements AccountDAO {
    private static final String COLUMNS = "account_id, customer_id, account_type, balance";

    @Override
    public long create(Account account) throws SQLException {
        String sql = "INSERT INTO accounts (customer_id, account_type, balance) VALUES (?, ?, ?)";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.setLong(1, account.getCustomerId());
            statement.setString(2, account.getAccountType());
            statement.setBigDecimal(3, account.getBalance());
            statement.executeUpdate();
            try (ResultSet keys = statement.getGeneratedKeys()) {
                if (keys.next()) return keys.getLong(1);
                throw new SQLException("Creating account did not return an ID.");
            }
        }
    }

    @Override
    public Optional<Account> findById(long accountId) throws SQLException {
        String sql = "SELECT " + COLUMNS + " FROM accounts WHERE account_id = ?";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, accountId);
            try (ResultSet result = statement.executeQuery()) {
                return result.next() ? Optional.of(map(result)) : Optional.empty();
            }
        }
    }

    @Override
    public List<Account> findAll() throws SQLException {
        return findUsing("SELECT " + COLUMNS + " FROM accounts ORDER BY account_id", null);
    }

    @Override
    public List<Account> findByCustomerId(long customerId) throws SQLException {
        return findUsing("SELECT " + COLUMNS + " FROM accounts WHERE customer_id = ? ORDER BY account_id",
                customerId);
    }

    private List<Account> findUsing(String sql, Long customerId) throws SQLException {
        List<Account> accounts = new ArrayList<>();
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            if (customerId != null) statement.setLong(1, customerId);
            try (ResultSet result = statement.executeQuery()) {
                while (result.next()) accounts.add(map(result));
            }
        }
        return accounts;
    }

    @Override
    public boolean update(Account account) throws SQLException {
        String sql = "UPDATE accounts SET account_type = ? WHERE account_id = ?";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, account.getAccountType());
            statement.setLong(2, account.getAccountId());
            return statement.executeUpdate() == 1;
        }
    }

    @Override
    public boolean delete(long accountId) throws SQLException {
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "DELETE FROM accounts WHERE account_id = ?")) {
            statement.setLong(1, accountId);
            return statement.executeUpdate() == 1;
        }
    }

    private Account map(ResultSet result) throws SQLException {
        return new Account(result.getLong("account_id"), result.getLong("customer_id"),
                result.getString("account_type"), result.getBigDecimal("balance"));
    }
}
