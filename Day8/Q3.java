package Day8;

import java.util.ArrayList;
import java.util.List;

public class Q3 {
    public static void main(String[] args) {
        ArrayList<Integer> numbers = new ArrayList<>();

        numbers.add(10);
        numbers.add(15);
        numbers.add(20);
        numbers.add(25);
        numbers.add(30);

        List<Integer> evens = new ArrayList<>();         
        for (Integer num : numbers) {             
                       if (num % 2 == 0) {                 
                        evens.add(num); 
                       }
                    }

        System.out.println(numbers);
    }
}