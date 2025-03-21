import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        var scanner = new Scanner(System.in);

        try {
            int classes = askForClasses(scanner);
            int[] studentsPerClass = askForStudents(scanner, classes);
            double studentsPerClassAverage = getStudentsPerClassAverage(studentsPerClass);

            System.out.printf(
                    "A média de estudantes por turma é %.2f." + System.lineSeparator(),
                    studentsPerClassAverage);

            alertIfMoreThanForty(studentsPerClass);
        } finally {
            scanner.close();
        }
    }

    public static int askForClasses(Scanner scanner) {
        int classes;

        while (true) {
            System.out.print("Insira a quantidade de turmas: ");

            if (scanner.hasNextInt()) {
                classes = scanner.nextInt();

                if (classes > 0) {
                    return classes;
                }
            }

            System.out.println("Insira uma quantidade válida de turmas.");
        }
    }

    public static int[] askForStudents(Scanner scanner, int classes) {
        int currentClassStudents;

        int[] studentsPerClass = new int[classes];

        for (int i = 0; i < classes; i++) {
            while (true) {
                System.out.print("Insira a quantidade de alunos na turma " + (i + 1) + ": ");

                if (scanner.hasNextInt()) {
                    currentClassStudents = scanner.nextInt();

                    if (currentClassStudents >= 0) {
                        studentsPerClass[i] = currentClassStudents;
                        break;
                    }

                    System.out.println("Insira uma quantidade de alunos válida.");
                }
            }
        }

        return studentsPerClass;
    }

    public static double getStudentsPerClassAverage(int[] studentsPerClass) {
        int sum = 0;
        for (int i : studentsPerClass) {
            sum += i;
        }

        return sum / studentsPerClass.length;
    }

    public static void alertIfMoreThanForty(int[] studentsPerClass) {
        for (int i = 0; i < studentsPerClass.length; i++) {
            if (studentsPerClass[i] > 40) {
                System.out.println("A sala " + (i + 1) + " tem mais de 40 alunos.");
            }
        }
    }
}
