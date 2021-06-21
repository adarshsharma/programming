package practice.algo.graphs;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by adarsh.sharma on 15/07/18.
 */
public class DisjointSet {

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
