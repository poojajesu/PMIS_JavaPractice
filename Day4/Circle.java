// Write a function that takes in the radius as input and returns the circumference of a circle. 



import java.util.Scanner;

public class Circle {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter radius: ");
        double r = sc.nextDouble();

        double circumference = 2 * 3.14 * r;

        System.out.println("Circumference = " + circumference);
    }
}
