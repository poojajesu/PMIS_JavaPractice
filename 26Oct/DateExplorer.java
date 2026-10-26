import java.time.LocalDate;
import java.time.DayOfWeek;
import java.util.Scanner;

public class DateExplorer {

    public static void displayMonth(LocalDate date) {
        System.out.println("Month: " + date.getMonth());
    }

    public static void displayYear(LocalDate date) {
        System.out.println("Year: " + date.getYear());

        if (date.isLeapYear()) {
            System.out.println("It is a leap year.");
        } else {
            System.out.println("It is not a leap year.");
        }
    }

    public static void displayDayOfWeek(LocalDate date) {
        DayOfWeek day = date.getDayOfWeek();
        System.out.println("Day of the week: " + day);
    }

    public static void lookBackMonths(LocalDate date, int months) {
        LocalDate previousDate = date.minusMonths(months);
        System.out.println("Date " + months + " months ago: " + previousDate);
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter a date (yyyy-MM-dd): ");
        String input = sc.nextLine();

        LocalDate date = LocalDate.parse(input);

        int choice;

        do {
            System.out.println("\n===== Date Explorer Menu =====");
            System.out.println("1. Display Month");
            System.out.println("2. Display Year and Check Leap Year");
            System.out.println("3. Display Day of the Week");
            System.out.println("4. Look Back by Months");
            System.out.println("5. Display Complete Date");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");

            choice = sc.nextInt();

            switch (choice) {

                case 1:
                    displayMonth(date);
                    break;

                case 2:
                    displayYear(date);
                    break;

                case 3:
                    displayDayOfWeek(date);
                    break;

                case 4:
                    System.out.print("Enter the number of months to look back: ");
                    int months = sc.nextInt();
                    lookBackMonths(date, months);
                    break;

                case 5:
                    System.out.println("Entered Date: " + date);
                    System.out.println("Month: " + date.getMonth());
                    System.out.println("Year: " + date.getYear());
                    System.out.println("Day of the Week: " + date.getDayOfWeek());
                    break;

                case 6:
                    System.out.println("Exiting Date Explorer. Goodbye!");
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }

        } while (choice != 6);

        sc.close();
    }
}