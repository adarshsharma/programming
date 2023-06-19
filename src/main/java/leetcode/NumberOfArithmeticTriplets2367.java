package leetcode;

import java.util.Arrays;

public class NumberOfArithmeticTriplets2367 {

  public int arithmeticTriplets(int[] nums, int diff) {
    int n = nums.length;
    int res = 0;
    for (int i = 0; i < n; i++) {
      if (Arrays.binarySearch(nums, i + 1, n, nums[i] + diff) >= 0 &&
          Arrays.binarySearch(nums, i + 1, n, nums[i] + 2 * diff) >= 0) {
        res++;
      }
    }

    return res;
  }

  public static void main(String[] args) {
    int[] nums = {0, 1, 4, 6, 7, 10};
    int diff = 3;
    System.out.println(new NumberOfArithmeticTriplets2367().arithmeticTriplets(nums, diff));
  }

}
