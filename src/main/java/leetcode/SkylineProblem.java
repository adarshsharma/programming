package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.TreeMap;

/**
 * Created by adarsh.sharma on 07/08/18.
 */
public class SkylineProblem {
    class P implements Comparable<P> {
        int x;
        int h;
        boolean start;

        public P(int x, int h, boolean st) {
            this.x = x;
            this.h = h;
            this.start = st;
        }

        @Override
        public int compareTo(P that) {
            if(this.x != that.x) {
                return this.x - that.x;
            }

            if(this.start != that.start) {
                return this.start?-1:1;
            } else {
                if(this.start) {
                    return that.h-this.h;
                } else {
                    return this.h-that.h;
                }
            }
        }
    }

    public List<int[]> getSkyline(int[][] buildings) {
        P[] points = new P[buildings.length*2];

        for(int i=0;i<buildings.length;i++) {
            points[i*2] = new P(buildings[i][0], buildings[i][2], true);
            points[i*2+1] = new P(buildings[i][1], buildings[i][2], false);
        }

        Arrays.sort(points);

        TreeMap<Integer, Integer> queue = new TreeMap<>();
        queue.put(0, 1);

        int prev = 0;
        List<int[]> result = new ArrayList<>();
        for(int i=0;i<points.length;i++) {
            P p = points[i];
            if(p.start) {
                if(queue.containsKey(p.h)) {
                    queue.merge(p.h, 1, (a, b) -> a+b);
                } else {
                    queue.put(p.h, 1);
                }
            } else {
                if(queue.get(p.h) == 1) {
                    queue.remove(p.h);
                } else {
                    queue.merge(p.h, -1, (a, b) -> a+b);
                }
            }

            if(prev != queue.lastKey()) {
                result.add(new int[]{p.x, queue.lastKey()});
                prev = queue.lastKey();
            }
        }
        return result;
    }

    public static void main(String[] args) {
        SkylineProblem skylineProblem = new SkylineProblem();
        int[][] buildings = {{2,9,10},{3,7,15},{5,12,12},{15,20,10},{19,24,8}};
        System.out.println(skylineProblem.getSkyline(buildings));
    }
}
