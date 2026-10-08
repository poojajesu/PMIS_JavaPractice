package Day5OOPS;

class CoffeeWallet {

    String name;
    double balance;

    CoffeeWallet(String name, double balance) {
        this.name = name;
        this.balance = balance;
    }

    void addFunds(double amount) {
        balance = balance + amount;
        System.out.println("Added: ₹" + amount);
        System.out.println("Balance: ₹" + balance);
    }

    void purchase(double amount) {
        if (amount <= balance) {
            balance = balance - amount;
            System.out.println("Purchase successful: ₹" + amount);
            System.out.println("Balance: ₹" + balance);
        } else {
            System.out.println("Insufficient funds!");
        }
    }

    void display() {
        System.out.println("Name: " + name);
        System.out.println("Current Balance: ₹" + balance);
    }
}

public class Constructor1 {

    public static void main(String[] args) {

        CoffeeWallet wallet = new CoffeeWallet("Pooja", 500);

        wallet.display();

        wallet.addFunds(200);

        wallet.purchase(150);

        wallet.purchase(800);

        wallet.display();
    }
}