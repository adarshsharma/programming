package leetcode;

public class MinimumTimeToCompleteTrips2187 {

  public long minimumTime(int[] time, int totalTrips) {
    long low = 0L;
    int min = 10000000;
    for (int t : time) {
      min = Integer.min(min, t);
    }

    long high = (long)totalTrips * (long) min;
    long res = high;

    while (low <= high) {
      long mid = low + (high - low) / 2;
      long numOfTrips = 0;
      for (int t : time) {
        numOfTrips += (mid / t);
      }
      if (numOfTrips >= totalTrips) {
        res = Long.min(res, mid);
        high = mid - 1;
      } else {
        low = mid + 1;
      }
    }

    return res;
  }

  public static void main(String[] args) {
    int[] time = {1, 1, 1, 1, 1, 1, 1, 1, 1, 1};
    int totalTrips = 100;
    System.out.println(new MinimumTimeToCompleteTrips2187().minimumTime(time, totalTrips));
  }

}
