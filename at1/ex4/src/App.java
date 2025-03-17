import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        int days;
        double kilometers, diaryValue, additionalKilometerValue, totalValue;

        var scanner = new Scanner(System.in);

        try {
            days = askForDays(scanner);
            kilometers = askForKilometers(scanner);

            diaryValue = calculateDiaryValue(days);
            additionalKilometerValue = calculateAdditionalKilometerValue(kilometers);

            totalValue = diaryValue + additionalKilometerValue;

            System.out.printf("O valor total do aluguel foi R$%.2f.\n", totalValue);
        } finally {
            scanner.close();
        }
    }

    private static int askForDays(Scanner scanner) {
        int days;

        while (true) {
            System.out.print("Insira o número de dias rodados: ");

            if (scanner.hasNextInt()) {
                days = scanner.nextInt();

                if (days >= 0)
                    return days;

                continue;
            }

            System.out.println("Insira um valor inteiro positivo válido.");
            scanner.next();
        }
    }

    private static double askForKilometers(Scanner scanner) {
        double kilometers;

        while (true) {
            System.out.print("Insira o número de quilômetros rodados: ");

            if (scanner.hasNextDouble()) {
                kilometers = scanner.nextDouble();

                if (kilometers >= 0)
                    return kilometers;

                continue;
            }

            System.out.println("Insira um valor numérico positivo válido.");
        }
    }

    private static double calculateDiaryValue(int days) {
        return days * 90;
    }

    private static double calculateAdditionalKilometerValue(double kilometers) {
        if (kilometers <= 100)
            return 0;

        return (kilometers - 100) * 12;
    }
}
