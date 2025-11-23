import java.util.*;

// Main Class to run the application
public class TrainReservationSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        AdminModule admin = new AdminModule();
        PassengerModule passenger = new PassengerModule();
        
        // Add some sample trains
        admin.addTrain(new Train(101, "Express", "Delhi", "Mumbai", 50, 1500.0));
        admin.addTrain(new Train(102, "Rajdhani", "Delhi", "Kolkata", 40, 2000.0));
        admin.addTrain(new Train(103, "Shatabdi", "Mumbai", "Pune", 30, 800.0));
        
        while (true) {
            try {
                System.out.println("\n===== TRAIN TICKET RESERVATION SYSTEM =====");
                System.out.println("1. Admin Login");
                System.out.println("2. Passenger Login");
                System.out.println("3. Exit");
                System.out.print("Enter your choice: ");
                
                int choice = scanner.nextInt();
                scanner.nextLine();
                
                switch (choice) {
                    case 1:
                        adminMenu(scanner, admin);
                        break;
                    case 2:
                        passengerMenu(scanner, passenger, admin);
                        break;
                    case 3:
                        System.out.println("Thank you for using Train Reservation System!");
                        scanner.close();
                        return;
                    default:
                        System.out.println("Invalid choice! Please try again.");
                }
            } catch (Exception e) {
                System.out.println("Invalid input! Please try again.");
                scanner.nextLine();
            }
        }
    }
    
    // Admin Menu
    public static void adminMenu(Scanner scanner, AdminModule admin) {
        System.out.print("Enter Admin Username: ");
        String username = scanner.nextLine();
        System.out.print("Enter Admin Password: ");
        String password = scanner.nextLine();
        
        if (username.equals("admin") && password.equals("admin123")) {
            System.out.println("Login Successful!");
            
            while (true) {
                try {
                    System.out.println("\n----- ADMIN MENU -----");
                    System.out.println("1. Add Train");
                    System.out.println("2. View All Trains");
                    System.out.println("3. View All Bookings");
                    System.out.println("4. Back to Main Menu");
                    System.out.print("Enter your choice: ");
                    
                    int choice = scanner.nextInt();
                    scanner.nextLine();
                    
                    switch (choice) {
                        case 1:
                            System.out.print("Enter Train Number: ");
                            int trainNo = scanner.nextInt();
                            scanner.nextLine();
                            System.out.print("Enter Train Name: ");
                            String name = scanner.nextLine();
                            System.out.print("Enter Source: ");
                            String source = scanner.nextLine();
                            System.out.print("Enter Destination: ");
                            String destination = scanner.nextLine();
                            System.out.print("Enter Total Seats: ");
                            int seats = scanner.nextInt();
                            System.out.print("Enter Fare: ");
                            double fare = scanner.nextDouble();
                            
                            Train newTrain = new Train(trainNo, name, source, destination, seats, fare);
                            admin.addTrain(newTrain);
                            break;
                        case 2:
                            admin.displayAllTrains();
                            break;
                        case 3:
                            admin.displayAllBookings();
                            break;
                        case 4:
                            return;
                        default:
                            System.out.println("Invalid choice!");
                    }
                } catch (Exception e) {
                    System.out.println("Invalid input! Please enter correct data.");
                    scanner.nextLine();
                }
            }
        } else {
            System.out.println("Invalid username or password!");
        }
    }
    
    // Passenger Menu
    public static void passengerMenu(Scanner scanner, PassengerModule passenger, AdminModule admin) {
        try {
            System.out.print("Enter Your Name: ");
            String name = scanner.nextLine();
            System.out.print("Enter Your Age: ");
            int age = scanner.nextInt();
            scanner.nextLine();
            System.out.print("Enter Your Phone: ");
            String phone = scanner.nextLine();
            
            Passenger user = new Passenger(name, age, phone);
            System.out.println("Welcome " + name + "!");
            
            while (true) {
                try {
                    System.out.println("\n----- PASSENGER MENU -----");
                    System.out.println("1. Search Trains");
                    System.out.println("2. Book Ticket");
                    System.out.println("3. Cancel Ticket");
                    System.out.println("4. View My Bookings");
                    System.out.println("5. Back to Main Menu");
                    System.out.print("Enter your choice: ");
                    
                    int choice = scanner.nextInt();
                    scanner.nextLine();
                    
                    switch (choice) {
                        case 1:
                            System.out.print("Enter Source: ");
                            String source = scanner.nextLine();
                            System.out.print("Enter Destination: ");
                            String destination = scanner.nextLine();
                            passenger.searchTrains(source, destination, admin.getTrainList());
                            break;
                        case 2:
                            System.out.print("Enter Train Number: ");
                            int trainNo = scanner.nextInt();
                            System.out.print("Enter Number of Seats: ");
                            int seats = scanner.nextInt();
                            passenger.bookTicket(trainNo, seats, user, admin);
                            break;
                        case 3:
                            System.out.print("Enter Booking ID: ");
                            int bookingId = scanner.nextInt();
                            passenger.cancelTicket(bookingId, admin);
                            break;
                        case 4:
                            passenger.viewMyBookings(user.getPhone(), admin);
                            break;
                        case 5:
                            return;
                        default:
                            System.out.println("Invalid choice!");
                    }
                } catch (Exception e) {
                    System.out.println("Invalid input! Please enter correct data.");
                    scanner.nextLine();
                }
            }
        } catch (Exception e) {
            System.out.println("Invalid input during registration!");
            scanner.nextLine();
        }
    }
}

