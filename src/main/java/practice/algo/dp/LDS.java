package practice.algo.dp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by adarsh.sharma on 04/08/18.
 */
public class LDS {

    static int ceilIndex(List<Integer> A, List<Integer> T, int l, int r, int key) {
        while (r - l > 1) {
            int m = l + (r - l) / 2;
            if (A.get(T.get(m)) < key)
                r = m;
            else
                l = m;
        }

        return r;
    }

    static int lds(List<Integer> A) {
        // Add boundary case, when array size is one

        List<Integer> tailTable = new ArrayList<>();
        List<Integer> c = new ArrayList<>();

        tailTable.add(0);
        c.add(-1);
        for (int i = 1; i < A.size(); i++) {
            if (A.get(i) > A.get(tailTable.get(0))) {
                // new smallest value
                tailTable.set(0, i);
                c.add(-1);
            } else if (A.get(i) < A.get(tailTable.get(tailTable.size() - 1))) {
                // A[i] wants to extend largest subsequence
                c.add(tailTable.get(tailTable.size() - 1));
                tailTable.add(i);
            } else {
                // A[i] wants to be current end candidate of an existing
                // subsequence. It will replace ceil value in tailTable
                int k = ceilIndex(A, tailTable, -1, tailTable.size(), A.get(i));
                tailTable.set(k, i);
//                c.add(c.get(tailTable.get(k-1)));
            }
        }

        int[] lds = new int[tailTable.size()];
        if (tailTable.size() > 0) {
            int idx = tailTable.get(tailTable.size() - 1);
            int i = tailTable.size() - 1;
            while (idx > 0) {
                lds[i--] = A.get(idx);
                idx = c.get(idx);
            }
        }
        System.out.println(lds);
        return tailTable.size();
    }

    public static void main(String[] args) {
//        List<Integer> input = Arrays.asList(0, 8, 4, 12, 2, 10, 6, 14, 1, 9, 5, 13, 3, 11, 7, 15);
        List<Integer> input = Arrays.asList(4, 15, 11, 10, 9, 8, 20, 7);
        System.out.println(lds(input));
//        System.out.println(lisLength(input));
    }
}
