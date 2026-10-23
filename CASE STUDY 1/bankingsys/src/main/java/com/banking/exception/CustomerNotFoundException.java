package com.banking.exception;

public class CustomerNotFoundException extends RuntimeException {
    public CustomerNotFoundException(long customerId) {
        super("Customer not found: " + customerId);
    }
}
