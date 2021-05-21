package leetcode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by adarsh.sharma on 14/09/18.
 */
public class NumberOfIslandsII {
    class DisjointSet {
        int[] roots;

        public DisjointSet(int n) {
            this.roots = new int[n];
            for (int i = 0; i < n; i++) {
                this.roots[i] = i;
            }
        }

        public void union(int value1, int value2) {
            if (roots[value1] != roots[value2]) {
                roots[findSet(value2)] = value1;
            }
        }

        public int findSet(int value) {
            while (value != roots[value]) {
                roots[value] = roots[roots[value]];
                value = roots[value];
            }
            return value;
        }
    }

    public List<Integer> numIslands2(int m, int n, int[][] positions) {
        DisjointSet disjointSet = new DisjointSet(m * n);
        Set<Integer> land = new HashSet<>();
        List<Integer> result = new ArrayList<>();

        int[] dirx = {1, -1, 0, 0};
        int[] diry = {0, 0, 1, -1};

        int prev = 0;
        for (int[] pos : positions) {
            int count = 0;
            int cur = n * pos[0] + pos[1];
            if (land.contains(cur)) {
                result.add(prev);
            } else {
                land.add(cur);
                for (int i = 0; i < dirx.length; i++) {
                    int x = pos[0] + dirx[i];
                    int y = pos[1] + diry[i];
                    int neig = n * x + y;
                    if (x >= 0 && x < m && y>=0 && y<n && land.contains(neig)) {
                        if (disjointSet.findSet(cur) != disjointSet.findSet(neig)) {
                            count++;
                            disjointSet.union(cur, neig);
                        }
                    }
                }
                result.add(prev - count + 1);
                prev = prev - count + 1;
            }
        }

        return result;
    }

    public static void main(String[] args) {
        NumberOfIslandsII numberOfIslandsII = new NumberOfIslandsII();
        int[][] positions = {{0, 1}, {1, 2}, {2, 1}, {1, 0}, {0, 2}, {0, 0}, {1, 1}};
        System.out.println(numberOfIslandsII.numIslands2(3, 3, positions));
    }
}
