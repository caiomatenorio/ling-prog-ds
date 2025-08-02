public class Main {
    public static void main(String[] args) {
        int[] numbers = {98235792, 187468, 716827, 25628};
        int sum = 0;
        
        for (int i : numbers) {
            sum += i;
        }

        System.out.println("A soma dos elementos do array é: " + sum);
    }
}