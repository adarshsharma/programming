package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class SmallestRangeCovering632 {

  public int[] smallestRange(List<List<Integer>> nums) {
    int k = nums.size();
    List<int[]> sorted = new ArrayList<>();
    Set<Integer> set = new HashSet<>();
    for (int i = 0; i < k; i++) {
      set.add(i);
      for (int num : nums.get(i)) {
        int[] val = new int[2];
        val[0] = num;
        val[1] = i;
        sorted.add(val);
      }
    }

    Collections.sort(sorted, Comparator.comparingInt(x -> x[0]));

    int[] range = new int[2];
    if (k == 1) {
      range[0] = sorted.get(0)[0];
      range[1] = range[0];
      return range;
    }
    //sorted.stream().forEach(x -> System.out.println(x[0] + " " + x[1]));
    //System.out.println(set);
    int left = 0;
    int right = 1;
    int n = sorted.size();
    Map<Integer, Integer> map = new HashMap<>();
    range[1] = n - 1;
    int[] leftVal = sorted.get(left);
    map.merge(leftVal[1], 1, Integer::sum);

    while (right < n) {
      int[] rightVal = sorted.get(right);
      map.merge(rightVal[1], 1, Integer::sum);
      while (left <= right && map.get(leftVal[1]) > 1) {
        map.merge(leftVal[1], -1, Integer::sum);
        left++;
        leftVal = sorted.get(left);
      }
      if (map.keySet().size() == set.size() && (range[1] - range[0] > rightVal[0] - leftVal[0])) {
        range[0] = leftVal[0];
        range[1] = rightVal[0];
      }

      right++;
    }

    return range;
  }

  public static void main(String[] args) {
    List<List<Integer>> nums = new ArrayList<>();
    nums.add(Arrays.asList(4, 10, 15, 24, 26));
    nums.add(Arrays.asList(0, 9, 12, 20));
    nums.add(Arrays.asList(5, 18, 22, 30));
    int[] res = new SmallestRangeCovering632().smallestRange(nums);
    System.out.println(Arrays.toString(res));
  }
}
