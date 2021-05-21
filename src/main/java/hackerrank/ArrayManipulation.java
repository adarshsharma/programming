package hackerrank;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by adarsh.sharma on 31/07/18.
 */
public class ArrayManipulation {
    static class P implements Comparable<P> {
        boolean start;
        int idx;
        int val;

        public P(boolean start, int idx, int val) {
            this.start = start;
            this.idx = idx;
            this.val = val;
        }

        @Override
        public int compareTo(P other) {
            if(this.idx == other.idx) {
                if(this.start == other.start) {
                    return 0;
                } else {
                    return this.start?-1:1;
                }
            }

            return this.idx - other.idx;
        }
    }

    // Complete the arrayManipulation function below.
    static long arrayManipulation(int n, int[][] queries) {
        List<P> lst = new ArrayList<>();

        for(int i=0;i<queries.length;i++) {
            lst.add(new P(true, queries[i][0]-1, queries[i][2]));
            lst.add(new P(false, queries[i][1]-1, queries[i][2]));
        }

        Collections.sort(lst);

        long max = Long.MIN_VALUE;
        long val = 0;
        for(P p: lst) {
            if(p.start) {
                val+=p.val;
                max = Math.max(max, val);
            } else {
                val-=p.val;
            }
        }
        return max;
    }
//    public static long arrayManipulation(int n, int[][] queries) {
//        long[] segArray = new long[2*n];
//        for(int i=0;i<queries.length;i++) {
//            manipulate(segArray, queries[i][0]-1, queries[i][1], queries[i][2], n);
//        }
//
//        for(int i=1;i<n;i++) {
//            segArray[2*i]+=segArray[i];
//            segArray[2*i+1]+=segArray[i];
//        }
//
//        long max = Long.MIN_VALUE;
//        for(int i=n;i<2*n;i++) {
//            max = Math.max(max, segArray[i]);
//        }
//
//        return max;
//    }
//
//    //returns minimum element in range [left, right)
//    private static void manipulate(long[] segArray, int left, int right, int val, int n) {
//        left = left + n;
//        right = right + n;
//
//        while (left < right) {
//            if (left % 2 == 1) {
//                segArray[left]+=val;
//                left++;
//            }
//            if (right % 2 == 1) {
//                right--;
//                segArray[right]+=val;
//            }
//            left = left / 2;
//            right = right / 2;
//        }
//    }

    public static void main(String[] args) {
        ArrayManipulation arrayManipulation = new ArrayManipulation();
        int[][] queries = {{1, 2, 100}, {2, 5, 100}, {3, 4, 100}};
        System.out.println(arrayManipulation.arrayManipulation(5, queries));
    }
}
