package leetcode;

import java.util.Arrays;

/**
 * Created by adarsh.sharma on 09/09/18.
 */
public class ThreeSumClosest {
    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int closest = nums[0]+nums[1]+nums[2];
        for(int i=0;i<=nums.length-3;i++) {
            int lo = i+1;
            int hi = nums.length-1;
            while(lo<hi) {
                int diff = target-nums[i]-nums[lo]-nums[hi];
                if(diff < 0) {
                    hi--;
                } else if(diff > 0) {
                    lo++;
                } else {
                    return target;
                }
                if(Math.abs(diff) < Math.abs(target-closest)) {
                    closest = target-diff;
                }
            }
        }

        return closest;
    }

    public static void main(String[] args) {
        int[] nums = {-3, 0, 1, 2};
        System.out.println(new ThreeSumClosest().threeSumClosest(nums, 1));
    }
}
