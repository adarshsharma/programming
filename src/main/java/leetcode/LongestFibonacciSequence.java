package leetcode;

/**
 * Created by adarsh.sharma on 22/07/18.
 */
public class LongestFibonacciSequence {
    public int lenLongestFibSubseq(int[] A) {
        int n = A.length;
        int[] prev = new int[n];
        int[] len = new int[n];
        int maxLen = Integer.MIN_VALUE;

        prev[0] = -1;
        prev[1] = -1;

        for (int i = 2; i < n; i++) {
            int s = 0;
            int e = i - 1;
            int l = 0;

            while (s < e) {
                if (A[s] + A[e] < A[i]) {
                    s++;
                } else if (A[s] + A[e] > A[i]) {
                    e--;
                } else {
                    if (prev[e] == s) {
                        if (len[e] + 1 > l) {
                            l = len[e] + 1;
                            prev[i] = e;
                        }
                    } else {
                        int count = getCount(A, s, e, i);
                        if (3 + count > l) {
                            l = 3 + count;
                            prev[i] = e;
                        }
                    }
                    s++;
                    e--;
                }
            }
            len[i] = l;
            maxLen = Math.max(maxLen, l);
        }
        return maxLen;
    }

    private int getCount(int[] A, int s, int e, int i) {
        if (s < 0) {
            return 0;
        }

        int idx = binarySearch(A, A[e] - A[s], s - 1);
        if (idx == -1) {
            return 0;
        }

        return 1 + getCount(A, idx, s, e);
    }

    private int binarySearch(int[] A, int v, int e) {
        int s = 0;
        while (s <= e) {
            int m = (s + e) / 2;
            if (A[m] == v) {
                return m;
            }

            if (A[m] < v) {
                s++;
            } else {
                e--;
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        int[] A = {1, 2, 3, 4, 5, 7, 8, 9, 10, 12, 17, 19, 27, 29};
        System.out.println(new LongestFibonacciSequence().lenLongestFibSubseq(A));
    }
}
