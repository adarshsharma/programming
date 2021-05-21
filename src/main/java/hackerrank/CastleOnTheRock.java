package hackerrank;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Created by adarsh.sharma on 05/08/18.
 */
public class CastleOnTheRock {

    static class N implements Comparable<N> {
        int idx;
        int val;

        public N(int idx, int val) {
            this.idx = idx;
            this.val = val;
        }

        @Override
        public int compareTo(N other) {
            return this.val - other.val;
        }
    }

    static int minimumMoves(String[] grid, int startX, int startY, int goalX, int goalY) {
        Queue<N> queue = new PriorityQueue<>();
        Map<Integer, N> mp = new HashMap<>();
        int m = grid.length;
        int n = grid[0].length();
        boolean[] visited = new boolean[m * n];
        for (int i = 0; i < m * n; i++) {
            mp.put(i, new N(i, Integer.MAX_VALUE));
        }
        int source = get1D(n, startX, startY);
        int target = get1D(n, goalX, goalY);
        N e = new N(source, 0);
        queue.add(e);
        mp.put(source, e);

        while (!queue.isEmpty()) {
            N node = queue.remove();
            mp.get(node.idx).val = node.val;
            int idx = node.idx;
            visited[idx] = true;
            if (idx == target) {
                return mp.get(target).val;
            }
            int r = get2DRow(n, idx);
            int c = get2DCol(n, idx);

            for (int j = c + 1; j < n && grid[r].charAt(j) != 'X'; j++) {
                if (!visited[get1D(n, r, j)]) {
                    if (mp.get(get1D(n, r, j)).val > mp.get(get1D(n, r, c)).val + 1) {
                        mp.get(get1D(n, r, j)).val = mp.get(get1D(n, r, c)).val + 1;
                        queue.remove(mp.get(get1D(n, r, j)));
                        queue.add(mp.get(get1D(n, r, j)));
                    }
                }
            }

            for (int j = c - 1; j >= 0 && grid[r].charAt(j) != 'X'; j--) {
                if (!visited[get1D(n, r, j)]) {
                    if (mp.get(get1D(n, r, j)).val > mp.get(get1D(n, r, c)).val + 1) {
                        mp.get(get1D(n, r, j)).val = mp.get(get1D(n, r, c)).val + 1;
                        queue.remove(mp.get(get1D(n, r, j)));
                        queue.add(mp.get(get1D(n, r, j)));
                    }
                }
            }

            for (int i = r + 1; i < m && grid[i].charAt(c) != 'X'; i++) {
                if (!visited[get1D(n, i, c)]) {
                    if (mp.get(get1D(n, i, c)).val > mp.get(get1D(n, r, c)).val + 1) {
                        mp.get(get1D(n, i, c)).val = mp.get(get1D(n, r, c)).val + 1;
                        queue.remove(mp.get(get1D(n, i, c)));
                        queue.add(mp.get(get1D(n, i, c)));
                    }
                }
            }

            for (int i = r - 1; i >= 0 && grid[i].charAt(c) != 'X'; i--) {
                if (!visited[get1D(n, i, c)]) {
                    if (mp.get(get1D(n, i, c)).val > mp.get(get1D(n, r, c)).val + 1) {
                        mp.get(get1D(n, i, c)).val = mp.get(get1D(n, r, c)).val + 1;
                        queue.remove(mp.get(get1D(n, i, c)));
                        queue.add(mp.get(get1D(n, i, c)));
                    }
                }
            }

        }

        return -1;
    }

    private static int get1D(int n, int i, int j) {
        return i * n + j;
    }

    private static int get2DRow(int n, int idx) {
        return idx / n;
    }

    private static int get2DCol(int n, int idx) {
        return idx % n;
    }

    public static void main(String[] args) {
        CastleOnTheRock castleOnTheRock = new CastleOnTheRock();
        String[] grid = {
                ".X..XX...X",
                "X.........",
                ".X.......X",
                "..........",
                "........X.",
                ".X...XXX..",
                ".....X..XX",
                ".....X.X..",
                "..........",
                ".....X..XX",
        };

        System.out.println(minimumMoves(grid, 9, 1, 9, 6));
    }
}
