package practice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

public class Test {

    static void computeIfPresent() {
        Map<Integer, Integer> mp = new HashMap<>();
        mp.merge(2, 2, Integer::sum);
        System.out.println(mp);
        mp.computeIfPresent(2, removeIfLast());
        System.out.println(mp);
        mp.computeIfPresent(2, removeIfLast());
        System.out.println(mp);
    }

    static int getFirstOccurance(int[] nums, int s, int e, int num) {

        int first = Arrays.binarySearch(nums, s, e, num);
        int found = first;
        while (found >= 0) {
            found = Arrays.binarySearch(nums, s, e, num);
            if (found >= 0) {
                first = found;
                e = found;
            }
        }
        return first;
    }

    private static BiFunction<Integer, Integer, Integer> removeIfLast() {
        return (a, b) -> b == 1 ? null : b - 1;
    }

    public static int solve1(int A) {
        int count = 0;
        count += 2 * (A / 6);
        if (A % 6 != 0) {
            count = count + (A % 6 == 1 ? 1 : 2);
        }
        return count;
    }

    public static int solve2(String A, String B, int C) {
        int n = A.length();
        int ans = 0;
        int diff = 0;
        for (int i = 0; i < n; i++) {
            if (A.charAt(i) != B.charAt(i)) {
                diff++;
            }
        }

        ans = diff;

        int[][] dp = new int[n][n];
        for (int i = 0; i < n; i++) {
            dp[i][i] = diff + C;
        }

        for (int l = 2; l <= n; l++) {
            for (int i = 0; i <= n - l; i++) {
                if (l == 2) {
                    dp[i][i + l - 1] = diff + C;
                } else {
                    dp[i][i + l - 1] = dp[i + 1][i + l - 2];
                }
                if (A.charAt(i) != B.charAt(i)) {
                    dp[i][i + l - 1]--;
                }

                if (A.charAt(i + l - 1) != B.charAt(i + l - 1)) {
                    dp[i][i + l - 1]--;
                }

                if (A.charAt(i) != B.charAt(i + l - 1)) {
                    dp[i][i + l - 1]++;
                }

                if (A.charAt(i + l - 1) != B.charAt(i)) {
                    dp[i][i + l - 1]++;
                }

                ans = Math.min(ans, dp[i][i + l - 1]);
            }
        }

        return ans;

    }

    public static int[] solve4(int A) {
        int lastSet = -1;
        for (int i = 0; i < 32; i++) {
            if ((A & (1 << i)) > 0) {
                lastSet = i;
            }
        }

        int[] res = new int[2];
        res[1] = A;
        for (int i = 0; i <= lastSet; i++) {
            if ((A & (1 << i)) == 0) {
                res[0] += (1 << i);
            }
        }
        if (res[0] == 0) {
            res[0] = 1;
            res[1] -= 1;
            if(res[1] == 0) {
                res[1] = 1;
            }
        }

        return res;
    }

    public static void main(String[] args) {
        List<Integer> l1 = new ArrayList<>();
        List<List<Integer>> ll1 = new ArrayList<>();
        l1.add(1);
        l1.add(2);
        ll1.add(new ArrayList<>(l1));
        System.out.println(ll1);
        l1.set(1, 3);
        ll1.add(new ArrayList<>(l1));
        System.out.println(ll1);


        // int[] nums = {2, 5, 5, 5, 5, 5, 5, 7};
        // System.out.println(getFirstOccurance(nums, 0, 7, 5));
        // System.out.println(solve(1));
        // System.out.println(solve2("abceda", "bdecbo", 1));
        // System.out.println(solve2("abcdeefghi", "edcbaihgfe", 2));
        // int[] a = {-2, 1, 3};
        // int i = Arrays.binarySearch(a, 1, 3, -4);
        // System.out.println(i);
        // int[] res = solve4(90);
        // System.out.println(res[0] + " " + res[1]);
        // int[] test = {3, 5, 6, 7};
        // System.out.println(Arrays.binarySearch(test, 2));

        // StringBuilder sb = new StringBuilder();
        // sb.append("ab");
        // System.out.println(sb.toString());
        // sb.append("cd");
        // System.out.println(sb.toString());
        // System.out.println(sb.length() );

        // String st = "content://a/b/c/213";
        // System.out.println(st.substring(st.lastIndexOf("/")+1));
    }

}
