# Banking Management System

A Java 17, menu-driven banking management application demonstrating object-oriented design, JDBC persistence, and MySQL transaction handling.

## Features

- Create, view, update, delete, and list customers, accounts, and beneficiaries.
- Deposit, withdraw, transfer between accounts, check balances, and review account transaction history.
- Enforces positive IDs, required fields, contact-number format, non-negative balances, positive monetary operations, and two-decimal currency precision.
- Uses prepared statements for database operations and closes JDBC resources with try-with-resources.
- Deposits, withdrawals, and both sides of a transfer are committed or rolled back atomically. Account rows are locked while their balances are changed.
- Account balances can only be changed using financial operations; updating an account changes its type without altering its ledger.

## Technologies

- Java 17
- Maven
- JDBC
- MySQL 8 / MySQL Connector/J
- Collections Framework

## Project structure

```text
banking-management-system/
├── database/
│   └── schema.sql
├── src/main/java/com/banking/
│   ├── app/BankingSystemApp.java
│   ├── dao/                         # DAO interfaces
│   ├── dao/impl/                    # JDBC implementations
│   ├── exception/                   # Domain exceptions
│   ├── model/                       # Customer, Account, Beneficiary, Transaction
│   ├── service/BankingService.java
│   ├── service/impl/BankingServiceImpl.java
│   └── util/DBConnection.java
├── .gitignore
├── pom.xml
└── README.md
```

## Database setup

1. Install and start MySQL 8.
2. Run `database/schema.sql` using MySQL Workbench or the MySQL command-line client. The script creates the `banking_sys` database, tables, constraints, indexes, and sample records.
3. Set connection values in the environment before starting the app:

   | Variable | Default |
   | --- | --- |
   | `BANKING_DB_URL` | `jdbc:mysql://localhost:3306/banking_sys?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC` |
   | `BANKING_DB_USERNAME` | `root` |
   | `BANKING_DB_PASSWORD` | empty |

   For example, in PowerShell:

   ```powershell
   $env:BANKING_DB_USERNAME = "root"
   $env:BANKING_DB_PASSWORD = "your-local-mysql-password"
   mvn exec:java
   ```

   Replace the placeholder with the password configured for your local MySQL user. These environment settings apply only to the current PowerShell window. In IntelliJ, add the same variables under **Run | Edit Configurations | Environment variables**, then restart the application. The app does not assume the MySQL `root` account has a blank password.

Do not place real credentials in source control. Create a MySQL user with only the privileges needed for this application's database in non-local environments.

## Run instructions

### IntelliJ IDEA

1. Open the project directory containing `pom.xml` as a Maven project.
2. Configure the database environment variables in the run configuration.
3. Run `com.banking.app.BankingSystemApp`.

### Maven

From the project root, run:

```shell
mvn clean compile
mvn exec:java
```

The app connects to MySQL when an operation is selected. It prints the relevant failure if the connection is unavailable or a database constraint rejects an operation.

## Menu

The console menu covers customer, account, and beneficiary operations; deposit, withdrawal, transfer, balance check, and transaction history; and lists for all customers, accounts, and beneficiaries. Menu option 20 exits.

## Screenshots

Add application screenshots here after running the console application, for example:

```markdown
![Main menu](docs/screenshots/main-menu.png)
```

## Notes

- IDs are database-generated primary keys, so duplicates cannot be inserted through the application.
- Transaction history is retained by a restrictive foreign key: an account with transactions cannot be deleted, and a customer with such an account cannot be deleted until its ledger is handled according to the applicable retention policy.
- This sample is an educational console application. Production banking systems require additional controls such as authentication, authorization, audit controls, idempotency, monitoring, and operational security.
