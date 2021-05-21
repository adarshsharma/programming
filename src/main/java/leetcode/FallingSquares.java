package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by adarsh.sharma on 07/08/18.
 */
public class FallingSquares {

    int[] segArray;
    int n;

    public List<Integer> fallingSquares(int[][] positions) {
        int max = 0;
        for (int i = 0; i < positions.length; i++) {
            max = Math.max(max, positions[i][0] + positions[i][1]);
        }
        this.n = max + 1;
        this.segArray = new int[2 * n];

        List<Integer> res = new ArrayList<>();
        for (int[] pos : positions) {
            max = getMax(pos[0], pos[0] + pos[1]);
            updateInterval(pos[0], pos[0] + pos[1], pos[1] + max);
            max = getMax(pos[0], pos[0] + pos[1]);
            if (res.size() == 0) {
                res.add(max);
            } else {
                res.add(Math.max(max, res.get(res.size() - 1)));
            }
        }

        return res;
    }

    //returns minimum element in range [left, right)
    int getMax(int left, int right) {
        int max = 0;
        left = left + n;
        right = right + n;

        while (left < right) {
            if (left % 2 == 1) {
                max = Math.max(max, segArray[left]);
                left++;
            }
            if (right % 2 == 1) {
                right--;
                max = Math.min(max, segArray[right]);
            }
            left = left / 2;
            right = right / 2;
        }

        return max;
    }

    //updates pos with new value val
    void update(int pos, int val) {
        segArray[pos + n] = val;
        int p = (pos + n) / 2;
        while (p > 0) {
            segArray[p] = Math.min(segArray[2 * p], segArray[2 * p + 1]);
            p = p / 2;
        }
    }

    //updates pos with new value val
    private void updateInterval(int left, int right, int val) {
        left = left + n;
        right = right + n;

        while (left < right) {
            if (left % 2 == 1) {
                segArray[left] = val;
                left++;
            }
            if (right % 2 == 1) {
                right--;
                segArray[right] = val;
            }
            left = left / 2;
            right = right / 2;
        }
    }

    public static void main(String[] args) {
        FallingSquares fallingSquares = new FallingSquares();
        int[][] positions = {{1, 2}, {2, 3}, {6, 1}};
        System.out.println(fallingSquares.fallingSquares(positions));
    }

//    static class Interval {
//        int start;
//        int end;
//        int height;
//
//        Interval(int s, int e, int h) {
//            start = s;
//            end = e;
//            height = h;
//        }
//
//        @Override
//        public String toString() {
//            return "(" + start + ", " + end + ")";
//        }
//    }
//
//    public List<Integer> fallingSquares(int[][] positions) {
//        List<Interval> intervals = new ArrayList<>();
//        List<Integer> res = new ArrayList<>();
//
//        for (int i = 0; i < positions.length; i++) {
//            intervals = mergeIntervals(intervals, positions[i], res);
//        }
//
//        return res;
//    }
//
//
//    private List<Interval> mergeIntervals(List<Interval> intervals, int[] pos, List<Integer> res) {
//        ArrayList<Interval> result = new ArrayList<>();
//        Interval newInterval = new Interval(pos[0], pos[1] + pos[0] + 1, pos[2]);
//        boolean found = false;
//        boolean afterLast = false;
//        int newStart = Integer.MAX_VALUE;
//        int newEnd = Integer.MIN_VALUE;
//        if (intervals.size() == 0) {
//            result.add(newInterval);
//            res.add(newInterval.height);
//            return result;
//        }
//        for (int i = 0; i < intervals.size(); i++) {
//            Interval interval = intervals.get(i);
//            if (i == 0 && newInterval.end < interval.start) {
//                result.add(newInterval);
//                result.addAll(intervals);
//                res.add(Math.max(newInterval.height, res.get(res.size() - 1)));
//                return result;
//            }
//
//            if (nonOverlapping(interval, newInterval)) {
//                if (afterLast && newInterval.end < interval.start) {
//                    result.add(newInterval);
//                    res.add(Math.max(newInterval.height, res.get(res.size() - 1)));
//                    afterLast = false;
//                }
//                if (interval.end < newInterval.start) {
//                    afterLast = true;
//                }
//
//                if (found) {
//                    result.add(new Interval(newStart, newEnd));
//                    found = false;
//                }
//                result.add(interval);
//            } else {
//                afterLast = false;
//                if (!found) {
//                    if (newInterval.start <= interval.start) {
//                        newStart = newInterval.start;
//                        newEnd = Math.max(interval.end, newInterval.end);
//                    } else {
//                        result.add(new Interval(interval.start, newInterval.start, interval.height));
//                    }
//
//                    if(newInterval.end < interval.end) {
//
//                    }
//                } else {
//                    newStart = Math.min(newStart, Math.min(interval.start, newInterval.start));
//                    newEnd = Math.max(newEnd, Math.max(interval.end, newInterval.end));
//                    found = true;
//                }
//            }
//            if (i == intervals.size() - 1 && newInterval.start > interval.end) {
//                result.add(newInterval);
//            }
//        }
//        if (found) {
//            result.add(new Interval(newStart, newEnd));
//        }
//
//        return result;
//    }
//
//    private boolean nonOverlapping(Interval first, Interval second) {
//        return first.end <= second.start || first.start >= second.end;
//    }

//    class Point {
//        boolean start;
//        int pos;
//        int height;
//
//        public Point(boolean start, int pos, int height) {
//            this.start = start;
//            this.pos = pos;
//            this.height = height;
//        }
//    }
//
//    public List<Integer> fallingSquares(int[][] positions) {
//        List<Point> points = new ArrayList<>();
//        List<Integer> res = new ArrayList<>();
//
//        for (int i = 0; i < positions.length; i++) {
//            points = mergeIntervals(points, positions[i], res);
//        }
//
//        return res;
//    }
//
//    private List<Point> mergeIntervals(List<Point> points, int[] pos, List<Integer> res) {
//        List<Point> result = new ArrayList<>();
//        if(points.size() == 0) {
//            points.add(new Point(true, pos[0], pos[1]));
//            points.add(new Point(false, pos[0] + pos[1] + 1, pos[1]));
//            res.add(pos[1]);
//            return result;
//        }
//
//        int posStart = pos[0];
//        int posEnd = pos[0] + pos[1] + 1;
//        int posHeight = pos[1];
//        int maxHeight = 0;
//
//        for(int i=0;i<points.size();i++) {
//            Point point = points.get(i);
//            if(point.pos <= posStart) {
//                result.add(point);
//            } else {
//                if(point.pos >= posEnd) {
//
//                } else {
//
//                }
//            }
//        }
//
//        return result;
//    }
}
