package leetcode;

import practice.algo.graphs.DisjointSet;

import java.util.Arrays;

/**
 * Created by adarsh.sharma on 02/09/18.
 */
public class RedundantConnectionII {
    public int[] findRedundantDirectedConnection(int[][] edges) {
        DisjointSet ds = new DisjointSet();
        for (int i = 0; i < edges.length; i++) {
            ds.makeSet(i + 1);
        }

        for (int[] edge : edges) {
            int p1 = ds.findSet(edge[0]);
            int p2 = ds.findSet(edge[1]);
            if (p1 == p2) {
                return edge;
            }
            ds.union(edge[0], edge[1]);
        }

        throw new IllegalArgumentException();
    }

    public static void main(String[] args) {
        RedundantConnectionII redundantConnectionII = new RedundantConnectionII();
        int[][] edges = {{1, 2}, {2, 3}, {3, 4}, {4, 1}, {1, 5}};
        int[] result = redundantConnectionII.findRedundantDirectedConnection(edges);
        Arrays.stream(result).forEach(System.out::println);
    }
}
