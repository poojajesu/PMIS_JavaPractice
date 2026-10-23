package com.banking.dao;

import com.banking.model.Transaction;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

public interface TransactionDAO {
    void deposit(long accountId, BigDecimal amount) throws SQLException;
    void withdraw(long accountId, BigDecimal amount) throws SQLException;
    void transfer(long senderAccountId, long receiverAccountId, BigDecimal amount) throws SQLException;
    List<Transaction> findByAccountId(long accountId) throws SQLException;
}
