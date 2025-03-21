import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        double celsiusValue, farenheitValue;

        var scanner = new Scanner(System.in);

        try {
            celsiusValue = askForCelsius(scanner);
            farenheitValue = convertCelsiusToFarenheit(celsiusValue);

            System.out.printf("O valor da temperatura é %.2f°F.", farenheitValue);
        } finally {
            scanner.close();
        }
    }

    private static double askForCelsius(Scanner scanner) {
        while (true) {
            System.out.print("Insira a temperatura em graus Celsius: ");

            if (scanner.hasNextDouble()) {
                return scanner.nextDouble();
            }

            System.out.println("Insira um valor numérico válido.");
            scanner.next();
        }
    }

    private static double convertCelsiusToFarenheit(double celsiusValue) {
        return (celsiusValue * 9 / 5) + 32;
    }
}
