package leetcode;


import java.util.HashMap;
import java.util.Map;

public class TaskScheduler2365 {

  public long taskSchedulerII(int[] tasks, int space) {
    long days = 0L;
    Map<Integer, Long> prevTasks = new HashMap<>();
    for (int task : tasks) {
      days++;
      if (prevTasks.containsKey(task)) {
        if (days < prevTasks.get(task)) {
          days = prevTasks.get(task);
        }
      }
      prevTasks.put(task, days + space + 1);
    }

    return days;
  }

  public static void main(String[] args) {
    int[] tasks = {1, 2, 1, 2, 3, 1};
    int space = 3;
    System.out.println(new TaskScheduler2365().taskSchedulerII(tasks, space));
  }

}
