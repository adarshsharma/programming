package leetcode;

import java.util.HashSet;
import java.util.Set;

public class CountUnguardedCellsInTheGrid2257 {

  public int countUnguarded(int m, int n, int[][] guards, int[][] walls) {
    int unguarded = 0;
    Set<Integer> guarded = new HashSet<>();
    Set<Integer> walled = new HashSet<>();
    for (int[] guard : guards) {
      guarded.add(guard[0] * n + guard[1]);
    }
    for (int[] wall : walls) {
      walled.add(wall[0] * n + wall[1]);
    }

    Set<Integer> notGuarded = new HashSet<>();

    for (int i = 0; i < m; i++) {
      boolean guard = false;
      Set<Integer> set = new HashSet<>();
      for (int j = 0; j < n; j++) {
        int idx = i * n + j;
        if (guarded.contains(idx)) {
          guard = true;
          set.clear();
        } else if (walled.contains(idx)) {
          guard = false;
          notGuarded.addAll(set);
          set.clear();
        } else if (!guard) {
          set.add(idx);
        }
      }
      notGuarded.addAll(set);
    }

    for (int j = 0; j < n; j++) {
      boolean guard = false;
      int count = 0;
      for (int i = 0; i < m; i++) {
        int idx = i * n + j;
        if (guarded.contains(idx)) {
          guard = true;
          count = 0;
        } else if (walled.contains(idx)) {
          guard = false;
          unguarded += count;
          count = 0;
        } else if (!guard) {
          if (notGuarded.contains(idx)) {
            count++;
          }
        }
      }
      unguarded += count;
    }

    return unguarded;
  }

  public static void main(String[] args) {
    int m = 4, n = 6;

    int[][] guards = {{0, 0}, {1, 1}, {2, 3}};
    int[][] walls = {{0, 1}, {2, 2}, {1, 4}};
    System.out.println(new CountUnguardedCellsInTheGrid2257().countUnguarded(m, n, guards, walls));
  }
}
