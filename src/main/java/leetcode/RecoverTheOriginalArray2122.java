package leetcode;

import java.util.Arrays;
import java.util.TreeMap;

public class RecoverTheOriginalArray2122 {

  public int[] recoverArray(int[] nums) {
    int n = nums.length / 2;
    int[] og = new int[n];
    Arrays.sort(nums);

    int start = 0;
    while (nums[start] == nums[0] || nums[start] == nums[0] + 1) {
      start++;
    }

    while (start <= n) {
      int k = (nums[start] - nums[0]) / 2;
      TreeMap<Integer, Integer> map = getCountMap(nums);
      og = new int[n];
      int i = 0;
      int j = 0;
      while (i < n) {
        while (j < 2 * n && !map.containsKey(nums[j])) {
          j++;
        }
        int l = nums[j];
        j++;
        if (map.containsKey(l + 2 * k)) {
          map.computeIfPresent(l, (a, b) -> b == 1 ? null : b - 1);
          map.computeIfPresent(l + 2 * k, (a, b) -> b == 1 ? null : b - 1);
          og[i] = l + k;
          i++;
        } else {
          start++;
          break;
        }
      }
      if (i == n) {
        System.out.println("k: " + k);
        break;
      }
    }

    return og;
  }


  private TreeMap<Integer, Integer> getCountMap(int[] nums) {
    TreeMap<Integer, Integer> map = new TreeMap<>();
    for (int num : nums) {
      map.merge(num, 1, Integer::sum);
    }
    return map;
  }

  public static void main(String[] args) {
    // int[] nums = {2, 10, 6, 4, 8, 12};
    // int[] nums = {1, 1, 3, 3};
    int[] nums = {11, 6, 3, 4, 8, 7, 8, 7, 9, 8, 9, 10, 10, 2, 1, 9};
    int[] og = new RecoverTheOriginalArray2122().recoverArray(nums);
    Arrays.stream(og).forEach(System.out::println);
  }
}
