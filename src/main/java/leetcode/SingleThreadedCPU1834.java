package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class SingleThreadedCPU1834 {

  static class Task {

    int s;
    int p;
    int id;

    public Task(int s, int p, int id) {
      this.s = s;
      this.p = p;
      this.id = id;
    }
  }

  public static int[] getOrder(int[][] tasks) {
    List<Task> tl = new ArrayList<>();
    for (int i = 0; i < tasks.length; i++) {
      int[] task = tasks[i];
      tl.add(new Task(task[0], task[1], i));
    }
    tl.sort((t1, t2) -> {
      if (t1.s != t2.s) {
        return t1.s - t2.s;
      }
      return t1.p - t2.p;
    });

    PriorityQueue<Task> pq = new PriorityQueue<>((t1, t2) -> {
      if (t1.p != t2.p) {
        return t1.p - t2.p;
      }
      return t1.id - t2.id;
    });

    int t = 0;
    int[] res = new int[tasks.length];
    int resi = 0;
    int timeElapsed = 0;

    while (resi < tasks.length) {
      if (pq.isEmpty()) {
        Task task = tl.get(t++);
        pq.add(task);
      } else {
        Task task = pq.poll();
        timeElapsed = (Math.max(timeElapsed, task.s) + task.p);
        res[resi++] = task.id;
        while (t < tasks.length) {
          if (tl.get(t).s <= timeElapsed) {
            pq.add(tl.get(t));
            t++;
          } else {
            break;
          }
        }
      }
    }

    return res;

  }

  public static void main(String[] args) {
    // int[][] tasks = {{1, 2}, {2, 4}, {3, 2}, {4, 1}};
    // int[][] tasks = {{7, 10}, {7, 12}, {7, 5}, {7, 4}, {7, 2}};
    int[][] tasks = {{19, 13}, {16, 9}, {21, 10}, {32, 25}, {37, 4}, {49, 24}, {2, 15}, {38, 41},
        {37, 34}, {33, 6}, {45, 4}, {18, 18}, {46, 39}, {12, 24}};
    // int[][] tasks = {
    //     {2, 15, 6},
    //     {12, 24, 13},
    //     {16, 9, 1},
    //     {18, 18, 11},
    //     {19, 13, 0},
    //     {21, 10, 2},
    //     {32, 25, 3},
    //     {33, 6, 9},
    //     {37, 4, 4},
    //     {37, 34, 8},
    //     {38, 41, 7},
    //     {45, 4, 10},
    //     {46, 39, 12},
    //     {49, 24, 5},
    // };
    int[] order = getOrder(tasks);
    Arrays.stream(order).

        forEach(System.out::println);
  }

}
