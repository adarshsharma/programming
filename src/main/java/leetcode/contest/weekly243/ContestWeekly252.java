package leetcode.contest.weekly243;

public class ContestWeekly252 {

  public long numberOfWeeks(int[] milestones) {
    long sum = 0;
    int max = Integer.MIN_VALUE;
    for (int milestone : milestones) {
      max = Math.max(max, milestone);
      sum += milestone;
    }

    sum -= max;

    long result = sum;
    if (max > sum + 1) {
      result += (sum + 1);
    } else {
      result += max;
    }

    return result;
  }

  public long minimumPerimeter(long neededApples) {
    long sum = 0;
    long p = 0;
    while (sum < neededApples) {
      p++;
      sum += 12L * p * p;
    }

    return 8 * p;
  }

  public int countSpecialSubsequences(int[] nums) {
    int M = 1000000007;
    long[] pdp = new long[3];
    if (nums[0] == 0) {
      pdp[0] = 1L;
    }

    for (int i = 1; i < nums.length; i++) {
      long[] ndp = new long[3];

      if (nums[i] == 0) {
        if (pdp[0] > 0) {
          ndp[0] = (2 * pdp[0] + 1) % M;
        } else {
          ndp[0] = 1;
        }
      } else {
        ndp[0] = pdp[0];
      }
      if (nums[i] == 1) {
        ndp[1] = pdp[0];
        if (pdp[1] > 0) {
          ndp[1] = (ndp[1] + 2 * pdp[1]) % M;
        }
      } else {
        ndp[1] = pdp[1];
      }
      if (nums[i] == 2) {
        ndp[2] = pdp[1];
        if (pdp[2] > 0) {
          ndp[2] = (ndp[2] + 2 * pdp[2]) % M;
        }
      } else {
        ndp[2] = pdp[2];
      }

      pdp = ndp;
    }

    return Long.valueOf(pdp[2]).intValue();
  }

  public static void main(String[] args) {
    // int[] milestones = {5, 2, 1};
    // System.out.println(new ContestWeekly252().numberOfWeeks(milestones));
    // System.out.println(new ContestWeekly252().minimumPerimeter(13));
    // int[] nums = {0, 1, 2, 0, 1, 2};
    int[] nums = {1, 0, 1, 0, 2};
    System.out.println(new ContestWeekly252().countSpecialSubsequences(nums));
  }
}
