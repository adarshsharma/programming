package leetcode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

// Not working
public class CheckIfValidParentheses2267 {

  public boolean hasValidPath(char[][] grid) {
    int m = grid.length;
    int n = grid[0].length;
    List<Set<Integer>> prev = new ArrayList<>();
    List<Set<Integer>> next = new ArrayList<>();

    if (grid[0][0] == ')') {
      return false;
    }

    int p = 0;
    for (int j = 0; j < n; j++) {
      prev.add(new HashSet<>());
      int val = grid[0][j] == '(' ? 1 : -1;
      p = p + val;
      prev.get(j).add(p);
    }

    for (int i = 1; i < m; i++) {
      for (int j = 0; j < n; j++) {
        next.add(new HashSet<>());
        int val = grid[i][j] == '(' ? 1 : -1;
        Set<Integer> tmp = new HashSet<>(prev.get(j));
        if (j != 0) {
          tmp.addAll(next.get(j - 1));
        }
        for (int prevVal : tmp) {
          if (prevVal >= 0) {
            next.get(j).add(prevVal + val);
          }
        }
      }
      prev = next;
      next = new ArrayList<>();
    }

    return prev.get(n - 1).contains(0);
  }

  public static void main(String[] args) {
    char[][] grid = {
        {'(', '(', '('},
        {')', '(', ')'},
        {'(', '(', ')'},
        {'(', '(', ')'}};
    System.out.println(new CheckIfValidParentheses2267().hasValidPath(grid));
  }
}
