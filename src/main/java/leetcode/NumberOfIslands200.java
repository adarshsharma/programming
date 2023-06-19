package leetcode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class NumberOfIslands200 {

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

  public int numIslands(char[][] grid) {
    int m = grid.length;
    int n = grid[0].length;
    int[][] adjs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    UnionFind unionFind = new UnionFind();
    for (int i = 0; i < m * n; i++) {
      unionFind.makeSet(i);
    }

    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        if (grid[i][j] == '1') {
          for (int[] adj : adjs) {
            int x = i + adj[0];
            int y = j + adj[1];
            if (x >= 0 && x < m && y >= 0 && y < n && grid[x][y] == '1') {
              unionFind.union(i * n + j, x * n + y);
            }
          }
        }
      }
    }

    Set<Integer> regions = new HashSet<>();
    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        if (grid[i][j] == '1') {
          regions.add(unionFind.findSet(i * n + j));
        }
      }
    }

    return regions.size();
  }

  public static void main(String[] args) {
    char[][] grid = {
        {'1', '1', '1', '1', '0'}, {'1', '1', '0', '1', '0'}, {'1', '1', '0', '0', '0'},
        {'0', '0', '0', '0', '0'}};
    System.out.println(new NumberOfIslands200().numIslands(grid));
  }
}
