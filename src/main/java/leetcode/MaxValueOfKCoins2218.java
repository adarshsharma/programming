package leetcode;

import java.util.Arrays;
import java.util.List;

public class MaxValueOfKCoins2218 {

  public int maxValueOfCoins(List<List<Integer>> piles, int k) {
    int[] prev = new int[k + 1];

    for (int p = 0; p < piles.size(); p++) {
      int[] sum = new int[k + 1];
      for (int i = 1; i < sum.length; i++) {
        sum[i] = sum[i - 1];
        if (piles.get(p).size() >= i) {
          sum[i] += piles.get(p).get(i - 1);
        }
      }

      if (p == 0) {
        prev = sum;
      } else {
        int[] cur = new int[k + 1];

        for (int c = 0; c <= k; c++) {
          int max = 0;
          for (int t = 0; t <= c; t++) {
            max = Math.max(max, sum[t] + prev[c - t]);
          }
          cur[c] = max;
        }

        prev = cur;
      }

    }

    return prev[k];
  }

  public static void main(String[] args) {
    List<List<Integer>> piles = Arrays.asList(Arrays.asList(1, 100, 3), Arrays.asList(7, 8, 9));
    int k = 2;
    System.out.println(new MaxValueOfKCoins2218().maxValueOfCoins(piles, k));
  }

}
