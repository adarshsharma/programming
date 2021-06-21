package leetcode;

import java.util.Arrays;

public class TheEarliestAndLatestRounds5787 {

    int[][][][] mp;

    public int[] earliestAndLatest(int n, int firstPlayer, int secondPlayer) {
        mp = new int[28][28][28][2];
        int earliest = get(firstPlayer - 1, secondPlayer - firstPlayer - 1,
            n - secondPlayer, true, 0);
        int[] res = new int[2];
        res[0] = earliest;
        int latest = get(firstPlayer - 1, secondPlayer - firstPlayer - 1, n - secondPlayer,
            false, 0);
        res[1] = latest;
        return res;
    }

    private int get(int left, int center, int right, boolean earliest, int round) {
        int idx = earliest ? 0 : 1;
        if (mp[left][center][right][idx] != 0) {
            return mp[left][center][right][idx];
        }

        if (left == right) {
            mp[left][center][right][idx] = 1;
            mp[right][center][left][idx] = 1;
        } else {
            int total = left + center + right + 2;
            int removals = total / 2;
            int maxRounds = 1;
            int minRounds = 28;
            for (int i = 0; i <= Math.min(left, removals); i++) {
                for (int j = 0; j <= Math.min(right, removals); j++) {
                    if (i + j >= Math.min(left, right) && i + j <= Math.max(Math.max(left, right),
                        removals) && center - (removals - i - j) >= 0) {
                        if (earliest) {
                            minRounds = Math.min(
                                get(left - i, center - (removals - i - j), right - j,
                                    earliest, round + 1),
                                minRounds);
                        } else {
                            maxRounds = Math.max(
                                get(left - i, center - (removals - i - j), right - j,
                                    earliest, round + 1),
                                maxRounds);
                        }
                    }

                }
            }
            mp[left][center][right][idx] = 1+ (earliest ? minRounds : maxRounds);
            mp[right][center][left][idx] = 1+ (earliest ? minRounds : maxRounds);
        }

        return mp[left][center][right][idx];
    }

    public static void main(String[] args) {
        int[] res = new TheEarliestAndLatestRounds5787().earliestAndLatest(5, 1, 4);
        Arrays.stream(res).forEach(System.out::println);
    }

}
