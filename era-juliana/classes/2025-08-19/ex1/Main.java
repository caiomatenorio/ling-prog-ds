package ex1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            int[] votes = new int[5];
            int totalVotes = askForTotalVotes(scanner);
            int nullVotes = askForVotes(scanner, votes, totalVotes);
            printResults(votes, nullVotes);
            int[] winners = findWinners(votes);
            printWinners(winners);
        } finally {
            scanner.close();
        }
    }

    private static int askForTotalVotes(Scanner scanner) {
        System.out.print("Insira a quantidade total de votos: ");
        return scanner.nextInt();
    }

    private static int askForVotes(Scanner scanner, int[] votes, int totalVotes) {
        int nullVotes = 0;

        for (int i = 0; i < totalVotes; i++) {
            System.out.printf("Insira o %dº voto (1-%d): ", i+1, votes.length);
            int vote = scanner.nextInt();
            if (vote >= 1 && vote <= votes.length) {
                votes[vote - 1]++;
            } else {
                System.out.println("Voto inválido.");
                nullVotes++;
            }
        }

        return nullVotes;
    }

    private static void printResults(int[] votes, int nullVotes) {
        System.out.println("Resultados da votação:");
        for (int i = 0; i < votes.length; i++) {
            System.out.printf("Candidato %d: %d votos\n", i + 1, votes[i]);
        }
        System.out.printf("Votos nulos: %d\n", nullVotes);
    }

    private static int[] findWinners(int[] votes) {
        int maxVotes = Arrays.stream(votes).max().orElse(0);
        return IntStream.range(0, votes.length)
                .filter(i -> votes[i] == maxVotes)
                .toArray();
    }

    private static void printWinners(int[] winners) {
        if (winners.length == 0) {
            System.out.println("Ninguém venceu.");
            return;
        }

        if (winners.length == 1) {
            System.out.printf("Candidato %d venceu.\n", winners[0] + 1);
            return;
        }

        System.out.printf("%d candidatos venceram: ", winners.length);
        for (int i = 0; i < winners.length; i++) {
            System.out.printf("%d ",winners[i] + 1);
        }
        System.out.println();
    }
}
