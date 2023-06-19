package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.TreeMap;

public class MaximumStrictlyIncreasingCellsMatrix2713 {

  public int maxIncreasingCells(int[][] mat) {
    int m = mat.length;
    int n = mat[0].length;

    int[][] all = new int[m * n][3];
    //
    int idx = 0;
    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        all[idx][0] = mat[i][j];
        all[idx][1] = i;
        all[idx][2] = j;
        idx++;
      }
    }

    Arrays.sort(all, Comparator.comparingInt(x -> x[0]));

    List<TreeMap<Integer, Integer>> rows = new ArrayList<>();
    List<TreeMap<Integer, Integer>> cols = new ArrayList<>();
    for (int i = 0; i < m; i++) {
      rows.add(new TreeMap<>());
    }
    for (int j = 0; j < n; j++) {
      cols.add(new TreeMap<>());
    }

    int max = 1;
    for (int[] val : all) {
      TreeMap<Integer, Integer> row = rows.get(val[1]);
      TreeMap<Integer, Integer> col = cols.get(val[2]);

      Integer rowLower = row.lowerKey(val[0]);
      Integer colLower = col.lowerKey(val[0]);
      int maxInRow = 0;
      int maxInColumn = 0;
      if (rowLower != null) {
        maxInRow = row.get(rowLower);
      }
      if (colLower != null) {
        maxInColumn = col.get(colLower);
      }

      int len = Math.max(maxInRow, maxInColumn) + 1;
      row.merge(val[0], len, Integer::max);
      col.merge(val[0], len, Integer::max);
      max = Math.max(max, len);
    }

    return max;

  }

  public static void main(String[] args) {
    // int[][] mat = {{3, 1, 6}, {-9, 5, 7}};
    // int[][] mat = {{1, 1}, {1, 1}};
    int[][] mat = {{3, 1}, {3, 4}};
    System.out.println(new MaximumStrictlyIncreasingCellsMatrix2713().maxIncreasingCells(mat));
  }
}
