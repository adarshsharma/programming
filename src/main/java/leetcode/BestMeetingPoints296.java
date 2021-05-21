package leetcode;

/**
 * Created by adarsh.sharma on 14/08/18.
 */
public class BestMeetingPoints296 {
    public int minTotalDistance(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        if (m == 0) {
            return 0;
        }

        return getDis(getRow(grid, m, n)) + getDis(getColumn(grid, m, n));
    }

    private int getDis(int[] vals) {
        int n = vals.length;
        int[] sum = new int[n];
        int count = vals[0];
        for (int i = 1; i < n; i++) {
            sum[i] = sum[i - 1] + count;
            count += vals[i];
        }

        int dis = Integer.MAX_VALUE;
        count = 0;
        int d = 0;
        for (int i = n - 1; i >= 0; i--) {
            d = d + count;
            dis = Math.min(dis, d + sum[i]);
            count += vals[i];
        }

        return dis;
    }

    private int[] getRow(int[][] grid, int m, int n) {
        int[] row = new int[n];
        for (int j = 0; j < n; j++) {
            for (int i = 0; i < m; i++) {
                row[j] += grid[i][j];
            }
        }
        return row;
    }

    private int[] getColumn(int[][] grid, int m, int n) {
        int[] column = new int[m];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                column[i] += grid[i][j];
            }
        }

        return column;
    }

    public static void main(String[] args) {
        BestMeetingPoints296 best = new BestMeetingPoints296();
//        int[][] grid = {{0,0,0,0,0,0,0,0,0},{0,0,0,0,1,0,0,1,0},{1,1,0,0,0,0,1,0,0},{0,0,0,1,1,1,0,0,0}};
        int[][] grid = {{1, 0, 0, 0, 1}, {0, 0, 0, 0, 0}, {0, 0, 1, 0, 0}};
        System.out.println(best.minTotalDistance(grid));
    }
}
