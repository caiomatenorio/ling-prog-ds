
public class Seat {
    private char row;
    private int column;
    private boolean reserved;
    private String reservationId;

    public Seat(char row, int column) {
        this.row = row;
        this.column = column;
        this.reserved = false;
        this.reservationId = null;
    }

    // Getters and setters
    public char getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public boolean isReserved() {
        return reserved;
    }

    public String getReservationId() {
        return reservationId;
    }

    public boolean reserve(String reservationId) {
        if (!reserved) {
            this.reserved = true;
            this.reservationId = reservationId;
            return true;
        }
        return false;
    }

    public boolean cancelReservation(String reservationId) {
        if (reserved && this.reservationId != null && this.reservationId.equals(reservationId)) {
            this.reserved = false;
            this.reservationId = null;
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return String.format("%c%d", row, column);
    }

    public String toFileString() {
        return String.format("%c,%d,%b,%s",
                row, column, reserved, reservationId == null ? "null" : reservationId);
    }

    public static Seat fromFileString(String data) {
        String[] parts = data.split(",");
        char row = parts[0].charAt(0);
        int column = Integer.parseInt(parts[1]);
        boolean reserved = Boolean.parseBoolean(parts[2]);
        String reservationId = parts[3].equals("null") ? null : parts[3];

        Seat seat = new Seat(row, column);
        if (reserved && reservationId != null) {
            seat.reserve(reservationId);
        }
        return seat;
    }
}