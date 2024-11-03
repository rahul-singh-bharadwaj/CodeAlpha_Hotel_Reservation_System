import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

// Room Class
class Room {
    private int roomNumber;
    private String category; // e.g., Single, Double, Tripple
    private boolean isAvailable;
    private double price;

    public Room(int roomNumber, String category, double price) {
        this.roomNumber = roomNumber;
        this.category = category;
        this.isAvailable = true;
        this.price = price;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public String getCategory() {
        return category;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "Room{" +
                "roomNumber=" + roomNumber +
                ", category='" + category + '\'' +
                ", isAvailable=" + isAvailable +
                ", price=" + price +
                '}';
    }
}

// Reservation Class
class Reservation {
    private int roomNumber;
    private String customerName;
    private Date checkInDate;
    private Date checkOutDate;

    public Reservation(int roomNumber, String customerName, Date checkInDate, Date checkOutDate) {
        this.roomNumber = roomNumber;
        this.customerName = customerName;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "roomNumber=" + roomNumber +
                ", customerName='" + customerName + '\'' +
                ", checkInDate=" + checkInDate +
                ", checkOutDate=" + checkOutDate +
                '}';
    }
}

// Hotel Class
class Hotel {
    private List<Room> rooms;
    private List<Reservation> reservations;

    public Hotel() {
        rooms = new ArrayList<>();
        reservations = new ArrayList<>();
    }

    public void addRoom(Room room) {
        rooms.add(room);
    }

    public List<Room> searchAvailableRooms() {
        List<Room> availableRooms = new ArrayList<>();
        for (Room room : rooms) {
            if (room.isAvailable()) {
                availableRooms.add(room);
            }
        }
        return availableRooms;
    }

    public boolean makeReservation(String customerName, int roomNumber, Date checkInDate, Date checkOutDate) {
        for (Room room : rooms) {
            if (room.getRoomNumber() == roomNumber && room.isAvailable()) {
                room.setAvailable(false);
                Reservation reservation = new Reservation(roomNumber, customerName, checkInDate, checkOutDate);
                reservations.add(reservation);
                return true;
            }
        }
        return false;
    }

    public List<Reservation> getReservations() {
        return reservations;
    }

    public Room getRoomByNumber(int roomNumber) {
        for (Room room : rooms) {
            if (room.getRoomNumber() == roomNumber) {
                return room;
            }
        }
        return null;
    }
}

// Payment Class
class Payment {
    public static boolean processPayment(double amount) {
        // Simulate payment processing
        System.out.println(" Processing payment of INR " + amount);
        return true; // Assume payment is successful
    }
}

// Main Class
public class HotelReservationSystem {
    public static void main(String[] args) {
        Hotel hotel = new Hotel();
        
        // Adding rooms to the hotel
        hotel.addRoom(new Room(101, " Single Non AC ", 800.0));
        hotel.addRoom(new Room(102, " Double Non AC ", 1100.0));
        hotel.addRoom(new Room(103, " Tripple Non AC ", 1500.0));
        hotel.addRoom(new Room(104, " Single AC ", 1600.0));
        hotel.addRoom(new Room(105, " Double AC ", 2200.0));
        hotel.addRoom(new Room(106, " Tripple AC ", 3000.0));

        // Searching for available rooms
        System.out.println("Available Rooms:");
        List<Room> availableRooms = hotel.searchAvailableRooms();
        for (Room room : availableRooms) {
            System.out.println(room);
        }

        // Making a reservation
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
            Date checkInDate = sdf.parse("15-10-2024");
            Date checkOutDate = sdf.parse("16-10-2024");
            String customerName = " Rahul Kumar ";
            int roomNumber = 106;

            if (hotel.makeReservation(customerName, roomNumber, checkInDate, checkOutDate)) {
                System.out.println("\nReservation successful for " + customerName + " in room " + roomNumber);
                // Process payment
                Room bookedRoom = hotel.getRoomByNumber(roomNumber);
                if (bookedRoom != null && Payment.processPayment(bookedRoom.getPrice())) {
                    System.out.println("Payment processed successfully.");
                }
            } else {
                System.out.println("\nReservation failed. Room " + roomNumber + " is not available.");
            }

            // Display all reservations
            System.out.println("\nCurrent Reservations:");
            List<Reservation> reservations = hotel.getReservations();
            for (Reservation reservation : reservations) {
                System.out.println(reservation);
            }

            // Display updated available rooms
            System.out.println("\nUpdated Available Rooms:");
            availableRooms = hotel.searchAvailableRooms();
            for (Room room : availableRooms) {
                System.out.println(room);
            }

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}