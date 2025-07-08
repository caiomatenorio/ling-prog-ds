import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Insira seu nome: ");
        String name = scanner.nextLine();

        System.out.print("Insira seu sexo (M/F)");
        String gender = scanner.nextLine().toLowerCase();

        System.out.print("Insira seu estado civil: ");
        String maritalStatus = scanner.nextLine().toLowerCase();

        if (gender.equals("f") && maritalStatus.equals("casada")) {
            System.out.print("Insira há quantos anos você está casada: ");
            int marriedForYears = scanner.nextInt();
        }

        scanner.close();
    }
}
