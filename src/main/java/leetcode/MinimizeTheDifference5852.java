package leetcode;

import java.util.Arrays;

public class MinimizeTheDifference5852 {

  public static void main(String[] args) {
    // int[][] mat = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
    // int target = 13;
    int[][] mat = {
        {4, 1, 2, 2, 2, 1},
        {1, 1, 6, 5, 3, 3},
        {3, 9, 1, 7, 10, 1},
        {4, 3, 1, 1, 6, 10},
        {2, 5, 6, 8, 1, 5},
        {8, 9, 10, 1, 5, 7
        }};
    int target = 23;
    System.out.println(new MinimizeTheDifference5852().minimizeTheDifference(mat, target));
  }

  public int minimizeTheDifference(int[][] mat, int target) {
    int m = mat.length;
    int n = mat[0].length;
    int sum = 0;
    int diff = Integer.MAX_VALUE;
    int[] indexes = new int[m];

    for (int i = 0; i < m; i++) {
      Arrays.sort(mat[i]);
      sum += mat[i][0];
      indexes[i] = 1;
    }

    diff = Math.min(diff, Math.abs(target - sum));

    int i = m;

    while (i <= m * n && diff != 0) {
      int min = Integer.MAX_VALUE;
      int idx = -1;
      for (int r = 0; r < m; r++) {
        if (indexes[r] < n) {
          if (min > mat[r][indexes[r]]) {
            idx = r;
            min = Math.min(min, mat[r][indexes[r]]);
          }
        }
      }
      if (idx != -1) {
        sum = sum + mat[idx][indexes[idx]] - mat[idx][indexes[idx] - 1];
        indexes[idx]++;
        diff = Math.min(diff, Math.abs(sum - target));
      }
      i++;
    }

    return diff;
  }

}
