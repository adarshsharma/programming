package leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by adarsh.sharma on 03/09/18.
 */
public class ContainsDuplicateIII {
//    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
//        TreeMap<Integer, Integer> mp = new TreeMap<>();
//        for (int i = 0; i < nums.length; i++) {
//            if (i > k) {
//                if (mp.get(nums[i - k - 1]) == 1) {
//                    mp.remove(nums[i - k - 1]);
//                } else {
//                    mp.merge(nums[i - k - 1], -1, Integer::sum);
//                }
//            }
//
//            Integer floor = mp.floorKey(nums[i]);
//            if (floor != null && (long) nums[i] - (long) floor <= (long) t) {
//                return true;
//            }
//            Integer ceiling = mp.ceilingKey(nums[i]);
//            if (ceiling != null && (long) ceiling - (long) nums[i] <= (long) t) {
//                return true;
//            }
//
//            mp.merge(nums[i], 1, Integer::sum);
//        }
//
//        return false;
//    }

    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        if (k < 1 || t < 0) return false;
        Map<Long, Long> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            long remappedNum = (long) nums[i] - Integer.MIN_VALUE;
            long bucket = remappedNum / ((long) t + 1);
            if (map.containsKey(bucket)
                    || (map.containsKey(bucket - 1) && remappedNum - map.get(bucket - 1) <= t)
                    || (map.containsKey(bucket + 1) && map.get(bucket + 1) - remappedNum <= t))
                return true;
            if (map.entrySet().size() >= k) {
                long lastBucket = ((long) nums[i - k] - Integer.MIN_VALUE) / ((long) t + 1);
                map.remove(lastBucket);
            }
            map.put(bucket, remappedNum);
        }
        return false;
    }

    public static void main(String[] args) {
        ContainsDuplicateIII containsDuplicateIII = new ContainsDuplicateIII();
        int[] nums = {1, 4, 1, 2};
        int k = 1;
        int t = 1;
        System.out.println(containsDuplicateIII.containsNearbyAlmostDuplicate(nums, k, t));
    }
}
