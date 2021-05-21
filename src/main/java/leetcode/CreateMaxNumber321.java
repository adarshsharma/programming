package leetcode;

/**
 * Created by adarsh.sharma on 09/08/18.
 */
public class CreateMaxNumber321 {
//    int m;
//    int n;
//    int k;
//    P[][][] dp;
//    int[] nums1;
//    int[] nums2;
//
//    class P {
//        int num;
//        int idx;
//
//        public P(int num, int idx) {
//            this.num = num;
//            this.idx = idx;
//        }
//    }
//
//    public int[] maxNumber(int[] nums1, int[] nums2, int k) {
//        int[] res = new int[k];
//        if (k == 0) {
//            return res;
//        }
//
//        this.m = nums1.length;
//        this.n = nums2.length;
//        this.nums1 = nums1;
//        this.nums2 = nums2;
//        this.k = k;
//        this.dp = new P[k + 1][m + 1][n + 1];
//
//        for (int l = 1; l <= k; l++) {
//            for (int n1 = 0; n1 <= m; n1++) {
//                int i = m - n1;
//                for (int n2 = Math.max(0, l - n1); n2 <= n; n2++) {
//                    int j = n - n2;
//                    populate(l, i, j);
//
////                    if (j < n) {
////                        if (i < m) {
////                        } else {
////                            if (n2 == l || nums2[j] >= nums2[dp[l][i][j + 1].idx]) {
////                                dp[l][i][j] = new P(2, j);
////                            } else {
////                                dp[l][i][j] = dp[l][i][j + 1];
////                            }
////                        }
////                    } else {
////                        if (n1 == l || nums1[i] >= nums1[dp[l][i + 1][j].idx]) {
////                            dp[l][i][j] = new P(1, i);
////                        } else {
////                            dp[l][i][j] = dp[l][i + 1][j];
////                        }
////                    }
//                }
//            }
//        }
//
//        for (int l = 0, i = 0, j = 0; l < k; l++) {
//            P p = dp[k - l][i][j];
//            if (p.num == 1) {
//                res[l] = nums1[p.idx];
//                i = p.idx + 1;
//            } else {
//                res[l] = nums2[p.idx];
//                j = p.idx + 1;
//            }
//        }
//
//        return res;
//    }
//
//    private void populate(int l, int i, int j) {
//        P maxP = j < n ? new P(2, j) : null;
//        P p2 = i < m ? new P(1, i) : null;
//        P p3 = getP(l, i + 1, j + 1);
//
//        maxP = compare(maxP, p2);
//        maxP = compare(maxP, p3);
//
//
//        if (j < n) {
//            P notPicked = getP(l, i, j + 1);
//            if (notPicked != null) {
//                int a = nums2[j];
//                P p = new P(2, j);
//                maxP = p;
//                int b = notPicked.num == 1 ? nums1[notPicked.idx] : nums2[notPicked.idx];
//                if (a < b || (a == b && compare(l, i, j, i, j + 1) == 1)) {
//                    maxP = dp[l][i][j + 1];
//                }
//            }
//        }
//
//        if (i < m) {
//            P notPicked = getP(l, i, j + 1);
//            int a = nums1[i];
//            P p = new P(1, i);
//            dp[l][i][j] = p;
//            int b = notPicked.num == 1 ? nums1[notPicked.idx] : nums2[notPicked.idx];
//            if (a < b || (a == b && compare(l, i, j, i + 1, j) == 1)) {
//                dp[l][i][j] = dp[l][i + 1][j];
//            }
//        }
//    }
//
//    private P compare(P p1, P p2) {
//        if (p1 == null && p2 == null) {
//            return null;
//        }
//        if (p1 == null || p2 == null) {
//            return p1 == null ? p2 : p1;
//        }
//
//        int v1 = getValue(p1);
//        int v2 = getValue(p2);
//        if (v1 > v2) {
//            return p1;
//        }
//        if (v1 < v2) {
//            return p2;
//        }
//
//
//    }
//
//    private int compare(int l, int i1, int j1, int i2, int j2) {
//        if (l == 0) {
//            return 1;
//        }
//
//        P p1 = getP(l, i1, j1);
//        P p2 = getP(l, i2, j2);
//        int v1 = getValue(p1);
//        int v2 = getValue(p2);
//        if (v1 > v2) {
//            return 1;
//        } else if (v1 < v2) {
//            return 2;
//        }
//
//        if (p1.num == 1) {
//            i1 = p1.idx + 1;
//        } else {
//            j1 = p1.idx + 1;
//        }
//
//        if (p2.num == 1) {
//            i2 = p2.idx + 1;
//        } else {
//            j2 = p2.idx + 1;
//        }
//
//        return compare(l - 1, i1, j1, i2, j2);
//    }
//
//    private int getValue(P p) {
//        return p.num == 1 ? nums1[p.idx] : nums2[p.idx];
//    }
//
//    private P getP(int l, int i, int j) {
//        if (l <= 0 || l > k || i < 0 || i > m || j < 0 || j > n) {
//            return null;
//        }
//
//        return dp[l][i][j];
//    }
//
//    public static void main(String[] args) {
//        CreateMaxNumber321 createMaxNumber321 = new CreateMaxNumber321();
//        int[] nums1 = {3, 4, 6, 5};
//        int[] nums2 = {9, 1, 2, 5, 8, 3};
//        int k = 5;
////        int[] nums1 = {6, 7};
////        int[] nums2 = {6, 0, 4};
////        int k = 5;
////        int[] nums1 = {6, 9};
////        int[] nums2 = {6, 8};
////        int k = 4;
////        int[] nums1 = {3, 4, 6, 5};
////        int[] nums2 = {9, 1, 2, 5, 8, 3};
////        int k = 3;
//        int[] res = createMaxNumber321.maxNumber(nums1, nums2, k);
//        Arrays.stream(res).forEach(System.out::println);
//    }
}
