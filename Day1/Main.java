package Day1;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("My firstName is: ");
        String FirstName = sc.nextLine();

        System.out.println("My LastName is : ");
        String LastName = sc.nextLine();
       

        System.out.println("My name is: " + FirstName + " " +  LastName);
    }
}