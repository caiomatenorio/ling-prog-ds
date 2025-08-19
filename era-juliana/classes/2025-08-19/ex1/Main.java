package ex1;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            int[] votes = new int[5];
            int nullVotes = 0;
            
            System.out.print("Insira a quantidade de votos que serão inseridos: ");
            int totalVotes = scanner.nextInt();

            for (int i = 0; i < totalVotes; i++) {
                System.out.printf("Insira o %dº voto (1-5): ", i+1);
                int vote = scanner.nextInt();
                if (vote >= 1 && vote <= 5) {
                    votes[vote - 1]++;
                } else {
                    System.out.println("Voto inválido.");
                    nullVotes++;
                }
            }

            System.out.println("Resultados da votação:");
            for (int i = 0; i < votes.length; i++) {
                System.out.printf("Candidato %d: %d votos\n", i + 1, votes[i]);
            }
            System.out.printf("Votos nulos: %d\n", nullVotes);
        } finally {
            scanner.close();
        }
    }
}
