package leetcode;

import static java.util.Collections.reverseOrder;

import java.util.Arrays;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class MaximumSumQueries2736 {

  public int[] maximumSumQueries(int[] nums1, int[] nums2, int[][] queries) {
    TreeMap<Integer, TreeSet<Integer>> tmap = new TreeMap<>(reverseOrder());
    int n = nums1.length;
    for (int i = 0; i < n; i++) {
      int sum = nums1[i] + nums2[i];
      if (!tmap.containsKey(sum)) {
        tmap.put(sum, new TreeSet<>());
      }
      tmap.get(sum).add(nums1[i]);
    }

    int[] result = new int[queries.length];
    for (int i = 0; i < queries.length; i++) {
      result[i] = findMaxSum(tmap, queries[i]);
    }
    return result;
  }

  int findMaxSum(TreeMap<Integer, TreeSet<Integer>> tmap, int[] query) {
    for (Integer key : tmap.keySet()) {
      TreeSet<Integer> set = tmap.get(key);
      Integer num1 = set.ceiling(query[0]);
      if (num1 != null && num1 >= query[0]) {
        int num2 = key - num1;
        if (num2 >= query[1]) {
          return key;
        }
      }
    }
    return -1;
  }

  public static void main(String[] args) {
    int[] nums1 = {4, 3, 1, 2};
    int[] nums2 = {2, 4, 9, 5};
    int[][] queries = {{4, 1}, {1, 3}, {2, 5}};

    int[] result = new MaximumSumQueries2736().maximumSumQueries(nums1, nums2, queries);
    System.out.println(Arrays.stream(result).boxed().collect(Collectors.toList()));
  }
}
