package practice;

import java.util.Arrays;

public class Test1 {

  public int findGCD(int[] nums) {
    int max = Arrays.stream(nums).max().getAsInt();
    int min = Arrays.stream(nums).min().getAsInt();
    return gcd(min, max);
  }

  private int gcd(int a, int b) {
    while (a % b != 0) {
      int n = a % b;
      a = b;
      b = n;
    }
    return b;
  }

  public static void main(String[] args) {
    int[] a = {1,2,3,4,6};
    Arrays.stream(a).min().getAsInt();
  }

}
