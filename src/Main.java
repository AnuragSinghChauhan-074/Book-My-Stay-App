import java.util.HashMap;
import java.util.Map;

/**
 * Hotel Booking Management System
 *
 * Use Case 3 - Centralized Room Inventory Management
 *
 * Demonstrates:
 * - HashMap for centralized inventory
 * - O(1) availability lookup
 * - Encapsulation of inventory logic
 * - Separation of concerns
 * - Scalable room registration
 *
 * @author Lakshmi M
 * @version 1.2
 */
public class Main {

    public static void main(String[] args) {

        System.out.println("=======================================");
        System.out.println("        BOOK MY STAY APPLICATION       ");
        System.out.println("     Hotel Booking Management System   ");
        System.out.println("              Version 1.2              ");
        System.out.println("=======================================\n");

        // Create Room Objects (Domain Model)
        Room singleRoom = new SingleRoom();
        Room doubleRoom = new DoubleRoom();
        Room suiteRoom = new SuiteRoom();

        // Initialize Centralized Inventory
        RoomInventory inventory = new RoomInventory();
        inventory.registerRoom(singleRoom.getRoomType(), 5);
        inventory.registerRoom(doubleRoom.getRoomType(), 3);
        inventory.registerRoom(suiteRoom.getRoomType(), 2);

        // Display Current Inventory
        inventory.displayInventory();

        // Controlled Update Example
        System.out.println("\nUpdating availability (Booking 1 Single Room)...");
        inventory.updateAvailability("Single Room", -1);

        inventory.displayInventory();

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
   Centralized Inventory Management
   ================================ */
class RoomInventory {

    // Single Source of Truth
    private Map<String, Integer> availabilityMap;

    // Constructor initializes inventory
    public RoomInventory() {
        availabilityMap = new HashMap<>();
    }

    // Register room type with availability
    public void registerRoom(String roomType, int count) {
        availabilityMap.put(roomType, count);
    }

    // Retrieve availability
    public int getAvailability(String roomType) {
        return availabilityMap.getOrDefault(roomType, 0);
    }

    // Controlled update (increase or decrease)
    public void updateAvailability(String roomType, int change) {
        int current = getAvailability(roomType);
        int updated = current + change;

        if (updated < 0) {
            System.out.println("Error: Cannot reduce below zero.");
            return;
        }

        availabilityMap.put(roomType, updated);
    }

    // Display entire inventory
    public void displayInventory() {
        System.out.println("------ Current Room Inventory ------");
        for (Map.Entry<String, Integer> entry : availabilityMap.entrySet()) {
            System.out.println(entry.getKey() + " Available: " + entry.getValue());
        }
        System.out.println("------------------------------------");
    }
}