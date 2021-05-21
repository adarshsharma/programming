package leetcode;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

/**
 * Created by adarsh.sharma on 09/08/18.
 */
public class MaxSlidingWindow {
    public int[] maxSlidingWindow(int[] nums, int k) {
        if(nums.length == 0) {
            return new int[0];
        }
        Deque<Integer> dq = new LinkedList<>();
        int[] res = new int[nums.length - k + 1];

        for (int i = 0; i < nums.length; i++) {
            if (!dq.isEmpty() && (i - dq.peekFirst() + 1) > k) {
                dq.removeFirst();
            }

            while (!dq.isEmpty() && nums[dq.peekLast()] < nums[i]) {
                dq.removeLast();
            }
            dq.addLast(i);

            if (i >= k - 1) {
                res[i - k + 1] = nums[dq.peekFirst()];
            }
        }

        return res;
    }

    public static void main(String[] args) {
        MaxSlidingWindow maxSlidingWindow = new MaxSlidingWindow();
        int[] nums = {1,3,-1,-3,5,3,6,7};
//        int[] nums = {1, 3, 1, 2, 0, 5};
        int k = 3;
        Arrays.stream(maxSlidingWindow.maxSlidingWindow(nums, k)).forEach(System.out::println);
    }
}
