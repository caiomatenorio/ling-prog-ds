import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        try {
            double sum = 0;
            for (int i = 0; i < 4; i++) {
                System.out.print("Digite o " + (i + 1) + "º número: ");
                sum += scanner.nextInt();
            }
            double average = sum / 4;
            System.out.println("A média dos números digitados é: " + average);            
        } finally {
            scanner.close();
        }
    }
}