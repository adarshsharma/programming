package leetcode;

import java.util.Collections;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 * Created by adarsh.sharma on 18/06/18.
 */
class ExamRoom855 {

    int N;
    TreeSet<Integer> occupiedLocations;
    TreeMap<Integer, TreeSet<Integer>> distanceLocationMap;

    public ExamRoom855(int N) {
        this.N = N;
        this.occupiedLocations = new TreeSet<>();
        this.distanceLocationMap = new TreeMap<>(Collections.reverseOrder());
        this.distanceLocationMap.computeIfAbsent(N - 1, k -> new TreeSet<>()).add(0);
    }

    public int seat() {
        TreeSet<Integer> treeSet = distanceLocationMap.get(distanceLocationMap.firstKey());
        Integer location = treeSet.first();
        treeSet.remove(location);
        if (treeSet.size() == 0) {
            distanceLocationMap.remove(distanceLocationMap.firstKey());
        }
        occupiedLocations.add(location);
        Integer floorKey = occupiedLocations.lower(location);
        Integer ceilingKey = occupiedLocations.higher(location);

        if (floorKey == null) {
            int dist = location;
            if (dist > 0) {
                distanceLocationMap.computeIfAbsent(dist, k -> new TreeSet<>()).add(0);
            }
        } else {
            int dist = (location - floorKey) / 2;
            if (dist > 0) {
                distanceLocationMap.computeIfAbsent(dist, k -> new TreeSet<>()).add(floorKey + dist);
            }
        }

        if (ceilingKey == null) {
            int dist = N - 1 - location;
            if (dist > 0) {
                distanceLocationMap.computeIfAbsent(dist, k -> new TreeSet<>()).add(N - 1);
            }
        } else {
            int dist = (ceilingKey - location) / 2;
            if (dist > 0) {
                distanceLocationMap.computeIfAbsent(dist, k -> new TreeSet<>()).add(location + dist);
            }
        }

        return location;
    }

    public void leave(int p) {
        Integer floorKey = occupiedLocations.lower(p);
        Integer ceilingKey = occupiedLocations.higher(p);

        if (floorKey == null) {
            int dist = p;
            if (dist > 0) {
                distanceLocationMap.get(dist).remove(0);
                if (distanceLocationMap.get(dist).size() == 0) {
                    distanceLocationMap.remove(dist);
                }
            }
        } else {
            int dist = (p - floorKey) / 2;
            if (dist > 0) {
                distanceLocationMap.get(dist).remove(floorKey + dist);
                if (distanceLocationMap.get(dist).size() == 0) {
                    distanceLocationMap.remove(p);
                }
            }
        }

        if (ceilingKey == null) {
            int dist = N - 1 - p;
            if (dist > 0) {
                distanceLocationMap.get(dist).remove(N - 1);
                if (distanceLocationMap.get(dist).size() == 0) {
                    distanceLocationMap.remove(p);
                }
            }
        } else {
            int dist = (ceilingKey - p) / 2;
            if (dist > 0) {
                distanceLocationMap.get(dist).remove(p + dist);
                if (distanceLocationMap.get(dist).size() == 0) {
                    distanceLocationMap.remove(dist);
                }
            }
        }

        if (floorKey == null && ceilingKey == null) {
            distanceLocationMap.computeIfAbsent(N - 1, k -> new TreeSet<>()).add(0);
        } else if (floorKey == null) {
            distanceLocationMap.computeIfAbsent(ceilingKey, k -> new TreeSet<>()).add(0);
        } else if (ceilingKey == null) {
            distanceLocationMap.computeIfAbsent(N - 1 - floorKey, k -> new TreeSet<>()).add(N - 1);
        } else {
            int dist = (ceilingKey - floorKey) / 2;
            if (dist > 0) {
                distanceLocationMap.computeIfAbsent(dist, k -> new TreeSet<>()).add(floorKey + dist);
            }
        }

        occupiedLocations.remove(p);
    }


    public static void main(String[] args) {
        ExamRoom855 e = new ExamRoom855(9);
        System.out.println(e.seat());
        System.out.println(e.seat());
        System.out.println(e.seat());
        System.out.println(e.seat());
        e.leave(4);

        System.out.println(e.seat());
        System.out.println(e.seat());
        System.out.println(e.seat());
        System.out.println(e.seat());
        System.out.println(e.seat());
        System.out.println(e.seat());
        e.leave(3);
        System.out.println(e.seat());
    }
}
