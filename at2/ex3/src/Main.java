import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static ReservationSystem reservationSystem;

    public static void main(String[] args) {
        System.out.println("Welcome to the Event Reservation System!");

        initializeSystem();

        boolean running = true;
        while (running) {
            displayMenu();
            System.out.print("Enter your choice: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    displaySeatingChart();
                    break;
                case "2":
                    reserveSeat();
                    break;
                case "3":
                    cancelReservation();
                    break;
                case "4":
                    running = false;
                    System.out.println("Thank you for using the Event Reservation System. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }

        scanner.close();
    }

    private static void initializeSystem() {
        System.out.println("Setting up the venue...");

        int rows = getPositiveIntInput("Enter number of rows (1-26): ", 1, 26);
        int columns = getPositiveIntInput("Enter number of columns: ", 1, 100);

        reservationSystem = new ReservationSystem(rows, columns);
        System.out.println("Venue created successfully!");
    }

    private static void displayMenu() {
        System.out.println("\n===== EVENT RESERVATION MENU =====");
        System.out.println("1. View seating chart");
        System.out.println("2. Reserve a seat");
        System.out.println("3. Cancel a reservation");
        System.out.println("4. Exit");
        System.out.println("=================================");
    }

    private static void displaySeatingChart() {
        System.out.println("\n===== SEATING CHART =====");
        reservationSystem.getVenue().displaySeatingChart();
    }

    private static void reserveSeat() {
        displaySeatingChart();

        System.out.println("\n=== SEAT RESERVATION ===");
        char row = getRowInput(reservationSystem.getVenue().getRows());
        int column = getColumnInput(reservationSystem.getVenue().getColumns());

        String reservationId = reservationSystem.reserveSeat(row, column);
        if (reservationId != null) {
            System.out.println("Seat " + row + column + " reserved successfully!");
            System.out.println("Your reservation ID is: " + reservationId);
            System.out.println("Please keep this ID for cancellation purposes.");
        } else {
            System.out.println("Sorry, this seat is already reserved or invalid.");
        }
    }

    private static void cancelReservation() {
        System.out.println("\n=== RESERVATION CANCELLATION ===");
        char row = getRowInput(reservationSystem.getVenue().getRows());
        int column = getColumnInput(reservationSystem.getVenue().getColumns());

        System.out.print("Enter your reservation ID: ");
        String reservationId = scanner.nextLine();

        boolean success = reservationSystem.cancelReservation(row, column, reservationId);
        if (success) {
            System.out.println("Reservation for seat " + row + column + " cancelled successfully!");
        } else {
            System.out.println("Cancellation failed. Please check the seat and reservation ID.");
        }
    }

    private static char getRowInput(int maxRows) {
        while (true) {
            System.out.print("Enter row (A-" + (char) ('A' + maxRows - 1) + "): ");
            String input = scanner.nextLine().toUpperCase();

            if (input.length() == 1) {
                char row = input.charAt(0);
                if (row >= 'A' && row < 'A' + maxRows) {
                    return row;
                }
            }
            System.out.println("Invalid row. Please try again.");
        }
    }

    private static int getColumnInput(int maxColumns) {
        return getPositiveIntInput("Enter column (1-" + maxColumns + "): ", 1, maxColumns);
    }

    private static int getPositiveIntInput(String prompt, int min, int max) {
        while (true) {
            System.out.print(prompt);
            try {
                int value = Integer.parseInt(scanner.nextLine());
                if (value >= min && value <= max) {
                    return value;
                } else {
                    System.out.println("Please enter a number between " + min + " and " + max + ".");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
            }
        }
    }
}