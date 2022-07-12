package leetcode;

import java.util.ArrayDeque;
import java.util.Deque;

public class SumOfSubArrayRanges {

  public long subArrayRanges(int[] nums) {
    int sum = 0;

    for (int len = 2; len <= nums.length; len++) {
      Deque<Integer> maxQ = new ArrayDeque<>();
      Deque<Integer> minQ = new ArrayDeque<>();
      for (int i = 0; i < nums.length; i++) {
        if (maxQ.size() > 0 && maxQ.peekFirst() <= i - len) {
          maxQ.pollFirst();
        }
        if (minQ.size() > 0 && minQ.peekFirst() <= i - len) {
          minQ.pollFirst();
        }

        while (maxQ.size() > 0 && nums[i] >= nums[maxQ.peekLast()]) {
          maxQ.pollLast();
        }
        maxQ.addLast(i);

        while (minQ.size() > 0 && nums[i] <= nums[minQ.peekLast()]) {
          minQ.pollLast();
        }
        minQ.addLast(i);
        if (i >= len - 1) {
          sum += (nums[maxQ.peekFirst()] - nums[minQ.peekFirst()]);
        }
      }
    }

    return sum;
  }

  public static void main(String[] args) {
    int[] nums = {-69, -70, -56, -83, 63};
    System.out.println(new SumOfSubArrayRanges().subArrayRanges(nums));
  }
}
