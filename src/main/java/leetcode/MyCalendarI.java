package leetcode;

import java.util.TreeMap;

/**
 * Created by adarsh.sharma on 10/09/18.
 */
public class MyCalendarI {
    TreeMap<Integer, Integer> calendar;

    MyCalendarI() {
        calendar = new TreeMap();
    }

    public boolean book(int start, int end) {
        Integer prev = calendar.floorKey(start);
        Integer next = calendar.ceilingKey(start);
        if ((prev == null || calendar.get(prev) <= start) &&
                (next == null || end <= next)) {
            calendar.put(start, end);
            return true;
        }
        return false;
    }
}
