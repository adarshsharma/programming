package leetcode;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class MinimumSideWaysJump1824 {

    public int minSideJumps(int[] obstacles) {
        int[] prev = new int[3];
        prev[0] = 1;
        prev[2] = 1;

        for (int i = 1; i < obstacles.length; i++) {
            int obstacle = obstacles[i] - 1;
            Set<Integer> finalSet = new HashSet<>();

            int[] cur = new int[3];
            int min = -1;
            for (int j = 0; j < 3; j++) {
                if (j == obstacle) {
                    finalSet.add(j);
                    cur[j] = -1;
                } else {
                    cur[j] = prev[j];
                    if (min == -1 || cur[min] > cur[j]) {
                        min = j;
                    }
                }
            }

            while (finalSet.size() < 3) {
                int newMin = -1;
                for (int j = 0; j < 3; j++) {
                    if (cur[j] != -1 && j != min && cur[min] < cur[j]) {
                        cur[j] = cur[min] + 1;
                        if (newMin == -1 || cur[newMin] > cur[j]) {
                            newMin = j;
                        }
                    }
                }
                finalSet.add(min);
                min = newMin;
            }
            prev = cur;
        }

        return Arrays.stream(prev).min().getAsInt();
    }

    public static void main(String[] args) {
        int[] obstacles = {0, 1, 2, 3, 0};
        MinimumSideWaysJump1824 obj = new MinimumSideWaysJump1824();
        System.out.println(obj.minSideJumps(obstacles));
    }

}
