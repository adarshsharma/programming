package leetcode;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.TreeMap;

public class MaxProfitWithJobScheduling1235 {

  static class Job {

    int s;
    int e;
    int p;

    public Job(int s, int e, int p) {
      this.s = s;
      this.e = e;
      this.p = p;
    }
  }

  public static int jobScheduling(int[] startTime, int[] endTime, int[] profit) {
    List<Job> jobs = new ArrayList<>();
    for (int i = 0; i < startTime.length; i++) {
      jobs.add(new Job(startTime[i], endTime[i], profit[i]));
    }

    jobs.sort(Comparator.comparingInt(job -> job.e));
    TreeMap<Integer, Integer> tm = new TreeMap<>();

    tm.put(0, 0);

    int maxProfit = Integer.MIN_VALUE;

    for (Job job : jobs) {
      int p = tm.floorEntry(job.s).getValue() + job.p;
      if(p > maxProfit) {
        maxProfit = p;
        tm.put(job.e, p);
      }
    }

    return maxProfit;
  }

  public static void main(String[] args) {
    int[] s = {4, 2, 4, 8, 2};
    int[] e = {5, 5, 5, 10, 8};
    int[] p = {1, 2, 8, 10, 4};
    System.out.println(jobScheduling(s, e, p));
  }
/*
  2------2-----5
         4--1--5
         4--8--5
         2----4----8
                   8----10---10

 */
}
