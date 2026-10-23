package com.banking.service;

import com.banking.model.Account;
import com.banking.model.Beneficiary;
import com.banking.model.Customer;
import com.banking.model.Transaction;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

public interface BankingService {
    long addCustomer(Customer customer) throws SQLException;
    Customer getCustomer(long customerId) throws SQLException;
    List<Customer> getAllCustomers() throws SQLException;
    void updateCustomer(Customer customer) throws SQLException;
    void deleteCustomer(long customerId) throws SQLException;

    long createAccount(Account account) throws SQLException;
    Account getAccount(long accountId) throws SQLException;
    List<Account> getAllAccounts() throws SQLException;
    List<Account> getAccountsForCustomer(long customerId) throws SQLException;
    void updateAccount(Account account) throws SQLException;
    void deleteAccount(long accountId) throws SQLException;

    long addBeneficiary(Beneficiary beneficiary) throws SQLException;
    Beneficiary getBeneficiary(long beneficiaryId) throws SQLException;
    List<Beneficiary> getAllBeneficiaries() throws SQLException;
    List<Beneficiary> getBeneficiariesForCustomer(long customerId) throws SQLException;
    void updateBeneficiary(Beneficiary beneficiary) throws SQLException;
    void deleteBeneficiary(long beneficiaryId) throws SQLException;

    void deposit(long accountId, BigDecimal amount) throws SQLException;
    void withdraw(long accountId, BigDecimal amount) throws SQLException;
    void transfer(long senderAccountId, long receiverAccountId, BigDecimal amount) throws SQLException;
    BigDecimal getBalance(long accountId) throws SQLException;
    List<Transaction> getTransactionHistory(long accountId) throws SQLException;
}
