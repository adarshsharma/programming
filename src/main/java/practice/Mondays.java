package practice;

import java.util.Arrays;

public class Mondays {

  static int circularPrime(int number) {
    boolean[] primes = new boolean[number + 1];
    Arrays.fill(primes, true);
    primes[0] = false;
    primes[1] = false;
    for (int i = 2; i <= number / 2; i++) {
      for (int j = i + i; j <= number; j += i) {
        primes[j] = false;
      }
    }

    int count = 0;
    for (int i = 2; i < primes.length; i++) {
      if (primes[i]) {
        int p = i;
        int digits = ((Double) Math.log10(p)).intValue();
        while (digits > 0) {
          int last = p % 10;

          digits--;
        }
        count++;
      }
    }

    return count;
  }

  public static void main(String[] args) {
    System.out.println(circularPrime(16));
  }


}
