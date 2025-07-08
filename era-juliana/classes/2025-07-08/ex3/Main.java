import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);        

        System.out.print("Insira um número qualquer: ");
        int n = scanner.nextInt();

        System.out.print("O número " + n + " é ");
        if (n % 2 == 0) {
            System.out.print("ímpar");
        } else {
            System.out.print("par");
        }
        System.out.print(".");
        
        scanner.close();
    }
}
