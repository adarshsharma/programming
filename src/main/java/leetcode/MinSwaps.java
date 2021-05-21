package leetcode;

/**
 * Created by adarsh.sharma on 21/08/18.
 */
public class MinSwaps {
    public int minSwap(int[] A, int[] B) {
        if(A.length==0) {
            return 0;
        }

        int swapped = A[0]==B[0]?0:1;
        int notswapped = 0;

        for(int i=1;i<A.length;i++) {
            if(A[i] != B[i]) {
                int ns = Integer.MAX_VALUE;
                int s = Integer.MAX_VALUE;
                if(A[i] > A[i-1] && B[i] > B[i-1]) {
                    ns = notswapped;
                    s = swapped + 1;
                }
                if(A[i] > B[i-1] && B[i] > A[i-1]) {
                    ns = Math.min(ns, swapped);
                    s = Math.min(s, notswapped + 1);
                }

                swapped = s;
                notswapped = ns;
            } else {
                int min = Math.min(swapped, notswapped);
                swapped = min;
                notswapped = min;
            }
        }

        return Math.min(swapped, notswapped);
    }

    public static void main(String[] args) {
        int[] A = {0,3,4,9,10};
        int[] B = {2,3,7,5,6};
        System.out.println(new MinSwaps().minSwap(A, B));
    }
}
