package practice.algo.dp;

import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.Collectors;

/**
 * Created by adarsh.sharma on 10/07/18.
 */
public class LIS {

//    private static int lisLength(List<Integer> input) {
//        return lis(input).size();
//    }
//
//    static int ceilIndex(List<Integer> A, List<Integer> T, int l, int r, int key) {
//        while (r - l > 1) {
//            int m = l + (r - l) / 2;
//            if (A.get(T.get(m)) >= key)
//                r = m;
//            else
//                l = m;
//        }
//
//        return r;
//    }
//
//    static List<Integer> lis(List<Integer> A) {
//        // Add boundary case, when array size is one
//
//        List<Integer> tailTable = new ArrayList<>();
//        List<Integer>  c = new ArrayList<>();
//
//        tailTable.add(0);
//        c.add(-1);
//        for (int i = 1; i < A.size(); i++) {
//            if (A.get(i) < A.get(tailTable.get(0))) {
//                // new biggest value
//                tailTable.set(0, i);
//                c.add(-1);
//            } else if (A.get(i) > A.get(tailTable.get(tailTable.size() - 1))) {
//                // A[i] wants to extend largest subsequence
//                c.add(tailTable.get(tailTable.size() - 1));
//                tailTable.add(i);
//            } else {
//                // A[i] wants to be current end candidate of an existing
//                // subsequence. It will replace ceil value in tailTable
//                int k = ceilIndex(A, tailTable, -1, tailTable.size(), A.get(i));
//                tailTable.set(k, i);
////                c.add(c.get(tailTable.get(k-1)));
//            }
//        }
//
//        List<Integer> lis = new ArrayList<>();
//        if(tailTable.size() > 0) {
//            for (Integer index: c.subList(1, c.size())) {
//                lis.add(A.get(index));
//            }
//            lis.add(A.get(tailTable.get(tailTable.size() - 1)));
//        }
//        return lis;
//    }
//
//    public static void main(String[] args) {
//        List<Integer> input = Arrays.asList(0, 8, 4, 12, 2, 10, 6, 14, 1, 9, 5, 13, 3, 11, 7, 15);  //0, 4, 6, 9, 11, 15
////        List<Integer> input = Arrays.asList(1, 6, 2, 2, 4);
//        System.out.println(lis(input));
//        System.out.println(lisLength(input));
//    }

//    public static int lis(Integer[] A) {
//        Integer[] dp = new Integer[A.length];
//        int len = 0;
//        for (int i=0;i<A.length;i++) {
//            int index = Arrays.binarySearch(dp, 0, len, i, Comparator.comparing(o -> A[o]));
//            if (index < 0) {
//                index = -(index + 1);
//                dp[index] = i;
//            }
//            if (index == len) {
//                len++;
//            }
//        }
//        return len;
//    }

    public static Integer[] lis(Integer[] A) {
        Integer[] dp = new Integer[A.length];
        int[] bkp = new int[A.length];
        int len = 0;
        for (int i=0;i<A.length;i++) {
            int index = Arrays.binarySearch(dp, 0, len, i, Comparator.comparing(o -> A[o]));
            if (index < 0) {
                index = -(index + 1);
                dp[index] = i;
            }
            if (index == len) {
                len++;
            }
            if(index==0) {
                bkp[i] = -1;
            } else {
                bkp[i] = dp[index-1];
            }
        }

        Integer[] res = new Integer[len];
        int end = dp[len-1];
        while(end != -1) {
            res[len-1] = A[end];
            len--;
            end = bkp[end];
        }

        return res;
    }

    public static void main(String[] args) {
                         //0, 1, 2, 3, 4, 5,  6, 7, , 8, 9, 10, 11, 11, 13, 14,15
        Integer[] input = {0, 8, 4, 12, 2, 10, 6, 14, 1, 9, 5, 13,  3,  11, 7, 15};  //0, 4, 6, 9, 11, 15
//        Integer[] input = {1, 6, 2, 2, 4};
        System.out.println((Arrays.stream(lis(input))
                .map(String::valueOf)
                .collect(Collectors.joining(" "))));
    }
}
