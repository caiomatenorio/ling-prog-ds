import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        int oddInput, previousNumber, nextOdd, result;

        var scanner = new Scanner(System.in);

        try {
            oddInput = askForOdd(scanner);

            previousNumber = oddInput - 1;
            nextOdd = oddInput + 2;

            result = previousNumber * previousNumber - nextOdd * nextOdd;

            System.out.printf("A diferença entre o quadrado do número anterior e do ímpar seguinte é %d.\n", result);
        } finally {
            scanner.close();
        }
    }

    private static int askForOdd(Scanner scanner) {
        int odd;

        while (true) {
            System.out.print("Insira um número ímpar: ");

            if (scanner.hasNextInt()) {
                odd = scanner.nextInt();

                if (odd % 2 == 1)
                    return odd;
            }

            System.out.println("Insira um inteiro ímpar válido.");
        }
    }
}
