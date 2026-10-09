package Day6;

// Q1- The Special Number


import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.regex.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;



class Result {
    
public static int isSpecialNumber(int n) {
    int original = n;
    int sum = 0;

    while (n > 0) {
        int digit = n % 10;
        int factorial = 1;

        for (int i = 1; i <= digit; i++) {
            factorial *= i;
        }

        sum += factorial;
        n /= 10;
    }

    return (sum == original) ? 1 : 0;
}


}

public class Q1 {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int n = Integer.parseInt(bufferedReader.readLine().trim());

        int result = Result.isSpecialNumber(n);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}
