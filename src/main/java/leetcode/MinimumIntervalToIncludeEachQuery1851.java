package leetcode;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class MinimumIntervalToIncludeEachQuery1851 {

  public static int[] minInterval(int[][] intervals, int[] queries) {
    Arrays.sort(intervals, Comparator.comparingInt(b -> b[0]));
    Integer[] q = new Integer[queries.length];
    for (int i = 0; i < q.length; i++) {
      q[i] = i;
    }
    Arrays.sort(q, new Comparator<Integer>() {
      public int compare(Integer a, Integer b) {
        return Integer.compare(queries[a], queries[b]);
      }
    });

    PriorityQueue<Integer[]> pq = new PriorityQueue<>(
        Comparator.comparingInt(b -> (b[1] - b[0] + 1)));
    int idx = 0;
    int[] res = new int[q.length];

    for (int i = 0; i < q.length; i++) {
      while (idx < intervals.length) {
        int[] interval = intervals[idx];
        if (interval[0] <= queries[q[i]]) {
          Integer[] invl = new Integer[2];
          invl[0] = interval[0];
          invl[1] = interval[1];
          pq.add(invl);
          idx++;
        } else {
          break;
        }
      }

      while (!pq.isEmpty()) {
        Integer[] interval = pq.poll();
        if (interval[1] >= queries[q[i]]) {
          res[q[i]] = interval[1] - interval[0] + 1;
          pq.add(interval);
          break;
        }
      }

      if (pq.isEmpty()) {
        res[q[i]] = -1;
      }
    }

    return res;
  }

  public static void main(String[] args) {
    int[][] intervals = {{2, 3}, {2, 5}, {1, 8}, {20, 25}};
    int[] q = {2, 19, 5, 22};
    int[] res = minInterval(intervals, q);
    Arrays.stream(res).forEach(System.out::println);
  }

}
