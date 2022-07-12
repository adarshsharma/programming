package leetcode;

import java.util.ArrayList;
import java.util.List;

public class FindAllNumbersDisappearedInArray448 {

  public List<Integer> findDisappearedNumbers(int[] nums) {
    List<Integer> ret = new ArrayList<>();
    int n = nums.length;
    for (int i = 0; i < n; i++) {
      int num = nums[i];
      int r = i;
      while (num - 1 != r) {
        int t = nums[num - 1];
        r = num - 1;
        nums[num-1] = num;
        num = t;
      }
    }
    for (int i = 0; i < n; i++) {
      if (nums[i] - 1 != i) {
        ret.add(i+1);
      }
    }
    return ret;
  }


  public static void main(String[] args) {
    int[] nums = {4, 3, 2, 7, 8, 2, 3, 1};
    System.out.println(new FindAllNumbersDisappearedInArray448().findDisappearedNumbers(nums));
  }

}
