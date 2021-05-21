package leetcode;

import java.util.Arrays;

/**
 * Created by adarsh.sharma on 14/09/18.
 */
public class CoinChange {
    static long getWays(int n, int[] c) {
        Arrays.sort(c);
        long[] dp = new long[n + 1];

        for (int i = c.length - 1; i >= 0; i--) {
            long[] newdp = new long[n + 1];
            newdp[0] = 1;
            for (int j = c[i]; j <= n; j++) {
                newdp[j] = newdp[j-c[i]];
                if(i<c.length) {
                    newdp[j]+=dp[j];
                }
            }
            dp = newdp;
        }

        return dp[n];
    }


    public static void main(String[] args) {
//        System.out.println(getWays(10, new int[]{5, 2, 3, 6}));
        System.out.println(getWays(6, new int[]{1, 2, 3}));
    }
}
