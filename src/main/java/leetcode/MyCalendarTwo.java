package leetcode;

import java.util.TreeMap;

/**
 * Created by adarsh.sharma on 10/09/18.
 */
public class MyCalendarTwo {
//    class P implements Comparable<P> {
//        Integer val;
//        boolean start;
//
//        public P(Integer val, boolean start) {
//            this.val = val;
//            this.start = start;
//        }
//
//        @Override
//        public int compareTo(P other) {
//            if (!this.val.equals(other.val)) {
//                return this.val.compareTo(other.val);
//            }
//
//            if (this.start == other.start) {
//                return 0;
//            }
//            return !this.start ? -1 : 1;
//        }
//    }
//
//    List<P> events;
//
//    public MyCalendarTwo() {
//        events = new ArrayList<>();
//    }
//
//    public boolean book(int start, int end) {
//        P st = new P(start, true);
//
//        int idx1 = Collections.binarySearch(events, st);
//        if(idx1 < 0) {
//            idx1 = -(idx1 + 1);
//        }
//        events.add(idx1, st);
//
//        P ed = new P(end, false);
//        int idx2 = Collections.binarySearch(events, ed);
//        if(idx2 < 0) {
//            idx2 = -(idx2 + 1);
//        }
//        events.add(idx2, ed);
//
//        int count = 0;
//        boolean added = true;
//        Iterator<P> it = events.iterator();
//        while (it.hasNext()) {
//            P p = it.next();
//            if (p.start) {
//                count++;
//                if (count > 2) {
//                    added = false;
//                    break;
//                }
//            } else {
//                count--;
//            }
//        }
//
//        if (!added) {
//            events.remove(idx2);
//            events.remove(idx1);
//        }
//
//        return added;
//    }

    TreeMap<Integer, Integer> delta;

    public MyCalendarTwo() {
        delta = new TreeMap<>();
    }

    public boolean book(int start, int end) {
        delta.put(start, delta.getOrDefault(start, 0) + 1);
        delta.put(end, delta.getOrDefault(end, 0) - 1);

        int active = 0;
        for (int d: delta.values()) {
            active += d;
            if (active >= 3) {
                delta.put(start, delta.get(start) - 1);
                delta.put(end, delta.get(end) + 1);
                if (delta.get(start) == 0)
                    delta.remove(start);
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        MyCalendarTwo myCalendarTwo = new MyCalendarTwo();
        System.out.println(myCalendarTwo.book(10, 20)); // returns true
        System.out.println(myCalendarTwo.book(50, 60)); // returns true
        System.out.println(myCalendarTwo.book(10, 40)); // returns true
        System.out.println(myCalendarTwo.book(5, 15)); // returns false
        System.out.println(myCalendarTwo.book(5, 10)); // returns true
        System.out.println(myCalendarTwo.book(25, 55)); // returns true
    }
}
