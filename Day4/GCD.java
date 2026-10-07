//Write a function that calculates the Greatest Common Divisor of 2 numbers.



import java.util.Scanner;

public class GCD {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter 2 numbers: ");
        int a = sc.nextInt();
        int b = sc.nextInt();

        int gcd = 1;

        for (int i = 1; i <= a && i <= b; i++) {
            if (a % i == 0 && b % i == 0) {
                gcd = i;
            }
        }

        System.out.println("GCD = " + gcd);
    }
}
