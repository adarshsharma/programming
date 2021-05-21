package leetcode;

import java.util.Arrays;

/**
 * Created by adarsh.sharma on 09/08/18.
 */
public class CreateMaxNumber321Old {
    int m;
    int n;
    int k;
    P[][][] dp;
    int[] nums1;
    int[] nums2;

    class P {
        int num;
        int idx;

        public P(int num, int idx) {
            this.num = num;
            this.idx = idx;
        }
    }

    public int[] maxNumber(int[] nums1, int[] nums2, int k) {
        int[] res = new int[k];
        if (k == 0) {
            return res;
        }
        if (nums1.length < nums2.length) {
            return maxNumber(nums2, nums1, k);
        }

        this.m = nums1.length;
        this.n = nums2.length;
        this.nums1 = nums1;
        this.nums2 = nums2;
        this.k = k;
        this.dp = new P[k + 1][m + 1][n + 1];

        for (int l = 1; l <= k; l++) {
            for (int n1 = 0; n1 <= m; n1++) {
                int i = m - n1;
                for (int n2 = Math.max(0, l - n1); n2 <= n; n2++) {
                    int j = n - n2;
                    dp[l][i][j] = getMax(l, i, j);
                }
            }
        }

        for (int l = 0, i = 0, j = 0; l < k; l++) {
            P p = dp[k - l][i][j];
            if (p.num == 1) {
                res[l] = nums1[p.idx];
                i = p.idx + 1;
            } else {
                res[l] = nums2[p.idx];
                j = p.idx + 1;
            }
        }

        return res;
    }

    private P getMax(int l, int i, int j) {
//        P maxP = i < m ? new P(1, i) : null;
//        P p = j < n ? new P(2, j) : null;
//        if (maxP == null) {
//            maxP = p;
//            p = null;
//        }
//        if (isSame(p, maxP)) {
//            P p1 = getP(l - 1, i + 1, j);
//            P p2 = getP(l - 1, i, j + 1);
//
//            if (isGreater(p2, p1)) {
//                maxP = p;
//            }
//        } else {
//            if (isGreater(p, maxP)) {
//                maxP = p;
//            }
//            p = getP(l, i + 1, j);
//            if (isGreater(p, maxP)) {
//                maxP = p;
//            }
//            p = getP(l, i, j + 1);
//            if (isGreater(p, maxP)) {
//                maxP = p;
//            }
//        }

        P maxP = i < m ? new P(1, i) : null;
        P p = getP(l-1, i+1, j);
        if(isGreater(p, maxP)) {
            maxP = p;
        }

        P maxP2 = j < n ? new P(2, j) : null;
        p = getP(l-1, i, j+1);
        if(isGreater(p, maxP2)) {
            maxP2 = p;
        }

        if(isGreater(maxP2, maxP)) {
            maxP = maxP2;
        }

        p = getP(l, i+1, j+1);
        if(isGreater(p, maxP)) {
            maxP = p;
        }

        return maxP;
    }

    private boolean isSame(P p1, P p2) {
        if (p1 == null) {
            return false;
        } else if (p2 == null) {
            return true;
        }

        int a = p1.num == 1 ? nums1[p1.idx] : nums2[p1.idx];
        int b = p2.num == 1 ? nums1[p2.idx] : nums2[p2.idx];
        return a == b;
    }


    private boolean isGreater(P p1, P p2) {
        if (p1 == null) {
            return false;
        } else if (p2 == null) {
            return true;
        }

        int a = p1.num == 1 ? nums1[p1.idx] : nums2[p1.idx];
        int b = p2.num == 1 ? nums1[p2.idx] : nums2[p2.idx];
        if (a > b) {
            return true;
        } else if (a < b) {
            return false;
        } else {

            return true;
        }
    }

    private P getP(int l, int i, int j) {
        if (l <= 0 || l > k || i < 0 || i > m || j < 0 || j > n) {
            return null;
        }

        return dp[l][i][j];
    }

    public static void main(String[] args) {
        CreateMaxNumber321Old createMaxNumber321Old = new CreateMaxNumber321Old();
//        int[] nums1 = {3, 4, 6, 5};
//        int[] nums2 = {9, 1, 2, 5, 8, 3};
//        int k = 5;
//        int[] nums1 = {6, 7};
//        int[] nums2 = {6, 0, 4};
//        int k = 5;
//        int[] nums1 = {6, 9};
//        int[] nums2 = {6, 8};
//        int k = 4;
        int[] nums1 = {3,4,6,5};
        int[] nums2 = {9,1,2,5,8,3};
        int k = 3;
        int[] res = createMaxNumber321Old.maxNumber(nums1, nums2, k);
        Arrays.stream(res).forEach(System.out::println);
    }
}
