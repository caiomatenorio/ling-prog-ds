import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.print("Insira o nome do aluno: ");
            String name = scanner.nextLine();

            System.out.print("Insira as notas do aluno (separadas por vírgula): ");
            String[] gradesInput = scanner.nextLine().split(",");
            double[] grades = new double[gradesInput.length];
            for (int i = 0; i < gradesInput.length; i++) {
                grades[i] = Double.parseDouble(gradesInput[i].trim());
            }

            double average = calculateArithmeticMean(grades);
            System.out.println("Média das notas de " + name + ": " + average);
        } finally {
            scanner.close();
        }
    }

    public static void printName(String name) {
        System.out.println(name);
    }

    public static double max(double a, double b) {
        return (a > b) ? a : b;
    }

    public static double calculateCircleArea(double radius) {
        return Math.PI * Math.pow(radius, 2);
    }

    public static double calculateArithmeticMean(double[] numbers) {
        double sum = 0;
        for (double number : numbers) {
            sum += number;
        }
        return sum / numbers.length;
    }
}