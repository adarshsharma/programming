package leetcode;

public class MinimumInsertionToPalindrome {

  public int minInsertions(String str) {
    int L = str.length();
    int[][] dp = new int[L][L];
    for (int i = 0; i < L; i++) {
      for (int j = 0; j < L; j++) {
        dp[i][j] = -1;
      }
    }

    return makePalindrome(str, 0, L - 1, dp);
  }

  private int makePalindrome(String str, int s, int e, int[][] dp) {
    if (s >= e) {
      return 0;
    }

    if (dp[s][e] != -1) {
      return dp[s][e];
    }

    char left = str.charAt(s);
    char right = str.charAt(e);

    if (left == right) {
      dp[s][e] = makePalindrome(str, s + 1, e - 1, dp);
      return dp[s][e];
    }

    int firstIdx = -1;
    int lastIdx = -1;
    for (int i = s + 1; i < e; i++) {
      if (str.charAt(i) == left) {
        lastIdx = i;
      } else if (str.charAt(i) == right && firstIdx == -1) {
        firstIdx = i;
      }
    }

    int res = 1 + Math.min(makePalindrome(str, s + 1, e, dp), makePalindrome(str, s, e - 1, dp));
    if (lastIdx != -1) {
      res = Math.min(res, e - lastIdx + makePalindrome(str, s + 1, lastIdx - 1, dp));
    }

    if (firstIdx != -1) {
      res = Math.min(res, firstIdx - s + makePalindrome(str, firstIdx + 1, e - 1, dp));
    }

    dp[s][e] = res;
    return dp[s][e];
  }

  public static void main(String[] args) {
    MinimumInsertionToPalindrome obj = new MinimumInsertionToPalindrome();
    // System.out.println(obj.minInsertions("veiiwv"));
    System.out.println(obj.minInsertions("zjveiiwvc"));
  }

}
