package leetcode;

import java.util.Arrays;

public class MinimumMaximumPairSum1877 {

    public int minPairSum(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < n / 2; i++) {
            max = Math.max(max, nums[i] + nums[n - i - 1]);
        }
        return max;
    }

}
