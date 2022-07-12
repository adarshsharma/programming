package leetcode;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class MinCostToConnectAllPoints1584 {

    static class UnionFind {

        static class Node {

            int val;
            int rank;
            Node parent;

            public Node(int v, int r) {
                this.val = v;
                this.rank = r;
            }
        }

        Map<Integer, Node> map = new HashMap<>();

        public void makeSet(int v) {
            Node node = new Node(v, 0);
            node.parent = node;
            map.put(v, node);
        }

        public int findSet(int v) {
            return findSet(map.get(v)).parent.val;
        }

        public Node findSet(Node node) {
            if (node.parent == node) {
                return node;
            }
            node.parent = findSet(node.parent);
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
    }

    public int minCostConnectPoints(int[][] points) {
        int n = points.length;
        int c = (n * (n - 1)) / 2;
        int[][] edges = new int[c][3];
        int e = 0;

        UnionFind uf = new UnionFind();

        for (int i = 0; i < n; i++) {
            uf.makeSet(i);
            int[] p1 = points[i];
            for (int j = i + 1; j < n; j++, e++) {
                int[] p2 = points[j];
                edges[e][0] = Math.abs(p1[0] - p2[0]) + Math.abs(p1[1] - p2[1]);
                edges[e][1] = i;
                edges[e][2] = j;
            }
        }

        Arrays.sort(edges, Comparator.comparingInt(p -> p[0]));

        int res = 0;
        int eCount = 0;
        int i = 0;

        while (i < c && eCount < n - 1) {
            int[] edge = edges[i++];
            if (uf.findSet(edge[1]) != uf.findSet(edge[2])) {
                res += edge[0];
                eCount++;
                uf.union(edge[1], edge[2]);
            }
        }

        return res;
    }

    public static void main(String[] args) {
        int[][] points = {{0, 0}, {2, 2}, {3, 10}, {5, 2}, {7, 0}};
        // int[][] points = {{-1000000,-1000000},{1000000,1000000}};
        System.out.println(new MinCostToConnectAllPoints1584().minCostConnectPoints(points));
    }

}
