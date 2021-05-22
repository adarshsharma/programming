package codeforces.round503Div2;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Scanner;

/**
 * Created by adarsh.sharma on 12/08/18.
 */
public class Elections {
    public static void main(String[] args) throws FileNotFoundException {
//        Scanner sc = new Scanner(System.in);
        Scanner sc = new Scanner(new BufferedReader(new FileReader("/Users/adarsh.sharma/code/programming/src/main/resources/elections.in")));
        int n = sc.nextInt();
        int m = sc.nextInt();

        if (m == 1) {
            System.out.println(0);
        } else {
            Map<Integer, List<Long>> mp = new HashMap<>();
            int got = 0;
            for (int i = 0; i < n; i++) {
                int candidate = sc.nextInt();
                long cost = sc.nextInt();
                if (candidate == 1) {
                    got++;
                } else {
                    List<Long> costs = mp.computeIfAbsent(candidate, k -> new ArrayList<>());
                    costs.add(cost);
                }
            }

            for (int key : mp.keySet()) {
                Collections.sort(mp.get(key));
            }

            int minR = (n - 1) % m == 0 ? (n - 1) / m + 1 : (n - 1) / m + 2;
            int maxR = n / 2 + 1;

            if(got >= maxR) {
                System.out.println(0);
                return;
            }

            long minCost = Long.MAX_VALUE;
            for (int win = Math.max(got, minR); win <= maxR; win++) {
                long cost = 0;
                int required = win - got;
                int collected = 0;
                PriorityQueue<Long> pq = new PriorityQueue<>();

                for (int key : mp.keySet()) {
                    List<Long> costs = mp.get(key);
                    int i = 0;
                    while (costs.size() - i >= win) {
                        cost += costs.get(i);
                        collected++;
                        i++;
                    }

                    while (i < costs.size()) {
                        pq.add(costs.get(i));
                        i++;
                    }
                }

                if (collected <= required) {
                    while (collected < required) {
                        cost += pq.poll();
                        collected++;
                    }

                    minCost = Math.min(cost, minCost);
                }
            }

            System.out.println(minCost);
        }
    }
}
