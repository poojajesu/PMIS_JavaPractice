public class Truck extends Vehicle {

    public Truck(String id, String model, double dailyRate) {
        super(id, model, dailyRate);
    }

    @Override
    public double calculateRent(int days) {
        return (dailyRate * days) + 500;
    }
}