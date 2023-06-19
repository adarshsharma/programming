package practice;

public class SubsetsDivisibleBy5 {

  static long subsetDivisibleByK(int n, int k) {
    long[] subsetWithRemainders = new long[k];
    subsetWithRemainders[0] = 1;

    for (int i = 1; i <= n; i++) {
      long[] curSubsetWithRemainders = new long[k];

      for (int j = 0; j < k; j++) {
        int idx = ((j - i % k) % k + k) % k;
        curSubsetWithRemainders[j] = subsetWithRemainders[idx];
      }
      for (int j = 0; j < k; j++) {
        subsetWithRemainders[j] += curSubsetWithRemainders[j];
      }
    }
    return subsetWithRemainders[0];
  }

  public static void main(String[] args) {
    System.out.println(subsetDivisibleByK(50, 5));
  }

  // 0 -> (0 - 1)
  // 1 -> (1 - 1)
  // 2 -> (2 - 1)
  // 3 -> (3 - 1)
  // 4 -> (4 - 1)

}
