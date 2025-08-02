import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            String[] names = new String[]{"caio", "emerson", "breno", "davi", "gabriel", "tulio"};

            System.out.print("Insira um nome: ");
            String promptedName = scanner.nextLine().toLowerCase();
            boolean isIn = false;

            for (String name : names) {
                if (name.equals(promptedName)) {
                    isIn = true;
                    break;                
                }            
            }

            System.out.println("O nome " + (isIn ? "" : "não ") + "está na lista.");
        } finally {
            scanner.close();        
        }
    }
}
