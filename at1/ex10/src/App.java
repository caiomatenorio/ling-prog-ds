import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        var scanner = new Scanner(System.in);

        try {
            int number = askForNumber(scanner);
            String prime = isPrime(number) ? "" : "não ";

            System.out.printf("O número %d %sé primo.\n", number, prime);
        } finally {
            scanner.close();
        }
    }

    private static int askForNumber(Scanner scanner) {
        int number;

        while (true) {
            System.err.print("Insira um inteiro maior que um: ");

            if (scanner.hasNextInt()) {
                number = scanner.nextInt();

                if (number > 1) {
                    return number;
                }

                continue;
            }

            System.out.println("Insira um inteiro maior que um.");
            scanner.next();
        }
    }

    private static boolean isPrime(int n) {
        // if a number is smaller than the first prime number, it is not prime
        if (n < 2)
            return false;

        // dividing n into two factors, if one of them satisfies 2 <= x <= sqrt(n), n is
        // not prime, because the largest factoring possible is n = x * x, therefore the
        // largest factor is x = sqrt(n)
        for (int d = 2; d <= Math.floor(Math.sqrt(n)); d++) {
            // d is considered a factor of n only if n is divisible by d (n mod d = 0)
            if (n % d == 0) {
                return false;
            }
        }

        return true;
    }
}
