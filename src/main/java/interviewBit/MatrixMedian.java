package interviewBit;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by adarsh.sharma on 12/08/18.
 */
public class MatrixMedian {
//    public int findMedian(ArrayList<ArrayList<Integer>> A) {
//        int m = A.size();
//        int n = A.get(0).size();
//
//        int low = Integer.MAX_VALUE;
//        int high = Integer.MIN_VALUE;
//
//        for (int i = 0; i < m; i++) {
//            low = Math.min(low, A.get(i).get(0));
//            high = Math.max(high, A.get(i).get(n - 1));
//        }
//
//        if (low == high) {
//            return low;
//        }
//
//        while (low <= high) {
//            int mid = (low + high) / 2;
//            int count = getNumbersLower(A, mid, m, n);
//            if (count == m * n / 2) {
//                return minAbove(A, mid, m, n);
//            } else
//            if (count < m * n / 2) {
//                low = mid + 1;
//            } else {
//                high = mid - 1;
//            }
//        }
//        return high;
//    }

//    private int minAbove(ArrayList<ArrayList<Integer>> A, int val, int m, int n) {
//        int min = Integer.MAX_VALUE;
//        for (int i = 0; i < m; i++) {
//            ArrayList<Integer> arr = A.get(i);
//            int low = 0;
//            int high = n - 1;
//            int mid = 0;
//            while (low <= high) {
//                mid = (low + high) / 2;
////                if (arr.get(mid) == val) {
////                    break;
////                } else
//                if (arr.get(mid) < val) {
//                    low = mid + 1;
//                } else {
//                    high = mid - 1;
//                }
//            }
//            if (low < n) {
//                min = Math.min(min, arr.get(low));
//            }
//        }
//
//        return min;
//    }

//    private int getNumbersLower(ArrayList<ArrayList<Integer>> A, int val, int m, int n) {
//        int count = 0;
//        for (int i = 0; i < m; i++) {
//            ArrayList<Integer> arr = A.get(i);
//            int low = 0;
//            int high = n - 1;
//            int mid = 0;
//            while (low <= high) {
//                mid = (low + high) / 2;
////                if (arr.get(mid) == val) {
////                    break;
////                } else
//                if (arr.get(mid) < val) {
//                    low = mid + 1;
//                } else {
//                    high = mid - 1;
//                }
//            }
//            count += low;
//        }
//
//        return count;
//    }

    public int findMedian(ArrayList<ArrayList<Integer>> A) {
        int n = A.size();
        if(n == 0) return -1;
        int m = A.get(0).size();
        int lo = 0;
        int hi = Integer.MAX_VALUE;
        int x = 1;
        while(lo <= hi) {
            int mid = (lo+hi)/2;
            if(isLessThanHalf(A, mid)) {
                lo = mid+1;
            } else {
                x = mid;
                hi = mid-1;
            }
        }
        return x;
    }
    boolean isLessThanHalf(ArrayList<ArrayList<Integer>> a, int num) {
        int N = a.size();
        int M = a.get(0).size();
        int count = 0;

        for(int i = 0; i < N; i++) {
            count += getCount(a.get(i), num);
            //   System.out.println(count);
        }
        // System.out.println();
        return count < (N*M)/2 + 1;
    }
    int getCount(ArrayList<Integer> a, int n) {
        int lo = 0;
        int hi = a.size()-1;
        while(lo <= hi) {
            int mid = (lo+hi)/2;
            if(a.get(mid) > n) hi = mid-1;
            else lo = mid+1;
        }
        return lo;
    }

    public static void main(String[] args) {
        MatrixMedian matrixMedian = new MatrixMedian();
        ArrayList<ArrayList<Integer>> A = new ArrayList<>();
        ArrayList<Integer> a = new ArrayList<>(Arrays.asList(1, 2, 2, 2, 2, 2, 3, 3, 3, 3, 3));
//        ArrayList<Integer> a = new ArrayList<>(Arrays.asList(1, 2, 2, 3, 3, 3, 3, 3, 3, 3, 3));
//        ArrayList<Integer> b = new ArrayList<>(Arrays.asList(2, 6, 9));
//        ArrayList<Integer> c = new ArrayList<>(Arrays.asList(3, 6, 17));
        A.add(a);
//        A.add(b);
//        A.add(c);
        System.out.println(matrixMedian.findMedian(A));
    }
}
