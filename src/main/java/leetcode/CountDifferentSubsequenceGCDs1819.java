package leetcode;

import java.util.HashSet;
import java.util.Set;

public class CountDifferentSubsequenceGCDs1819 {

    public int countDifferentSubsequenceGCDs(int[] nums) {
        int count = 1;
        // Set<Integer> st = new HashSet<>();
        // int max = -1;
        // for (int num : nums) {
        //     st.add(num);
        //     max = Math.max(max, num);
        // }
        //
        // for (int i = 2; i <= max / 2; i++) {
        //     if (st.contains(i)) {
        //         for (int j = 2; j <= max / i; j++) {
        //             if (st.contains(i * j)) {
        //                 count++;
        //                 break;
        //             }
        //         }
        //     }
        // }
        //
        return count;
    }

    public static void main(String[] args) {
        int[] nums = {6, 10, 3};
        System.out
            .println(new CountDifferentSubsequenceGCDs1819().countDifferentSubsequenceGCDs(nums));
    }

}
