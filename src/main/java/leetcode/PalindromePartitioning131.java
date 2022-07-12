package leetcode;

import java.util.ArrayList;
import java.util.List;

public class PalindromePartitioning131 {

    public List<List<String>> partition(String s) {
        int n = s.length();
        boolean[][] p = new boolean[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= i; j++) {
                p[j][i] = (i==j) || (s.charAt(i) == s.charAt(j) && (i-j<2 || p[j+1][i-1])) ;
            }
        }

        List<List<String>> res = new ArrayList<>();
        List<String> perm = new ArrayList<>();
        permute(res, perm, s, p, 0);
        return res;
    }

    private void permute(List<List<String>> res, List<String> perm, String s, boolean[][] p,
        int start) {
        if (start == s.length()) {
            res.add(new ArrayList<>(perm));
        }

        for (int e = start; e < s.length(); e++) {
            if (p[start][e]) {
                perm.add(s.substring(start, e + 1));
                permute(res, perm, s, p, e + 1);
                perm.remove(perm.size() - 1);
            }
        }
    }

    public static void main(String[] args) {
        System.out.println(new PalindromePartitioning131().partition("efe"));
    }

}
