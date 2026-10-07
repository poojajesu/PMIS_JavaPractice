/*Two numbers are entered by the user, x and n.
   Write a function to find the value of one number
   raised to the power of another */


import java.util.Scanner;

public class PowerNum {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter x: ");
        int x = sc.nextInt();

        System.out.print("Enter n: ");
        int n = sc.nextInt();

        int result = 1;

        for (int i = 1; i <= n; i++) {
            result = result * x;
        }

        System.out.println("Answer = " + result);
    }
}
