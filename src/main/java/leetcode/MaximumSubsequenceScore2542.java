package leetcode;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.TreeMap;

public class MaximumSubsequenceScore2542 {

  public long maxScore(int[] nums1, int[] nums2, int k) {
    int[][] values = new int[nums1.length][2];
    for (int i = 0; i < nums1.length; i++) {
      values[i][0] = i;
      values[i][1] = nums1[i];
    }

    Arrays.sort(values, Comparator.comparingInt(a -> -a[1]));

    PriorityQueue<Integer[]> pq2 = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
    for (int i = 0; i < nums2.length; i++) {
      pq2.add(new Integer[]{i, nums2[i]});
    }
    Integer[] min = pq2.poll();

    Set<Integer> included = new HashSet<>();
    long sum = 0;
    sum += nums1[min[0]];
    included.add(min[0]);
    int n = k - 1;
    int curIdx = 0;
    while (n > 0) {
      if (!included.contains(values[curIdx][0])) {
        sum += values[curIdx][1];
        included.add(values[curIdx][0]);
        n--;
      }
      curIdx++;
    }

    long res = sum * min[1];

    for (int i = 0; i < nums1.length - k; i++) {
      sum -= nums1[min[0]];
      min = pq2.poll();
      if (included.contains(min[0])) {
        while (included.contains(values[curIdx][0])) {
          curIdx++;
        }
        included.add(values[curIdx][0]);
        sum += values[curIdx][1];
      } else {
        included.add(min[0]);
        sum += nums1[min[0]];
      }

      res = Long.max(res, sum * min[1]);
    }

    return res;
  }


  public static void main(String[] args) {
    // int[] nums1 = {1, 3, 3, 2};
    // int[] nums2 = {2, 1, 3, 4};
    // int k = 3;
    int[] nums1 = {4,2,3,1,1};
    int[] nums2 = {7,5,10,9,6};
    int k = 1;
    System.out.println(new MaximumSubsequenceScore2542().maxScore(nums1, nums2, k));
  }

}
