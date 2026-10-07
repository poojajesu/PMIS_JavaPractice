
// Enter 3 numbers from the user & make a function to print their average.

import java.util.Scanner;

public class Main {

    static void average(int a, int b, int c) {
        System.out.println((a + b + c) / 3.0);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int a = sc.nextInt();
        int b = sc.nextInt();
        int c = sc.nextInt();

        average(a, b, c);
    }
}
