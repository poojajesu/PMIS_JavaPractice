
/*Write a program to enter the numbers till the user wants and at the end 
it should display the count of positive, negative and zeros entered. */ 


import java.util.Scanner;

public class CountNum {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int positive = 0;
        int negative = 0;
        int zero = 0;
        int choice;

        do {
            System.out.print("Enter number: ");
            int n = sc.nextInt();

            if (n > 0)
                positive++;
            else if (n < 0)
                negative++;
            else
                zero++;

            System.out.print("Enter 1 to continue, 0 to stop: ");
            choice = sc.nextInt();

        } while (choice == 1);

        System.out.println("Positive = " + positive);
        System.out.println("Negative = " + negative);
        System.out.println("Zero = " + zero);
    }
}