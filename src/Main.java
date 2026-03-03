import java.util.LinkedList;
import java.util.Queue;

/**
 * Hotel Booking Management System
 *
 * Use Case 5 - Booking Request Queue
 *
 * Demonstrates:
 * - Queue data structure
 * - FIFO (First-Come-First-Served)
 * - Fair request handling
 * - Decoupling request intake from allocation
 * - No inventory mutation at intake stage
 *
 * @author Lakshmi M
 * @version 1.4
 */
public class Main {

    public static void main(String[] args) {

        System.out.println("=======================================");
        System.out.println("        BOOK MY STAY APPLICATION       ");
        System.out.println("     Hotel Booking Management System   ");
        System.out.println("              Version 1.4              ");
        System.out.println("=======================================\n");

        // Booking Request Queue (FIFO)
        BookingRequestQueue bookingQueue = new BookingRequestQueue();

        // Guests submit booking requests
        bookingQueue.addRequest(new Reservation("Alice", "Single Room"));
        bookingQueue.addRequest(new Reservation("Bob", "Suite Room"));
        bookingQueue.addRequest(new Reservation("Charlie", "Double Room"));
        bookingQueue.addRequest(new Reservation("Diana", "Single Room"));

        // Display queued requests (no allocation yet)
        bookingQueue.displayPendingRequests();

        System.out.println("\nAll requests are queued and waiting for allocation.");
        System.out.println("No inventory updates performed at this stage.");
        System.out.println("\nApplication Terminated Successfully.");
    }
}

/* ================================
   Reservation (Guest Intent)
   ================================ */
class Reservation {

    private String guestName;
    private String requestedRoomType;

    public Reservation(String guestName, String requestedRoomType) {
        this.guestName = guestName;
        this.requestedRoomType = requestedRoomType;
    }

    public String getGuestName() {
        return guestName;
    }

    public String getRequestedRoomType() {
        return requestedRoomType;
    }

    @Override
    public String toString() {
        return "Guest: " + guestName + " | Requested Room: " + requestedRoomType;
    }
}

/* ================================
   Booking Request Queue (FIFO)
   ================================ */
class BookingRequestQueue {

    private Queue<Reservation> requestQueue;

    public BookingRequestQueue() {
        requestQueue = new LinkedList<>();
    }

    // Accept booking request
    public void addRequest(Reservation reservation) {
        requestQueue.offer(reservation);
        System.out.println("Request received: " + reservation);
    }

    // View pending requests (Read-only)
    public void displayPendingRequests() {

        System.out.println("\n------ Pending Booking Requests (FIFO Order) ------");

        if (requestQueue.isEmpty()) {
            System.out.println("No pending requests.");
            return;
        }

        for (Reservation reservation : requestQueue) {
            System.out.println(reservation);
        }

        System.out.println("----------------------------------------------------");
    }
}