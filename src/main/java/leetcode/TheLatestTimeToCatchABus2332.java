package leetcode;

import java.util.Arrays;

public class TheLatestTimeToCatchABus2332 {

  public int latestTimeCatchTheBus(int[] buses, int[] passengers, int capacity) {
    Arrays.sort(buses);
    Arrays.sort(passengers);

    int latestTime = 1;
    int prev = 1;
    int i = 0;

    for (int bus : buses) {
      int used = 0;
      while (used < capacity && i < passengers.length) {
        if (passengers[i] <= bus) {
          if (prev != passengers[i] - 1) {
            latestTime = passengers[i] - 1;
          }
          prev = passengers[i];
          used++;
          i++;
        } else {
          if (i==0 || passengers[i - 1] != bus) {
            latestTime = bus;
          }
          break;
        }
      }
      if (i == passengers.length && used < capacity && prev != bus) {
        latestTime = bus;
      }
    }

    return latestTime;
  }

  public static void main(String[] args) {
    // int[] buses = {10, 20};
    // int[] passengers = {2, 17, 18, 19};
    // int[] buses = {10, 20, 30};
    // int[] passengers = {4, 11, 13, 19, 21, 25, 26};
    int[] buses = {3};
    int[] passengers = {4};
    int capacity = 1;
    System.out.println(
        new TheLatestTimeToCatchABus2332().latestTimeCatchTheBus(buses, passengers, capacity));
  }

}
