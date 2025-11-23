# Train-Ticket-Booking-System
🚂 Train Ticket Reservation System (Java Console App)This is a simple console-based Java application that simulates a basic train reservation system, offering different functionalities for Admin and Passenger users.

✨ Core Features
Role: KeyFunctionalities.
Admin: Add new trains, view all trains, view all customer bookings.
Passenger: Search trains, book tickets, cancel tickets, view personal booking history.

🛠️ How It Works (System Structure)
The application is built around several interconnected classes that manage data and logic:
Train: Stores details like number, name, route, fare, and available seats.
Passenger: Stores user information (name, age, phone number).
Booking: Records a reservation, generating a unique Booking ID.
AdminModule: Manages the central lists of all trains and all bookings.
PassengerModule: Contains all public facing methods like searchTrains and bookTicket.

🚀 Getting Started
Save: Save all the provided code into one file: TrainReservationSystem.java.
Compile: Use the Java compiler: javac TrainReservationSystem.java
Run: Execute the compiled class: java TrainReservationSystem

🔑 Key Credentials
Admin Login: Username: admin | Password: admin123
Passenger Login: Requires simple registration (Name, Age, Phone - the phone number is used to retrieve their specific bookings).
