import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        var scanner = new Scanner(System.in);
        double grade1, grade2, grade3, arithmeticMean, weightedMean1, weightedMean2;

        try {
            grade1 = askForGrade(scanner, "Digite a primeira nota: ");
            grade2 = askForGrade(scanner, "Digite a segunda nota: ");
            grade3 = askForGrade(scanner, "Digite a terceira nota: ");

            arithmeticMean = calculateArithmeticMean(grade1, grade2, grade3);
            weightedMean1 = calculateWeightedMean(grade1, grade2, grade3, 2, 2, 3);
            weightedMean2 = calculateWeightedMean(grade1, grade2, grade3, 1, 2, 2);

            System.out.printf("Média aritmética: %.2f\n", arithmeticMean);
            System.out.printf("Média ponderada 1: %.2f\n", weightedMean1);
            System.out.printf("Média ponderada 2: %.2f\n", weightedMean2);
        } finally {
            scanner.close();
        }
    }

    public static double askForGrade(Scanner scanner, String message) {
        while (true) {
            System.out.print(message);

            if (scanner.hasNextDouble())
                return scanner.nextDouble();

            System.out.println("Nota inválida. Digite um número.");
            scanner.next();
        }
    }

    public static double calculateArithmeticMean(double grade1, double grade2, double grade3) {
        return (grade1 + grade2 + grade3) / 3;
    }

    public static double calculateWeightedMean(
            double grade1,
            double grade2,
            double grade3,
            double weight1,
            double weight2,
            double weight3) {
        return (grade1 * weight1 + grade2 * weight2 + grade3 * weight3) / (weight1 + weight2 + weight3);
    }
}
