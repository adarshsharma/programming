package leetcode;

import java.util.Arrays;

public class MinPathCostInAGrid2304 {

  public int minPathCost(int[][] grid, int[][] moveCost) {
    int m = grid.length;
    int n = grid[0].length;
    int[] prev = new int[n];
    int[] next = new int[n];

    for (int j = 0; j < n; j++) {
      prev[j] = grid[0][j];
    }

    for (int i = 1; i < m; i++) {
      for (int j = 0; j < n; j++) {
        int min = Integer.MAX_VALUE;
        for (int k = 0; k < n; k++) {
          min = Math.min(min, prev[k] + moveCost[grid[i-1][k]][j]);
        }
        next[j] = grid[i][j] + min;
      }
      prev = next;
      next = new int[n];
    }

    return Arrays.stream(prev).min().getAsInt();
  }

  public static void main(String[] args) {
    int[][] grid = {{5, 3}, {4, 0}, {2, 1}};
    int[][] moveCost = {{9, 8}, {1, 5}, {10, 12}, {18, 6}, {2, 4}, {14, 3}};

    System.out.println(new MinPathCostInAGrid2304().minPathCost(grid, moveCost));
  }

}
