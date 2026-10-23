package com.banking.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Transaction {
    private long transactionId;
    private long accountId;
    private String transactionType;
    private BigDecimal amount;
    private LocalDateTime transactionDate;

    public Transaction() {}

    public Transaction(long transactionId, long accountId, String transactionType,
                       BigDecimal amount, LocalDateTime transactionDate) {
        this.transactionId = transactionId;
        this.accountId = accountId;
        this.transactionType = transactionType;
        this.amount = amount;
        this.transactionDate = transactionDate;
    }

    public long getTransactionId() { return transactionId; }
    public void setTransactionId(long transactionId) { this.transactionId = transactionId; }
    public long getAccountId() { return accountId; }
    public void setAccountId(long accountId) { this.accountId = accountId; }
    public String getTransactionType() { return transactionType; }
    public void setTransactionType(String transactionType) { this.transactionType = transactionType; }
    public BigDecimal getAmount() { return amount; }
    public void setAmount(BigDecimal amount) { this.amount = amount; }
    public LocalDateTime getTransactionDate() { return transactionDate; }
    public void setTransactionDate(LocalDateTime transactionDate) { this.transactionDate = transactionDate; }

    @Override
    public String toString() {
        return "Transaction{id=" + transactionId + ", accountId=" + accountId + ", type='"
                + transactionType + "', amount=" + amount + ", date=" + transactionDate + "}";
    }
}
