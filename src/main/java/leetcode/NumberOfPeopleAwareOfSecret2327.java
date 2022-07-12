package leetcode;

import java.util.LinkedList;
import java.util.Queue;

public class NumberOfPeopleAwareOfSecret2327 {

  public static class Data {

    int time;
    long count;

    public Data(int t, long c) {
      time = t;
      count = c;
    }
  }

  public int peopleAwareOfSecret(int n, int delay, int forget) {
    Queue<Data> waitQueue = new LinkedList<>();
    Queue<Data> shareQueue = new LinkedList<>();

    long mod = 1000000007;

    long shareCount = 0L;
    long waitCount = 1L;
    waitQueue.offer(new Data(delay, 1));
    shareQueue.offer(new Data(forget, 1));

    for (int d = 2; d <= n; d++) {
      while (!waitQueue.isEmpty() && waitQueue.peek().time < d) {
        Data data = waitQueue.poll();
        shareCount = (shareCount + data.count) % mod;
        waitCount = (waitCount - data.count + mod) % mod;
      }
      while (!shareQueue.isEmpty() && shareQueue.peek().time < d) {
        Data data = shareQueue.poll();
        shareCount = (shareCount - data.count + mod) % mod;
      }
      waitQueue.offer(new Data(d + delay - 1, shareCount));
      waitCount = (waitCount + shareCount) % mod;
      shareQueue.offer(new Data(d + forget - 1, shareCount));
    }

    return Long.valueOf((shareCount + waitCount) % mod).intValue();
  }

  public static void main(String[] args) {
    int n = 6;
    int delay = 2;
    int forget = 4;
    System.out.println(new NumberOfPeopleAwareOfSecret2327().peopleAwareOfSecret(n, delay, forget));
  }

}
