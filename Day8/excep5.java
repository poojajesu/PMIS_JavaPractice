package Day8;
import java.util.Scanner;

public class excep5 {
  public static void main(String args[]){
    Scanner sc = new Scanner(System.in);

    int a = sc.nextInt();
    int b = sc. nextInt();

    try{
        int result = a/b;
        System.out.println(result);
    } catch(ArithmeticException e){
        System.out.println("Cannot divide by zero");
    }

    sc.close();

  }
    
}
