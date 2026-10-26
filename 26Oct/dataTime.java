import java.time.LocalDate;
import java.time.Month;

class dataTime{
    public static void main(String[] args){

        LocalDate now = LocalDate.now();
        System.out.println("Todays date is " + now);

        int dayOdMonth = now.getDayOfMonth();
        Month month = now.getMonth();
        int year = now.getYear();

        System.out.println("Day" + dayOdMonth);
        System.out.println("Month " + month);
        System.out.println("Year" + year);


    }
}