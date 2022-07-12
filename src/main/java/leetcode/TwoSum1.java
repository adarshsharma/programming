package leetcode;

import java.util.Arrays;
import java.util.Comparator;

public class TwoSum1 {

  public int[] twoSum(int[] nums, int target) {
    Integer[] idxs = new Integer[nums.length];
    for (int i = 0; i < nums.length; i++) {
      idxs[i] = i;
    }
    Arrays.sort(idxs, Comparator.comparingInt(o -> nums[o]));
    int[] res = new int[2];
    int s = 0;
    int e = nums.length - 1;
    while (s < e) {
      int sum = nums[idxs[s]] + nums[idxs[e]];
      if (sum == target) {
        res[0] = idxs[s];
        res[1] = idxs[e];
        break;
      } else if (sum < target) {
        s++;
      } else {
        e--;
      }
    }

    return res;
  }

}
