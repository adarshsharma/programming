package interviewBit;

import java.util.*;

/**
 * Created by adarsh.sharma on 16/08/18.
 */
public class PointsOnAStraightLine {
    class Slope {
        int y;
        int x;

        public Slope(int y, int x) {
            this.y = y;
            this.x = x;
        }

        public boolean same(Slope other) {
            long p1 = (long) this.x * (long) other.y;
            long p2 = (long) this.y * (long) other.x;
            return p1 == p2;
        }
    }
//
//    class Point {
//        int x;
//        int y;
//
//        public Point(int x, int y) {
//            this.x = x;
//            this.y = y;
//        }
//    }
//
//    public int maxPoints(ArrayList<Integer> a, ArrayList<Integer> b) {
//        if (a.size() < 3) {
//            return a.size();
//        }
//        int max = 2;
//        List<Point> points = new ArrayList<>();
//        for (int i = 0; i < a.size(); i++) {
//            points.add(new Point(a.get(i), b.get(i)));
//        }
//
//        points.sort(Comparator.comparingInt(p -> p.x));
//
//        for (int i = 0; i < points.size(); i++) {
//            Slope slope = null;
//            int curMax = 1;
//
//            for (int j = i + 1; j < points.size(); j++) {
//                int y = points.get(j).y - points.get(i).y;
//                int x = points.get(j).x - points.get(i).x;
//                if (y == 0 && x == 0) {
//                    curMax++;
//                } else {
//                    Slope newSlope = new Slope(y, x);
//                    if (slope == null) {
//                        slope = newSlope;
//                        curMax++;
//                    } else if (newSlope.same(slope)) {
//                        curMax++;
//                    }
//                }
//            }
//            max = Math.max(max, curMax);
//        }
//
//        return max;
//    }

    public int maxPoints(ArrayList<Integer> a, ArrayList<Integer> b) {
        if(a.size()<3) {
            return a.size();
        }
        int max = 2;

        for(int i=0;i<a.size();i++) {
            int dup = 0;
            for(int j=i+1;j<a.size();j++) {
                int y = b.get(j) - b.get(i);
                int x = a.get(j) - a.get(i);
                if(y==0 && x==0) {
                    dup++;
                } else {
                    int curMax = 2;
                    Slope slope = new Slope(y, x);
                    for (int k = j + 1; k < a.size(); k++) {
                        int y1 = b.get(k) - b.get(i);
                        int x1 = a.get(k) - a.get(i);
                        Slope newSlope = new Slope(y1, x1);
                        if (slope.same(newSlope)) {
                            curMax++;
                            max = Math.max(max, curMax + dup);
                        }
                    }
                }
            }
            max = Math.max(max, dup + 1);
        }

        return max;
    }

    public static void main(String[] args) {
//        ArrayList<Integer> a = new ArrayList<>(Arrays.asList(-1, -1, -1, -10, -11, -11, -11, -12, -12, -12, -13, -14, -14, -14, -15, -15, -15, -15, -15, -16, -16, -16, -16, -17, -17, -17, -17, -18, -18, -18, -18, -18, -2, -2, -3, -3, -3, -4, -5, -5, -5, -5, -6, -7, -8, -9, -9, -9, 0, 0, 1, 1, 1, 1, 1, 1, 10, 10, 10, 11, 13, 13, 14, 14, 15, 15, 15, 15, 15, 15, 16, 18, 19, 19, 19, 19, 19, 2, 20, 20, 3, 3, 3, 4, 5, 6, 6, 7, 7, 7, 7, 7, 8, 9, 9, 9));
//        ArrayList<Integer> b = new ArrayList<>(Arrays.asList(-2, -7, 7, -15, -8, 12, 2, -14, -4, 11, 8, -1, -15, 5, 12, 15, 19, 19, 20, -15, -17, -18, 3, -1, -5, -6, 9, -12, -17, 1, 13, 9, -10, -20, -13, -15, 4, -9, -1, -16, -3, 20, -8, -11, -3, -9, 14, 16, -1, 14, -1, -11, -20, -20, -6, 0, -14, -7, 14, -10, -3, 17, 3, 9, -11, -14, -2, -3, 12, 8, 7, 10, -12, -18, -9, 19, 7, 16, -18, -18, 11, 6, 7, 18, -17, -6, 20, -3, -5, 1, 19, 3, 7, -10, -17, 10));
        ArrayList<Integer> a = new ArrayList<>(Arrays.asList(15,9,-16,-15,11,-5,-3,-11,-8,3,15,-16,-6,14,-1,-1,3,6,10,0,19,-10,-17,8,20,-4,-9,10,-14,-2,-18,7,-12,-17,5,-2,15,-5,1,19,-14,18,1,-15,-18,13,-16,1,20,7,1,-7,7,-15,-1,-3,-11,-17,-12,15,15,7,19,-15,10,-14,0,-12,4,7,-5,1,1,2,6,-17,14,-13,-  9,-5,-18,9,19,16,3,-18,-11,-15,-3,-18,13,-16,-9,15,19,9));
        ArrayList<Integer> b = new ArrayList<>(Arrays.asList(12,10,3,15,-10,20,-15,-8,-3,6,-14,-18,-8,9,-7,-2,11,20,-7,14,-18,-15,-1,7,-18,-9,16,14,-15,-10,9,-5,11,-6,-17,-20,-2,-16,-20,-12,-1,10,-20,19,13,-3,-17,0,-18,19,-6,-11,1,12,7,-13,2,-5,-14,-3,-11,3,7,19,-14,5,-1,-4,18,-3,-3,-11,-1,16,-6,9,3,8,14,-1,-17,-10,19,7,7,-12,12,20,4,1,17,-15,-9,8,-9,-17));
//        ArrayList<Integer> a = new ArrayList<>(Arrays.asList(1,1,1,1,1));
//        ArrayList<Integer> b = new ArrayList<>(Arrays.asList(1,1,1,1,1));
        PointsOnAStraightLine pointsOnAStraightLine = new PointsOnAStraightLine();
        System.out.println(pointsOnAStraightLine.maxPoints(a, b));
    }
}
