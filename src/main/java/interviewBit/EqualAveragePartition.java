package interviewBit;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 * Created by adarsh.sharma on 24/07/18.
 */
public class EqualAveragePartition {
    public ArrayList<ArrayList<Integer>> avgset(ArrayList<Integer> A) {
        int n = A.size();
        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum += A.get(i);
        }

        if (n < 2) {
            return new ArrayList<>();
        }

        Collections.sort(A);
        int num = sum / n;
        int den = 1;
        if (sum % n != 0) {
            int gcd = gcd(sum, n);
            num = sum / gcd;
            den = n / gcd;
        }

        boolean[][][] M = new boolean[n / 2 + 1][sum + 1][n];
        int[][][] prev = new int[n / 2 + 1][sum + 1][n + 1];

        for (int idx = n - 1; idx >= 0; idx--) {
            for (int j = idx; j >= 0; j--) {
                M[1][A.get(idx)][j] = true;
                if (prev[1][A.get(idx)][j] == 0) {
                    if (idx == j) {
                        prev[1][A.get(idx)][j] = j + 1;
                    } else {
                        prev[1][A.get(idx)][j] = idx + 1;
                    }
                }
            }
        }

        for (int i = 2; i <= n / 2; i++) {
            for (int s = 1; s <= sum; s++) {
                for (int idx = n - 1; idx >= 0; idx--) {
                    if (get(M, i - 1, s - A.get(idx), idx + 1, n)) {
                        M[i][s][idx] = true;
                        prev[i][s][idx] = idx + 1;
                    } else if (get(M, i, s, idx + 1, n)) {
                        M[i][s][idx] = true;
                        prev[i][s][idx] = prev[i][s][idx + 1];
                    }
                }
            }
        }

        ArrayList<ArrayList<Integer>> res = new ArrayList<>();
        for (int i = den; i <= n / 2; i += den) {
            if (M[i][(i * num) / den][0]) {
                ArrayList<Integer> first = new ArrayList<>();
                ArrayList<Integer> second = new ArrayList<>();
                int s1 = (i * num) / den;
                int k1 = i;
                int start = 0;

                boolean[] idx = new boolean[n];
                while (k1 > 0) {
                    first.add(A.get(prev[k1][s1][start] - 1));
                    idx[prev[k1][s1][start] - 1] = true;
                    s1 -= A.get(prev[k1][s1][start] - 1);
                    k1--;
                    start = prev[k1][s1][start] - 1;
                }

                for (int j = 0; j < n; j++) {
                    if (!idx[j]) {
                        second.add(A.get(j));
                    }
                }

                res.add(first);
                res.add(second);
                return res;
            }
        }


        return res;
    }

    private int gcd(int a, int b) {
        while (a % b != 0) {
            int n = a % b;
            a = b;
            b = n;
        }
        return b;
    }

    private boolean get(boolean[][][] M, int count, int sum, int idx, int n) {
        if (idx >= n) {
            return count == 0 && sum == 0;
        }

        if (count == 0) {
            return sum == 0;
        }

        if (sum < 0) {
            return false;
        } else if (sum == 0) {
            return count > 0 && count <= n;
        }

        return M[count][sum][idx];
    }

    public static void main(String[] args) {
        ArrayList<Integer> a = new ArrayList<>();
//        a.addAll(Arrays.asList(1, 7, 15, 29, 11, 9));
//        a.addAll(Arrays.asList(4, 21));
//        a.addAll(Arrays.asList(4, 21));
//        a.addAll(Arrays.asList(8, 3, 5, 6));
//        a.addAll(Arrays.asList(1, 2, 3, 4, 5, 6, 1, 3, 6, 1, 2, 6));
//        a.addAll(Arrays.asList(47, 14, 30, 19, 30, 4, 32, 32, 15, 2, 6, 24));
//        a.addAll(Arrays.asList(1, 3, 3, 5));
        a.addAll(Arrays.asList(1, 1, 2, 5, 9, 10, 13, 13, 16, 18, 24, 28, 28, 31, 31, 32, 33, 39, 39, 40, 44, 46, 46, 48));
        System.out.println(new EqualAveragePartition().avgset(a));
    }
}
