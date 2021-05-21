package leetcode;

import java.util.Arrays;
import java.util.HashMap;

/**
 * Created by adarsh.sharma on 22/08/18.
 */
public class CountSmaller {
    class BIT {
        private int[] sums;

        public BIT(int n) {
            this.sums = new int[n + 1];
        }

        public void update(int i) {
            while (i < sums.length) {
                sums[i]++;
                i += (i & -i);
            }
        }

        public int sum(int i) {
            int r = 0;
            while (i > 0) {
                r += sums[i];
                i -= (i & -i);
            }
            return r;
        }
    }

    public int[] countSmaller(int[] nums) {
        int[] r = new int[nums.length];
        BIT bit = new BIT(r.length);
        int[] sorted = nums.clone();
        Arrays.sort(sorted);

        HashMap<Integer, Integer> mp = new HashMap<>();
        for (int i = 0; i < sorted.length; i++) {
            mp.put(sorted[i], i);
        }

        for (int i = nums.length - 1; i >= 0; i--) {
            r[i] = bit.sum(mp.get(nums[i]));
            bit.update(mp.get(nums[i]) + 1);
        }

        return r;
    }

    public static void main(String[] args) {
        CountSmaller countSmaller = new CountSmaller();
        int[] nums = {5, 2, 6, 6, 3};
        int[] smaller = countSmaller.countSmaller(nums);
        Arrays.stream(smaller).forEach(System.out::println);
    }
}
