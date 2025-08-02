public class Main {
    public static void main(String[] args) {
        int[] numbers = {1, 10, 20, 5, 4, 29};
        int larger_than_10 = 0;

        for (int i : numbers) {
            if (i > 10) {
                larger_than_10++;
            }
        }

        System.out.println("A quantidade de números maiores que 10 é: " + larger_than_10);
    }
}