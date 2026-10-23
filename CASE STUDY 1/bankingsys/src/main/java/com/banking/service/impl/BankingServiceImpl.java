package com.banking.service.impl;

import com.banking.dao.AccountDAO;
import com.banking.dao.BeneficiaryDAO;
import com.banking.dao.CustomerDAO;
import com.banking.dao.TransactionDAO;
import com.banking.dao.impl.AccountDAOImpl;
import com.banking.dao.impl.BeneficiaryDAOImpl;
import com.banking.dao.impl.CustomerDAOImpl;
import com.banking.dao.impl.TransactionDAOImpl;
import com.banking.exception.AccountNotFoundException;
import com.banking.exception.CustomerNotFoundException;
import com.banking.model.Account;
import com.banking.model.Beneficiary;
import com.banking.model.Customer;
import com.banking.model.Transaction;
import com.banking.service.BankingService;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

public class BankingServiceImpl implements BankingService {
    private static final String CONTACT_PATTERN = "^\\+?[0-9][0-9 -]{6,18}$";

    private final CustomerDAO customerDAO;
    private final AccountDAO accountDAO;
    private final BeneficiaryDAO beneficiaryDAO;
    private final TransactionDAO transactionDAO;

    public BankingServiceImpl() {
        this(new CustomerDAOImpl(), new AccountDAOImpl(), new BeneficiaryDAOImpl(), new TransactionDAOImpl());
    }

    public BankingServiceImpl(CustomerDAO customerDAO, AccountDAO accountDAO,
                              BeneficiaryDAO beneficiaryDAO, TransactionDAO transactionDAO) {
        this.customerDAO = customerDAO;
        this.accountDAO = accountDAO;
        this.beneficiaryDAO = beneficiaryDAO;
        this.transactionDAO = transactionDAO;
    }

    @Override
    public long addCustomer(Customer customer) throws SQLException {
        validateCustomer(customer);
        return customerDAO.create(customer);
    }

    @Override
    public Customer getCustomer(long customerId) throws SQLException {
        requireId(customerId, "Customer ID");
        return customerDAO.findById(customerId).orElseThrow(() -> new CustomerNotFoundException(customerId));
    }

    @Override
    public List<Customer> getAllCustomers() throws SQLException {
        return customerDAO.findAll();
    }

    @Override
    public void updateCustomer(Customer customer) throws SQLException {
        if (customer == null) throw new IllegalArgumentException("Customer is required.");
        requireId(customer.getCustomerId(), "Customer ID");
        validateCustomer(customer);
        if (!customerDAO.update(customer)) throw new CustomerNotFoundException(customer.getCustomerId());
    }

    @Override
    public void deleteCustomer(long customerId) throws SQLException {
        requireId(customerId, "Customer ID");
        if (!customerDAO.delete(customerId)) throw new CustomerNotFoundException(customerId);
    }

    @Override
    public long createAccount(Account account) throws SQLException {
        if (account == null) throw new IllegalArgumentException("Account is required.");
        requireId(account.getCustomerId(), "Customer ID");
        requireText(account.getAccountType(), "Account type");
        requireMoney(account.getBalance(), true);
        getCustomer(account.getCustomerId());
        return accountDAO.create(account);
    }

    @Override
    public Account getAccount(long accountId) throws SQLException {
        requireId(accountId, "Account ID");
        return accountDAO.findById(accountId).orElseThrow(() -> new AccountNotFoundException(accountId));
    }

    @Override
    public List<Account> getAllAccounts() throws SQLException {
        return accountDAO.findAll();
    }

    @Override
    public List<Account> getAccountsForCustomer(long customerId) throws SQLException {
        getCustomer(customerId);
        return accountDAO.findByCustomerId(customerId);
    }

    @Override
    public void updateAccount(Account account) throws SQLException {
        if (account == null) throw new IllegalArgumentException("Account is required.");
        requireId(account.getAccountId(), "Account ID");
        requireText(account.getAccountType(), "Account type");
        if (!accountDAO.update(account)) throw new AccountNotFoundException(account.getAccountId());
    }

    @Override
    public void deleteAccount(long accountId) throws SQLException {
        requireId(accountId, "Account ID");
        if (!accountDAO.delete(accountId)) throw new AccountNotFoundException(accountId);
    }

    @Override
    public long addBeneficiary(Beneficiary beneficiary) throws SQLException {
        if (beneficiary == null) throw new IllegalArgumentException("Beneficiary is required.");
        requireId(beneficiary.getCustomerId(), "Customer ID");
        validateBeneficiary(beneficiary);
        getCustomer(beneficiary.getCustomerId());
        return beneficiaryDAO.create(beneficiary);
    }

