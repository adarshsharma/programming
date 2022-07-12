package leetcode;

import java.util.ArrayList;
import java.util.List;

public class DescribeThePainting1943 {

  public List<List<Long>> splitPainting(int[][] segments) {
    List<int[]> p = new ArrayList<>();
    for (int[] s : segments) {
      p.add(new int[]{s[0], 0, s[2]});
      p.add(new int[]{s[1], 1, s[2]});
    }

    p.sort((a, b) -> {
      if (a[0] != b[0]) {
        return a[0] - b[0];
      }
      return b[1] - a[1];
    });

    long sum = 0L;
    long closedSum = 0L;
    List<List<Long>> res = new ArrayList<>();
    long start = p.get(0)[0];
    long end = 0L;
    int openCount = 0;

    for (int i = 0; i < p.size(); i++) {
      int[] ival = p.get(i);
      if (ival[1] == 0) { //start
        if (ival[0] != start) {
          if (end > 0) {
            addList(res, start, end, closedSum);
          } else {
            addList(res, start, ival[0], sum);
          }
          start = openCount == 1 ? ival[0] : end;
          end = 0;
        }
        sum += ival[2];
        openCount++;
      } else {
        if (end == 0) {
          end = ival[0];
          closedSum = sum;
          sum -= ival[2];
        } else if (end == ival[0]) {
          sum -= ival[2];
        } else {
          addList(res, start, end, closedSum);
          start = end;
          end = ival[0];
          closedSum = sum;
          sum -= ival[2];
        }
        if (i == p.size() - 1) {
          addList(res, start, end, closedSum);
        }
        openCount--;
      }
    }

    return res;
  }

  private void addList(List<List<Long>> res, long s, long e, long c) {
    List<Long> ls = new ArrayList<>();
    ls.add(s);
    ls.add(e);
    ls.add(c);
    res.add(ls);
  }

  public static void main(String[] args) {
    int[][] segments = {
        {3, 10, 8},
        {4, 5, 9},
        {4, 7, 19},
        {7, 19, 14},
        {8, 12, 5},
        {8, 16, 6},
        {11, 13, 3},
        {14, 15, 1},
        {14, 17, 7},
        {17, 20, 18},
    };
    System.out.println(new DescribeThePainting1943().splitPainting(segments));
    // int[][] segments = {{1, 4, 5}, {4, 7, 7}, {1, 7, 9}};
    // System.out.println(new DescribeThePainting1943().splitPainting(segments));
    // int[][] segments = new int[][]{{1, 7, 9}, {6, 8, 15}, {8, 10, 7}};
    // System.out.println(new DescribeThePainting1943().splitPainting(segments));
    // segments = new int[][]{{1, 4, 5}, {1, 4, 7}, {4, 7, 1}, {4, 7, 11}};
    // System.out.println(new DescribeThePainting1943().splitPainting(segments));
  }

}
