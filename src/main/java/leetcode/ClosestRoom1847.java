package leetcode;

import java.util.Arrays;
import java.util.TreeSet;

public class ClosestRoom1847 {

  public int[] closestRoom(int[][] rooms, int[][] queries) {
    int[] res = new int[queries.length];

    Arrays.sort(rooms, (a, b) -> {
      if (a[1] != b[1]) {
        return b[1] - a[1];
      }
      return a[0] - b[0];
    });

    Integer[] idx = new Integer[queries.length];
    for (int i = 0; i < queries.length; i++) {
      idx[i] = i;
    }

    Arrays.sort(idx, (o1, o2) -> queries[o2][1] - queries[o1][1]);

    TreeSet<Integer> ts = new TreeSet<>();

    int ridx = 0;
    for (int i = 0; i < idx.length; i++) {
      int[] query = queries[idx[i]];
      while (ridx < rooms.length && rooms[ridx][1] >= query[1]) {
        ts.add(rooms[ridx][0]);
        ridx++;
      }

      Integer left = ts.floor(query[0]);
      Integer right = ts.ceiling(query[0]);
      if (left == null && right == null) {
        res[idx[i]] = -1;
      } else if (left != null && right != null) {
        res[idx[i]] = (2 * query[0] - left - right) <= 0 ? left : right;
      } else {
        res[idx[i]] = left == null ? right : left;
      }
    }

    return res;
  }

  public static void main(String[] args) {
    ClosestRoom1847 obj = new ClosestRoom1847();
    // int[][] rooms = {{3, 5}, {1, 4}, {2, 3}, {5, 2}, {4, 1}};
    // int[][] queries = {{2, 3}, {2, 4}, {2, 5}};
    int[][] rooms = {
        {3, 1},
        {6, 25},
        {7, 14},
        {9, 4},
        {11, 6},
        {12, 22},
        {14, 14},
        {17, 11},
        {21, 9},
        {22, 13},
    };
    int[][] queries = {{21, 17}, {4, 6}, {17, 25}, {15, 18}, {17, 16}, {18, 16}, {8, 17}, {6, 7},
        {9, 22}, {17, 18}};

    Arrays.stream(obj.closestRoom(rooms, queries)).forEach(System.out::println);
  }

}
