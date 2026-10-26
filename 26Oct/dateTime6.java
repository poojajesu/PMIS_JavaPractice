import java.util.Scanner;
import java.time.LocalTime;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Set;




public class dateTime6 {
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter a title zone(eg. Asia/Tokyo): ");
        String zoneInput = sc.nextLine();

        try{
            ZoneId zone = ZoneId.of(zoneInput);
            ZonedDateTime currentTime = ZonedDateTime.now(zone);
            System.out.println("Time zone: " + zone);
            System.out.println("Current date and time " + currentTime);
        } catch(java.time.DateTimeException e){
            System.out.println("Invalid time zone!" + "Please enter a valid zone ID");

        }
        sc.close();

    }
}