// Train Class - Represents train data
class Train {
    private int trainNumber;
    private String trainName;
    private String source;
    private String destination;
    private int totalSeats;
    private int availableSeats;
    private double fare;
    
    public Train(int trainNumber, String trainName, String source, 
                 String destination, int totalSeats, double fare) {
        this.trainNumber = trainNumber;
        this.trainName = trainName;
        this.source = source;
        this.destination = destination;
        this.totalSeats = totalSeats;
        this.availableSeats = totalSeats;
        this.fare = fare;
    }
    
    // Getters
    public int getTrainNumber() { return trainNumber; }
    public String getTrainName() { return trainName; }
    public String getSource() { return source; }
    public String getDestination() { return destination; }
    public int getAvailableSeats() { return availableSeats; }
    public double getFare() { return fare; }
    
    // Method to book seats
    public boolean bookSeats(int seats) {
        if (availableSeats >= seats) {
            availableSeats -= seats;
            return true;
        }
        return false;
    }
    
    // Method to cancel seats
    public void cancelSeats(int seats) {
        availableSeats += seats;
    }
    
    public void displayTrainInfo() {
        System.out.println("Train No: " + trainNumber + " | Name: " + trainName + 
                         " | " + source + " -> " + destination + 
                         " | Available Seats: " + availableSeats + 
                         " | Fare: Rs." + fare);
    }
}

// Passenger Class - Represents user data
class Passenger {
    private String name;
    private int age;
    private String phone;
    
    public Passenger(String name, int age, String phone) {
        this.name = name;
        this.age = age;
        this.phone = phone;
    }
    
    public String getName() { return name; }
    public int getAge() { return age; }
    public String getPhone() { return phone; }
}

// Booking Class - Represents ticket booking
class Booking {
    private static int bookingCounter = 1000;
    private int bookingId;
    private int trainNumber;
    private String passengerName;
    private String passengerPhone;
    private int seatsBooked;
    private double totalFare;
    
    public Booking(int trainNumber, String passengerName, String passengerPhone, 
                   int seatsBooked, double totalFare) {
        this.bookingId = ++bookingCounter;
        this.trainNumber = trainNumber;
        this.passengerName = passengerName;
        this.passengerPhone = passengerPhone;
        this.seatsBooked = seatsBooked;
        this.totalFare = totalFare;
    }
    
    public int getBookingId() { return bookingId; }
    public int getTrainNumber() { return trainNumber; }
    public String getPassengerPhone() { return passengerPhone; }
    public int getSeatsBooked() { return seatsBooked; }
    
    public void displayBooking() {
        System.out.println("Booking ID: " + bookingId + " | Train No: " + trainNumber + 
                         " | Passenger: " + passengerName + " | Seats: " + seatsBooked + 
                         " | Total Fare: Rs." + totalFare);
    }
}

// Admin Module - Admin functionalities
class AdminModule {
    private ArrayList<Train> trainList;
    private ArrayList<Booking> bookingList;
    
