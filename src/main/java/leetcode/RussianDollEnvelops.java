package leetcode;

import java.util.Arrays;

/**
 * Created by adarsh.sharma on 23/08/18.
 */
public class RussianDollEnvelops {
    public int maxEnvelopes(int[][] envelopes) {
        if (envelopes == null || envelopes.length == 0
                || envelopes[0] == null || envelopes[0].length != 2)
            return 0;
        Arrays.sort(envelopes, (arr1, arr2) -> {
            if (arr1[0] == arr2[0]) {
                return arr2[1] < arr1[1] ? -1 : arr2[1] == arr1[1] ? 0 : 1;
            } else {
                return arr1[0] < arr2[0] ? -1 : 1;
            }
        });
        int dp[] = new int[envelopes.length];
        int len = 0;
        for (int[] envelope : envelopes) {
            int index = Arrays.binarySearch(dp, 0, len, envelope[1]);
            if (index < 0) {
                index = -(index + 1);
                dp[index] = envelope[1];
            }
            if (index == len) {
                len++;
            }
        }
        return len;
    }

    public static void main(String[] args) {
        RussianDollEnvelops russianDollEnvelops = new RussianDollEnvelops();
        int[][] envs = {{1, 2}, {2, 3}, {3, 4}, {4, 5}, {5, 6}, {5, 5}, {6, 7}, {7, 8}};
        System.out.println(russianDollEnvelops.maxEnvelopes(envs));
    }
}
