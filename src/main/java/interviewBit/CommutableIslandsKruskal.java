package interviewBit;

import java.util.*;

/**
 * Created by adarsh.sharma on 14/07/18.
 */
public class CommutableIslandsKruskal {

    public class DisjointSet {
        Map<Integer, Node> mp = new HashMap<>();

        class Node {
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

            if (parent1.value == parent2.value) {
                return;
            }

            if (parent1.rank >= parent2.rank) {
                parent1.rank = (parent1.rank == parent2.rank) ? parent1.rank + 1 : parent1.rank;
                parent2.parent = parent1;
            } else {
                parent1.parent = parent2;
            }
        }
    }

    public class Edge implements Comparable<Edge> {
        int s;
        int e;
        int weight;

        public Edge(int s, int e, int weight) {
            this.s = s;
            this.e = e;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge that) {
            return this.weight - that.weight;
        }
    }

    public int solve(int A, List<List<Integer>> B) {
        int minCost = 0;
        PriorityQueue<Edge> pq = new PriorityQueue<>();

        for (List<Integer> b : B) {
            pq.add(new Edge(b.get(0), b.get(1), b.get(2)));
        }

        DisjointSet disjointSet = new DisjointSet();
        for (int i = 1; i <= A; i++) {
            disjointSet.makeSet(i);
        }

        int count = 0;

        while (!pq.isEmpty() && count < A - 1) {
            Edge edge = pq.poll();
            int left = disjointSet.findSet(edge.s);
            int right = disjointSet.findSet(edge.e);

            if (left != right) {
                disjointSet.union(left, right);
                minCost += edge.weight;
                count++;
            }
        }

        return minCost;
    }

    public static void main(String[] args) {
        List<List<Integer>> B = new ArrayList<>();
        B.add(Arrays.asList(1, 2, 10));
        B.add(Arrays.asList(2, 3, 5));
        B.add(Arrays.asList(1, 3, 9));

        System.out.println(new CommutableIslandsKruskal().solve(3, B));
    }
}