    public AdminModule() {
        trainList = new ArrayList<>();
        bookingList = new ArrayList<>();
    }
    
    public void addTrain(Train train) {
        trainList.add(train);
        System.out.println("Train added successfully!");
    }
    
    public void displayAllTrains() {
        if (trainList.isEmpty()) {
            System.out.println("No trains available!");
            return;
        }
        System.out.println("\n----- ALL TRAINS -----");
        for (Train train : trainList) {
            train.displayTrainInfo();
        }
    }
    
    public void displayAllBookings() {
        if (bookingList.isEmpty()) {
            System.out.println("No bookings found!");
            return;
        }
        System.out.println("\n----- ALL BOOKINGS -----");
        for (Booking booking : bookingList) {
            booking.displayBooking();
        }
    }
    
    public ArrayList<Train> getTrainList() {
        return trainList;
    }
    
    public void addBooking(Booking booking) {
        bookingList.add(booking);
    }
    
    public Train findTrain(int trainNumber) {
        for (Train train : trainList) {
            if (train.getTrainNumber() == trainNumber) {
                return train;
            }
        }
        return null;
    }
    
    public Booking findBooking(int bookingId) {
        for (Booking booking : bookingList) {
            if (booking.getBookingId() == bookingId) {
                return booking;
            }
        }
        return null;
    }
    
    public boolean removeBooking(int bookingId) {
        return bookingList.removeIf(booking -> booking.getBookingId() == bookingId);
    }
    
    public ArrayList<Booking> getBookingsByPhone(String phone) {
        ArrayList<Booking> userBookings = new ArrayList<>();
        for (Booking booking : bookingList) {
            if (booking.getPassengerPhone().equals(phone)) {
                userBookings.add(booking);
            }
        }
        return userBookings;
    }
}

// Passenger Module - Passenger functionalities
class PassengerModule {
    
    public void searchTrains(String source, String destination, ArrayList<Train> trainList) {
        System.out.println("\n----- AVAILABLE TRAINS -----");
        boolean found = false;
        for (Train train : trainList) {
            if (train.getSource().equalsIgnoreCase(source) && 
                train.getDestination().equalsIgnoreCase(destination)) {
                train.displayTrainInfo();
                found = true;
            }
        }
        if (!found) {
            System.out.println("No trains found for this route!");
        }
    }
    
    public void bookTicket(int trainNumber, int seats, Passenger passenger, AdminModule admin) {
        try {
            Train train = admin.findTrain(trainNumber);
            
            if (train == null) {
                System.out.println("Train not found!");
                return;
            }
            
            if (train.bookSeats(seats)) {
                double totalFare = train.getFare() * seats;
                Booking booking = new Booking(trainNumber, passenger.getName(), 
                                             passenger.getPhone(), seats, totalFare);
                admin.addBooking(booking);
                System.out.println("\n***** BOOKING SUCCESSFUL *****");
                System.out.println("Booking ID: " + booking.getBookingId());
                System.out.println("Total Fare: Rs." + totalFare);
                System.out.println("Payment Confirmed!");
            } else {
                System.out.println("Not enough seats available!");
            }
        } catch (Exception e) {
            System.out.println("Booking failed! Please try again.");
        }
    }
    
    public void cancelTicket(int bookingId, AdminModule admin) {
        try {
            Booking booking = admin.findBooking(bookingId);
            
            if (booking == null) {
                System.out.println("Booking not found!");
                return;
            }
            
            Train train = admin.findTrain(booking.getTrainNumber());
            if (train != null) {
                train.cancelSeats(booking.getSeatsBooked());
            }
            
            admin.removeBooking(bookingId);
            System.out.println("Ticket cancelled successfully!");
        } catch (Exception e) {
            System.out.println("Cancellation failed!");
        }
    }
    
    public void viewMyBookings(String phone, AdminModule admin) {
        ArrayList<Booking> myBookings = admin.getBookingsByPhone(phone);
        
        if (myBookings.isEmpty()) {
            System.out.println("No bookings found!");
            return;
        }
        
        System.out.println("\n----- YOUR BOOKINGS -----");
        for (Booking booking : myBookings) {
            booking.displayBooking();
        }
    }
}