import java.util.HashMap;
import java.util.Map;

/**
 * UseCase3InventorySetup
 *
 * This class demonstrates centralized room inventory management
 * using a HashMap as a single source of truth.
 *
 * @author YourName
 * @version 3.1
 */

// Inventory Management Class
class RoomInventory {

    private Map<String, Integer> inventory;

    // Constructor - initialize inventory
    public RoomInventory() {
        inventory = new HashMap<>();

        // Initialize room availability
        inventory.put("Single Room", 5);
        inventory.put("Double Room", 3);
        inventory.put("Suite Room", 2);
    }

    // Get availability of a specific room type
    public int getAvailability(String roomType) {
        return inventory.getOrDefault(roomType, 0);
    }

    // Update availability (increase/decrease)
    public void updateAvailability(String roomType, int countChange) {
        int current = inventory.getOrDefault(roomType, 0);
        inventory.put(roomType, current + countChange);
    }

    // Display full inventory
    public void displayInventory() {
        System.out.println("---- Current Room Inventory ----\n");

        for (Map.Entry<String, Integer> entry : inventory.entrySet()) {
            System.out.println("Room Type: " + entry.getKey());
            System.out.println("Available: " + entry.getValue());
            System.out.println();
        }
    }
}
 class BookMyStayApp {

    /**
     * Main method - entry point
     */
    public static void main(String[] args) {

        System.out.println("=====================================");
        System.out.println("   Welcome to Book My Stay App");
        System.out.println("   Hotel Booking System v3.1");
        System.out.println("=====================================\n");

        // Initialize inventory
        RoomInventory inventory = new RoomInventory();

        // Display initial inventory
        inventory.displayInventory();

        // Example updates
        System.out.println("Updating inventory...\n");

        inventory.updateAvailability("Single Room", -1); // booking
        inventory.updateAvailability("Suite Room", +1);  // cancellation

        // Display updated inventory
        inventory.displayInventory();
    }
}