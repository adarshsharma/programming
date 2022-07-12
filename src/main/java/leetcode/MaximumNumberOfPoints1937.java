package leetcode;

public class MaximumNumberOfPoints1937 {

    public long maxPoints(int[][] points) {
        int m = points.length;
        int n = points[0].length;
        long[] prev = new long[n];

        long max = Long.MIN_VALUE;
        long[] cur;

        for (int r = 0; r < m; r++) {
            cur = new long[n];
            for (int c = 0; c < n; c++) {
                cur[c] = points[r][c] + prev[c];
                max = Math.max(max, cur[c]);
            }
            long mx = 0;
            for (int c = 0; c < n; c++) {
                prev[c] = Math.max(cur[c], mx - 1);
                mx = Math.max(mx - 1, cur[c]);
            }

            mx = 0;
            for (int c = n - 1; c >= 0; c--) {
                prev[c] = Math.max(prev[c], mx - 1);
                mx = Math.max(mx - 1, prev[c]);
            }
        }

        return max;

    }

    public static void main(String[] args) {
        int[][] points = {{1, 2, 3}, {1, 5, 1}, {3, 1, 1}};
        // int[][] points = {
        //     {0, 3, 0, 4, 2},
        //     {5, 4, 2, 4, 1},
        //     {5, 0, 0, 5, 1},
        //     {2, 0, 1, 0, 3}};
        System.out.println(new MaximumNumberOfPoints1937().maxPoints(points));
    }

}
