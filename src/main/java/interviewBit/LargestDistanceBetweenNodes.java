package interviewBit;

import java.util.*;

/**
 * Created by adarsh.sharma on 12/07/18.
 */
public class LargestDistanceBetweenNodes {
    public int solve(List<Integer> A) {

        Set<Integer> ready = new HashSet<>();
        boolean[] processed = new boolean[A.size()];
        int[] children = new int[A.size()];
        for (int i = 0; i < A.size(); i++) {
            if (A.get(i) != -1) {
                children[A.get(i)]++;
                ready.add(i);
            } else {
                processed[i] = true;
            }
        }

        for (int i = 0; i < A.size(); i++) {
            if (A.get(i) != -1 && ready.contains(A.get(i))) {
                ready.remove(A.get(i));
            }
        }

        ArrayList<Integer> readyList = new ArrayList<>();
        readyList.addAll(ready);

        int max = 0;
        int[] v = new int[A.size()];

        while (readyList.size() > 0) {
            Integer next = readyList.get(0);
            readyList.remove(0);
            processed[next] = true;
            Integer p = A.get(next);
            max = Math.max(max, v[next] + 1 + v[p]);
            v[p] = Math.max(v[p], v[next] + 1);
            children[p]--;
            if (!processed[p] && children[p] == 0) {
                readyList.add(p);
            }
        }

        return max;
    }

    public int solve2(List<Integer> A) {
        int[][] arr = new int[A.size()][2];
        int maxDistance = 0;
        for (int i = A.size() - 1; i > 0; --i) {
            int element = A.get(i);
            int[] parent = arr[element];
            int currentLength = arr[i][0] + 1;

            parent[1] = Math.max(parent[1], currentLength + parent[0]);
            parent[0] = Math.max(parent[0], currentLength);
            maxDistance = Math.max(maxDistance, parent[1]);
        }
        return maxDistance;
    }

    public static void main(String[] args) {
//        System.out.println(new LargestDistanceBetweenNodes().solve(Arrays.asList(-1)));
//        System.out.println(new LargestDistanceBetweenNodes().solve2(Arrays.asList(-1)));
        System.out.println(new LargestDistanceBetweenNodes().solve(Arrays.asList(-1, 0, 0, 2, 2, 2, 5)));
        System.out.println(new LargestDistanceBetweenNodes().solve2(Arrays.asList(-1, 0, 0, 2, 2, 2, 5)));
//        System.out.println(new LargestDistanceBetweenNodes().solve(Arrays.asList(-1, 0, 0, 0, 3)));
//        System.out.println(new LargestDistanceBetweenNodes().solve2(Arrays.asList(-1, 0, 0, 0, 3)));
    }
}
