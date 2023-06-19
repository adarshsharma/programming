package leetcode;

import java.util.HashSet;
import java.util.Set;

public class NumberOfExcellentPairs2354 {

  public long countExcellentPairs(int[] nums, int k) {
    long[] count = new long[30];
    long res = 0;
    Set<Integer> set = new HashSet<>();
    for (int a : nums) {
      set.add(a);
    }
    for (int a : set) {
      count[Integer.bitCount(a)]++;
    }
    for (int i = 1; i < 30; ++i) {
      for (int j = 1; j < 30; ++j) {
        if (i + j >= k) {
          res += count[i] * count[j];
        }
      }
    }
    return res;
  }

  public static void main(String[] args) {
    int[] nums = {1, 2, 3, 1, 536870911};
    int k = 3;
    System.out.println(new NumberOfExcellentPairs2354().countExcellentPairs(nums, k));
  }

}
