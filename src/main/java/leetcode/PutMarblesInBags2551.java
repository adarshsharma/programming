package leetcode;

import java.util.Collections;
import java.util.PriorityQueue;

public class PutMarblesInBags2551 {

  public long putMarbles(int[] weights, int k) {
    PriorityQueue<Integer> minQueue = new PriorityQueue<>();
    PriorityQueue<Integer> maxQueue = new PriorityQueue<>(Collections.reverseOrder());

    for (int i = 0; i < weights.length - 1; i++) {
      minQueue.add(weights[i] + weights[i + 1]);
      maxQueue.add(weights[i] + weights[i + 1]);
    }

    long max = 0;
    long min = 0;
    while (k > 1 && !maxQueue.isEmpty() && !minQueue.isEmpty()) {
      max += maxQueue.poll();
      min += minQueue.poll();
      k--;
    }

    return max - min;
  }

  public static void main(String[] args) {
    int[] weights = {1, 3, 5, 1};
    int k = 2;

    System.out.println(new PutMarblesInBags2551().putMarbles(weights, k));
  }
}
