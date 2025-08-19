import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            int[] seats = new int[10];

            for (int i = 0; i < seats.length; i++) {
                seats[i] = 0;
            }

            do {
                System.out.println("Menu:");
                System.out.println("1. Reservar assento");
                System.out.println("2. Cancelar reserva");
                System.out.println("3. Mostrar assentos");
                System.out.println("4. Sair");
                System.out.print("Escolha uma opção: ");

                int option = scanner.nextInt();

                switch (option) {
                    case 1:
                        System.out.print("Digite o número do assento para reservar: ");
                        int bookSeat = scanner.nextInt();
                        bookSeat(bookSeat, seats);
                        break;
                    case 2:
                        System.out.print("Digite o número do assento para cancelar a reserva: ");
                        int cancelSeat = scanner.nextInt();
                        cancelBooking(cancelSeat, seats);
                        break;
                    case 3:
                        showSeats(seats);
                        break;
                    case 4:
                        System.out.println("Saindo...");
                        return;
                    default:
                        System.out.println("Opção inválida.");
                }
            } while (true);
        } finally {
            scanner.close();
        }
    }

    private static void bookSeat(int seat, int[] seats) {
        if (seat < 0 || seat >= seats.length) {
            System.out.println("Assento inválido.");
            return;
        }

        if (seats[seat-1] != 0) {
            System.out.println("Assento " + seat + " já está reservado.");
            return;
        }

        seats[seat-1] = 1;
        System.out.println("Assento " + seat + " reservado com sucesso.");
    }

    private static void cancelBooking(int seat, int[] seats) {
        if (seat < 0 || seat >= seats.length) {
            System.out.println("Assento inválido.");
            return;
        }

        if (seats[seat-1] == 0) {
            System.out.println("Assento " + seat + " não está reservado.");
            return;
        }

        seats[seat-1] = 0;
        System.out.println("Reserva do assento " + seat + " cancelada com sucesso.");
    }

    private static void showSeats(int[] seats) {
        System.out.println("Assentos:");

        for (int seat : seats) {
            System.out.print(seat != 0 ? "[X] " : "[ ] ");
        }

        System.out.println();
    }
}
