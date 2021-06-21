package leetcode;

import java.util.Map;
import java.util.TreeMap;

public class MinimumCostForTickets983 {

    public int mincostTickets(int[] days, int[] costs) {
        TreeMap<Integer, Integer> mp = new TreeMap<>();

        for (int i = 0; i < days.length; i++) {
            int val =
                ((i > 0) ? mp.lastEntry().getValue() : 0) + Math.min(Math.min(costs[0], costs[1]),
                    costs[2]);

            val = Math.min(val, getPrevVal(mp, days[i], costs[1], 6));
            val = Math.min(val, getPrevVal(mp, days[i], costs[2], 29));

            mp.put(days[i], val);
        }

        return mp.lastEntry().getValue();
    }

    private int getPrevVal(TreeMap<Integer, Integer> mp, int day, int cost, int prev) {
        Map.Entry<Integer, Integer> entry = mp.lowerEntry(day - prev);
        int prevVal = (entry == null) ? 0 : entry.getValue();
        return prevVal + cost;
    }

    public static void main(String[] args) {
        int[] days = {1, 4, 6, 7, 8, 20};
        int[] costs = {7, 2, 15};
        System.out.println(new MinimumCostForTickets983().mincostTickets(days, costs));
    }

}
