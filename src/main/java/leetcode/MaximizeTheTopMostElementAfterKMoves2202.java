package leetcode;

public class MaximizeTheTopMostElementAfterKMoves2202 {

  public int maximumTop(int[] nums, int k) {
    if (nums.length == 1) {
      if (k % 2 == 0) {
        return nums[0];
      } else {
        return -1;
      }
    }

    int mx = -1;
    for (int i = 0; i <= Math.min(k, nums.length - 1); i++) {
      int remK = k - i;
      if (remK == 0 || remK > 1) {
        mx = Math.max(mx, nums[i]);
      }
    }

    return mx;
  }

  public static void main(String[] args) {
    int[] nums = {5, 6};
    int k = 1;
    System.out.println(new MaximizeTheTopMostElementAfterKMoves2202().maximumTop(nums, k));
  }

}
