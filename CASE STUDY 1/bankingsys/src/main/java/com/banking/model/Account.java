package com.banking.model;

import java.math.BigDecimal;

public class Account {
    private long accountId;
    private long customerId;
    private String accountType;
    private BigDecimal balance;

    public Account() {}

    public Account(long accountId, long customerId, String accountType, BigDecimal balance) {
        this.accountId = accountId;
        this.customerId = customerId;
        this.accountType = accountType;
        this.balance = balance;
    }

    public Account(long customerId, String accountType, BigDecimal balance) {
        this(0, customerId, accountType, balance);
    }

    public long getAccountId() { return accountId; }
    public void setAccountId(long accountId) { this.accountId = accountId; }
    public long getCustomerId() { return customerId; }
    public void setCustomerId(long customerId) { this.customerId = customerId; }
    public String getAccountType() { return accountType; }
    public void setAccountType(String accountType) { this.accountType = accountType; }
    public BigDecimal getBalance() { return balance; }
    public void setBalance(BigDecimal balance) { this.balance = balance; }

    @Override
    public String toString() {
        return "Account{id=" + accountId + ", customerId=" + customerId + ", type='"
                + accountType + "', balance=" + balance + "}";
    }
}
