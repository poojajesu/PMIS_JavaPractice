package com.banking.dao.impl;

import com.banking.dao.TransactionDAO;
import com.banking.exception.AccountNotFoundException;
import com.banking.exception.InsufficientBalanceException;
import com.banking.model.Account;
import com.banking.model.Transaction;
import com.banking.util.DBConnection;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TransactionDAOImpl implements TransactionDAO {
    @Override
    public void deposit(long accountId, BigDecimal amount) throws SQLException {
        requirePositiveAmount(amount);
        inTransaction(connection -> {
            Account account = lockAccount(connection, accountId);
            setBalance(connection, accountId, account.getBalance().add(amount));
            insertTransaction(connection, accountId, "DEPOSIT", amount);
        });
    }

    @Override
    public void withdraw(long accountId, BigDecimal amount) throws SQLException {
        requirePositiveAmount(amount);
        inTransaction(connection -> {
            Account account = lockAccount(connection, accountId);
            if (account.getBalance().compareTo(amount) < 0) {
                throw new InsufficientBalanceException(account.getBalance(), amount);
            }
            setBalance(connection, accountId, account.getBalance().subtract(amount));
            insertTransaction(connection, accountId, "WITHDRAWAL", amount);
        });
    }

    @Override
    public void transfer(long senderAccountId, long receiverAccountId, BigDecimal amount) throws SQLException {
        requirePositiveAmount(amount);
        if (senderAccountId == receiverAccountId) {
            throw new IllegalArgumentException("Sender and receiver accounts must be different.");
        }
        inTransaction(connection -> {
            long firstId = Math.min(senderAccountId, receiverAccountId);
            long secondId = Math.max(senderAccountId, receiverAccountId);
            Map<Long, Account> lockedAccounts = new HashMap<>();
            lockedAccounts.put(firstId, lockAccount(connection, firstId));
            lockedAccounts.put(secondId, lockAccount(connection, secondId));
            Account sender = lockedAccounts.get(senderAccountId);
            Account receiver = lockedAccounts.get(receiverAccountId);

            if (sender.getBalance().compareTo(amount) < 0) {
                throw new InsufficientBalanceException(sender.getBalance(), amount);
            }
            setBalance(connection, senderAccountId, sender.getBalance().subtract(amount));
            setBalance(connection, receiverAccountId, receiver.getBalance().add(amount));
            insertTransaction(connection, senderAccountId, "TRANSFER_OUT", amount);
            insertTransaction(connection, receiverAccountId, "TRANSFER_IN", amount);
        });
    }

    @Override
    public List<Transaction> findByAccountId(long accountId) throws SQLException {
        String sql = "SELECT transaction_id, account_id, transaction_type, amount, transaction_date "
                + "FROM bank_transactions WHERE account_id = ? ORDER BY transaction_date DESC, transaction_id DESC";
        List<Transaction> transactions = new ArrayList<>();
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, accountId);
            try (ResultSet result = statement.executeQuery()) {
                while (result.next()) {
                    transactions.add(new Transaction(result.getLong("transaction_id"),
                            result.getLong("account_id"), result.getString("transaction_type"),
                            result.getBigDecimal("amount"),
                            result.getTimestamp("transaction_date").toLocalDateTime()));
                }
            }
        }
        return transactions;
    }

    private Account lockAccount(Connection connection, long accountId) throws SQLException {
        String sql = "SELECT account_id, customer_id, account_type, balance FROM accounts "
                + "WHERE account_id = ? FOR UPDATE";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, accountId);
            try (ResultSet result = statement.executeQuery()) {
                if (!result.next()) throw new AccountNotFoundException(accountId);
                return new Account(result.getLong("account_id"), result.getLong("customer_id"),
                        result.getString("account_type"), result.getBigDecimal("balance"));
            }
        }
    }

    private void setBalance(Connection connection, long accountId, BigDecimal balance) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(
                "UPDATE accounts SET balance = ? WHERE account_id = ?")) {
            statement.setBigDecimal(1, balance);
            statement.setLong(2, accountId);
            if (statement.executeUpdate() != 1) throw new AccountNotFoundException(accountId);
        }
    }

    private void insertTransaction(Connection connection, long accountId, String type, BigDecimal amount)
            throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(
                "INSERT INTO bank_transactions (account_id, transaction_type, amount) VALUES (?, ?, ?)")) {
            statement.setLong(1, accountId);
            statement.setString(2, type);
            statement.setBigDecimal(3, amount);
            statement.executeUpdate();
        }
    }

    private void inTransaction(SqlWork work) throws SQLException {
        try (Connection connection = DBConnection.getConnection()) {
            connection.setAutoCommit(false);
            try {
                work.run(connection);
                connection.commit();
            } catch (SQLException | RuntimeException exception) {
                try {
                    connection.rollback();
                } catch (SQLException rollbackException) {
                    exception.addSuppressed(rollbackException);
                }
                throw exception;
            }
        }
    }

    private void requirePositiveAmount(BigDecimal amount) {
        if (amount == null || amount.signum() <= 0) {
            throw new IllegalArgumentException("Amount must be greater than zero.");
        }
        if (amount.scale() > 2) {
            throw new IllegalArgumentException("Amount may have at most two decimal places.");
        }
    }

    @FunctionalInterface
    private interface SqlWork {
        void run(Connection connection) throws SQLException;
    }
}
