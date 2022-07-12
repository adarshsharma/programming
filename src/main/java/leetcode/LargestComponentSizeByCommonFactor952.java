package leetcode;

import java.util.HashMap;
import java.util.Map;

public class LargestComponentSizeByCommonFactor952 {

  static class UnionFind {

    Map<Integer, Node> mp = new HashMap<>();
    int[] size;
    public int maxSize;

    static class Node {

      int value;
      int rank;
      Node parent;

      public Node(int value, int rank) {
        this.value = value;
        this.rank = rank;
      }
    }

    public UnionFind(int n) {
      size = new int[n];
      for (int i = 0; i < n; i++) {
        makeSet(i);
        size[i] = 1;
      }
      maxSize = 1;
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

  public static int largestComponentSize(int[] nums) {
    UnionFind unionFind = new UnionFind(nums.length);
    int n = nums.length;

    Map<Integer, Integer> valToIndex = new HashMap<>();
    for (int i = 0; i < n; i++) {
      int val = nums[i];
      for (int j = 2; j * j <= val; j++) {
        if (val % j == 0) {
          putOrUnion(j, i, valToIndex, unionFind);
          putOrUnion(val / j, i, valToIndex, unionFind);
        }
      }
      putOrUnion(val, i, valToIndex, unionFind);
    }
    return unionFind.maxSize;
  }

  private static void putOrUnion(int val, int i, Map<Integer, Integer> valToIndex,
      UnionFind unionFind) {
    if (!valToIndex.containsKey(val)) {
      valToIndex.put(val, i);
    } else {
      unionFind.union(i, valToIndex.get(val));
    }
  }


  public static void main(String[] args) {
    int[] nums = {4, 6, 15, 35};
    // int[] nums = {20, 50, 9, 63};
    System.out.println(largestComponentSize(nums));
  }
}
