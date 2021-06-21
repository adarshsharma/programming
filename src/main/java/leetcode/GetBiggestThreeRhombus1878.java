package leetcode;

import java.util.Arrays;
import java.util.Comparator;
import java.util.TreeSet;


public class GetBiggestThreeRhombus1878 {

    public int[] getBiggestThree(int[][] grid) {
        TreeSet<Integer> rest = new TreeSet<>(Comparator.reverseOrder());
        int m = grid.length;
        int n = grid[0].length;
        int maxS = (Math.min(m, n) + 1) / 2;
        int[][] leftPrev = new int[m][n];
        int[][] rightPrev = new int[m][n];

        for (int s = 0; s < maxS; s++) {
            int[][] leftCur = new int[m][n];
            int[][] rightCur = new int[m][n];
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (s == 0) {
                        leftCur[i][j] = grid[i][j];
                        rightCur[i][j] = grid[i][j];
                        addToSet(rest, grid[i][j]);
                    } else {
                        if (i + 1 < m && j > 0) {
                            leftCur[i][j] = leftPrev[i + 1][j - 1] + grid[i][j];
                        }
                        if (i + 1 < m && j < n - 1) {
                            rightCur[i][j] = rightPrev[i + 1][j + 1] + grid[i][j];
                        }

                        // if (j >= s && j <= n - maxS && i <= m - maxS - 1) {
                        if (j >= s && j < n - s && i < m - 2 * s) {
                            int sum = leftCur[i][j] + rightCur[i][j] - grid[i][j];
                            sum = sum + rightPrev[i + s + 1][j - s + 1]
                                      + leftPrev[i + s + 1][j + s - 1] - grid[i + 2 * s][j];
                            addToSet(rest, sum);
                        }
                    }
                }
            }
            leftPrev = leftCur;
            rightPrev = rightCur;
        }

        int[] res = new int[rest.size()];
        int i = 0;
        for (int x : rest) {
            res[i++] = x;
        }
        return res;
    }

    private void addToSet(TreeSet<Integer> rest, int s) {
        rest.add(s);
        if (rest.size() > 3) {
            rest.remove(rest.last());
        }
    }

    public static void main(String[] args) {
        // int[][] grid = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        // int[][] grid = {{3, 4, 5, 1, 3}, {3, 3, 4, 2, 3}, {20, 30, 200, 40, 10}, {1, 5, 5, 4, 1},
        //     {4, 3, 2, 2, 5}};
        int[][] grid = {
            {20, 17, 9, 13, 5, 2, 9, 1, 5},
            {14, 9, 9, 9, 16, 18, 3, 4, 12},
            {18, 15, 10, 20, 19, 20, 15, 12, 11},
            {19, 16, 19, 18, 8, 13, 15, 14, 11},
            {4, 19, 5, 2, 19, 17, 7, 2, 2},
        };
        int[] res = new GetBiggestThreeRhombus1878().getBiggestThree(grid);
        Arrays.stream(res).forEach(System.out::println);
    }

}
