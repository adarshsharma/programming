package leetcode;

public class NumberOfIncreasingPaths2328 {

  int[][] adjs = {{-1, 0}, {0, -1}, {0, 1}, {1, 0}};
  int m, n;
  Long[][] dp;
  long mod = 1000000007;

  public int countPaths(int[][] grid) {
    m = grid.length;
    n = grid[0].length;
    long result = 0L;
    dp = new Long[m][n];

    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        result = (result + find(i, j, grid)) % mod;
      }
    }

    return Long.valueOf(result).intValue();
  }

  int find(int i, int j, int[][] grid) {
    if (dp[i][j] != null) {
      return dp[i][j].intValue();
    }

    long res = 1L;
    for (int[] adj : adjs) {
      int x = i + adj[0];
      int y = j + adj[1];
      if (x >= 0 && x < m && y >= 0 && y < n && grid[x][y] > grid[i][j]) {
        res = (res + find(x, y, grid)) % mod;
      }
    }

    dp[i][j] = res;

    return dp[i][j].intValue();
  }

  public static void main(String[] args) {
    int[][] grid = {{1, 1}, {3, 4}};
    // int[][] grid = {{1, 2}};
    System.out.println(new NumberOfIncreasingPaths2328().countPaths(grid));
    ;
  }
}
