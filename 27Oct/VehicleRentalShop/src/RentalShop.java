import java.util.ArrayList;
import java.util.List;

public class RentalShop {
    private List<Vehicle> fleet;

    public RentalShop() {
        fleet = new ArrayList<>();
    }

    public void addVehicle(Vehicle vehicle) {
        fleet.add(vehicle);
    }

    public void rent(String vehicleId, Customer customer, int days) {
        Vehicle selectedVehicle = null;

        for (Vehicle vehicle : fleet) {
            if (vehicle.getId().equals(vehicleId)) {
                selectedVehicle = vehicle;
                break;
            }
        }

        if (selectedVehicle == null) {
            throw new IllegalArgumentException(
                    "Vehicle " + vehicleId + " not found"
            );
        }

        if (!selectedVehicle.isAvailable()) {
            throw new IllegalStateException(
                    selectedVehicle.getVehicleName() + " is already rented"
            );
        }

        if (days <= 0) {
            throw new IllegalArgumentException(
                    "Rental days must be greater than zero"
            );
        }

        Rental rental = new Rental(selectedVehicle, customer, days);

        selectedVehicle.setAvailable(false);
        customer.addRental(rental);

        System.out.printf(
                "%s rented %s for %d days = %.2f%n",
                customer.getName(),
                selectedVehicle.getVehicleName(),
                days,
                rental.getTotalRent()
        );
    }

    public void giveBack(String vehicleId) {
        for (Vehicle vehicle : fleet) {
            if (vehicle.getId().equals(vehicleId)) {
                if (!vehicle.isAvailable()) {
                    vehicle.setAvailable(true);
                    System.out.println(
                            vehicle.getVehicleName() + " returned successfully"
                    );
                    return;
                }

                throw new IllegalStateException(
                        vehicle.getVehicleName() + " is not currently rented"
                );
            }
        }

        throw new IllegalArgumentException(
                "Vehicle " + vehicleId + " not found"
        );
    }

    public void showAvailable() {
        System.out.println("Available vehicles:");

        for (Vehicle vehicle : fleet) {
            if (vehicle.isAvailable()) {
                System.out.println("  " + vehicle.getVehicleName());
            }
        }
    }
}