package leetcode;

public class MaximumRunningTimeForNComputers2141 {

  public long maxRunTime(int n, int[] batteries) {
    long sum = 0L;
    for (int batt : batteries) {
      sum += batt;
    }

    long t = sum / n;
    boolean flag = true;
    long s = sum;
    while (flag) {
      s = sum;
      flag = false;
      for (int batt : batteries) {
        if (batt > t) {
          s -= (batt - t);
          flag = true;
        }
      }
      System.out.println(s);
      if (t == s / n) {
        break;
      } else {
        t = s/n;
      }
    }
    return s / n;
  }

  public static void main(String[] args) {
    int[] batteries = {18, 54, 2, 53, 87, 31, 71, 4, 29, 25};
    int n = 9;
    System.out.println(new MaximumRunningTimeForNComputers2141().maxRunTime(n, batteries));
  }

}
