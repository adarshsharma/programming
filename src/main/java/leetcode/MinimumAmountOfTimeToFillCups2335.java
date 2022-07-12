package leetcode;

import java.util.Arrays;

public class MinimumAmountOfTimeToFillCups2335 {

  public int fillCups(int[] amount) {
    Arrays.sort(amount);
    int res = 0;

    while (true) {
      if (amount[2] > 0 && amount[1] > 0) {
        int seconds = Math.min(amount[1] - amount[0] + 1, amount[1]);
        res += seconds;
        amount[1] -= seconds;
        amount[2] -= seconds;
        Arrays.sort(amount);
      } else {
        if (amount[2] > 0) {
          res += amount[2];
        }
        break;
      }
    }

    return res;
  }

  public static void main(String[] args) {
    int[] amount = {5, 4, 4};
    System.out.println(new MinimumAmountOfTimeToFillCups2335().fillCups(amount));
  }

}
