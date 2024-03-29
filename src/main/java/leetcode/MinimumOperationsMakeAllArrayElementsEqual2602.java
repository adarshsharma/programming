package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MinimumOperationsMakeAllArrayElementsEqual2602 {

  public List<Long> minOperations(int[] nums, int[] queries) {
    Arrays.sort(nums);
    long[] prefixSum = new long[nums.length];
    prefixSum[0] = nums[0];
    for (int i = 1; i < nums.length; i++) {
      prefixSum[i] = prefixSum[i - 1] + nums[i];
    }

    List<Long> res = new ArrayList<>();
    for (int query : queries) {
      long ops = 0L;
      int idx = Arrays.binarySearch(nums, query);
      if (idx >= 0) {
        ops += ((long) (idx + 1) * query - prefixSum[idx]);
        ops += (prefixSum[nums.length - 1] - prefixSum[idx]
            - (long) (nums.length - idx - 1) * query);
      } else {
        idx = -(idx + 1);
        if (idx == 0) {
          ops += (prefixSum[nums.length - 1] - ((long) nums.length * query));
        } else {
          ops += ((long) idx * query - prefixSum[idx - 1]);
          if (idx != nums.length) {
            ops += (prefixSum[nums.length - 1] - prefixSum[idx - 1]
                - (long) (nums.length - idx) * query);
          }
        }
      }
      res.add(ops);
    }

    return res;
  }


  public static void main(String[] args) {
    // int[] nums = {2, 3, 6, 8};
    // int[] queries = {1};
    int[] nums = {47, 50, 97, 58, 87, 72, 41, 63, 41, 51, 17, 21, 7, 100, 69, 66, 79, 92, 84, 9, 57,
        26, 26, 28, 83, 38};
    int[] queries = {50, 84, 76, 41, 64, 82, 20, 22, 64, 7, 38, 92, 39, 28, 22, 3, 41, 46, 47, 50,
        88, 51, 9, 49, 38, 67, 26, 65, 89, 27, 71, 25, 77, 72, 65, 41, 84, 68, 51, 26, 84, 24, 79,
        41, 96, 83, 92, 9, 93, 84, 35, 70, 74, 79, 37, 38, 26, 26, 41, 26};
    System.out.println(
        new MinimumOperationsMakeAllArrayElementsEqual2602().minOperations(nums, queries));
  }

}
