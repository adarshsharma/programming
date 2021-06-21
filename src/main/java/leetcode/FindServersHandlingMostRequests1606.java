package leetcode;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.TreeSet;

public class FindServersHandlingMostRequests1606 {

    public List<Integer> busiestServers(int k, int[] arrival, int[] load) {
        TreeSet<Integer> availableServers = new TreeSet<>();
        PriorityQueue<Integer[]> busyServers = new PriorityQueue<>(
            Comparator.comparingInt(x -> x[0]));

        int max = 0;
        int[] requests = new int[k];
        for (int i = 0; i < k; i++) {
            availableServers.add(i);
        }

        for (int i = 0; i < arrival.length; i++) {
            int rtime = arrival[i];
            int rload = load[i];

            while (!busyServers.isEmpty() && busyServers.peek()[0] <= rtime) {
                Integer[] server = busyServers.poll();
                availableServers.add(server[1]);
            }

            if (availableServers.size() > 0) {
                Integer s = availableServers.ceiling(i % k);
                if (s == null) {
                    s = availableServers.first();
                }

                availableServers.remove(s);
                requests[s]++;
                max = Math.max(max, requests[s]);

                busyServers.add(new Integer[]{rtime + rload, s});
            }
        }

        List<Integer> results = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            if (requests[i] == max) {
                results.add(i);
            }
        }

        return results;
    }

    public static void main(String[] args) {
        int k = 3;
        int[] arrival = {1, 2, 3, 4};
        int[] load = {1, 2, 1, 2};
        System.out
            .println(new FindServersHandlingMostRequests1606().busiestServers(k, arrival, load));
    }

}
