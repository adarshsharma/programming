package leetcode;

import java.util.Arrays;

public class MaximumNumberOfGroups2358 {

  public int maximumGroups(int[] grades) {
    Arrays.sort(grades);
    int groups = 0;
    int prev_sum = 0;
    int prev_count = 0;
    int cur_sum = 0;
    int cur_count = 0;
    int i = 0;
    while (i < grades.length) {
      if (cur_sum <= prev_sum || cur_count <= prev_count) {
        cur_sum += grades[i];
        cur_count++;
        i++;
      } else {
        groups++;
        prev_sum = cur_sum;
        cur_sum = 0;
        prev_count = cur_count;
        cur_count = 0;
      }
    }
    if (cur_sum > prev_sum && cur_count > prev_count) {
      groups++;
    }

    return groups;
  }

  public static void main(String[] args) {
    int[] grades = {10, 6, 12, 7, 3, 5};
    System.out.println(new MaximumNumberOfGroups2358().maximumGroups(grades));
  }

}
