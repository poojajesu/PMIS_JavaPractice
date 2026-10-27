import java.util.ArrayList;
import java.util.List;

public class Customer {
    private String name;
    private List<Rental> rentalHistory;

    public Customer(String name) {
        this.name = name;
        this.rentalHistory = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void addRental(Rental rental) {
        rentalHistory.add(rental);
    }

    public List<Rental> getRentalHistory() {
        return rentalHistory;
    }
}