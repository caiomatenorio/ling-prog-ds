import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        try {
            int evens, odds;
            int[] numbers = new int[10];
            
            evens = 0;
            for (int i = 0; i < numbers.length; i++) {
                System.out.print(String.format("Insira o %dº número: ", i+1));
                numbers[i] = scanner.nextInt();
                if (numbers[i] % 2 == 0) evens++; 
            }
            odds = numbers.length - evens;

            System.out.println(String.format("Dos números inseridos, %d são pares e %d são ímpares.", evens, odds));            
        } finally {
            scanner.close();
        }
    }
}
