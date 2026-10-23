package com.banking.dao;

import com.banking.model.Beneficiary;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface BeneficiaryDAO {
    long create(Beneficiary beneficiary) throws SQLException;
    Optional<Beneficiary> findById(long beneficiaryId) throws SQLException;
    List<Beneficiary> findAll() throws SQLException;
    List<Beneficiary> findByCustomerId(long customerId) throws SQLException;
    boolean update(Beneficiary beneficiary) throws SQLException;
    boolean delete(long beneficiaryId) throws SQLException;
}
