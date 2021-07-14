package leetcode;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class CheckExistenceOfEdgeLength1697 {

    static class UnionFind {

        Map<Integer, Node> mp = new HashMap<>();

        static class Node {

            int value;
            int rank;
            Node parent;

            public Node(int value, int rank) {
                this.value = value;
                this.rank = rank;
            }
        }

        public void makeSet(int value) {
            Node n = new Node(value, 0);
            n.parent = n;
            mp.put(value, n);
        }

        public int findSet(int value) {
            return findSet(mp.get(value)).value;
        }


        private Node findSet(Node node) {
            Node parent = node.parent;
            if (parent == node) {
                return parent;
            }
            node.parent = findSet(node.parent);
            return node.parent;
        }

        public void union(int value1, int value2) {
            Node node1 = mp.get(value1);
            Node node2 = mp.get(value2);

            Node parent1 = findSet(node1);
            Node parent2 = findSet(node2);

            if (parent1.value != parent2.value) {
                if (parent1.rank >= parent2.rank) {
                    parent1.rank = (parent1.rank == parent2.rank) ? parent1.rank + 1 : parent1.rank;
                    parent2.parent = parent1;
                } else {
                    parent1.parent = parent2;
                }
            }
        }

    }

    public boolean[] distanceLimitedPathsExist(int n, int[][] edgeList, int[][] queries) {
        boolean[] result = new boolean[queries.length];
        Integer[] idx = new Integer[queries.length];
        for (int i = 0; i < queries.length; i++) {
            idx[i] = i;
        }
        Arrays.sort(idx, Comparator.comparingInt(o -> queries[o][2]));
        Arrays.sort(edgeList, Comparator.comparingInt(x -> x[2]));

        UnionFind uf = new UnionFind();
        for (int i = 0; i < n; i++) {
            uf.makeSet(i);
        }

        int i = 0;
        for (int q = 0; q < queries.length; q++) {
            int[] query = queries[idx[q]];
            while (i < edgeList.length && edgeList[i][2] < query[2]) {
                uf.union(edgeList[i][0], edgeList[i][1]);
                i++;
            }
            if (uf.findSet(query[0]) == uf.findSet(query[1])) {
                result[idx[q]] = true;
            }
        }

        return result;
    }

    public static void main(String[] args) {
        int n = 3;
        int[][] edgeList = {{0, 1, 2}, {1, 2, 4}, {2, 0, 8}, {1, 0, 16}};
        int[][] queries = {{0, 1, 2}, {0, 2, 5}};
        boolean[] results = new CheckExistenceOfEdgeLength1697()
                                .distanceLimitedPathsExist(n, edgeList, queries);
        for (int i = 0; i < results.length; i++) {
            System.out.println(results[i]);
        }
    }

}
