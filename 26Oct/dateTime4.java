import java.time.LocalTime;


public class dateTime4 {
    public static void main(String[] args){
        LocalTime now = LocalTime.now();
        System.out.println(now);

        LocalTime customTime = LocalTime.of(11,32,45);
        System.out.println(customTime);
        LocalTime par = LocalTime.parse("15:30:45");
        System.out.println(par);

    }
}
