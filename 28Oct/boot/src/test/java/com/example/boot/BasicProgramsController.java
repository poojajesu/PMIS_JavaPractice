package com.spring.boot;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BasicProgramsController {

    // 1. Even or Odd
    @GetMapping("/basics/even-or-odd")
    public String evenOrOdd(@RequestParam int number) {
        if (number % 2 == 0) {
            return number + " is even.";
        }
        return number + " is odd.";
    }

    // 2. Grade Calculator
    @GetMapping("/basics/grade")
    public String grade(@RequestParam int marks) {
        if (marks < 0 || marks > 100) {
            return "Marks must be between 0 and 100.";
        } else if (marks >= 90) {
            return "Grade A";
        } else if (marks >= 75) {
            return "Grade B";
        } else if (marks >= 50) {
            return "Grade C";
        }
        return "Grade F";
    }

    // 3. Person Introduction
    @GetMapping("/basics/person")
    public String person(
            @RequestParam String name,
            @RequestParam int age) {

        Person person = new Person(name, age);
        return person.introduce();
    }

    // 4. Positive, Negative, or Zero
    @GetMapping("/basics/positive-negative")
    public String positiveNegative(@RequestParam int number) {
        if (number > 0) {
            return "Positive number";
        } else if (number < 0) {
            return "Negative number";
        }
        return "Zero";
    }

    // 5. Largest of Two Numbers
    @GetMapping("/basics/largest")
    public String largest(@RequestParam int a,
                          @RequestParam int b) {
        if (a > b) {
            return "Largest number is " + a;
        } else if (b > a) {
            return "Largest number is " + b;
        }
        return "Both numbers are equal";
    }

    // 6. Largest of Three Numbers
    @GetMapping("/basics/largest-three")
    public String largestThree(@RequestParam int a,
                               @RequestParam int b,
                               @RequestParam int c) {
        int largest = Math.max(a, Math.max(b, c));
        return "Largest number is " + largest;
    }

    // 7. Leap Year
    @GetMapping("/basics/leap-year")
    public String leapYear(@RequestParam int year) {
        if (year % 400 == 0 ||
                (year % 4 == 0 && year % 100 != 0)) {
            return year + " is a leap year.";
        }
        return year + " is not a leap year.";
    }

    // 8. Multiplication Table
    @GetMapping("/basics/table")
    public String multiplicationTable(@RequestParam int number) {
        StringBuilder result = new StringBuilder();

        for (int i = 1; i <= 10; i++) {
            result.append(number)
                    .append(" x ")
                    .append(i)
                    .append(" = ")
                    .append(number * i)
                    .append("\n");
        }

        return result.toString();
    }

    // 9. Factorial
    @GetMapping("/basics/factorial")
    public String factorial(@RequestParam int number) {
        if (number < 0) {
            return "Factorial is not defined for negative numbers.";
        }
        if (number > 20) {
            return "Enter a number between 0 and 20.";
        }

        long fact = 1;

        for (int i = 1; i <= number; i++) {
            fact *= i;
        }

        return "Factorial is " + fact;
    }

    // 10. Prime Number
    @GetMapping("/basics/prime")
    public String prime(@RequestParam int number) {
        if (number < 2) {
            return number + " is not prime.";
        }

        for (int i = 2; i <= number / i; i++) {
            if (number % i == 0) {
                return number + " is not prime.";
            }
        }

        return number + " is prime.";
    }

    // 11. Reverse a Number
    @GetMapping("/basics/reverse")
    public String reverse(@RequestParam int number) {
        long n = Math.abs((long) number);
        long reversed = 0;

        while (n > 0) {
            reversed = reversed * 10 + n % 10;
            n = n / 10;
        }

        if (number < 0) {
            reversed = -reversed;
        }

        return "Reversed number is " + reversed;
    }

    // 12. Palindrome String
    @GetMapping("/basics/palindrome")
    public String palindrome(@RequestParam String text) {
        String original = text.toLowerCase();
        String reversed = new StringBuilder(original)
                .reverse().toString();

        if (original.equals(reversed)) {
            return text + " is a palindrome.";
        }

        return text + " is not a palindrome.";
    }

    // 13. Sum of Digits
    @GetMapping("/basics/sum-digits")
    public String sumDigits(@RequestParam int number) {
        long n = Math.abs((long) number);
        int sum = 0;

        while (n > 0) {
            sum += n % 10;
            n /= 10;
        }

        return "Sum of digits is " + sum;
    }

    // 14. Armstrong Number
    @GetMapping("/basics/armstrong")
    public String armstrong(@RequestParam int number) {
        if (number < 0) {
            return number + " is not an Armstrong number.";
        }

        String digits = String.valueOf(number);
        int power = digits.length();
        long sum = 0;

        for (char digit : digits.toCharArray()) {
            int value = digit - '0';
            sum += (long) Math.pow(value, power);
        }

        if (sum == number) {
            return number + " is an Armstrong number.";
        }

        return number + " is not an Armstrong number.";
    }

    // 15. Sum from 1 to N
    @GetMapping("/basics/sum-n")
    public String sumN(@RequestParam int number) {
        if (number < 0) {
            return "Enter a non-negative number.";
        }

        long sum = (long) number * (number + 1) / 2;
        return "Sum is " + sum;
    }

    // 16. Celsius to Fahrenheit
    @GetMapping("/basics/celsius-to-fahrenheit")
    public String celsiusToFahrenheit(
            @RequestParam double celsius) {

        double fahrenheit = (celsius * 9 / 5) + 32;

        return celsius + " Celsius = "
                + fahrenheit + " Fahrenheit";
    }
}