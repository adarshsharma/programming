package leetcode;

import java.util.Arrays;
import java.util.stream.Collectors;

public class LongestValidObstacleCourse1964 {

  static class BinaryIndexedTree {

    int[] bit_arr;

    public BinaryIndexedTree(int n) {
      this.bit_arr = new int[n + 1];
    }

    //replace val at idx th element
    void replace(int idx, int val) {
      while (idx < bit_arr.length) {
        bit_arr[idx] = Math.max(val, bit_arr[idx]);
        idx += idx & (-idx);
      }
    }

    //Computes sum of first idx elements
    int max(int idx) {
      int result = Integer.MIN_VALUE;

      while (idx > 0) {
        result = Math.max(result, bit_arr[idx]);
        idx -= idx & (-idx);
      }

      return result;
    }
  }


  public int[] longestObstacleCourseAtEachPosition(int[] obstacles) {
    int n = obstacles.length;
    if (n == 0) {
      throw new RuntimeException("invalid input");
    }
    int[] res = new int[n];
    int max = Arrays.stream(obstacles).max().getAsInt();
    BinaryIndexedTree bit = new BinaryIndexedTree(max + 1);
    for (int i = 0; i < n; i++) {
      res[i] = bit.max(obstacles[i]) + 1;
      bit.replace(obstacles[i], res[i]);
    }

    return res;
  }

  public static void main(String[] args) {
    int[] obstacles = {3, 1, 5, 6, 4, 2};
    int[] res = new LongestValidObstacleCourse1964().longestObstacleCourseAtEachPosition(obstacles);
    System.out.println(Arrays.stream(res).boxed().collect(Collectors.toList()));
  }
}
