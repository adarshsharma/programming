package leetcode;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class MinimumSpaceWasted5779 {

    public int minWastedSpace(int[] packages, int[][] boxes) {
        Arrays.sort(packages);
        int n = packages.length;
        long mod = 1000000007;

        Set<Integer> invalid = new HashSet<>();
        for (int i = 0; i < boxes.length; i++) {
            Arrays.sort(boxes[i]);
            if (boxes[i][boxes[i].length - 1] < packages[n - 1]) {
                invalid.add(i);
            }
        }

        int[] diff = new int[n];
        int[] ssum = new int[n];

        for (int i = n - 2; i >= 0; i--) {
            diff[i] = packages[n - 1] - packages[i];
            ssum[i] = diff[i] + diff[i + 1];
        }

        long min = Long.MAX_VALUE;

        for (int i = 0; i < boxes.length; i++) {
            long space = 0L;
            if (!invalid.contains(i)) {
                int[] box = boxes[i];
                if (boxes.length == 1) {
                    space = (space + ssum[0] + packages.length * (box[0] - packages[n - 1])) % mod;
                } else {
                    int j = box.length - 2;
                    int prev = n - 1;
                    while (j >= -1) {
                        int idx;
                        if (j == -1) {
                            idx = 0;
                        } else {
                            idx = findFirst(packages, 0, prev + 1, box[j] + 1);
                        }
                        if (idx < 0) {
                            idx = -(idx + 1);
                        }
                        space =
                            (space + ((prev - idx + 1) * (box[j + 1] - packages[prev]) % mod))
                                % mod;
                        space = (space + ssum[idx] - (prev == n - 1 ? 0 : ssum[prev])) % mod;
                        space =
                            (space - (prev - idx + 1) * (packages[n - 1] - packages[prev + 1]))
                                % mod;
                        prev = idx - 1;
                        j--;
                    }
                }
                min = Math.min(min, space);
            }
        }

        if (invalid.size() > 0) {
            return Long.valueOf(min).intValue();
        }
        return -1;
    }


    private int findFirst(int[] nums, int s, int e, int num) {
        int first = Arrays.binarySearch(nums, s, e, num);
        int found = first;
        e = found;
        while (found >= 0) {
            found = Arrays.binarySearch(nums, s, e, num);
            if (found >= 0) {
                first = found;
                e = found;
            }
        }
        return first;
    }

    public static void main(String[] args) {
        // int[] packages = {3, 5, 8, 10, 11, 12};
        // int[][] boxes = {{12}, {11, 9}, {10, 5, 14}};
        int[] packages = {2, 3, 5};
        int[][] boxes = {{4, 8}, {2, 8}};
        System.out.println(new MinimumSpaceWasted5779().minWastedSpace(packages, boxes));
    }

}
