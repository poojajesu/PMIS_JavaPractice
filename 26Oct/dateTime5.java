import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Set;
public class dateTime5 {
    public static void main(String[] args){

        ZonedDateTime now =  ZonedDateTime.now();

        Set<String>availableZones = ZoneId.getAvailableZoneIds();
        availableZones.forEach(System.out::println);
    }
}
