package leetcode;

import java.util.Arrays;

public class MinimumSideWaysJump1824 {

    public int minSideJumps(int[] obstacles) {
        int[] prev = new int[3];
        prev[0] = 1;
        prev[2] = 1;

        for (int i = 1; i < obstacles.length; i++) {
            int obstacle = obstacles[i] - 1;

            int[] cur = new int[3];
            int min = -1;
            for (int j = 0; j < 3; j++) {
                if (j == obstacle) {
                    cur[j] = -1;
                } else {
                    cur[j] = prev[j] == -1 ? Integer.MAX_VALUE : prev[j];
                    if (min == -1 || cur[min] > cur[j]) {
                        min = j;
                    }
                }
            }

            for (int j = 0; j < 3; j++) {
                if (j != min && j != obstacle) {
                    cur[j] = Math.min(cur[min] + 1, cur[j]);
                }
            }

            prev = cur;
        }

        return Arrays.stream(prev).min().getAsInt();
    }

    public static void main(String[] args) {
        int[] obstacles = {0, 2, 1, 0, 3, 0};
        MinimumSideWaysJump1824 obj = new MinimumSideWaysJump1824();
        System.out.println(obj.minSideJumps(obstacles));
    }

}
