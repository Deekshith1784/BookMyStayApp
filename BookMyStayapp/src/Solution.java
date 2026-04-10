import java.util.*;

/**
 * UseCase7AddOnServiceSelection
 *
 * This class demonstrates how optional add-on services can be
 * attached to reservations without modifying core booking logic.
 *
 * @author YourName
 * @version 7.0
 */

// Add-On Service Class
class AddOnService {
    private String serviceName;
    private double cost;

    public AddOnService(String serviceName, double cost) {
        this.serviceName = serviceName;
        this.cost = cost;
    }

    public String getServiceName() {
        return serviceName;
    }

    public double getCost() {
        return cost;
    }
}

// Add-On Service Manager
class AddOnServiceManager {

    // Map: Reservation ID -> List of Services
    private Map<String, List<AddOnService>> serviceMap = new HashMap<>();

    // Add service to a reservation
    public void addService(String reservationId, AddOnService service) {
        serviceMap
                .computeIfAbsent(reservationId, k -> new ArrayList<>())
                .add(service);

        System.out.println("Added service '" + service.getServiceName() +
                "' to Reservation ID: " + reservationId);
    }

    // Display services for a reservation
    public void displayServices(String reservationId) {
        List<AddOnService> services = serviceMap.get(reservationId);

        System.out.println("\n---- Services for Reservation ID: " + reservationId + " ----");

        if (services == null || services.isEmpty()) {
            System.out.println("No add-on services selected.");
            return;
        }

        for (AddOnService service : services) {
            System.out.println(service.getServiceName() + " - ₹" + service.getCost());
        }
    }

    // Calculate total service cost
    public double calculateTotalCost(String reservationId) {
        List<AddOnService> services = serviceMap.get(reservationId);

        double total = 0.0;

        if (services != null) {
            for (AddOnService service : services) {
                total += service.getCost();
            }
        }

        return total;
    }
}

// Main Class
class BookMyStayApp {

    public static void main(String[] args) {

        System.out.println("=====================================");
        System.out.println("   Welcome to Book My Stay App");
        System.out.println("   Hotel Booking System v7.0");
        System.out.println("=====================================\n");

        // Simulated reservation ID (from Use Case 6)
        String reservationId = "SI1";

        // Create service manager
        AddOnServiceManager manager = new AddOnServiceManager();

        // Create add-on services
        AddOnService breakfast = new AddOnService("Breakfast", 500.0);
        AddOnService wifi = new AddOnService("WiFi", 200.0);
        AddOnService airportPickup = new AddOnService("Airport Pickup", 1000.0);

        // Guest selects services
        manager.addService(reservationId, breakfast);
        manager.addService(reservationId, wifi);
        manager.addService(reservationId, airportPickup);

        // Display selected services
        manager.displayServices(reservationId);

        // Calculate total cost
        double totalCost = manager.calculateTotalCost(reservationId);

        System.out.println("\nTotal Add-On Cost: ₹" + totalCost);
    }
}