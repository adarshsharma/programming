package leetcode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import leetcode.CountSubIslands5791.UnionFind.Node;

public class CountSubIslands5791 {

    public static void main(String[] args) {
        // int[][] grid1 = {{1, 0, 1, 0, 1}, {1, 1, 1, 1, 1}, {0, 0, 0, 0, 0}, {1, 1, 1, 1, 1},
        //     {1, 0, 1, 0, 1}};
        // int[][] grid2 = {{0, 0, 0, 0, 0}, {1, 1, 1, 1, 1}, {0, 1, 0, 1, 0}, {0, 1, 0, 1, 0},
        //     {1, 0, 0, 0, 1}};
        int[][] grid1 = {
            {1, 1, 1, 1, 0, 0},
            {1, 1, 0, 1, 0, 0},
            {1, 0, 0, 1, 1, 1},
            {1, 1, 1, 0, 0, 1},
            {1, 1, 1, 1, 1, 0},
            {1, 0, 1, 0, 1, 0},
            {0, 1, 1, 1, 0, 1},
            {1, 0, 0, 0, 1, 1},
            {1, 0, 0, 0, 1, 0},
            {1, 1, 1, 1, 1, 0}};
        int[][] grid2 = {
            {1, 1, 1, 1, 0, 1},
            {0, 0, 1, 0, 1, 0},
            {1, 1, 1, 1, 1, 1},
            {0, 1, 1, 1, 1, 1},
            {1, 1, 1, 0, 1, 0},
            {0, 1, 1, 1, 1, 1},
            {1, 1, 0, 1, 1, 1},
            {1, 0, 0, 1, 0, 1},
            {1, 1, 1, 1, 1, 1},
            {1, 0, 0, 1, 0, 0}};
        System.out.println(new CountSubIslands5791().countSubIslands(grid1, grid2));
    }

    public int countSubIslands(int[][] grid1, int[][] grid2) {
        int m = grid1.length;
        int n = grid1[0].length;

        UnionFind uf = new UnionFind();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid2[i][j] == 1) {
                    uf.makeSet(i * n + j);
                }
            }
        }

        int[][] neighbours = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid2[i][j] == 1) {
                    for (int[] neigh : neighbours) {
                        int x = i + neigh[0];
                        int y = j + neigh[1];
                        if (x >= 0 && x < m && y >= 0 && y < n && grid2[x][y] == 1) {
                            uf.union(i * n + j, x * n + y);
                        }
                    }
                }
            }
        }

        Set<Integer> islands = uf.getIslands();

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid2[i][j] == 1) {
                    int parent = uf.findSet(i * n + j);
                    if (grid1[i][j] != 1) {
                        islands.remove(parent);
                    }
                }
            }
        }

        return islands.size();
    }

    static class UnionFind {

        Map<Integer, Node> map = new HashMap<>();

        public void makeSet(int v) {
            Node node = new Node(
                v, 0);
            node.parent = node;
            map.put(v, node);
        }

        public int findSet(int v) {
            return findSet(map.get(v)).parent.val;
        }

        public Node findSet(
            Node node) {
            if (node.parent == node) {
                return node;
            }
            node.parent = findSet(node.parent);
            ;
            return node.parent;
        }

        public void union(int v1, int v2) {
            Node n1 = map.get(v1);
            Node n2 = map.get(v2);

            Node p1 = findSet(n1);
            Node p2 = findSet(n2);

            if (p1.val != p2.val) {
                if (p1.rank >= p2.rank) {
                    p1.rank = p1.rank == p2.rank ? p1.rank + 1 : p1.rank;
                    p2.parent = p1;
                } else {
                    p1.parent = p2;
                }
            }

        }

        public Set<Integer> getIslands() {
            Set<Integer> set = new HashSet<>();
            for (Node node : map.values()) {
                set.add(findSet(node.val));
            }
            return set;
        }

        static class Node {

            int val;

            int rank;
            Node parent;

            public Node(int v, int r) {
                this.val = v;
                this.rank = r;
            }

        }
    }

}
