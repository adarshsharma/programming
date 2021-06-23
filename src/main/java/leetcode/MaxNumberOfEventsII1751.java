package leetcode;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

public class MaxNumberOfEventsII1751 {

    public int maxValue(int[][] events, int k) {
        Arrays.sort(events, Comparator.comparingInt(x -> x[1]));
        TreeMap<Integer, Integer> prev = new TreeMap<>();
        int n = events.length;

        for (int i = 1; i <= k; i++) {
            TreeMap<Integer, Integer> next = new TreeMap<>();

            for (int m = 0; m < n; m++) {
                int val = events[m][2];
                if (i > 1) {
                    Map.Entry<Integer, Integer> entry = prev.lowerEntry(events[m][0]);
                    if (entry != null) {
                        val += entry.getValue();
                    }
                }
                if (m > 0) {
                    val = Math.max(val, next.get(events[m - 1][1]));
                }
                next.put(events[m][1], val);
            }

            prev = next;
        }

        return prev.values().stream().max(Comparator.naturalOrder()).get();
    }

    public static void main(String[] args) {
        int[][] events = {{1, 2, 4}, {3, 4, 3}, {2, 3, 1}};
        int k = 2;
        System.out.println(new MaxNumberOfEventsII1751().maxValue(events, k));
    }

}
