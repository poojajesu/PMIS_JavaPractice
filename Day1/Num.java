// write a program to compare the num and check which is greater or equal

package Day1;

import java.util.Scanner;

public class Num {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter num1: ");
        int num1 = sc.nextInt();

        System.out.print("Enter num2: ");
        int num2 = sc.nextInt();

        if (num1 > num2) {
            System.out.println("Num1 is greater ");
        } 
        else if (num1 < num2) {
            System.out.println("Num2 is greater ");
        } 
        else {
            System.out.println(" equal");
        }
    }
}