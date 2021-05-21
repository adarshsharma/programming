package leetcode;

import java.util.Arrays;

/**
 * Created by adarsh.sharma on 20/08/18.
 */
public class RaceCar818 {
//    public int racecar(int target) {
//        if(target<2) {
//            return target;
//        }
//
//        int[] steps = new int[target+1];
//        steps[0] = 0;
//        steps[1] = 1;
//        int pos = 1;
//        int speed = 2;
//
//        for(int i=2;i<=target;i++) {
//            if(pos+speed == i) {
//                steps[i] = steps[pos] + 1;
//                pos+=speed;
//                speed*=2;
//            } else {
//                int less = i - pos;
//                int more = pos+speed - i;
//                if(steps[less] <= steps[more]) {
//                    steps[i] = steps[pos] + 2 + steps[less];
//                } else {
//                    steps[i] = steps[pos] + 2 + steps[more];
//                }
//            }
//        }
//
//        return steps[target];
//    }

    public int racecar(int target) {
        int[] dp = new int[target + 3];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        dp[1] = 1;
        dp[2] = 4;

        for (int t = 3; t <= target; ++t) {
            int k = 32 - Integer.numberOfLeadingZeros(t);
            if (t == (1 << k) - 1) {
                dp[t] = k;
                continue;
            }
            for (int j = 0; j < k - 1; ++j) {
                dp[t] = Math.min(dp[t], dp[t - (1 << (k - 1)) + (1 << j)] + k - 1 + j + 2);
            }
            dp[t] = Math.min(dp[t], dp[(1 << k) - 1 - t] + k + 1);
        }

        return dp[target];
    }

    public static void main(String[] args) {
        System.out.println(new RaceCar818().racecar(10));
    }
}
