package leetcode;

/**
 * Created by adarsh.sharma on 08/08/18.
 */
public class MaxProfitWithFee {
    public int maxProfit(int[] prices, int fee) {
        int profit = 0;
        int min = 0;
        int max = 0;
        int tempProfit = 0;
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] > prices[i - 1]) {
                tempProfit = prices[i] - prices[min] - fee;
                max = i;
            } else if (prices[i] < (prices[max] - fee) || prices[i] < prices[min]) {
                profit += Math.max(0, tempProfit);
                tempProfit = 0;
                min = i;
            }
        }

        profit += Math.max(0, tempProfit);
        return profit;
    }

    public static void main(String[] args) {
        MaxProfitWithFee maxProfitWithFee = new MaxProfitWithFee();
        int fee = 2;
        int[] prices = {2, 2, 1, 1, 5, 5, 3, 1, 5, 4};
        System.out.println(maxProfitWithFee.maxProfit(prices, fee));
    }
}
