package leetcode;

import java.util.PriorityQueue;

public class MarkElementsOnArray3080 {

  public long[] unmarkedSumArray(int[] nums, int[][] queries) {
    PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) ->
    {
      if (a[0] == b[0]) {
        return a[1] - b[1];
      }
      return a[0] - b[0];
    }
    );

    long totalSum = 0L;
    for (int i = 0; i < nums.length; i++) {
      int[] v = new int[2];
      v[0] = nums[i];
      v[1] = i;
      pq.offer(v);
      totalSum += nums[i];
    }

    boolean[] marked = new boolean[nums.length];
    long[] result = new long[queries.length];
    for (int i = 0; i < queries.length; i++) {
      int[] query = queries[i];
      if (!marked[query[0]]) {
        marked[query[0]] = true;
        totalSum -= nums[query[0]];
      }

      int removal = query[1];
      while (removal > 0 && !pq.isEmpty()) {
        int[] top = pq.poll();
        if (!marked[top[1]]) {
          totalSum -= top[0];
          marked[top[1]] = true;
          removal--;
        }
      }

      result[i] = totalSum;
    }

    return result;
  }
}
