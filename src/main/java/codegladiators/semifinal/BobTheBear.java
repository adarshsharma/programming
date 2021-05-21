package codegladiators.semifinal;

import java.util.*;

/**
 * Created by adarsh.sharma on 18/05/18.
 */
public class BobTheBear {
    public static void main(String args[]) throws Exception {
        Scanner in = new Scanner(System.in);
//        Scanner in = new Scanner(new FileInputStream("/Users/adarsh.sharma/code/test/src/main/resources/bobTheBear.in"));

        int n = in.nextInt();
        Interval[] intervals = new Interval[n];
        Integer[] starts = new Integer[n];
        Integer[] ends = new Integer[n];
        List<Point> points = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            ends[i] = in.nextInt();
        }

        for (int i = 0; i < n; i++) {
            starts[i] = in.nextInt();
            ends[i] = ends[i] + starts[i];
        }

        for (int i = 0; i < n; i++) {
            intervals[i] = (new Interval(starts[i], ends[i]));
            points.add(new Point(starts[i], true, i));
            points.add(new Point(ends[i], false, i));
        }

        Collections.sort(points);

        int max = 0;

        for (Point point : points) {
            Set<Integer> nonOverlapping = getNonOverlappingIntervals(intervals, point, n);
            int overlappingCount = getMaxOverlappingInterval(points, nonOverlapping);
            int currentMax = (n - nonOverlapping.size()) + overlappingCount;
            if (currentMax > max) {
                max = currentMax;
            }
        }

        System.out.println(max);
    }

    private static int getMaxOverlappingInterval(List<Point> points, Set<Integer> intervalsToConsider) {
        int max = 0;
        int curMax = 0;

        for (Point point : points) {
            if (intervalsToConsider.contains(point.index)) {
                if (point.start) {
                    curMax++;
                    if (curMax > max) {
                        max = curMax;
                    }
                } else {
                    curMax--;
                }
            }
        }


        return max;
    }

    private static Set<Integer> getNonOverlappingIntervals(Interval[] intervals, Point point, int n) {
        Set<Integer> nonOverlappingIntervals = new HashSet<>();
        for (int i = 0; i < n; i++) {
            Interval interval = intervals[i];
            if (interval.start > point.value || interval.end < point.value) {
                nonOverlappingIntervals.add(i);
            }
        }

        return nonOverlappingIntervals;
    }


    private static class Interval {
        int start;
        int end;

        public Interval(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    private static class Point implements Comparable<Point> {
        Integer value;
        boolean start;
        int index;

        public Point(int value, boolean start, int index) {
            this.value = value;
            this.start = start;
            this.index = index;
        }

        @Override
        public int compareTo(Point other) {
            return this.value.compareTo(other.value);
        }
    }
}
