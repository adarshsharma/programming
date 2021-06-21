package leetcode;

import java.util.Arrays;

public class CountOfRangeSum327 {

    public int countRangeSum(int[] nums, int lower, int upper) {
        int count = 0;

        for (int i = 1; i < nums.length; i++) {
            nums[i] += nums[i - 1];
        }

        int l = lower;
        int u = upper;
        for (int i = 0; i < nums.length; i++) {
            int left = getFirstOccurance(nums, i, nums.length, l);
            int right = Arrays.binarySearch(nums, i, nums.length, u + 1);

            if (left < 0) {
                left = -(left + 1);
            }
            if (right < 0) {
                right = -(right + 1);
            }
            // left -= i;
            count += (right - left);

            l = lower + nums[i];
            u = upper + nums[i];
        }

        return count;
    }

    int getFirstOccurance(int[] nums, int s, int e, int num) {
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

    public static void main(String[] args) {
        int[] nums = {-2, 5, -1};
        int lower = -2;
        int upper = 2;
        System.out.println(new CountOfRangeSum327().countRangeSum(nums, lower, upper));
    }

}
