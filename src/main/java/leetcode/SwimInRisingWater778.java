package leetcode;

/**
 * Created by adarsh.sharma on 29/06/18.
 */
public class SwimInRisingWater778 {

    private int minDistance(int V, int dist[], Boolean sptSet[]) {
        // Initialize min value
        int min = Integer.MAX_VALUE, min_index = -1;

        for (int v = 0; v < V; v++)
            if (!sptSet[v] && dist[v] <= min) {
                min = dist[v];
                min_index = v;
            }

        return min_index;
    }

    // Function that implements Dijkstra's single source shortest path
    // algorithm for a graph represented using adjacency matrix
    // representation
    private int dijkstra(int V, int graph[][], int src, int dst) {
        int dist[] = new int[V]; // The output array. dist[i] will hold
        // the shortest distance from src to i

        // sptSet[i] will true if vertex i is included in shortest
        // path tree or shortest distance from src to i is finalized
        Boolean sptSet[] = new Boolean[V];

        // Initialize all distances as INFINITE and stpSet[] as false
        for (int i = 0; i < V; i++) {
            dist[i] = Integer.MAX_VALUE;
            sptSet[i] = false;
        }

        // Distance of source vertex from itself is always 0
        dist[src] = 0;

        // Find shortest path for all vertices
        for (int count = 0; count < V; count++) {
            // Pick the minimum distance vertex from the set of vertices
            // not yet processed. u is always equal to src in first
            // iteration.
            int u = minDistance(V, dist, sptSet);

            if (u == dst) {
                return dist[u];
            }
            // Mark the picked vertex as processed
            sptSet[u] = true;

            // Update dist value of the adjacent vertices of the
            // picked vertex.
            for (int v = 0; v < V; v++)

                // Update dist[v] only if is not in sptSet, there is an
                // edge from u to v, and total weight of path from src to
                // v through u is smaller than current value of dist[v]
                if (!sptSet[v] && graph[u][v] != -1 &&
                        dist[u] != Integer.MAX_VALUE &&
                        Math.max(dist[u], graph[u][v]) < dist[v])
                    dist[v] = Math.max(dist[u], graph[u][v]);
        }

        throw new IllegalArgumentException();
    }

    public int swimInWater(int[][] grid) {
        int N = grid.length;
        int V = N * N;
        int[][] adj = new int[V][V];
        for (int i = 0; i < V; i++) {
            for (int j = 0; j < V; j++) {
                adj[i][j] = -1;
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                int cur = i * N + j;
                if (j > 0) {
                    int left = cur - 1;
                    adj[cur][left] = Math.max(grid[i][j - 1], grid[i][j]);
                }
                if (j < N - 1) {
                    int right = cur + 1;
                    adj[cur][right] = Math.max(grid[i][j + 1],grid[i][j]);
                }
                if (i > 0) {
                    int up = cur - N;
                    adj[cur][up] = Math.max(grid[i - 1][j], grid[i][j]);
                }
                if (i < N - 1) {
                    int down = cur + N;
                    adj[cur][down] = Math.max(grid[i + 1][j], grid[i][j]);
                }

            }
        }

        return dijkstra(V, adj, 0, (N * N - 1));
    }

    public static void main(String[] args) {
        SwimInRisingWater778 s = new SwimInRisingWater778();
        int[][] grid = {{0, 1, 2, 3, 4}, {24, 23, 22, 21, 5}, {12, 13, 14, 15, 16}, {11, 17, 18, 19, 20}, {10, 9, 8, 7, 6}};
//        int[][] grid = {{3, 2},
//                        {0, 1}};
        System.out.println(s.swimInWater(grid));
    }
}
