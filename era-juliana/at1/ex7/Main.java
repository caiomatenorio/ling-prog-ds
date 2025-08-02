import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        try {
            Integer largest = null;
            
            int current;
            for (int i = 0; i < 7; i++) {
                System.out.print(String.format("Insira o %dº número: ", i+1));
                current = scanner.nextInt();
                largest = largest == null || current > largest ? current : largest;
            }      
  
            System.out.println(String.format("O maior número inserido foi %d.", largest));
        } finally {
            scanner.close();
        }
    }
}
