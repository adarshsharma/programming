package leetcode;

import java.util.HashMap;
import java.util.Map;

public class LatestDayToCross {

  static class DisjointSet {

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

  public int latestDayToCross(int row, int col, int[][] cells) {
    int[][] adj = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    DisjointSet ds = new DisjointSet();
    for (int r = 1; r <= row; r++) {
      for (int c = 1; c <= col; c++) {
        ds.makeSet((r - 1) * col + c);
      }
    }
    for (int c = 1; c < col; c++) {
      ds.union(c, c + 1);
      ds.union((row - 1) * col + c, (row - 1) * col + c + 1);
    }

    boolean[] land = new boolean[row * col];

    for (int i = cells.length - 1; i >= 0; i--) {
      int[] cell = cells[i];
      int node = (cell[0] - 1) * col + cell[1];
      land[node - 1] = true;
      for (int[] ad : adj) {
        int r = cell[0] + ad[0];
        int c = cell[1] + ad[1];
        if (r > 0 && c > 0 && r <= row && c <= col) {
          int neigh = (r - 1) * col + c;
          if (neigh > 0 && neigh <= row * col && land[neigh - 1]) {
            ds.union(node, neigh);
          }
        }
      }
      if (ds.findSet(1) == ds.findSet(row * col)) {
        return i;
      }
    }

    return 0;
  }

  public static void main(String[] args) {
    int row = 3;
    int col = 4;
    int[][] cells = {{3, 1}, {2, 3}, {1, 3}, {3, 2}, {2, 1}, {1, 4}, {1, 1}, {2, 4}, {3, 4}, {1, 2},
        {2, 2}, {3, 3}};
    // int row = 3;
    // int col = 3;
    // int[][] cells = {{1, 2}, {2, 1}, {3, 3}, {2, 2}, {1, 1}, {1, 3}, {2, 3}, {3, 2}, {3, 1}};
    // int row = 2;
    // int col = 2;
    // int[][] cells = {{1, 1}, {1, 2}, {2, 1}, {2, 2}};
    System.out.println(new LatestDayToCross().latestDayToCross(row, col, cells));
  }

}
