package leetcode;

import java.util.PriorityQueue;
import java.util.Queue;

public class MaxEatenApples1705 {

  static class P implements Comparable<P> {

    int idx;
    int expIdx;
    int quantity;

    public P(int i, int e, int q) {
      this.idx = i;
      this.expIdx = e;
      this.quantity = q;
    }

    @Override
    public int compareTo(P other) {
      if (this.expIdx != other.expIdx) {
        return this.expIdx - other.expIdx;
      }

      return this.idx - other.idx;
    }
  }

  public static int eatenApples(int[] apples, int[] days) {
    Queue<P> queue = new PriorityQueue<>();

    for (int i = 0; i < apples.length; i++) {
      if (apples[i] != 0) {
        queue.add(new P(i, i + days[i] - 1, apples[i]));
      }
    }
    int res = 0;
    int day = 0;
    while (!queue.isEmpty()) {
      P p = queue.peek();
      if (p.expIdx < day) {
        queue.poll();
      } else {
        if (p.idx <= day) {
          res++;
          if (p.quantity == 1) {
            queue.poll();
          } else {
            p.quantity--;
          }
        }
        day++;
      }
    }

    return res;
  }

  public static void main(String[] args) {
    int[] apples = {1, 2, 3, 5, 2};
    int[] days = {3, 2, 1, 4, 2};

    // System.out.println(eatenApples(apples, days));
  }
}
