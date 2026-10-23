package com.banking.app;

import com.banking.model.Account;
import com.banking.model.Beneficiary;
import com.banking.model.Customer;
import com.banking.service.BankingService;
import com.banking.service.impl.BankingServiceImpl;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Scanner;

public class BankingSystemApp {
    private final Scanner scanner = new Scanner(System.in);
    private final BankingService bankingService = new BankingServiceImpl();

    public static void main(String[] args) {
        new BankingSystemApp().run();
    }

    private void run() {
        System.out.println("Welcome to the Banking Management System");
        boolean running = true;
        while (running) {
            printMenu();
            try {
                int choice = readInt("Choose an option: ");
                if (choice == 20) {
                    running = false;
                } else {
                    handle(choice);
                }
            } catch (IllegalArgumentException exception) {
                System.out.println("Invalid input: " + exception.getMessage());
            } catch (SQLException exception) {
                System.out.println("Database operation failed: " + exception.getMessage());
            } catch (RuntimeException exception) {
                System.out.println("Operation failed: " + exception.getMessage());
            }
        }
        System.out.println("Thank you for using the Banking Management System.");
    }

    private void printMenu() {
        System.out.println("""

                1. Add Customer              2. View Customer
                3. Update Customer           4. Delete Customer
                5. Create Account            6. View Account
                7. Update Account            8. Delete Account
                9. Add Beneficiary          10. View Beneficiary
                11. Update Beneficiary      12. Delete Beneficiary
                13. Deposit                 14. Withdraw
                15. Transfer Money          16. Check Balance
                17. Transaction History     18. View All Customers
                19. View All Accounts        20. Exit
                21. View All Beneficiaries
                """);
    }

    private void handle(int choice) throws SQLException {
        switch (choice) {
            case 1 -> addCustomer();
            case 2 -> System.out.println(bankingService.getCustomer(readLong("Customer ID: ")));
            case 3 -> updateCustomer();
            case 4 -> bankingService.deleteCustomer(readLong("Customer ID: "));
            case 5 -> createAccount();
            case 6 -> System.out.println(bankingService.getAccount(readLong("Account ID: ")));
            case 7 -> updateAccount();
            case 8 -> bankingService.deleteAccount(readLong("Account ID: "));
            case 9 -> addBeneficiary();
            case 10 -> System.out.println(bankingService.getBeneficiary(readLong("Beneficiary ID: ")));
            case 11 -> updateBeneficiary();
            case 12 -> bankingService.deleteBeneficiary(readLong("Beneficiary ID: "));
            case 13 -> bankingService.deposit(readLong("Account ID: "), readMoney("Deposit amount: "));
            case 14 -> bankingService.withdraw(readLong("Account ID: "), readMoney("Withdrawal amount: "));
            case 15 -> bankingService.transfer(readLong("Sender account ID: "),
                    readLong("Receiver account ID: "), readMoney("Transfer amount: "));
            case 16 -> System.out.println("Balance: " + bankingService.getBalance(readLong("Account ID: ")));
            case 17 -> printList(bankingService.getTransactionHistory(readLong("Account ID: ")));
            case 18 -> printList(bankingService.getAllCustomers());
            case 19 -> printList(bankingService.getAllAccounts());
            case 21 -> printList(bankingService.getAllBeneficiaries());
            default -> System.out.println("Please choose a listed menu option.");
        }
    }

    private void addCustomer() throws SQLException {
        Customer customer = new Customer(readRequired("Name: "), readRequired("Address: "),
                readRequired("Contact number: "));
        System.out.println("Customer created with ID " + bankingService.addCustomer(customer));
    }

    private void updateCustomer() throws SQLException {
        long id = readLong("Customer ID: ");
        Customer customer = new Customer(id, readRequired("New name: "),
                readRequired("New address: "), readRequired("New contact: "));
        bankingService.updateCustomer(customer);
        System.out.println("Customer updated.");
    }

    private void createAccount() throws SQLException {
        long customerId = readLong("Customer ID: ");
        String type = readRequired("Account type (for example CHECKING or SAVINGS): ");
        BigDecimal openingBalance = readMoneyAllowingZero("Opening balance: ");
        long accountId = bankingService.createAccount(new Account(customerId, type, openingBalance));
        System.out.println("Account created with ID " + accountId);
    }

    private void updateAccount() throws SQLException {
        long accountId = readLong("Account ID: ");
        Account account = bankingService.getAccount(accountId);
        account.setAccountType(readRequired("New account type: "));
        bankingService.updateAccount(account);
        System.out.println("Account updated. Balance is changed only through deposit, withdrawal, or transfer.");
    }

    private void addBeneficiary() throws SQLException {
        long customerId = readLong("Customer ID: ");
        Beneficiary beneficiary = new Beneficiary(customerId,
                readRequired("Beneficiary name: "), readRequired("Beneficiary account number: "),
                readRequired("Bank details: "));
        System.out.println("Beneficiary added with ID " + bankingService.addBeneficiary(beneficiary));
    }

    private void updateBeneficiary() throws SQLException {
        long id = readLong("Beneficiary ID: ");
        Beneficiary beneficiary = bankingService.getBeneficiary(id);
        beneficiary.setBeneficiaryName(readRequired("New beneficiary name: "));
        beneficiary.setAccountNumber(readRequired("New beneficiary account number: "));
        beneficiary.setBankDetails(readRequired("New bank details: "));
        bankingService.updateBeneficiary(beneficiary);
        System.out.println("Beneficiary updated.");
    }

    private String readRequired(String prompt) {
        System.out.print(prompt);
        String value = scanner.nextLine().trim();
        if (value.isEmpty()) throw new IllegalArgumentException("Value cannot be empty.");
        return value;
    }

    private int readInt(String prompt) {
        String input = readRequired(prompt);
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException exception) {
            throw new IllegalArgumentException("Enter a whole-number menu choice.");
        }
    }

    private long readLong(String prompt) {
        String input = readRequired(prompt);
        try {
            long value = Long.parseLong(input);
            if (value <= 0) throw new IllegalArgumentException("ID must be positive.");
            return value;
        } catch (NumberFormatException exception) {
            throw new IllegalArgumentException("Enter a valid positive whole-number ID.");
        }
    }

    private BigDecimal readMoney(String prompt) {
        return parseMoney(readRequired(prompt), false);
    }

    private BigDecimal readMoneyAllowingZero(String prompt) {
        return parseMoney(readRequired(prompt), true);
    }

    private BigDecimal parseMoney(String input, boolean allowZero) {
        try {
            BigDecimal amount = new BigDecimal(input);
            if (allowZero ? amount.signum() < 0 : amount.signum() <= 0) {
                throw new IllegalArgumentException(allowZero
                        ? "Balance cannot be negative." : "Amount must be greater than zero.");
            }
            if (amount.scale() > 2) throw new IllegalArgumentException("Use at most two decimal places.");
            return amount;
        } catch (NumberFormatException exception) {
            throw new IllegalArgumentException("Enter a valid monetary amount.");
        }
    }

    private void printList(Iterable<?> values) {
        boolean any = false;
        for (Object value : values) {
            System.out.println(value);
            any = true;
        }
        if (!any) System.out.println("No records found.");
    }
}
