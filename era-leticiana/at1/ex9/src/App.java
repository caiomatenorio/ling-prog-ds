import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        var scanner = new Scanner(System.in);

        try {
            double[] numbers = askForThreeNumbers(scanner);
            double largestNumber = max(numbers);
            double smallestNumber = min(numbers);

            System.out.printf("O maior número é %f e o menor é %f.\n", largestNumber, smallestNumber);
        } finally {
            scanner.close();
        }
    }

    private static double[] askForThreeNumbers(Scanner scanner) {
        String cardinal;
        var numbers = new double[3];

        for (int i = 0; i < 3; i++) {
            cardinal = i == 0 ? "primeiro" : i == 1 ? "segundo" : "terceiro";

            while (true) {
                System.out.printf("Insira o %s número: ", cardinal);

                if (scanner.hasNextDouble()) {
                    numbers[i] = scanner.nextDouble();
                    break;
                }

                System.out.println("Insira um valor numérico válido.");
                scanner.next();
            }
        }

        return numbers;
    }

    private static double max(double[] numbers) {
        Double maximum = null;

        for (double i : numbers) {
            if (maximum == null || maximum < i) {
                maximum = i;
            }
        }

        return maximum;
    }

    private static double min(double[] numbers) {
        Double minimum = null;

        for (double i : numbers) {
            if (minimum == null || minimum > i) {
                minimum = i;
            }
        }

        return minimum;
    }
}
