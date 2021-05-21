package practice.algo.graphs;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by adarsh.sharma on 15/07/18.
 */
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

        if(parent1.rank >= parent2.rank) {
            parent1.rank = (parent1.rank == parent2.rank)? parent1.rank + 1: parent1.rank;
            parent2.parent = parent1;
        } else {
            parent1.parent = parent2;
        }
    }

//    int[] roots;
//
//    public DisjointSet(int n) {
//        this.roots = new int[n];
//        for (int i = 0; i < n; i++) {
//            this.roots[i] = i;
//        }
//    }
//
//    public void union(int value1, int value2) {
//        if(roots[value1] != roots[value2]) {
//            roots[findSet(value2)] = value1;
//        }
//    }
//
//    public int findSet(int value) {
//        while(value != roots[value]) {
//            roots[value] = roots[roots[value]];
//            value = roots[value];
//        }
//        return value;
//    }
}
