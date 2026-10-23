package com.banking.dao;

import com.banking.model.Customer;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface CustomerDAO {
    long create(Customer customer) throws SQLException;
    Optional<Customer> findById(long customerId) throws SQLException;
    List<Customer> findAll() throws SQLException;
    boolean update(Customer customer) throws SQLException;
    boolean delete(long customerId) throws SQLException;
}
