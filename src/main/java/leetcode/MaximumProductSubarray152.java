package leetcode;

public class MaximumProductSubarray152 {

  public int maxProduct(int[] nums) {
    long maxP = nums[0];
    long minPrev = nums[0];
    long maxPrev = nums[0];
    for (int i = 1; i < nums.length; i++) {
      long newMinPrev = Math.min(Math.min(nums[i], maxPrev * nums[i]), minPrev * nums[i]);
      maxPrev = Math.max(Math.max(nums[i], maxPrev * nums[i]), minPrev * nums[i]);
      minPrev = newMinPrev;
      maxP = Math.max(maxPrev, maxP);
    }

    return Long.valueOf(maxP).intValue();
  }

  public static void main(String[] args) {
    int[] nums = {2, 3, -2, 4};
    System.out.println(new MaximumProductSubarray152().maxProduct(nums));
  }

}
