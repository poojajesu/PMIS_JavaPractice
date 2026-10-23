package com.banking.dao.impl;

import com.banking.dao.BeneficiaryDAO;
import com.banking.model.Beneficiary;
import com.banking.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BeneficiaryDAOImpl implements BeneficiaryDAO {
    private static final String COLUMNS =
            "beneficiary_id, customer_id, beneficiary_name, account_number, bank_details";

    @Override
    public long create(Beneficiary beneficiary) throws SQLException {
        String sql = "INSERT INTO beneficiaries (customer_id, beneficiary_name, account_number, bank_details) "
                + "VALUES (?, ?, ?, ?)";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.setLong(1, beneficiary.getCustomerId());
            statement.setString(2, beneficiary.getBeneficiaryName());
            statement.setString(3, beneficiary.getAccountNumber());
            statement.setString(4, beneficiary.getBankDetails());
            statement.executeUpdate();
            try (ResultSet keys = statement.getGeneratedKeys()) {
                if (keys.next()) return keys.getLong(1);
                throw new SQLException("Creating beneficiary did not return an ID.");
            }
        }
    }

    @Override
    public Optional<Beneficiary> findById(long beneficiaryId) throws SQLException {
        String sql = "SELECT " + COLUMNS + " FROM beneficiaries WHERE beneficiary_id = ?";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, beneficiaryId);
            try (ResultSet result = statement.executeQuery()) {
                return result.next() ? Optional.of(map(result)) : Optional.empty();
            }
        }
    }

    @Override
    public List<Beneficiary> findAll() throws SQLException {
        return findUsing("SELECT " + COLUMNS + " FROM beneficiaries ORDER BY beneficiary_id", null);
    }

    @Override
    public List<Beneficiary> findByCustomerId(long customerId) throws SQLException {
        return findUsing("SELECT " + COLUMNS
                + " FROM beneficiaries WHERE customer_id = ? ORDER BY beneficiary_id", customerId);
    }

    private List<Beneficiary> findUsing(String sql, Long customerId) throws SQLException {
        List<Beneficiary> beneficiaries = new ArrayList<>();
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            if (customerId != null) statement.setLong(1, customerId);
            try (ResultSet result = statement.executeQuery()) {
                while (result.next()) beneficiaries.add(map(result));
            }
        }
        return beneficiaries;
    }

    @Override
    public boolean update(Beneficiary beneficiary) throws SQLException {
        String sql = "UPDATE beneficiaries SET beneficiary_name = ?, account_number = ?, bank_details = ? "
                + "WHERE beneficiary_id = ?";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, beneficiary.getBeneficiaryName());
            statement.setString(2, beneficiary.getAccountNumber());
            statement.setString(3, beneficiary.getBankDetails());
            statement.setLong(4, beneficiary.getBeneficiaryId());
            return statement.executeUpdate() == 1;
        }
    }

    @Override
    public boolean delete(long beneficiaryId) throws SQLException {
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "DELETE FROM beneficiaries WHERE beneficiary_id = ?")) {
            statement.setLong(1, beneficiaryId);
            return statement.executeUpdate() == 1;
        }
    }

    private Beneficiary map(ResultSet result) throws SQLException {
        return new Beneficiary(result.getLong("beneficiary_id"), result.getLong("customer_id"),
                result.getString("beneficiary_name"), result.getString("account_number"),
                result.getString("bank_details"));
    }
}
