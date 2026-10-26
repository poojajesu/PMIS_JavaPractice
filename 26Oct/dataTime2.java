import java.time.LocalDate;

class dataTime2{
    public static void main(String args[]){

        LocalDate now = LocalDate.now();

        System.out.println(now);
        LocalDate yesterday = now.minusDays(1);
        System.out.println("\nYesterday was" +yesterday);

        LocalDate pastDate = now.minusMonths(100);

        System.out.println("100 months before was " + pastDate);


    }
}