import java.util.HashMap;
import java.util.Map;

/**
 * Hotel Booking Management System
 *
 * Use Case 4 - Room Search & Availability Check
 *
 * Demonstrates:
 * - Read-only search operations
 * - Defensive programming
 * - Filtering unavailable rooms
 * - Clear separation of concerns
 * - Inventory as centralized state holder
 *
 * @author Lakshmi M
 * @version 1.3
 */
public class Main {

    public static void main(String[] args) {

        System.out.println("=======================================");
        System.out.println("        BOOK MY STAY APPLICATION       ");
        System.out.println("     Hotel Booking Management System   ");
        System.out.println("              Version 1.3              ");
        System.out.println("=======================================\n");

        // Domain Objects
        Room singleRoom = new SingleRoom();
        Room doubleRoom = new DoubleRoom();
        Room suiteRoom = new SuiteRoom();

        // Centralized Inventory (State Holder)
        RoomInventory inventory = new RoomInventory();
        inventory.registerRoom(singleRoom.getRoomType(), 5);
        inventory.registerRoom(doubleRoom.getRoomType(), 0); // Not available
        inventory.registerRoom(suiteRoom.getRoomType(), 2);

        // Search Service (Read-only operation)
        RoomSearchService searchService = new RoomSearchService(inventory);

        searchService.searchAvailableRooms(
                new Room[]{singleRoom, doubleRoom, suiteRoom}
        );

        System.out.println("\nApplication Terminated Successfully.");
    }
}

/* ================================
   Abstract Room (Domain Model)
   ================================ */
abstract class Room {

    private String roomType;
    private int beds;
    private double pricePerNight;
    private double size;

    public Room(String roomType, int beds, double pricePerNight, double size) {
        this.roomType = roomType;
        this.beds = beds;
        this.pricePerNight = pricePerNight;
        this.size = size;
    }

    public String getRoomType() {
        return roomType;
    }

    public void displayDetails() {
        System.out.println("Room Type    : " + roomType);
        System.out.println("Beds         : " + beds);
        System.out.println("Size (sq ft) : " + size);
        System.out.println("Price/Night  : $" + pricePerNight);
    }
}

/* ================================
   Concrete Room Types
   ================================ */
class SingleRoom extends Room {
    public SingleRoom() {
        super("Single Room", 1, 100.0, 200.0);
    }
}

class DoubleRoom extends Room {
    public DoubleRoom() {
        super("Double Room", 2, 180.0, 350.0);
    }
}

class SuiteRoom extends Room {
    public SuiteRoom() {
        super("Suite Room", 3, 300.0, 600.0);
    }
}

/* ================================
   Centralized Inventory
   ================================ */
class RoomInventory {

    private Map<String, Integer> availabilityMap;

    public RoomInventory() {
        availabilityMap = new HashMap<>();
    }

    public void registerRoom(String roomType, int count) {
        availabilityMap.put(roomType, count);
    }

    // Read-only access
    public int getAvailability(String roomType) {
        return availabilityMap.getOrDefault(roomType, 0);
    }
}

/* ================================
   Room Search Service (Read-Only)
   ================================ */
class RoomSearchService {

    private RoomInventory inventory;

    public RoomSearchService(RoomInventory inventory) {
        this.inventory = inventory;
    }

    public void searchAvailableRooms(Room[] rooms) {

        System.out.println("------ Available Rooms ------");

        for (Room room : rooms) {

            int available = inventory.getAvailability(room.getRoomType());

            // Defensive validation
            if (available > 0) {
                room.displayDetails();
                System.out.println("Available: " + available);
                System.out.println("--------------------------------");
            }
        }

        System.out.println("-------------------------------");
    }
}