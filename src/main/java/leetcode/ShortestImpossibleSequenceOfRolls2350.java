package leetcode;

import java.util.HashSet;
import java.util.Set;

public class ShortestImpossibleSequenceOfRolls2350 {

  public int shortestSequence(int[] rolls, int k) {
    int minIdx = -1;

    int n = 1;
    Set<Integer> set = new HashSet<>();
    for (; n <= rolls.length && minIdx < rolls.length - 1; n++) {
      for (int i = minIdx + 1; i < rolls.length; i++) {
        set.add(rolls[i]);
        if (set.size() == k) {
          minIdx = i;
          set = new HashSet<>();
          break;
        }
      }
      if (set.size() > 0) {
        break;
      }
    }

    return n;
  }

  public static void main(String[] args) {
    // int[] rolls = {4, 2, 1, 2, 3, 3, 2, 4, 1};
    int[] rolls = {3, 2, 1, 3, 3, 3, 3, 3, 1, 2, 2, 3, 1, 3, 3, 1, 1, 3, 1, 1, 1, 3, 1, 3, 3, 1, 2,
        3, 2, 1, 1, 2};
    int k = 3;
    System.out.println(new ShortestImpossibleSequenceOfRolls2350().shortestSequence(rolls, k));
  }

}
