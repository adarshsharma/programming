package leetcode;


import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class MinimumSumOfSquaredDifference2333 {

  public long minSumSquareDiff(int[] nums1, int[] nums2, int k1, int k2) {
    int k = k1 + k2;
    PriorityQueue<Integer> queue = new PriorityQueue<>(Comparator.reverseOrder());
    Map<Integer, Integer> map = new HashMap<>();
    for (int i = 0; i < nums1.length; i++) {
      map.merge(Math.abs(nums1[i] - nums2[i]), 1, Integer::sum);
    }
    for (int key : map.keySet()) {
      queue.offer(key);
    }

    while (k > 0 && !queue.isEmpty()) {
      int largest = queue.poll();
      if (largest == 0) {
        break;
      }

      if (!queue.isEmpty()) {
        int secondLargest = queue.peek();

        int diff = Math.min(k, largest - secondLargest);
        int count = map.remove(largest);
        if (count * diff > k) {
          int allDiff = k / count;
          int remain = k % count;
          k = 0;
          map.merge(largest - allDiff, count - remain, Integer::sum);
          map.merge(largest - allDiff - 1, remain, Integer::sum);
        } else {
          k -= (count * diff);
          map.merge(largest - diff, count, Integer::sum);
        }
      } else {
        int count = map.remove(largest);
        if (count * largest > k) {
          int allDiff = k / count;
          int remain = k % count;
          map.merge(largest - allDiff, count - remain, Integer::sum);
          map.merge(largest - allDiff - 1, remain, Integer::sum);
        } else {
          map.remove(largest);
          k = 0;
        }
      }
    }

    long ssum = 0L;
    for (int key : map.keySet()) {
      int value = map.get(key);
      ssum += (long) value * (long) key * (long) key;
    }

    return ssum;
  }

  public static void main(String[] args) {
    // int[] nums1 = {1, 4};
    // int[] nums2 = {2, 5};
    // int k1 = 1;
    // int k2 = 0;
    int[] nums1 = {11,12,13,14,15};
    int[] nums2 = {13,16,16,12,14};
    int k1 = 3;
    int k2 = 6;
    System.out.println(
        new MinimumSumOfSquaredDifference2333().minSumSquareDiff(nums1, nums2, k1, k2));
  }

}
