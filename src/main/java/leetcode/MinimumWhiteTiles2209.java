package leetcode;

public class MinimumWhiteTiles2209 {

  int[] prefixSum;
  Integer[][] dp;

  public int minimumWhiteTiles(String floor, int numCarpets, int carpetLen) {
    char[] f = floor.toCharArray();
    dp = new Integer[1001][1001];

    prefixSum = new int[floor.length()];
    for (int i = 0; i < floor.length(); i++) {
      prefixSum[i] = (floor.charAt(i) == '1' ? 1 : 0) + (i > 0 ? prefixSum[i - 1] : 0);
    }
    return minWhite(f, floor.length() - 1, numCarpets, carpetLen);
  }

  private int minWhite(char[] f, int idx, int numCarpets, int carpetLen) {
    //starting point of first tile
    if (idx < 0) {
      return 0;
    }

    if (numCarpets == 0) {
      dp[idx][numCarpets] = prefixSum[idx];
    }

    if (dp[idx][numCarpets] != null) {
      return dp[idx][numCarpets];
    }

    int min = f.length;
    int uncovered = 0;

    for (int i = idx; i >= 0; i--) {
      int val = uncovered + minWhite(f, i - carpetLen, numCarpets - 1, carpetLen);
      if (min < val) {
        dp[idx][numCarpets] = min;
        return dp[idx][numCarpets];
      } else {
        min = val;
        uncovered += (f[i] == '1' ? 1 : 0);
      }
    }
    return min;
  }

  public static void main(String[] args) {
    String floor = "10110101";
    int numCarpets = 2;
    int carpetLen = 2;
    System.out.println(new MinimumWhiteTiles2209().minimumWhiteTiles(floor, numCarpets, carpetLen));
  }

}
