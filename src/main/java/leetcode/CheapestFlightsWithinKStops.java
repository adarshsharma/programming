package leetcode;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * Created by adarsh.sharma on 28/08/18.
 */
public class CheapestFlightsWithinKStops {
//    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int K) {
//        int[][] adj = new int[n][n];
//        for (int i = 0; i < n; i++) {
//            Arrays.fill(adj[i], -1);
//        }
//        for (int[] flight : flights) {
//            adj[flight[0]][flight[1]] = flight[2];
//        }
//        int[] cost = new int[n];
//        int min = -1;
//        Arrays.fill(cost, -1);
//        cost[src] = 0;
//
//        for (int k = 0; k <= K; k++) {
//            int[] costNew = new int[n];
//            Arrays.fill(costNew, -1);
//            for (int v = 0; v < n; v++) {
//                if (v != src) {
//                    for (int u = 0; u < n; u++) {
//                        if (u != v) {
//                            if (cost[u] != -1 && adj[u][v] != -1) {
//                                if (costNew[v] == -1 || costNew[v] > cost[u] + adj[u][v]) {
//                                    costNew[v] = cost[u] + adj[u][v];
//                                }
//                            }
//                        }
//                    }
//                }
//            }
//            if (min == -1 || (costNew[dst] != -1 && costNew[dst] < min)) {
//                min = costNew[dst];
//            }
//            cost = costNew;
//        }
//
//        return min;
//    }

    private class City implements Comparable<City>{
        int id;
        int costFromSrc;
        int stopFromSrc;
        public City(int id, int costFromSrc, int stopFromSrc){
            this.id = id;
            this.costFromSrc = costFromSrc;
            this.stopFromSrc = stopFromSrc;
        }
        public boolean equals(City c){
            if(c instanceof City)
                return this.id == c.id;
            return false;
        }
        public int compareTo(City c){
            return this.costFromSrc - c.costFromSrc;
        }
    }
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int K) {
        int[][] srcToDst = new int[n][n];
        for(int i = 0; i < flights.length; i++)
            srcToDst[flights[i][0]][flights[i][1]] = flights[i][2];

        PriorityQueue<City> minHeap = new PriorityQueue();
        minHeap.offer(new City(src,0,0));

        int[] cost = new int[n];
        Arrays.fill(cost, Integer.MAX_VALUE);
        cost[src] = 0;
        int[] stop = new int[n];
        Arrays.fill(stop, Integer.MAX_VALUE);
        stop[src] = 0;

        while(!minHeap.isEmpty()){
            City curCity = minHeap.poll();
            if(curCity.id == dst) return curCity.costFromSrc;
            if(curCity.stopFromSrc == K + 1) continue;
            int[] nexts = srcToDst[curCity.id];
            for(int i = 0; i < n; i++){
                if(nexts[i] != 0){
                    int newCost = curCity.costFromSrc + nexts[i];
                    int newStop = curCity.stopFromSrc + 1;
                    if(newCost < cost[i]){
                        minHeap.offer(new City(i, newCost, newStop));
                        cost[i] = newCost;
                    }
                    else if(newStop < stop[i]){
                        minHeap.offer(new City(i, newCost, newStop));
                        stop[i] = newStop;
                    }
                }
            }
        }

        return cost[dst] == Integer.MAX_VALUE? -1:cost[dst];
    }

    public static void main(String[] args) {
        CheapestFlightsWithinKStops cheaps = new CheapestFlightsWithinKStops();
        int[][] flights = {{0, 1, 2}, {1, 2, 1}, {2, 0, 10}};
        System.out.println(cheaps.findCheapestPrice(3, flights, 1, 2, 1));
    }
}
