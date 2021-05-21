package leetcode;

import java.util.TreeSet;

/**
 * Created by adarsh.sharma on 04/09/18.
 */
public class Find132Pattern {
    public boolean find132pattern(int[] nums) {
        int n = nums.length;
        if(n<3) {
            return false;
        }

        int[] inc = new int[n];
        inc[0] = -1;
        int min = 0;
        for(int i=1;i<n;i++) {
            if(nums[i] > nums[min]) {
                inc[i] = min;
            } else if(nums[i] < nums[min]) {
                inc[i] = -1;
                min = i;
            }
        }
//        Arrays.stream(inc).forEach(System.out::println);
        TreeSet<Integer> tset = new TreeSet<>();
        tset.add(nums[n-1]);
        for(int i=n-2;i>=1;i--) {
            if(inc[i] >= 0) {
                Integer lower = tset.lower(nums[i]);
                Integer higher = tset.higher(nums[inc[i]]);
                if(lower != null && higher != null && lower > nums[inc[i]] && higher < nums[i]) {
                    return true;
                }
            }
            tset.add(nums[i]);
        }

        return false;

    }

    public static void main(String[] args) {
        Find132Pattern find132Pattern = new Find132Pattern();
        int[] nums = {3,5,0,3,4};
        System.out.println(find132Pattern.find132pattern(nums));
    }
}
