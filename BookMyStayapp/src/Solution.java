import java.util.*;

/**
 * UseCase6RoomAllocationService
 *
 * This class demonstrates reservation confirmation and room allocation
 * with strict consistency, FIFO processing, and prevention of double-booking.
 *
 * @author YourName
 * @version 6.0
 */

// Reservation (Booking Request)
class Reservation {
    private String guestName;
    private String roomType;

    public Reservation(String guestName, String roomType) {
        this.guestName = guestName;
        this.roomType = roomType;
    }

    public String getGuestName() {
        return guestName;
    }

    public String getRoomType() {
        return roomType;
    }
}

// Inventory Service
class RoomInventory {
    private Map<String, Integer> inventory = new HashMap<>();

    public RoomInventory() {
        inventory.put("Single Room", 2);
        inventory.put("Double Room", 1);
        inventory.put("Suite Room", 1);
    }

    public int getAvailability(String roomType) {
        return inventory.getOrDefault(roomType, 0);
    }

    public void reduceAvailability(String roomType) {
        inventory.put(roomType, getAvailability(roomType) - 1);
    }

    public void displayInventory() {
        System.out.println("\n---- Updated Inventory ----");
        for (Map.Entry<String, Integer> entry : inventory.entrySet()) {
            System.out.println(entry.getKey() + " -> " + entry.getValue());
        }
    }
}

// Booking Service (Core Allocation Logic)
class BookingService {

    private Set<String> allocatedRoomIds = new HashSet<>();
    private Map<String, Set<String>> roomAllocations = new HashMap<>();
    private int roomCounter = 1;

    public void processBookings(Queue<Reservation> queue, RoomInventory inventory) {

        System.out.println("\n---- Processing Bookings ----\n");

        while (!queue.isEmpty()) {

            Reservation request = queue.poll(); // FIFO
            String roomType = request.getRoomType();

            System.out.println("Processing request for " + request.getGuestName());

            // Check availability
            if (inventory.getAvailability(roomType) > 0) {

                // Generate unique Room ID
                String roomId;
                do {
                    roomId = roomType.replace(" ", "").substring(0, 2).toUpperCase() + roomCounter++;
                } while (allocatedRoomIds.contains(roomId));

                // Allocate room
                allocatedRoomIds.add(roomId);

                roomAllocations
                        .computeIfAbsent(roomType, k -> new HashSet<>())
                        .add(roomId);

                // Update inventory immediately
                inventory.reduceAvailability(roomType);

                // Confirm booking
                System.out.println("Booking CONFIRMED for " + request.getGuestName());
                System.out.println("Room Type: " + roomType);
                System.out.println("Allocated Room ID: " + roomId + "\n");

            } else {
                System.out.println("Booking FAILED for " + request.getGuestName());
                System.out.println("Reason: No rooms available for " + roomType + "\n");
            }
        }
    }
}

// Main Class
 class BookMyStayApp {

    public static void main(String[] args) {

        System.out.println("=====================================");
        System.out.println("   Welcome to Book My Stay App");
        System.out.println("   Hotel Booking System v6.0");
        System.out.println("=====================================\n");

        // Initialize inventory
        RoomInventory inventory = new RoomInventory();

        // Initialize booking queue (FIFO)
        Queue<Reservation> bookingQueue = new LinkedList<>();

        // Add booking requests
        bookingQueue.offer(new Reservation("Alice", "Single Room"));
        bookingQueue.offer(new Reservation("Bob", "Single Room"));
        bookingQueue.offer(new Reservation("Charlie", "Single Room")); // should fail
        bookingQueue.offer(new Reservation("David", "Double Room"));

        // Process bookings
        BookingService bookingService = new BookingService();
        bookingService.processBookings(bookingQueue, inventory);

        // Display updated inventory
        inventory.displayInventory();
    }
}