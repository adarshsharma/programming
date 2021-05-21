package interviewBit;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by adarsh.sharma on 20/07/18.
 */
//Without Duplicates
public class Permutations {
    public ArrayList<ArrayList<Integer>> permute(ArrayList<Integer> A) {
        ArrayList<ArrayList<Integer>> res = new ArrayList<>();
        if (A == null) {
            return null;
        } else if (A.size() == 0) {
            return res;
        } else if (A.size() == 1) {
            res.add(A);
            return res;
        }

        permute(0, A, res);

        return res;
    }

    private void permute(int start, ArrayList<Integer> A,
                         ArrayList<ArrayList<Integer>> res) {
        if (start == A.size() - 1) {
            ArrayList<Integer> p = new ArrayList<>();
            p.addAll(A);
            res.add(p);
        }

        for (int i = start; i < A.size(); i++) {
            swap(i, start, A);
            permute(start + 1, A, res);
            swap(i, start, A);
        }
    }

    private void swap(int i, int j, ArrayList<Integer> A) {
        if (i != j) {
            int temp = A.get(j);
            A.set(j, A.get(i));
            A.set(i, temp);
        }
    }

    public static void main(String[] args) {
        ArrayList<Integer> A = new ArrayList<>();
        A.addAll(Arrays.asList(1, 2, 3));
        System.out.println(new Permutations().permute(A));
    }
}
