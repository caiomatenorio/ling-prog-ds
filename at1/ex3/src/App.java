import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        double rawPrice, finalPrice;

        var scanner = new Scanner(System.in);

        try {
            rawPrice = askForPrice(scanner);

            if (needsTaxes(rawPrice)) {
                finalPrice = applyTaxes(rawPrice);

                System.out.printf("O valor total das mercadorias com os impostos foi de R$%.2f.\n", finalPrice);
            } else {
                System.out.printf("Não houve acréscimo de impostos, portanto o valor final foi %.2f.\n", rawPrice);
            }
        } finally {
            scanner.close();
        }
    }

    private static double askForPrice(Scanner scanner) {
        double price;

        while (true) {
            System.out.print("Insira o valor total das mercadorias compradas: ");

            if (scanner.hasNextDouble()) {
                price = scanner.nextDouble();

                if (price >= 0)
                    return price;

                continue;
            }

            System.out.println("Insira um valor numérico positivo válido.");
            scanner.next();
        }
    }

    private static boolean needsTaxes(double value) {
        return value >= 500;
    }

    private static double applyTaxes(double previousValue) {
        /*
         * Following this base function, we can simplify the process of applying taxes
         * on the values over R$500
         * V(x) = 500 + 1.5(x - 500)
         * V(x) = 500 + 1.5x - 750
         * V(x) = 1.5x - 250
         */
        return 1.5 * previousValue - 250;
    }
}
