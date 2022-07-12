package leetcode;

import java.util.HashMap;
import java.util.Map;

public class PalindromePartitioning132 {

    public int minCut(String s) {
        int n = s.length();
        boolean[][] p = new boolean[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= i; j++) {
                p[j][i] =
                    (i == j) || (s.charAt(i) == s.charAt(j) && (i - j < 2 || p[j + 1][i - 1]));
            }
        }

        Map<Integer, Integer> dp = new HashMap<>();
        dp.put(0, 0);
        return min(p, dp, n - 1);
    }

    private int min(boolean[][] p, Map<Integer, Integer> dp, int end) {
        if (dp.containsKey(end)) {
            return dp.get(end);
        }

        if (p[0][end]) {
            dp.put(end, 0);
            return 0;
        }

        int min = Integer.MAX_VALUE;
        for (int i = end - 1; i >= 0; i--) {
            if (p[i + 1][end]) {
                min = Math.min(min, 1 + min(p, dp, i));
            }
        }

        dp.put(end, min);
        return min;
    }

    public static void main(String[] args) {
        System.out.println(new PalindromePartitioning132().minCut("abaac"));
    }

}
