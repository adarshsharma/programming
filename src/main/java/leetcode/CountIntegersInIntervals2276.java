package leetcode;

import java.util.TreeSet;

public class CountIntegersInIntervals2276 {

  /*
    2   4       8  9
  1        6
    2   4  5     8
  1           7
    2   4     7     8
  1           7
    2      7    9   10
  1        7


    2      6    8  9
      3 4
    2 3     8  9
        5 6
    2 3     8  9
      3 6
    2 3 5    9
        5 6
  */


  static class Point {

    int val;
    boolean start;

    public Point(int v, boolean s) {
      this.val = v;
      this.start = s;
    }
  }

  TreeSet<Point> tSet;
  int count;


  public CountIntegersInIntervals2276() {
    count = 0;
    tSet = new TreeSet<>((p1, p2) -> {
      if (p1.val != p2.val) {
        return p1.val - p2.val;
      }
      return p1.start == p2.start ? 0 : (p1.start ? -1 : 1);
    });
  }

  public void add(int left, int right) {
    Point start = new Point(left, true);
    Point end = new Point(right, false);

    Point startFloor = tSet.floor(start);

    if (startFloor == null || !startFloor.start) {
      tSet.add(start);
    }

    Point next = tSet.higher(start);
    if (next != null && next.start && next.val <= right) {
      count += (next.val - start.val);
    }
    addEnd(right, start, end);
  }

  private void addEnd(int right, Point start, Point end) {
    Point removed = null;
    Point next = tSet.higher(start);
    while (next != null && next.val <= right) {
      tSet.remove(next);
      if (next.start && removed != null) {
        count += Math.max(0, (next.val - removed.val - 1));
      }
      removed = next;
      next = tSet.higher(start);
    }
    if (next == null || next.start) {
      tSet.add(end);
      if (removed != null) {
        count += Math.max(0, (right - removed.val));
      } else {
        count += Math.max(0, (right - start.val + 1));
      }
    }

  }

  public int count() {
    // int count = 0;
    // Iterator<Point> iterator = tSet.iterator();
    // while (iterator.hasNext()) {
    //   Point start = iterator.next();
    //   Point end = iterator.next();
    //   count += (end.val - start.val + 1);
    // }
    return count;
  }

  public static void main(String[] args) {
    CountIntegersInIntervals2276 countIntervals = new CountIntegersInIntervals2276();
    countIntervals.add(2, 3);  // add [2, 3] to the set of intervals.
    countIntervals.add(7, 10); // add [7, 10] to the set of intervals.
    System.out.println(countIntervals.count());    // return 6
    countIntervals.add(5, 8);  // add [5, 8] to the set of intervals.
    System.out.println(countIntervals.count());    // return 6
  }
}
