package leetcode;

public class LargestMergeOfTwoStrings1754 {

    public String largestMerge(String word1, String word2) {
        StringBuffer sb = new StringBuffer();

        int n = word1.length();
        int m = word2.length();
        // 0 w1 > w2
        // 1 w1 < w2
        // 2 w1 == w2
        int[][] dp = new int[n + 1][m + 1];

        dp[n][m] = 2;

        for (int i = n - 1; i >= 0; i--) {
            dp[i][m] = 0;
        }

        for (int i = m - 1; i >= 0; i--) {
            dp[n][i] = 1;
        }

        for (int i = n - 1; i >= 0; i--) {
            char c1 = word1.charAt(i);
            for (int j = m - 1; j >= 0; j--) {
                char c2 = word2.charAt(j);
                if (c1 > c2) {
                    dp[i][j] = 0;
                } else if (c1 < c2) {
                    dp[i][j] = 1;
                } else {
                    dp[i][j] = dp[i + 1][j + 1];
                }
            }
        }

        int i = 0;
        int j = 0;
        while (i < n || j < m) {
            char c1 = i < n ? word1.charAt(i) : Character.MIN_VALUE;
            char c2 = j < m ? word2.charAt(j) : Character.MIN_VALUE;
            if (c1 > c2) {
                sb.append(c1);
                i++;
            } else if (c2 > c1) {
                sb.append(c2);
                j++;
            } else {
                if (dp[i][j] == 0) {
                    sb.append(c1);
                    i++;
                } else {
                    sb.append(c2);
                    j++;
                }
            }
        }

        return sb.toString();

    }

    public static void main(String[] args) {
        String word1 = "uuur";
        String word2 = "ur";
        String res = new LargestMergeOfTwoStrings1754().largestMerge(word1, word2);
        System.out.println(res.equals("uuuure"));
    }

}
