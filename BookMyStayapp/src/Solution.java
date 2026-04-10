import java.util.*;

/**
 * UseCase5BookingRequestQueue
 *
 * This class demonstrates how booking requests are collected
 * and stored using a Queue to ensure First-Come-First-Served (FIFO) handling.
 *
 * No inventory updates or allocations are performed at this stage.
 *
 * @author YourName
 * @version 5.0
 */

// Reservation class (represents a booking request)
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

    public void displayReservation() {
        System.out.println("Guest: " + guestName);
        System.out.println("Requested Room: " + roomType);
    }
}

// Booking Request Queue (FIFO)
class BookingRequestQueue {
    private Queue<Reservation> queue;

    public BookingRequestQueue() {
        queue = new LinkedList<>();
    }

    // Add request to queue
    public void addRequest(Reservation reservation) {
        queue.offer(reservation);
        System.out.println("Booking request added for " + reservation.getGuestName());
    }

    // View all queued requests
    public void displayQueue() {
        System.out.println("\n---- Booking Request Queue ----\n");

        if (queue.isEmpty()) {
            System.out.println("No booking requests in queue.");
            return;
        }

        for (Reservation res : queue) {
            res.displayReservation();
            System.out.println();
        }
    }
}

// Main Class
 class BookMyStayApp {

    public static void main(String[] args) {

        System.out.println("=====================================");
        System.out.println("   Welcome to Book My Stay App");
        System.out.println("   Hotel Booking System v5.0");
        System.out.println("=====================================\n");

        // Initialize booking queue
        BookingRequestQueue bookingQueue = new BookingRequestQueue();

        // Simulate guest booking requests
        Reservation r1 = new Reservation("Alice", "Single Room");
        Reservation r2 = new Reservation("Bob", "Double Room");
        Reservation r3 = new Reservation("Charlie", "Suite Room");

        // Add requests (FIFO order)
        bookingQueue.addRequest(r1);
        bookingQueue.addRequest(r2);
        bookingQueue.addRequest(r3);

        // Display queue (order preserved)
        bookingQueue.displayQueue();
    }
}