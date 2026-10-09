
// Q2 - Find the Second Largest Distinct Number


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

    public static int secondLargest(int n, List<Integer> arr) {
        int largest = Integer.MIN_VALUE;
        int second = Integer.MIN_VALUE;

        for (int i = 0; i < n; i++) {
            int num = arr.get(i);

            if (num > largest) {
                second = largest;
                largest = num;
            } else if (num < largest && num > second) {
                second = num;
            }
        }

        if (second == Integer.MIN_VALUE) {
            return -1;
        }

        return second;
    }
}

public class Q2 {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(
            new InputStreamReader(System.in)
        );

        BufferedWriter bufferedWriter = new BufferedWriter(
            new OutputStreamWriter(System.out)
        );

        int n = Integer.parseInt(bufferedReader.readLine().trim());

        List<Integer> arr = Stream.of(bufferedReader.readLine().trim().split("\\s+"))
            .map(Integer::parseInt)
            .collect(toList());

        int result = Result.secondLargest(n, arr);

        if (result == -1) {
            bufferedWriter.write("Not Possible");
        } else {
            bufferedWriter.write(String.valueOf(result));
        }

        bufferedWriter.newLine();
        bufferedReader.close();
        bufferedWriter.close();
    }
}
