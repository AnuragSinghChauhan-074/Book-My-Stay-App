/**
 * Hotel Booking Management System
 *
 * Use Case 2 - Basic Room Types & Static Availability
 *
 * Demonstrates:
 * - Abstract Class
 * - Inheritance
 * - Polymorphism
 * - Encapsulation
 * - Static availability representation
 *
 * @author Lakshmi M
 * @version 1.1
 */
public class Main {

    public static void main(String[] args) {

        System.out.println("=======================================");
        System.out.println("        BOOK MY STAY APPLICATION       ");
        System.out.println("     Hotel Booking Management System   ");
        System.out.println("              Version 1.1              ");
        System.out.println("=======================================\n");

        // Polymorphism: Referencing child objects using parent type
        Room singleRoom = new SingleRoom();
        Room doubleRoom = new DoubleRoom();
        Room suiteRoom = new SuiteRoom();

        // Static availability variables
        int singleRoomAvailability = 5;
        int doubleRoomAvailability = 3;
        int suiteRoomAvailability = 2;

        displayRoomInfo(singleRoom, singleRoomAvailability);
        displayRoomInfo(doubleRoom, doubleRoomAvailability);
        displayRoomInfo(suiteRoom, suiteRoomAvailability);

        System.out.println("\nApplication Terminated Successfully.");
    }

    private static void displayRoomInfo(Room room, int availability) {
        room.displayDetails();
        System.out.println("Available Rooms: " + availability);
        System.out.println("---------------------------------------");
    }
}

/* ================================
   Abstract Room Class
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

    public void displayDetails() {
        System.out.println("Room Type      : " + roomType);
        System.out.println("Beds           : " + beds);
        System.out.println("Size (sq ft)   : " + size);
        System.out.println("Price/Night    : $" + pricePerNight);
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