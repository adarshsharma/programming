package leetcode;

import java.util.HashMap;
import java.util.Map;

public class CountArrayPairsDivisibleByK2183 {

  public long countPairs(int[] nums, int k) {
    Map<Integer, Integer> map = new HashMap<>();
    int count = 0;
    // for(int num: nums) {
    //   int fact = k/gcd(num, k);
    //   count+=map.getOrDefault(fact, 0);
    //
    // }

    return count;
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
    int[] nums = {1, 2, 3, 4, 5};
    int k = 2;
    System.out.println(new CountArrayPairsDivisibleByK2183().countPairs(nums, k));
  }
}
