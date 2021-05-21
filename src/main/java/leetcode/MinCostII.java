package leetcode;

/**
 * Created by adarsh.sharma on 28/07/18.
 */
public class MinCostII {
    public int minCostII(int[][] costs) {
        if (costs.length == 0) {
            return 0;
        }
        int k = costs[0].length;
        int n = costs.length;
        if (n > 1 && k < 2) {
            throw new IllegalArgumentException("minimum 2 colors are required");
        }

        if (n == 1 && k == 1) {
            return costs[0][0];
        }

        int[] min = {-1, -1};

        for (int j = 0; j < k; j++) {
            update(costs[0], j, min);
        }

        for (int i = 1; i < n; i++) {
            int[] newMin = {-1, -1};
            for (int j = 0; j < k; j++) {
                if (min[0] != j) {
                    costs[i][j] += costs[i - 1][min[0]];
                } else {
                    costs[i][j] += costs[i - 1][min[1]];
                }
                update(costs[i], j, newMin);
            }
            min = newMin;
        }

        return Math.min(costs[n - 1][min[0]], costs[n - 1][min[1]]);
    }

    private void update(int[] costs, int k, int[] min) {
        if (k == 0) {
            min[0] = 0;
        } else if (k == 1) {
            min[1] = 1;
            if (costs[1] < costs[0]) {
                min[0] = 1;
                min[1] = 0;
            }
        } else {
            if (costs[k] < costs[min[1]]) {
                if (costs[k] < costs[min[0]]) {
                    min[1] = min[0];
                    min[0] = k;
                } else {
                    min[1] = k;
                }
            }
        }
    }

    public static void main(String[] args) {
//        int[][] costs = {{1,5,3},{2,9,4}};
        int[][] costs = {{15, 17, 15, 20, 7, 16, 6, 10, 4, 20, 7, 3, 4}, {11, 3, 9, 13, 7, 12, 6, 7, 5, 1, 7, 18, 9}};
//        int[][] costs = {{4, 5, 3, 1, 7}};
//        int[][] costs = {{8, 16, 12, 18, 9}, {19, 18, 8, 2, 8}, {8, 5, 5, 13, 4}, {15, 9, 3, 19, 2}, {8, 7, 1, 8, 17}, {8, 2, 8, 15, 5}, {8, 17, 1, 15, 3}, {8, 8, 5, 5, 16}, {2, 2, 18, 2, 9}};
        System.out.println(new MinCostII().minCostII(costs));
    }
}
