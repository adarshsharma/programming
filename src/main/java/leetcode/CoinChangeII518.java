package leetcode;

import java.util.Arrays;

public class CoinChangeII518 {

    Integer[][] dp;

    public int change(int amount, int[] coins) {
        if(amount == 0) {
            return 1;
        }
        Arrays.sort(coins);
        if (amount < coins[0]) {
            return 0;
        }

        dp = new Integer[amount + 1][coins.length];
        dp[0][0] = 1;
        dp[coins[0]][0] = 1;
        return change(amount, coins, coins.length - 1);
    }

    private int change(int amount, int[] coins, int last) {
        if (dp[amount][last] != null) {
            return dp[amount][last];
        }
        if (last == 0) {
            if (amount % coins[last] == 0) {
                dp[amount][last] = 1;
            } else {
                dp[amount][last] = 0;
            }
            return dp[amount][last];
        }

        int ways = 0;
        int i = 0;
        while (i <= amount) {
            ways += change(amount - i, coins, last - 1);
            i += coins[last];
        }
        dp[amount][last] = ways;
        return ways;
    }

    public static void main(String[] args) {
        int[] coins = {7};
        int amount = 0;
        System.out.println(new CoinChangeII518().change(amount, coins));
    }

}
