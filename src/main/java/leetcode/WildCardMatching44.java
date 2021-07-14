package leetcode;

public class WildCardMatching44 {

    boolean[][] dp;

    public boolean isMatch(String s, String p) {
        if (s.length() == 0 && p.length() == 0) {
            return true;
        }
        if (p.length() == 0) {
            return false;
        }

        dp = new boolean[s.length() + 1][p.length() + 1];
        for (int i = 0; i <= s.length(); i++) {
            for (int j = 0; j <= p.length(); j++) {
                if (i == 0) {
                    dp[i][j] = (j == 0) || (p.charAt(j - 1) == '*' && dp[i][j - 1]);
                } else {
                    if (j == 0) {
                        dp[i][j] = false;
                    } else {
                        if (s.charAt(i - 1) == p.charAt(j - 1) || p.charAt(j - 1) == '?') {
                            dp[i][j] = dp[i - 1][j - 1];
                        } else if (p.charAt(j - 1) == '*') {
                            dp[i][j] = dp[i][j - 1] || dp[i - 1][j];
                        } else {
                            dp[i][j] = false;
                        }
                    }
                }
            }
        }

        return dp[s.length()][p.length()];

    }

    public static void main(String[] args) {
        System.out.println(new WildCardMatching44().isMatch("aa", "a"));
        System.out.println(new WildCardMatching44().isMatch("cb", "?a"));
    }

}
