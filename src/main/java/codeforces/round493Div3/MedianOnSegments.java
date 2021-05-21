package codeforces.round493Div3;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Created by adarsh.sharma on 10/07/18.
 */
public class MedianOnSegments {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] A = new int[n];
        int m = scanner.nextInt();
        int midx = -1;

        for (int i = 0; i < n; i++) {
            A[i] = scanner.nextInt();
            if (A[i] == m) {
                midx = i;
            }
        }

        Map<Integer, Integer> bigSmallDiffCount = new HashMap<>();
        bigSmallDiffCount.put(0, 1);
        int diff = 0;
        for (int i = midx + 1; i < n; i++) {
            if (A[i] > m) {
                diff += 1;
            } else {
                diff--;
            }
            bigSmallDiffCount.merge(diff, 1, Integer::sum);
        }

        long count = 0;
        count+=bigSmallDiffCount.getOrDefault(0, 0);
        count+=bigSmallDiffCount.getOrDefault(1, 0);
        diff = 0;
        for (int i = midx - 1; i >= 0; i--) {
            if (A[i] > m) {
                diff += 1;
            } else {
                diff--;
            }
            count+=bigSmallDiffCount.getOrDefault(1 - diff, 0);
            count+=bigSmallDiffCount.getOrDefault(-diff, 0);
        }

        System.out.println(count);
    }
}
