import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        var scanner = new Scanner(System.in);
        double seconds, days, hours, minutes;

        try {
            seconds = askForSeconds(scanner);

            days = calculateDays(seconds);
            hours = calculateHours(seconds);
            minutes = calculateMinutes(seconds);

            System.out.printf("Dias: %f\n", days);
            System.out.printf("Horas: %f\n", hours);
            System.out.printf("Minutos: %f\n", minutes);
        } finally {
            scanner.close();
        }
    }

    private static double askForSeconds(Scanner scanner) {
        double seconds;

        while (true) {
            System.out.print("Insira um valor em segundos: ");

            if (scanner.hasNextDouble()) {
                seconds = scanner.nextDouble();

                if (seconds >= 0)
                    return seconds;
            }

            System.out.println("Valor inválido. Insira um número positivo.");
            scanner.next();
        }
    }

    private static double calculateDays(double seconds) {
        return seconds / 86400;
    }

    private static double calculateHours(double seconds) {
        return seconds / 3600;
    }

    private static double calculateMinutes(double seconds) {
        return seconds / 60;
    }
}
