package practice;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;

public class Test {

  static int getFirstOccurance(int[] nums, int s, int e, int num) {

    Map<Integer, Integer> mp = new HashMap<>();
    mp.merge(2, 2, Integer::sum);
    System.out.println(mp);
    mp.computeIfPresent(2, removeIfLast());
    System.out.println(mp);
    mp.computeIfPresent(2, removeIfLast());
    System.out.println(mp);

    int first = Arrays.binarySearch(nums, s, e, num);
    int found = first;
    while (found >= 0) {
      found = Arrays.binarySearch(nums, s, e, num);
      if (found >= 0) {
        first = found;
        e = found;
      }
    }
    return first;
  }

  private static BiFunction<Integer, Integer, Integer> removeIfLast() {
    return (a, b) -> b == 1 ? null : b - 1;
  }

  public static void main(String[] args) {
    int[] nums = {2, 5, 5, 5, 5, 5, 5, 7};
    System.out.println(getFirstOccurance(nums, 0, 7, 5));
    // int[][] a = {{3, 1}, {3, 5}, {3, 2}};
    // Arrays.sort(a, Comparator.comparingInt(b -> b[1]));
    // Arrays.stream(a).forEach(x -> System.out.println(x[0] + " " + x[1]));
  }

}
