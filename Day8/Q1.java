package Day8;

public class Q1 {
    public static void main(String[] args) {
        String str = "10,20,30,40";
        String[] arr = str.split(",");
        int sum = 0;

        for (int i = 0; i < arr.length; i++) {
            sum = sum + Integer.parseInt(arr[i]);
        }

        System.out.println("Sum = " + sum);
    }
}