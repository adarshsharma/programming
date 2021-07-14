package leetcode;

public class NQueensII52 {
    public int totalNQueens(int n) {
        boolean[][] arr = new boolean[n][n];
        return queen(arr, n, 0);
    }

    private int queen(boolean[][] arr, int n, int c) {
        if (c == n) {
            return 1;
        }

        int count = 0;
        for (int j = 0; j < n; j++) {
            if (possible(arr, c, j)) {
                arr[c][j] = true;
                count += queen(arr, n, c + 1);
                arr[c][j] = false;
            }
        }
        return count;
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

}
