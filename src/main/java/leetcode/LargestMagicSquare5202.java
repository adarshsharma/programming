package leetcode;

public class LargestMagicSquare5202 {

    public int largestMagicSquare(int[][] grid) {
        int res = 1;
        int m = grid.length;
        int n = grid[0].length;

        int[][] rowSum = new int[m][n];
        int[][] colSum = new int[m][n];
        int[][] diagSum = new int[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < m; j++) {
                rowSum[i][j] = grid[i][j];
                colSum[i][j] = grid[i][j];
                diagSum[i][j] = grid[i][j];
                if (j > 0) {
                    rowSum[i][j] += rowSum[i][j - 1];
                }
                if (i > 0) {
                    colSum[i][j] += colSum[i - 1][j];
                }
                if (i > 0 && j > 0) {
                    diagSum[i][j] += diagSum[i - 1][j - 1];
                }
            }
        }

        for (int len = 2; len < Math.min(m, n); len++) {
            for (int i = 0; i <= m - len; i++) {
                for (int j = 0; j <= n - len; j++) {
                    boolean found = true;
                    int val = diagSum[i + len][j + len];
                    if (i > 0 && j > 0) {
                        val -= rowSum[i - 1][j - 1];
                    }
                    for (int k = 0; k < len && found; k++) {
                        int rs = rowSum[i + k][j + len] - j > 0 ? rowSum[i + k][j - 1] : 0;
                        int cs = colSum[i + len][j + k] - i > 0 ? colSum[i - 1][j + k] : 0;
                        if (rs != val || cs != val) {
                            found = false;
                        }
                    }
                    int ds = diagSum[i + len][j + len];
                    if (found) {
                        res = len;
                    }
                }
            }
        }

        return res;
    }


    public static void main(String[] args) {
        int[][] grid = {{7, 1, 4, 5, 6}, {2, 5, 1, 6, 4}, {1, 5, 4, 3, 2}, {1, 2, 7, 3, 4}};
        System.out.println(new LargestMagicSquare5202().largestMagicSquare(grid));
    }

}
