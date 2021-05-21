package leetcode;

import java.util.Arrays;

/**
 * Created by adarsh.sharma on 02/09/18.
 */
public class KthSmallestPair {
    public int smallestDistancePair(int[] nums, int k) {
        Arrays.sort(nums);

        int lo = 0;
        int hi = nums[nums.length - 1] - nums[0];
        while (lo < hi) {
            int mi = (lo + hi) / 2;
            int left = 0;
            int count = getCount(nums, mi, left);

            if (count >= k) hi = mi;
            else lo = mi + 1;
        }
        return lo;
    }

    private int getCount(int[] nums, int mi, int left) {
        int count = 0;
        for (int right = 0; right < nums.length; ++right) {
            while (nums[right] - nums[left] > mi) left++;
            count += right - left;
        }
        return count;
    }

    public static void main(String[] args) {
        KthSmallestPair kthSmallestPair = new KthSmallestPair();
        int k = 1;
        int[] nums = {1, 3, 1};
        System.out.println(kthSmallestPair.smallestDistancePair(nums, k));
    }
}
