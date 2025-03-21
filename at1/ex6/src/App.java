import java.util.ArrayList;
import java.util.List;

public class App {
    public static void main(String[] args) throws Exception {
        Integer[] primes = generatePrimes(100);

        for (int i : primes) {
            System.out.println(i);
        }
    }

    private static boolean isPrime(int n) {
        // if a number is smaller than the first prime number, it is not prime
        if (n < 2) {
            return false;
        }

        // dividing n into two factors, if one of them satisfies 2 <= x <= sqrt(n), n is
        // not prime, because the largest factoring possible is n = x * x, therefore the
        // largest factor is x = sqrt(n)
        for (int d = 2; d <= Math.floor(Math.sqrt(n)); d++) {
            // d is considered a factor of n only if n is divisible by d (n mod d = 0)
            if (n % d == 0) {
                return false;
            }
        }

        return true;
    }

    private static Integer[] generatePrimes(int n) throws IllegalArgumentException {
        if (n < 1) {
            throw new IllegalArgumentException("n must be a positive integer");
        }

        List<Integer> primes = new ArrayList<>() {
            {
                add(2); // the first prime number
            }
        };

        int current = 3; // second prime number
        while (primes.size() < n) {
            if (isPrime(current)) {
                primes.add(current);
            }

            current += 2; // as the only pair prime is 2, we can only verify primes
        }

        return primes.toArray(new Integer[0]);
    }
}
