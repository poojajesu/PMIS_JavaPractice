package com.banking.dao;

import com.banking.model.Account;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface AccountDAO {
    long create(Account account) throws SQLException;
    Optional<Account> findById(long accountId) throws SQLException;
    List<Account> findAll() throws SQLException;
    List<Account> findByCustomerId(long customerId) throws SQLException;
    boolean update(Account account) throws SQLException;
    boolean delete(long accountId) throws SQLException;
}
