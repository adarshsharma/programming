package leetcode;

/**
 * Created by adarsh.sharma on 15/09/18.
 */
public class MinimumWindowSubsequence {
    public String minWindow(String S, String T) {
        int n = S.length();
        int m = T.length();

        int[][] dp = new int[m][n + 1];
        dp[0][0] = -1;
        for (int j = 1; j <= n; j++) {
            if (S.charAt(j - 1) == T.charAt(0)) {
                dp[0][j] = j - 1;
            } else {
                dp[0][j] = dp[0][j - 1];
            }
        }

        for (int i = 1; i < m; i++) {
            for (int j = 0; j <= n; j++) {
                if (j == 0) {
                    dp[i][j] = -1;
                } else {
                    if (S.charAt(j - 1) == T.charAt(i)) {
                        dp[i][j] = dp[i - 1][j - 1];
                    } else {
                        dp[i][j] = dp[i][j - 1];
                    }
                }
            }
        }
        int s = -1;
        int len = Integer.MAX_VALUE;
        for (int j = 1; j <= n; j++) {
            if (dp[m - 1][j] != -1 && (j - dp[m - 1][j]) < len) {
                len = j - dp[m - 1][j];
                s = dp[m - 1][j];
            }
        }

        return s == -1 ? "" : S.substring(s, s + len);
    }

    public static void main(String[] args) {
        System.out.println(new MinimumWindowSubsequence().minWindow("abcbcadebdde", "bde"));
    }
}
