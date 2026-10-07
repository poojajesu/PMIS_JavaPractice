
// Write a function which takes in 2 numbers and returns the greater of those two. 


import java.util.Scanner;

public class GreaterNo {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter 2 numbers: ");
        int a = sc.nextInt();
        int b = sc.nextInt();

        if (a > b) {
            System.out.println("Greater = " + a);
        } else {
            System.out.println("Greater = " + b);
        }
    }
}
