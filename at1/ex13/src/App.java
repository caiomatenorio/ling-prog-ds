import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        var scanner = new Scanner(System.in);

        try {
            double initialSalary = askForInitialSalary(scanner);
            double rate = askForRate(scanner);
            int years = askForYears(scanner);

            double increasedSalary = calculateIncreasedSalary(initialSalary, rate, years);

            System.out.printf("O salário após %d anos será: %.2f%n", years, increasedSalary);
        } finally {
            scanner.close();
        }
    }

    private static double askForInitialSalary(Scanner scanner) {
        while (true) {
            System.out.print("Insira o salário inicial: ");

            if (scanner.hasNextDouble()) {
                return scanner.nextDouble();
            }

            System.out.println("Insira um valor numérico válido.");
        }
    }

    private static double askForRate(Scanner scanner) {
        while (true) {
            System.out.print("Insira a taxa anual de aumento em porcentagem: ");

            if (scanner.hasNextDouble()) {
                return scanner.nextDouble();
            }

            System.out.println("Insira um valor numérico válido.");
        }
    }

    private static int askForYears(Scanner scanner) {
        while (true) {
            System.out.print("Insira os anos: ");

            if (scanner.hasNextInt()) {
                return scanner.nextInt();
            }

            System.out.println("Insira um valor inteiro válido.");
        }
    }

    private static double calculateIncreasedSalary(double initialSalary, double rate, int years) {
        double currentSalary = initialSalary;

        for (int i = 0; i < years; i++) {
            currentSalary += currentSalary * (rate / 100);
            rate *= 2;
        }

        return currentSalary;
    }
}
