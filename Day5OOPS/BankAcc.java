package Day5OOPS;
public class BankAcc {

    String accountHolder;
    double balance;

    // Constructor
    BankAcc(String accountHolder, double balance) {
        this.accountHolder = accountHolder;
        this.balance = balance;
    }

    // Deposit
    void deposit(double amount) {
        balance = balance + amount;

        System.out.println("Deposited: " + amount);
        System.out.println("New Balance: " + balance);
    }

    // Withdraw
    void withdraw(double amount) {

        if (amount <= balance) {
            balance = balance - amount;

            System.out.println("Withdrawal: " + amount);
            System.out.println("Updated Balance: " + balance);
        } else {
            System.out.println("Insufficient Balance");
        }
    }

    // Display account details
    void displayAccount() {
        System.out.println("Account Holder Name: " + accountHolder);
        System.out.println("Current Balance: " + balance);
    }

    // Main method
    public static void main(String[] args) {

        BankAcc b1 = new BankAcc("Pooja", 100000);

        b1.displayAccount();

        System.out.println();

        b1.deposit(5000);

        System.out.println();

        b1.withdraw(2000);

        System.out.println();

        b1.displayAccount();
    }
}