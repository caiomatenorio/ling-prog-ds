public class Main {
    public static void main(String[] args) {
        int[] numbers = {81, 51, 67, 25, 90};
        System.out.print("A nova array é: ");
        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = numbers[i] * 2;
            System.out.print(numbers[i] + " ");
        }
        System.out.println();
    }
}
