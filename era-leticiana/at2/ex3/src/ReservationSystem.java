import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.UUID;

public class ReservationSystem {
    private EventVenue venue;
    private static final String DATA_FILE = "reservations.txt";

    public ReservationSystem() {
        this(8, 10); // Default size: 8 rows, 10 columns
    }

    public ReservationSystem(int rows, int columns) {
        this.venue = new EventVenue(rows, columns);
        loadReservations();
    }

    public EventVenue getVenue() {
        return venue;
    }

    public String reserveSeat(char row, int column) {
        Seat seat = venue.getSeat(row, column);

        if (seat != null && !seat.isReserved()) {
            String reservationId = generateReservationId();
            if (seat.reserve(reservationId)) {
                saveReservations();
                return reservationId;
            }
        }

        return null;
    }

    public boolean cancelReservation(char row, int column, String reservationId) {
        Seat seat = venue.getSeat(row, column);

        if (seat != null && seat.cancelReservation(reservationId)) {
            saveReservations();
            return true;
        }

        return false;
    }

    private String generateReservationId() {
        return UUID.randomUUID().toString().substring(0, 8);
    }

    private void saveReservations() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(DATA_FILE))) {
            Seat[][] seats = venue.getAllSeats();

            writer.write(venue.getRows() + "," + venue.getColumns());
            writer.newLine();

            for (Seat[] row : seats) {
                for (Seat seat : row) {
                    if (seat.isReserved()) {
                        writer.write(seat.toFileString());
                        writer.newLine();
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Error saving reservations: " + e.getMessage());
        }
    }

    private void loadReservations() {
        File file = new File(DATA_FILE);
        if (!file.exists()) {
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line = reader.readLine();

            String[] dimensions = line.split(",");
            int fileRows = Integer.parseInt(dimensions[0]);
            int fileColumns = Integer.parseInt(dimensions[1]);

            if (fileRows == venue.getRows() && fileColumns == venue.getColumns()) {
                while ((line = reader.readLine()) != null) {
                    Seat seat = Seat.fromFileString(line);
                    char row = seat.getRow();
                    int column = seat.getColumn();

                    Seat venueSeat = venue.getSeat(row, column);
                    if (venueSeat != null) {
                        venueSeat.reserve(seat.getReservationId());
                    }
                }
            } else {
                System.out.println("Warning: Saved venue size doesn't match current venue. Starting with empty venue.");
            }
        } catch (IOException e) {
            System.err.println("Error loading reservations: " + e.getMessage());
        }
    }
}