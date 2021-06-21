package leetcode;

import java.util.Arrays;

public class MovingStonesUntilConsecutive1040 {

    public int[] numMovesStonesII(int[] stones) {
        Arrays.sort(stones);
        int count = stones.length;
        int max = Integer.MIN_VALUE;
        int start = 0;
        int end = 1;
        while (start < count && end < count) {
            if (stones[end] - stones[start] + 1 > count) {
                max = Math.max(max, end - start);
                while (stones[end] - stones[start] + 1 > count && start <= end) {
                    start++;
                }
            } else {
                end++;
            }
        }

        if (stones[end - 1] - stones[start] + 1 == count) {
            max = Math.max(max, end - start);
        }

        int gap = Integer.MAX_VALUE;
        if (max == count - 1) {
            for (int i = 1; i < count; i++) {
                if (stones[i] - stones[i - 1] != 1) {
                    gap = Integer.min(gap, stones[i] - stones[i - 1]);
                }
            }

            if (gap != 2) {
                max--;
            }
        }

        int[] res = new int[2];
        res[0] = count - max;
        res[1] = 1 + Math.max(stones[count - 1] - stones[1] + 1 - count,
            stones[count - 2] - stones[0] + 1 - count);
        return res;
    }

    public static void main(String[] args) {
        int[] stones = {100, 101, 104, 102, 103};
        int[] x = new MovingStonesUntilConsecutive1040().numMovesStonesII(stones);
        System.out.println(x[0] + " " + x[1]);
    }

}
