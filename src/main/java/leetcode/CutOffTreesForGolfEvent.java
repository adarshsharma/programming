package leetcode;

import java.util.*;

/**
 * Created by adarsh.sharma on 01/09/18.
 */
public class CutOffTreesForGolfEvent {
    int[] dr = {-1, 1, 0, 0};
    int[] dc = {0, 0, -1, 1};

    public int cutOffTree(List<List<Integer>> forest) {
        List<int[]> trees = new ArrayList<>();
        for (int r = 0; r < forest.size(); ++r) {
            for (int c = 0; c < forest.get(0).size(); ++c) {
                int v = forest.get(r).get(c);
                if (v > 1) trees.add(new int[]{v, r, c});
            }
        }

        Collections.sort(trees, Comparator.comparingInt(a -> a[0]));

        int ans = 0, sr = 0, sc = 0;
        for (int[] tree: trees) {
            int d = aStar(forest, sr, sc, tree[1], tree[2]);
            if (d < 0) return -1;
            ans += d;
            sr = tree[1]; sc = tree[2];
        }
        return ans;
    }

    public int hadlocks(List<List<Integer>> forest, int sr, int sc, int tr, int tc) {
        int R = forest.size(), C = forest.get(0).size();
        Set<Integer> processed = new HashSet<>();
        Deque<int[]> deque = new ArrayDeque<>();
        deque.offerFirst(new int[]{0, sr, sc});
        while (!deque.isEmpty()) {
            int[] cur = deque.pollFirst();
            int detours = cur[0], r = cur[1], c = cur[2];
            if (!processed.contains(r*C + c)) {
                processed.add(r*C + c);
                if (r == tr && c == tc) {
                    return Math.abs(sr-tr) + Math.abs(sc-tc) + 2 * detours;
                }
                for (int di = 0; di < 4; ++di) {
                    int nr = r + dr[di];
                    int nc = c + dc[di];
                    boolean closer;
                    if (di <= 1) {
                        closer = di == 0 ? r > tr : r < tr;
                    } else {
                        closer = di == 2 ? c > tc : c < tc;
                    }
                    if (0 <= nr && nr < R && 0 <= nc && nc < C && forest.get(nr).get(nc) > 0) {
                        if (closer) {
                            deque.offerFirst(new int[]{detours, nr, nc});
                        } else {
                            deque.offerLast(new int[]{detours+1, nr, nc});
                        }
                    }
                }
            }
        }
        return -1;
    }

    public int aStar(List<List<Integer>> forest, int sr, int sc, int tr, int tc) {
        int R = forest.size(), C = forest.get(0).size();
        PriorityQueue<int[]> heap = new PriorityQueue<int[]>(Comparator.comparingInt(a -> a[0]));
        heap.offer(new int[]{0, 0, sr, sc});

        HashMap<Integer, Integer> cost = new HashMap<>();
        cost.put(sr * C + sc, 0);

        while (!heap.isEmpty()) {
            int[] cur = heap.poll();
            int g = cur[1], r = cur[2], c = cur[3];
            if (r == tr && c == tc) return g;
            for (int di = 0; di < 4; ++di) {
                int nr = r + dr[di], nc = c + dc[di];
                if (0 <= nr && nr < R && 0 <= nc && nc < C && forest.get(nr).get(nc) > 0) {
                    int ncost = g + 1 + Math.abs(nr-tr) + Math.abs(nc-tc);
                    if (!cost.containsKey(nr * C + nc) || ncost < cost.get(nr * C + nc)) {
                        cost.put(nr * C + nc, ncost);
                        heap.offer(new int[]{ncost, g+1, nr, nc});
                    }
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        CutOffTreesForGolfEvent cut = new CutOffTreesForGolfEvent();
        List<List<Integer>> forest = new ArrayList<>();
        forest.add(Arrays.asList(1,2,3));
        forest.add(Arrays.asList(0,0,4));
        forest.add(Arrays.asList(7,6,5));
        System.out.println(cut.cutOffTree(forest));
    }
}
