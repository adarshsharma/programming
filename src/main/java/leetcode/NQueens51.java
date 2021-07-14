package leetcode;

import java.util.ArrayList;
import java.util.List;

public class NQueens51 {

    public List<List<String>> solveNQueens(int n) {
        List<List<String>> res = new ArrayList<>();
        boolean[][] arr = new boolean[n][n];

        queen(res, arr, n, 0);

        return res;
    }

    private void queen(List<List<String>> res, boolean[][] arr, int n, int c) {
        if (c == n) {
            res.add(create(arr));
            return;
        }

        for (int j = 0; j < n; j++) {
            if (possible(arr, c, j)) {
                arr[c][j] = true;
                queen(res, arr, n, c + 1);
                arr[c][j] = false;
            }
        }
    }

    private boolean possible(boolean[][] arr, int c, int j) {
        for (int r = 0; r < c; r++) {
            if (arr[r][j]) {
                return false;
            }
            int d = c - r;
            if (j - d >= 0 && arr[r][j - d]) {
                return false;
            }
            if (j + d < arr.length && arr[r][j + d]) {
                return false;
            }
        }

        return true;
    }

    private List<String> create(boolean[][] arr) {
        List<String> lst = new ArrayList<>();
        for (int i = 0; i < arr.length; i++) {
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < arr.length; j++) {
                sb.append(arr[i][j] ? 'Q' : '.');
            }
            lst.add(sb.toString());
        }

        return lst;
    }

    public static void main(String[] args) {
        List<List<String>> lists = new NQueens51().solveNQueens(8);
        lists.forEach(System.out::println);
    }

}
