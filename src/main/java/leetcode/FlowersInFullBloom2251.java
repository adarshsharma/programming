package leetcode;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class FlowersInFullBloom2251 {

  static class Interval implements Comparable<Interval> {

    int start;
    int end;
    int bloomed;

    public Interval(int s, int e, int b) {
      this.start = s;
      this.end = e;
      this.bloomed = b;
    }

    @Override
    public int compareTo(Interval o) {
      return this.start - o.start;
    }
  }

  public int[] fullBloomFlowers(int[][] flowers, int[] people) {
    PriorityQueue<Integer> endTimes = new PriorityQueue<>();
    Arrays.sort(flowers, Comparator.comparingInt(x -> x[0]));

    TreeSet<Interval> processed = new TreeSet<>();
    int bloomed = 1;
    int start = flowers[0][0];
    endTimes.offer(flowers[0][1]);
    int i = 1;

    while (i < flowers.length) {
      int[] flower = flowers[i];
      int removed = 0;

      if (!endTimes.isEmpty() && endTimes.peek() < flower[0]) {
        // remove existing intervals first
        int end = endTimes.poll();
        removed++;

        while (!endTimes.isEmpty() && end == endTimes.peek()) {
          endTimes.poll();
          removed++;
        }
        processed.add(new Interval(start, end, bloomed));
        bloomed -= removed;
        start = end + 1;
      } else {    // insert new
        if(flower[0] > start) {
          processed.add(new Interval(start, flower[0] - 1, bloomed));
          start = flower[0];
        }
        bloomed++;
        endTimes.add(flower[1]);
        i++;
      }
    }

    while (!endTimes.isEmpty()) {
      int removed = 0;
      int end = endTimes.poll();
      removed++;

      while (!endTimes.isEmpty() && end == endTimes.peek()) {
        endTimes.poll();
        removed++;
      }
      processed.add(new Interval(start, end, bloomed));
      bloomed -= removed;
      start = end + 1;
    }

    int[] result = new int[people.length];
    for (i = 0; i < people.length; i++) {
      Interval floor = processed.floor(new Interval(people[i], 0, 0));
      result[i] = floor == null ? 0 : (floor.end >= people[i] ? floor.bloomed : 0);
    }
    return result;
  }

  public static void main(String[] args) {
    // int[][] flowers = {{1, 6}, {3, 7}, {9, 12}, {4, 13}};
    // int[] people = {2, 3, 7, 11};
    int[][] flowers = {{19, 37}, {19, 38}, {19, 35}};
    int[] people = {6, 7, 21, 1, 13, 37, 5, 37, 46, 43};
    int[] result = new FlowersInFullBloom2251().fullBloomFlowers(flowers, people);
    System.out.println(IntStream.of(result).boxed().collect(Collectors.toList()));
  }
}
