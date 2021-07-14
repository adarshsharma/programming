package leetcode;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class MaximumNumberOfEvents1353 {

    public int maxEvents(int[][] events) {
        Arrays.sort(events, (a, b) -> {
            if (a[0] == b[0]) {
                return a[1] - b[1];
            }
            return a[0] - b[0];
        });
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));

        int start = 1;
        int res = 0;

        int i = 0;
        while (i < events.length) {
            int[] event = events[i];
            if (event[0] <= start) {
                pq.add(event);
                i++;
            } else {
                if (pq.isEmpty()) {
                    pq.add(event);
                    start = event[0];
                    i++;
                }

                int[] ev = pq.poll();
                if (ev[1] >= start) {
                    res++;
                    start++;
                }
            }
        }

        while (!pq.isEmpty()) {
            int[] ev = pq.poll();
            if (ev[1] >= start) {
                res++;
                start++;
            }
        }

        return res;
    }

    public static void main(String[] args) {
        int[][] events = {
            {1, 2},
            {1, 2},
            {1, 5},
            {1, 5},
            {3, 3},
        };

        System.out.println(new MaximumNumberOfEvents1353().maxEvents(events));
    }

}
