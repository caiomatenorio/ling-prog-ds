
public class EventVenue {
    private Seat[][] seats;
    private int rows;
    private int columns;

    public EventVenue(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        this.seats = new Seat[rows][columns];

        // Initialize all seats
        for (int r = 0; r < rows; r++) {
            char rowLabel = (char) ('A' + r);
            for (int c = 0; c < columns; c++) {
                seats[r][c] = new Seat(rowLabel, c + 1);
            }
        }
    }

    public Seat getSeat(char row, int column) {
        int rowIndex = row - 'A';
        int colIndex = column - 1;

        if (rowIndex >= 0 && rowIndex < rows && colIndex >= 0 && colIndex < columns) {
            return seats[rowIndex][colIndex];
        }
        return null;
    }

    public Seat[][] getAllSeats() {
        return seats;
    }

    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;
    }

    public void displaySeatingChart() {
        System.out.print("   ");
        for (int c = 1; c <= columns; c++) {
            System.out.printf("%3d", c);
        }
        System.out.println("\n   " + "---".repeat(columns));

        for (int r = 0; r < rows; r++) {
            char rowLabel = (char) ('A' + r);
            System.out.print(rowLabel + " |");

            for (int c = 0; c < columns; c++) {
                if (seats[r][c].isReserved()) {
                    System.out.print(" X ");
                } else {
                    System.out.print(" O ");
                }
            }
            System.out.println();
        }

        System.out.println("\nLegend: O = Available, X = Reserved");
    }
}