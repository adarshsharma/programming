package algoexpert;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class RiverSizes {

    public static List<Integer> riverSizes(int[][] matrix) {
        // Write your code here.
        int m = matrix.length;
        int n = matrix[0].length;
        List<Integer> res = new ArrayList<>();
        int[][] neighbours = {{0, -1}, {0, 1}, {-1, 0}, {1, 0}};

        Set<Integer> visited = new HashSet<>();
        Deque<Integer> dq;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 1 && !visited.contains(i * n + j)) {
                    dq = new ArrayDeque<>();
                    dq.addLast(i * n + j);
                    visited.add(i * n + j);
                    int count = 0;
                    while (!dq.isEmpty()) {
                        int cur = dq.pollFirst();
                        count++;
                        for (int[] adj : neighbours) {
                            int x = (cur / n) + adj[0];
                            int y = (cur % n) + adj[1];
                            if (x >= 0 && x < m && y >= 0 && y < n &&
                                    matrix[x][y] == 1 && !visited.contains(x * n + y)) {
                                dq.addLast(x * n + y);
                                visited.add(x * n + y);
                            }
                        }
                    }
                    res.add(count);
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int[][] matrix = {
            {1, 0, 0, 1, 0},
            {1, 0, 1, 0, 0},
            {0, 0, 1, 0, 1},
            {1, 0, 1, 0, 1},
            {1, 0, 1, 1, 0},
        };
        List<Integer> rivers = riverSizes(matrix);
        System.out.println(rivers);
    }

}
