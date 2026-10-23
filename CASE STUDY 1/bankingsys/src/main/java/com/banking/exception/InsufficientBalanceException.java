package com.banking.exception;

import java.math.BigDecimal;

public class InsufficientBalanceException extends RuntimeException {
    public InsufficientBalanceException(BigDecimal available, BigDecimal requested) {
        super("Insufficient balance. Available: " + available + ", requested: " + requested);
    }
}