    @Override
    public Beneficiary getBeneficiary(long beneficiaryId) throws SQLException {
        requireId(beneficiaryId, "Beneficiary ID");
        return beneficiaryDAO.findById(beneficiaryId)
                .orElseThrow(() -> new IllegalArgumentException("Beneficiary not found: " + beneficiaryId));
    }

    @Override
    public List<Beneficiary> getAllBeneficiaries() throws SQLException {
        return beneficiaryDAO.findAll();
    }

    @Override
    public List<Beneficiary> getBeneficiariesForCustomer(long customerId) throws SQLException {
        getCustomer(customerId);
        return beneficiaryDAO.findByCustomerId(customerId);
    }

    @Override
    public void updateBeneficiary(Beneficiary beneficiary) throws SQLException {
        if (beneficiary == null) throw new IllegalArgumentException("Beneficiary is required.");
        requireId(beneficiary.getBeneficiaryId(), "Beneficiary ID");
        validateBeneficiary(beneficiary);
        if (!beneficiaryDAO.update(beneficiary)) {
            throw new IllegalArgumentException("Beneficiary not found: " + beneficiary.getBeneficiaryId());
        }
    }

    @Override
    public void deleteBeneficiary(long beneficiaryId) throws SQLException {
        requireId(beneficiaryId, "Beneficiary ID");
        if (!beneficiaryDAO.delete(beneficiaryId)) {
            throw new IllegalArgumentException("Beneficiary not found: " + beneficiaryId);
        }
    }

    @Override
    public void deposit(long accountId, BigDecimal amount) throws SQLException {
        requireId(accountId, "Account ID");
        requireMoney(amount, false);
        transactionDAO.deposit(accountId, amount);
    }

    @Override
    public void withdraw(long accountId, BigDecimal amount) throws SQLException {
        requireId(accountId, "Account ID");
        requireMoney(amount, false);
        transactionDAO.withdraw(accountId, amount);
    }

    @Override
    public void transfer(long senderAccountId, long receiverAccountId, BigDecimal amount) throws SQLException {
        requireId(senderAccountId, "Sender account ID");
        requireId(receiverAccountId, "Receiver account ID");
        requireMoney(amount, false);
        transactionDAO.transfer(senderAccountId, receiverAccountId, amount);
    }

    @Override
    public BigDecimal getBalance(long accountId) throws SQLException {
        return getAccount(accountId).getBalance();
    }

    @Override
    public List<Transaction> getTransactionHistory(long accountId) throws SQLException {
        getAccount(accountId);
        return transactionDAO.findByAccountId(accountId);
    }

    private void validateCustomer(Customer customer) {
        if (customer == null) throw new IllegalArgumentException("Customer is required.");
        requireText(customer.getName(), "Name");
        requireText(customer.getAddress(), "Address");
        requireText(customer.getContact(), "Contact");
        if (!customer.getContact().trim().matches(CONTACT_PATTERN)) {
            throw new IllegalArgumentException("Contact must contain 7-19 digits, spaces, hyphens, and an optional leading +.");
        }
        customer.setName(customer.getName().trim());
        customer.setAddress(customer.getAddress().trim());
        customer.setContact(customer.getContact().trim());
    }

    private void validateBeneficiary(Beneficiary beneficiary) {
        if (beneficiary == null) throw new IllegalArgumentException("Beneficiary is required.");
        requireText(beneficiary.getBeneficiaryName(), "Beneficiary name");
        requireText(beneficiary.getAccountNumber(), "Beneficiary account number");
        requireText(beneficiary.getBankDetails(), "Bank details");
        beneficiary.setBeneficiaryName(beneficiary.getBeneficiaryName().trim());
        beneficiary.setAccountNumber(beneficiary.getAccountNumber().trim());
        beneficiary.setBankDetails(beneficiary.getBankDetails().trim());
    }

    private void requireMoney(BigDecimal amount, boolean allowZero) {
        if (amount == null || (allowZero ? amount.signum() < 0 : amount.signum() <= 0)) {
            throw new IllegalArgumentException(allowZero
                    ? "Balance cannot be negative." : "Amount must be greater than zero.");
        }
        if (amount.scale() > 2) throw new IllegalArgumentException("Money values may have at most two decimal places.");
    }

    private void requireId(long id, String label) {
        if (id <= 0) throw new IllegalArgumentException(label + " must be a positive number.");
    }

    private void requireText(String value, String label) {
        if (value == null || value.isBlank()) throw new IllegalArgumentException(label + " cannot be empty.");
    }
}
