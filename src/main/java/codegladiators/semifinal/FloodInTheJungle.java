package codegladiators.semifinal;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * Created by adarsh.sharma on 21/05/18.
 */
public class FloodInTheJungle {
    public static void main(String args[]) throws Exception {
        Scanner in = new Scanner(System.in);
//        Scanner in = new Scanner(new FileInputStream("/Users/adarsh.sharma/code/test/src/main/resources/floodInTheJungle.in"));

        int N = in.nextInt();
        int V = 2 * N + 1;
        double C = in.nextDouble();
        int[] x = new int[N];
        int[] y = new int[N];
        int[] m = new int[N];
        int[] t = new int[N];
        int totalFlowRequired = 0;
        for (int i = 0; i < N; i++) {
            x[i] = in.nextInt();
            y[i] = in.nextInt();
            m[i] = in.nextInt();
            t[i] = in.nextInt();
            totalFlowRequired += m[i];
        }

        int[][] graph = new int[V][V];
        for (int i = 0; i < V; i++) {
            for (int j = 0; j < V; j++) {
                graph[i][j] = 0;
            }
        }

        for (int i = 0; i < N; i++) {
            graph[2 * N][2 * i] = m[i];
            graph[2 * i][2 * i + 1] = t[i];
//            graph[2 * i + 1][2 * i] = t[i];

            for (int j = 0; j < N; j++) {
                if (j != i && reachable(x[i], y[i], x[j], y[j], C)) {
                    graph[2 * i + 1][2 * j] = Integer.MAX_VALUE;
                }
            }
        }

        List<Integer> possibleTrees = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            int flow = fordFulkerson(graph, V - 1, 2 * i, V);
            if (flow == totalFlowRequired) {
                possibleTrees.add(i);
            }
        }
        if (possibleTrees.size() == 0) {
            System.out.println(-1);
        } else {
            System.out.println(possibleTrees.stream().map(String::valueOf)
                    .collect(Collectors.joining(" ")));
        }

    }

    private static boolean reachable(int x1, int y1, int x2, int y2, double c) {
        return c >= Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
    }

    private static int fordFulkerson(int graph[][], int s, int t, int V) {
        int u, v;
        int rGraph[][] = new int[V][V];

        for (u = 0; u < V; u++)
            for (v = 0; v < V; v++)
                rGraph[u][v] = graph[u][v];

        int parent[] = new int[V];

        int max_flow = 0;

        while (bfs(rGraph, s, t, parent, V)) {
            int path_flow = Integer.MAX_VALUE;
            for (v = t; v != s; v = parent[v]) {
                u = parent[v];
                path_flow = Math.min(path_flow, rGraph[u][v]);
            }

            for (v = t; v != s; v = parent[v]) {
                u = parent[v];
                rGraph[u][v] -= path_flow;
                rGraph[v][u] += path_flow;
            }

            max_flow += path_flow;
        }

        return max_flow;
    }

    private static boolean bfs(int rGraph[][], int s, int t, int parent[], int V) {
        boolean visited[] = new boolean[V];
        for (int i = 0; i < V; ++i)
            visited[i] = false;

        LinkedList<Integer> queue = new LinkedList<>();
        queue.add(s);
        visited[s] = true;
        parent[s] = -1;

        while (queue.size() != 0) {
            int u = queue.poll();

            for (int v = 0; v < V; v++) {
                if (!visited[v] && rGraph[u][v] > 0) {
                    queue.add(v);
                    parent[v] = u;
                    visited[v] = true;
                }
            }
        }

        return (visited[t]);
    }

}
