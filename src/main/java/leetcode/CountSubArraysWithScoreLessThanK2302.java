package leetcode;

public class CountSubArraysWithScoreLessThanK2302 {

  public long countSubarrays(int[] nums, long k) {
    long result = 0;
    int start = 0, end = 0;
    long sum = 0;
    int n = nums.length;

    while (start < n) {
      while (end < n && ((sum + nums[end]) * (end - start + 1)) < k) {
        sum += nums[end];
        end++;
      }
      result += (end - start);
      sum -= nums[start];
      start++;
    }

    return result;
  }

  public static void main(String[] args) {
    int[] nums = {1, 1, 1};
    // int[] nums = {2, 1, 4, 3, 5};
    long k = 5;
    System.out.println(new CountSubArraysWithScoreLessThanK2302().countSubarrays(nums, k));
  }

}
