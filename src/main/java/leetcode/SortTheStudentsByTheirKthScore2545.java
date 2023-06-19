package leetcode;

import java.util.Arrays;
import java.util.Comparator;

public class SortTheStudentsByTheirKthScore2545 {

  public int[][] sortTheStudents(int[][] score, int k) {
    Arrays.sort(score, Comparator.comparingInt(a -> -a[k]));
    return score;
  }

  public static void main(String[] args) {

  }
}
