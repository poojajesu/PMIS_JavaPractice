CREATE DATABASE IF NOT EXISTS banking_sys
    CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci;
USE banking_sys;

CREATE TABLE IF NOT EXISTS customer (
    customer_id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(120) NOT NULL,
    address VARCHAR(255) NOT NULL,
    contact VARCHAR(20) NOT NULL UNIQUE,
    CONSTRAINT chk_customer_name CHECK (CHAR_LENGTH(TRIM(name)) > 0),
    CONSTRAINT chk_customer_contact CHECK (contact REGEXP '^[+]?[0-9][0-9 -]{6,18}$')
) ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS accounts (
    account_id BIGINT PRIMARY KEY AUTO_INCREMENT,
    customer_id BIGINT NOT NULL,
    account_type VARCHAR(30) NOT NULL,
    balance DECIMAL(15, 2) NOT NULL DEFAULT 0.00,
    CONSTRAINT chk_account_type CHECK (CHAR_LENGTH(TRIM(account_type)) > 0),
    CONSTRAINT chk_account_balance CHECK (balance >= 0),
    CONSTRAINT fk_accounts_customer FOREIGN KEY (customer_id)
        REFERENCES customer(customer_id) ON UPDATE CASCADE ON DELETE CASCADE
) ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS beneficiaries (
    beneficiary_id BIGINT PRIMARY KEY AUTO_INCREMENT,
    customer_id BIGINT NOT NULL,
    beneficiary_name VARCHAR(120) NOT NULL,
    account_number VARCHAR(34) NOT NULL,
    bank_details VARCHAR(255) NOT NULL,
    CONSTRAINT uq_beneficiary_customer_account UNIQUE (customer_id, account_number),
    CONSTRAINT fk_beneficiaries_customer FOREIGN KEY (customer_id)
        REFERENCES customer(customer_id) ON UPDATE CASCADE ON DELETE CASCADE
) ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS bank_transactions (
    transaction_id BIGINT PRIMARY KEY AUTO_INCREMENT,
    account_id BIGINT NOT NULL,
    transaction_type VARCHAR(30) NOT NULL,
    amount DECIMAL(15, 2) NOT NULL,
    transaction_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT chk_transaction_type CHECK (
        transaction_type IN ('DEPOSIT', 'WITHDRAWAL', 'TRANSFER_IN', 'TRANSFER_OUT')
    ),
    CONSTRAINT chk_transaction_amount CHECK (amount > 0),
    CONSTRAINT fk_transactions_account FOREIGN KEY (account_id)
        REFERENCES accounts(account_id) ON UPDATE CASCADE ON DELETE RESTRICT,
    INDEX idx_transactions_account_date (account_id, transaction_date)
) ENGINE=InnoDB;

INSERT INTO customer (name, address, contact)
VALUES ('Alex Morgan', '12 Oak Street', '+15551234567'),
       ('Jamie Lee', '48 Pine Avenue', '+15557654321')
ON DUPLICATE KEY UPDATE name = VALUES(name);

INSERT INTO accounts (customer_id, account_type, balance)
SELECT customer_id, 'CHECKING', 1500.00 FROM customer
WHERE contact = '+15551234567'
  AND NOT EXISTS (SELECT 1 FROM accounts WHERE customer_id = customer.customer_id AND account_type = 'CHECKING');

INSERT INTO accounts (customer_id, account_type, balance)
SELECT customer_id, 'SAVINGS', 800.00 FROM customer
WHERE contact = '+15557654321'
  AND NOT EXISTS (SELECT 1 FROM accounts WHERE customer_id = customer.customer_id AND account_type = 'SAVINGS');

INSERT INTO beneficiaries (customer_id, beneficiary_name, account_number, bank_details)
SELECT customer_id, 'Taylor Reed', '9988776655', 'Example Bank, US'
FROM customer WHERE contact = '+15551234567'
ON DUPLICATE KEY UPDATE beneficiary_name = VALUES(beneficiary_name);

INSERT INTO bank_transactions (account_id, transaction_type, amount)
SELECT account_id, 'DEPOSIT', balance FROM accounts
WHERE account_type IN ('CHECKING', 'SAVINGS')
  AND balance > 0
  AND NOT EXISTS (SELECT 1 FROM bank_transactions WHERE bank_transactions.account_id = accounts.account_id);
