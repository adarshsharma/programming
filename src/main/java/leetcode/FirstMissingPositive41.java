package leetcode;

/**
 * Created by adarsh.sharma on 19/06/18.
 */
public class FirstMissingPositive41 {
    public int firstMissingPositive(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            int cur = nums[i];
            if (cur != i + 1) {
                while (cur <= nums.length && cur > 0 && cur != nums[cur - 1]) {
                    int next = nums[cur - 1];
                    nums[cur - 1] = cur;
                    cur = next;
                }
            }
        }

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != i + 1) {
                return i + 1;
            }
        }

        return nums.length + 1;
    }

    public static void main(String[] args) {
        FirstMissingPositive41 firstMissingPositive41 = new FirstMissingPositive41();
        int[] nums = {1,2,0};
        System.out.println(firstMissingPositive41.firstMissingPositive(nums));
    }
}
